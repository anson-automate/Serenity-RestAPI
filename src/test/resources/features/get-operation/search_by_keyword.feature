Feature: Sample get operation test 

@sampleRest
  Scenario: Get operation validation - search for CUSAT in university list 
    Given user set below
    |universityList|universityListHeader.json|
    When user run get call on "/search" method for country parameter "India"
    Then response code should be 200
    And user should see "Cochin University of Science and Technology" in the search result

