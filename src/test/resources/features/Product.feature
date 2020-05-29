#Author: anhdtv@dkt.com.vn
Feature: Product

@product
  Scenario: Post product
    When I post some product
      | name             | vendor | product_type | quantity | inventory_policy |
      | Áo lông          | H2T    | Áo           |        4 | denny            |
      | Quần bò thêu hoa | H3T    | Quần         |        2 | bizweb           |

#@products
  #Scenario Outline: Post product
    #When I post product with <name> and <vendor> and < product_type>
#
    #Examples: 
      #| name             | vendor | product_type |
      #| Áo lông          | H2T    | Áo           |
      #| Quần bò thêu hoa | H3T    | Quần         |
