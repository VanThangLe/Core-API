#Feature: Page function
#Scenario: Post a page
#When I create Page request
#
#Scenario: Put title of a page
#When I change title to 'update title'
#
#Scenario: Put content of a page
#When I change content to 'update content'
#
#Scenario: Get a single page
#When I get a single page
#Scenario: Get list pages
#When I get list pages
#
#Scenario: Delete page
#When I delete page
#Scenario: Post some pages
#When I create some Page request
#| title                | alias                | content |
#| Hướng dẫn thanh toán | huong-dan-thanh-toan |         |
#| Hướng dẫn mua hàng   | huong-dan-mua-hang   |         |
#Scenario Outline:
#When I create page request with <name> and <content>
#Then I get details
#And I delete it
#
#Examples:
#| name                 | content                             |
#| Hướng dẫn thanh toán | Thanh toán qua ATM                  |
#| Hướng dẫn mua hàng   | Theo chính sách ưu đãi của cửa hàng |
#@FunctionalTest
#Feature: Page function
#
  #@SmockTest @RegressionTest @DailyTesting
  #Scenario: Successful Login
#
  #@RegressionTest @WeeklyTesting
  #Scenario: UnSuccessful Login
#
  #@SmockTest @DailyTesting
  #Scenario: Add a product
#
  #@WeeklyTesting
  #Scenario: Add multiple product
#
  #@SmockTest @RegressionTest @DailyTesting
  #Scenario: Remove a products
#
  #@SmockTest @DailyTesting
  #Scenario: Increase inventory quantity
#
  #@WeeklyTesting
  #Scenario: Decrease inventory quantity
#
  #@SmockTest @DailyTesting
  #Scenario: Order and payment
#
  #@SmockTest @DailyTesting
  #Scenario: Capture Order
#
  #@WeeklyTesting
  #Scenario: Cancel Order
#
  #@WeeklyTesting
  #Scenario: Delete Order
