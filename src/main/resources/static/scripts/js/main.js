/**
 * Author: Isienyi, Amachi Stephen
 * Date: September 12, 2017 - 3:49 PM EST
 * Desc: jQuery based app events handler
 */
var appEngine = function( $ ){
	var url = location.protocol + '//' + location.hostname + ( location.port ? ':' + location.port: '' ), 
		xhr = null,
		$outputDiv = $( "#output" ),
		$canvas = $outputDiv.children().first(),
		canvasHeight = $canvas.height(),
		canvasWidth = $canvas.width(),
		$shapeDiv = $canvas.children().first(),
		$shapeDivBisect = $shapeDiv.next(),
		shapeDivHeightToBisectRatio = $shapeDivBisect.height() / $shapeDiv.height(),
		$showSizeQueryButton = $( "button", $outputDiv ),
		$dimensionsDiv = $outputDiv.next(),
		$select = $( "select" ),
		$shapeTypeRadiosContainer = $( "[name=shapetype]" ).parent(),
		$areaInput = $( '#area', $outputDiv ),
        $perimeterInput = $( '#perimeter', $outputDiv ),
        $volumeInput = $( '#volume', $outputDiv ),
        persistedState = {
			active: {
				shape: $( ":checked", $shapeTypeRadiosContainer ).val(),
				dim: { 	//dimensions of previously used shapes
					box: {},
					sphere: {},
					pentagon: {}
				}
			},
			scaleFactor:  parseInt( $( "option:selected", $select ).val() )
		},
		getChildDimPct = function( parentDim, childDim ){
			return (( childDim >= parentDim ? parentDim : childDim ) / parentDim ) * 100;
		},
		startingPos = function( elDimPct, containerDimPct  ){
			containerDimPct = containerDimPct || 100;
			return ( containerDimPct - elDimPct ) / 2;
		},
		formatNum = function( num, decimalPlaces ){
			decimalPlaces = decimalPlaces || 2;
			return parseFloat( num.toFixed( decimalPlaces )).toLocaleString();
		},
		updateSizeQuery = function( queryHtmlString ){
			if( !queryHtmlString ){
				var activeShape = persistedState.active.shape;
				queryHtmlString = sizeQueriesHtml[ activeShape ].call( null, persistedState.active.dim[ activeShape ]);
			}
			var $queryDiv = $( "div:first", $dimensionsDiv );
			$queryDiv.find( "> div:not(:last-child)" ).remove();
			$queryDiv.prepend( queryHtmlString );
		},
		sizeQueriesHtml = {
			box: function( dimensions ){
				return "<div><div>Bisect Length:</div><div><input name='length' type='number' value='" + dimensions.length + "'/></div></div><div><div>Width:</div><div><input name='width' value='" + dimensions.width + "' type='number'/></div></div><div><div>Height:</div><div><input name='height' value='" + dimensions.height + "' type='number'/></div></div>";
			},
			sphere: function( dimensions ){
				return "<div><div>Bisect Length:</div><div><input name='length' type='number' value='" + dimensions.length + "'/></div></div><div><div>Radius:</div><div><input name='radius' value='" + dimensions.radius + "' type='number'/></div></div>";
			},
			pentagon: function( dimensions ){},
			empty: {
				box: "<div><div>Bisect Length:</div><div><input name='length' type='number'/></div></div><div><div>Width:</div><div><input name='width' type='number'/></div></div><div><div>Height:</div><div><input name='height' type='number'/></div></div>",
				sphere: "<div><div>Bisect Length:</div><div><input name='length' type='number'/></div></div><div><div>Radius:</div><div><input name='radius' type='number'/></div></div>",
				pentagon: ""
			}
		},
		updateActiveShapeState = function( dimensions ){
			persistedState.active.shape = $( ":checked", $shapeTypeRadiosContainer ).val();
			var activeShape = persistedState.active.shape;
			persistedState.active.dim[ activeShape ] = dimensions;
		}, 
		setMetrics = function( dimensions ){	
			dimensions = dimensions || persistedState.active.dim[ persistedState.active.shape ];
			$areaInput.val( formatNum( dimensions.area ));
			$perimeterInput.val( formatNum( dimensions.perimeter ));
			$volumeInput.val( formatNum( dimensions.volume ));
		},
		adjustShapeDivBisect = function( bisectHeightPct, bisectWidthPct ){
			$shapeDivBisect.css({
				height: bisectHeightPct + "%",
				left: startingPos( bisectWidthPct ) + "%",
				top: startingPos( bisectHeightPct, 80 ) + "%",
				width: bisectWidthPct + "%",
			});
		},
		redrawShape = function( shapeDivClassName, shown ){
			shapeDivClassName = shapeDivClassName || 'box';
			shown = shown || { height: 0, length: 0, width: 0 };
			$shapeDiv.removeClass().addClass( shapeDivClassName ).css({ 
				height: getChildDimPct( canvasHeight, shown.height ) + "%",
				width: getChildDimPct( canvasWidth, shown.width ) + "%"
			});
			adjustShapeDivBisect(((( shapeDivHeightToBisectRatio * shown.height ) / canvasHeight ) * 100 ), 
					  			  getChildDimPct( canvasWidth, shown.length ));
		},
		shapeArtist = {
			draw: {
				box: function( dimensions ){
					redrawShape( 'box', {
							height: dimensions.height > canvasHeight ? canvasHeight : dimensions.height,
							length: dimensions.length > canvasWidth ? canvasWidth : dimensions.length,
							width: dimensions.width > canvasWidth ? canvasWidth : dimensions.width
						});
				},
				sphere: function( dimensions ){
					var _width = dimensions.radius * 2;
					_width = _width > canvasWidth ? canvasWidth : _width;
					_width = _width > canvasHeight ? canvasHeight : _width;
					redrawShape( 'sphere', {
							height: _width, 
							width: _width,
							length: dimensions.length > canvasWidth ? canvasWidth : dimensions.length
						});
				},
				pentagon: function( dimensions ){
					redrawShape( 'pentagon', dimensions );
				}
			}
		},
		buildApiRequest = function(){
			var _scaleFactor = parseInt( $( "option:selected", $select ).val() ),
				_multiplier = _scaleFactor - persistedState.scaleFactor;
			persistedState.scaleFactor =  _scaleFactor; 
			return url + '/rest'
					   + '/' + $( ":checked", $shapeTypeRadiosContainer ).val() 
					   + '/' + ( $( "input", $dimensionsDiv ).map( 
							 					function( i, input ){
													return input.value
												})
											.get()
											.join( "/" ))
					   + '/' + ( _multiplier < 0 ? _multiplier - 1 : _multiplier + 1 );
		},
		updateShapeRequest = function(){
			var shapeType = $( ":checked", $shapeTypeRadiosContainer ).val();
			xhr = $.ajax({	
				url: buildApiRequest()
			}).then( function( data ){
				updateActiveShapeState( data );
				updateSizeQuery();
				shapeArtist.draw[ shapeType ].call( null, data );
				setMetrics();
				$( '.loading', $shapeDiv ).css( "display", "none" );
				$( ".closeBtn", $dimensionsDiv ).trigger( "click" )
			});
		};
	
	$( function(){
		var h = $shapeDiv.height(),
			l = $shapeDivBisect.width(),
			w = $shapeDiv.width(),
			a = l * w,
			p = 2 * ( l + w );
			v = h * l * w;
		updateActiveShapeState({ area: a, height: h, length: l, perimeter: p, volume: v, width: w });
		updateSizeQuery();
		setMetrics();
	});
	$showSizeQueryButton.on( "click", function( e ){
		$outputDiv.hide( "fast", "swing" );
		$dimensionsDiv.show( "fast", "swing" );
	});
	$( "input", $shapeTypeRadiosContainer ).on( "change", function( e ){
		var $this = $( this ),
			shape = $this.val();
		if( $this.is( ":checked" )){
			persistedState.active.shape = shape;
			if( $.isEmptyObject( persistedState.active.dim[ shape ])){
				updateSizeQuery( sizeQueriesHtml.empty[ $this.val() ]);
				$showSizeQueryButton.trigger( "click" );
				return;
			}
			var dimensions = persistedState.active.dim[ shape ];
			updateSizeQuery( sizeQueriesHtml[ shape ].call( null, dimensions ));
			shapeArtist.draw[ shape ].call( null, dimensions );
			setMetrics( dimensions );
		}
	});
	$select.on( "change", function( e ){
		xhr && xhr.abort();
		xhr = updateShapeRequest();
		$( '.loading', $shapeDiv ).css( "display", "block" );
	});
	$( ".closeBtn", $dimensionsDiv ).on( "click", function( e ){
		$dimensionsDiv.hide( "fast", "swing" );
		$outputDiv.show( "fast", "swing" );
		var activeShape = persistedState.active.shape;
		if( $( ':checked', $shapeTypeRadiosContainer ).val() !== activeShape ){
			$( ':checked', $shapeTypeRadiosContainer ).prop( 'checked', false );
			$( '[value=' + activeShape + ']', $shapeTypeRadiosContainer ).prop( 'checked', true );
			updateSizeQuery();
		}
	});
	$( ".redrawBtn", $dimensionsDiv ).on( "click", function( e ){
		$select.trigger( "change" );
	});
}