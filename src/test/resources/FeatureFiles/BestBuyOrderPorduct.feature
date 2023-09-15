#Author: your.email@your.domain.com
Feature: User orders productt on BestBuy website
  This feature is used to test the order product scenario for Best Buy Application

  Background: 
    Given Launch the Best Buy application

  Scenario Outline: Add products into cart
    Given Search for product "<productName>" on home page and click on Search icon
    Then Validate the serach results for the "<productName>"
    When Find the product with "<description>" "<model>" and "<price>"
    And Click on the Add to cart
    Then Validate the Added to cart popup for "<description>"
    When Close the added to cart popup
    And Click on Cart icon
    Then Validate the Order Summary Page
		And Validate product "<description>" and "<price>" on Summary Page
    Examples: 
      | productName | description                                                    | model     | price   |
      | iPhone 13   | Apple - Pre-Owned iPhone 13 Pro 5G 128GB (Unlocked) - Graphite | A2483-GRY | $879.99 |
      | iPhone 12   | Apple - Pre-Owned iPhone 12 5G 64GB (Unlocked) - Black         | A2172     | $499.99 |
