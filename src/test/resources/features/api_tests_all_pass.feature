Feature: Restful API Tests - All Pass

  Scenario: Get all objects
    Given I send a GET request to "/objects"
    Then the response status code should be 200
    And the response should contain all objects

  Scenario: Get all objects by ids
    Given I send a GET request to "/objects?id=3&id=5&id=10"
    Then the response status code should be 200
    And the response should contain all objects

  Scenario: Get a single object by id
    Given I send a GET request to "/objects/1"
    Then the response status code should be 200
    And the response should contain the object with id "1"

  Scenario: Create a new object
    Given I send a POST request to "/objects" with the following data
      """
      {
        "name": "Apple MacBook Pro 16",
        "data": {
          "year": 2019,
          "price": 1849.99,
          "CPU model": "Intel Core i9",
          "Hard disk size": "1 TB"
        }
      }
      """
    Then the response status code should be 200
    And the response should contain the created object

  Scenario: Update an object
    Given I send a PUT request to "/objects/{{savedID}}" with the following data
      """
      {
        "name": "Apple MacBook Pro 16",
        "data": {
          "year": 2020,
          "price": 1999.99,
          "CPU model": "Intel Core i9",
          "Hard disk size": "1 TB"
        }
      }
      """
    Then the response status code should be 200
    And the response should contain the updated object

  Scenario: Partial Update an object
    Given I send a PATCH request to "/objects/{{savedID}}" with the following data
      """
      {"name": "Apple MacBook Pro 16 (Updated Name)"}
      """
    Then the response status code should be 200
    And the response should contain the updated object

  Scenario: Delete an object
    Given I send a DELETE request to "/objects/{{savedID}}"
    Then the response status code should be 200