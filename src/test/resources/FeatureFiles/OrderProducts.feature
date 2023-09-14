Feature: Order products from Best buy
 
Background:
	Given User is on the Best buy home page
	
	Scenario: Place order on the Best Buy site
		When User adds the product
		When User click on Cart button
		Then User should be on the Order Summary page