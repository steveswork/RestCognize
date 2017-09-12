# RestCognize
Spring boot rest test app

# Introduction
This is restful shape construction online application. It is based on the Domain Driven Design architecture using the Spring framework and jQuery for page event management. All inputs are processed and all output values are calculated in the server via javascript restful API calls.

# Usage
Click the 'Change Size' button to reveal the dimensions table. Changes to the shapes dimensons could be made at this table. The "close" button simply return user to the canvas without committing the changes. The "redraw" button returns user to the canvas and redraws the current shape therein using the newly entered dimension values. 


The aggregate displays are read-only for displaying the calculated the area, perimeter and volume information derived from the values contained within the dimensions table.

Changes to the "Pick a size" input redraws the currently displyed shape by scaling its dimensions according the selected size. The dimensions table and the the aggregate displays are updated to reflect the current changes.

Changes to the "Draw a" shape radio group draws the selected shape on the canvas using the currently fitting dimensions values. In any event that additional dimensions data informataion is required to complete the construction of the seleted shape, the dimensions table will display to allow the the user to provide the missing data requirement. Only the box shape is presently available.
