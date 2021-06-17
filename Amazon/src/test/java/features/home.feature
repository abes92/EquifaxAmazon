Feature: end to end testing
Background:

Scenario: search for an item
  Given  user on amazon home page
   When  user click on all to select books section
   And   user enter your book name on search box option
   Then  user validate your book is displaying properly
   When  user click on add to cart
   And   user click on cart option
   Then  user proceed to check out

