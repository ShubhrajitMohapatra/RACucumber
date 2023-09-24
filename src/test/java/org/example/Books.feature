# Do Remember few things -> This file can be created by the testers or Can be done by PO/BA/PM
# If the Customer wants to check all the features working well then I go for BDD.Ability:
# feature is basically tells about which application you are testing and what are the feature yo are testing.

  Feature: Validate all the end to end  Endpoints of SimpleBook

#@Background:
  # If there is common scenario for Given then we can put inside in Background then no need to mention multiple times.
  #Given :


@Authenticate
    Scenario:Verify the authentication to fetch accessToken.
      # Given , When ,Then , And  are method

      Given Authenticate base url along with the body
      When Execute the authenticate "/api-clients/" which provides accessToken
      Then Verify the status code is 201.
      And Verify the accessToken in the response


@CreateOrder
    Scenario:Verify the authentication to fetch accessToken.
      # Given , When ,Then , And  are method

      Given Create order by using accessKey
      When Execute the order  "/orders" which provides accessToken
      Then Verify the status code for order is 201.
      And Verify the status created as True
      And Verify the order id