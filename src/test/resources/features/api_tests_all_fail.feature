Feature: Restful API Tests - All Fail

  Scenario: Get a single object by id - fail
    Given I send a GET request to "/objects/1898988989"
    Then the response status code should be 200
    And the response should contain the object with id "1"

  Scenario: Create a new object
    Given I send a POST request to "/objects" with the following data
      """

      """
    Then the response status code should be 200
    And the response should contain the created object

  Scenario: Update an object - fail
    Given I send a PUT request to "/objects/{{savedID}}" with the following data
      """

      """
    Then the response status code should be 200
    And the response should contain the updated object

  Scenario: Partial Update an object - fail
    Given I send a PATCH request to "/objects/{{savedID}}" with the following data
      """

      """
    Then the response status code should be 200
    And the response should contain the updated object

  Scenario: Delete an object - fail
    Given I send a DELETE request to "/objects/1898988989"
    Then the response status code should be 200