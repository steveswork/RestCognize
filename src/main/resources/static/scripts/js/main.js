/**
 * Author: Isienyi, Amachi Stephen
 * Date: September 12, 2017 - 3:49 PM EST
 * Desc: jQuery based app events handler
 */
var appEngine = function( $ ){
	var url = location.protocol + '//' + location.hostname + ( location.port ? ':' + location.port: '' ), 
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
		scaleFactor = parseInt( $( "option:selected", $select ).val() ),
		$areaInput = $( '#area', $outputDiv ),
        $perimeterInput = $( '#perimeter', $outputDiv ),
        $volumeInput = $( '#volume', $outputDiv ),
		getChildDimPct = function( parentDim, childDim ){
			return ( childDim >= parentDim ? parentDim * 0.75 : childDim / parentDim ) * 100;
		},
		startingPos = function( elDimPct, containerDimPct  ){
			containerDimPct = containerDimPct || 100;
			return ( containerDimPct - elDimPct ) / 2;
		},
		updateSizeQuery = function( queryHtmlString ){
			var $queryDiv = $( "div:first", $dimensionsDiv );
			$queryDiv.find( "> div:not(:last-child)" ).remove();
			$queryDiv.prepend( queryHtmlString );
		},
		sizeQueriesHtml = {
			box: function( dimensions ){
				return "<div><div>Bisect Length:</div><div><input name='length' type='number' value='" + dimensions.length + "'/></div></div><div><div>Width:</div><div><input name='width' value='" + dimensions.width + "' type='number'/></div></div><div><div>Height:</div><div><input name='height' value='" + dimensions.height + "' type='number'/></div></div>";
			},
			circle: function( dimensions ){},
			pentagon: function( dimensions ){},
			empty: {
				box: "<div><div>Bisect Length:</div><div><input name='length' type='number'/></div></div><div><div>Width:</div><div><input name='width' type='number'/></div></div><div><div>Height:</div><div><input name='height' type='number'/></div></div>",
				circle: '',
				pentagon: ''
			}
		},
		setMetrics = function( area, perimeter, volume ){			
			$areaInput.val( area.toFixed() );
			$perimeterInput.val( perimeter.toFixed() );
			$volumeInput.val( volume.toFixed() );
		},
		shapeArtist = {
			draw: {
				box: function( dimensions ){
					var shown = {
							height: dimensions.height > canvasHeight ? canvasHeight : dimensions.height,
							length: dimensions.length > canvasWidth ? canvasWidth : dimensions.length,
							width: dimensions.width > canvasWidth ? canvasWidth : dimensions.width
						},
						bisectWidthPct = getChildDimPct( canvasWidth, shown.length ),
						bisectHeightPct = ((( shapeDivHeightToBisectRatio * shown.height ) / canvasHeight ) * 100 );
					$shapeDiv.css({ 
							borderRadus: "0px",
							height: getChildDimPct( canvasHeight, shown.height ) + "%",
							width: getChildDimPct( canvasWidth, shown.width ) + "%"
						});
					$shapeDivBisect.css({
							height: bisectHeightPct + "%",
							left: startingPos( bisectWidthPct ) + "%",
							top: startingPos( bisectHeightPct, 80 ) + "%",
							width: bisectWidthPct + "%",
						});
				},
				circle: function(){},
				pentagon: function(){}
			}
		},
		buildApiRequest = function(){
			var _scaleFactor = parseInt( $( "option:selected", $select ).val() ),
				_multiplier = _scaleFactor - scaleFactor;
			scaleFactor =  _scaleFactor; 
			return url + '/rest'
					   + '/' + $( ":checked", $shapeTypeRadiosContainer ).val() 
					   + '/' + ( $( "input", $dimensionsDiv ).map( 
							 					function( i, input ){
													return input.value
												})
											.get()
											.join( "/" ))
						+ '/' + ( _multiplier < 0 ? _multiplier - 1 : _multiplier + 1 );
		};
	
	$( function(){
		var height = $shapeDiv.height(),
			width = $shapeDiv.width();
		$areaInput.val(( height * width ).toFixed() );
        $perimeterInput.val(( 2 * height + width ).toFixed() );
        $volumeInput.val( 0 );
		$( "input[name=length]", $dimensionsDiv ).val( $shapeDivBisect.width().toFixed() );
		$( "input[name=height]", $dimensionsDiv ).val( $shapeDiv.height().toFixed() );
		$( "input[name=width]", $dimensionsDiv ).val( $shapeDiv.width().toFixed() );
	});
	$showSizeQueryButton.on( "click", function( e ){
		$outputDiv.hide( "fast", "swing" );
		$dimensionsDiv.show( "fast", "swing" );
	});
	$( "input", $shapeTypeRadiosContainer ).on( "change", function( e ){
		var $this = $( this );
		if( $this.is( ":checked" )){
			displaySizeQuery( sizeQueriesHtml.empty[ $this.val() ]);
		}
	});
	$select.on( "change", function( e ){
		var shapeType = $( ":checked", $shapeTypeRadiosContainer ).val();
		$.ajax({	
			url: buildApiRequest()
		}).then( function( data ){
			updateSizeQuery( sizeQueriesHtml[ shapeType.toLowerCase() ].call( null, data ));
			shapeArtist.draw[ shapeType ].call( null, data );
			setMetrics( data.area, data.perimeter, data.volume );
			$( '.loading', $shapeDiv ).css( "display", "none" );
		});
		$( ".closeBtn", $dimensionsDiv ).trigger( "click" )
		$( '.loading', $shapeDiv ).css( "display", "block" );
	});
	$( ".closeBtn", $dimensionsDiv ).on( "click", function( e ){
		$dimensionsDiv.hide( "fast", "swing" );
		$outputDiv.show( "fast", "swing" );
	});
	$( ".redrawBtn", $dimensionsDiv ).on( "click", function( e ){
		$select.trigger( "change" );
	});
}