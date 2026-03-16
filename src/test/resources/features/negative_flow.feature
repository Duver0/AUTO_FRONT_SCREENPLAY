Feature: New item form validation
  As a registered user
  I want to receive clear feedback when I submit incomplete data
  So that I can correct my input before the record is saved

  Scenario: User receives a required field error when submitting an empty title
    Given a user is on the new item creation page
    When the user submits the form without providing a title
    Then a required field validation error should be displayed
