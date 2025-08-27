Feature: Date Selection in JQuery Datepicker
  As a user
  I want to select a date from the JQuery Datepicker
  So that I can verify the date selection functionality

  Background:
    Given I open the JQuery Datepicker page
    When I switch to the iframe containing the calendar
    And I click on the date input field

  Scenario Outline: Select current month's 15th day from Datepicker
    And I select the "<day>" day of the current month
    Then the selected date should be displayed in the input field "<expectDate>"
    
    Examples:
      | caseId | expectDate | day |
      ##@externaldata@./src/test/resources/data/date.xlsx@date@caseId=1
|1|08/15/2025|15|

  Scenario Outline: Select the month closest to the current month and the 10th of the current month in the date picker.
    And I select next month
    And I select the "<day>" day of the current month
    Then the selected date should be displayed in the input field "<expectDate>"
    
    Examples:
      | caseId | expectDate | day |
      ##@externaldata@./src/test/resources/data/date.xlsx@date@caseId=2
|2|09/10/2025|10|

  Scenario Outline: enter a date
    And I enter the date "<expectDate>"
    Then Validate that manual editing is not allowed and that only one date can be selected from the calendar

    Examples:
      | caseId | expectDate | day |
      ##@externaldata@./src/test/resources/data/date.xlsx@date@caseId=2
|2|09/10/2025|10|
