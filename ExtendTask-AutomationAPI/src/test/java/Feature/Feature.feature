Feature: Users API

  Scenario: Retrieve all users
    Given the API BaseUrl
    When User Send a "Get" request to "/users"
    Then the response status code should be 200
    And the response body should contain a list of users

  Scenario: Create a new user
    Given the API BaseUrl
    When User Send a "Post" request to "/users" with name "Bob" and Email "Bob.Dep@gmail.com" as a "new" user
    Then the response status code should be 201
    And the response body should contain the username "Bob" and his email "Bob.Dep@gmail.com"


  Scenario Outline: Verify user email
    Given the API BaseUrl
    When User Send a GET request to "/users/<id>"
    Then the response status code should be 200
    And "<email>" of user and his appropriate "<id>" should be shown

    Examples:
      | id | email                   |
      | 1  | george.bluth@reqres.in  |
      | 2  | janet.weaver@reqres.in  |
      | 3  | emma.wong@reqres.in     |
      | 4  | eve.holt@reqres.in      |
      | 5  | charles.morris@reqres.in|
      | 6  | tracey.ramos@reqres.in  |

  Scenario: Update an existing user
    Given the API BaseUrl
    When User Send a "Put" request to "/users/id" with name "John" and Email "John.hh@gmail.com" as a "updated" user
    Then the response status code should be 200
    And the response body should contain the username "John" and his email "John.hh@gmail.com"

  Scenario: Delete an existing user
    Given the API BaseUrl
    When User Send a "Delete" request to "/users/2"
    Then the response status code should be 204
    And the response body should be empty
