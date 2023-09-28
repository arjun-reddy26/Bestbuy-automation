Feature: User search and add iPhones in the cart	

Background:
Given user Open the BestBuy website


Scenario Outline: User adds iPhone to the cart
Given user search for "<iPhoneVersion>" in the top search bar and click the search icon
When user choose Pre-Owned from conditions filter
Then user verify result is present in the page
And user find items with "<description>" "<model>" and "<price>" then click Add to Cart
And user verify that a popup appears with the text "<popupMessage>"
And user close popup dialog

 Examples:
      | iPhoneVersion | description | model | price| popupMessage | 
      | iPhone 13 | Apple - Pre-Owned iPhone 13 Pro 5G 128GB (Unlocked) - Graphite | A2483-GRY | $879.99 | Added to cart |
      | iPhone 12 | Apple - Pre-Owned iPhone 12 5G 64GB (Unlocked) - Black | A2172 | $499.99 | Added to cart |
      
      
@updateCart
Scenario Outline: User verify cart and change quantity
When user open cart
Then user verify order summary in the cart page
Then user verify "<iPhoneVersionNotInclude>" not present in the cart items
And user change quantity of item "<iPhoneVersionInclude>" to "<quantity>"
Then user close automation browser window
#And user verify total items in cart should be "<updatedQauntity>"

 Examples:
      | iPhoneVersionNotInclude | iPhoneVersionInclude | quantity | updatedQauntity |
      | iPhone 14 |  iPhone 13 | 2 | 3 |