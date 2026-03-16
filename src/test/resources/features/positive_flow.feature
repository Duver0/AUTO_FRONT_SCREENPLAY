Feature: Application home page content
  As a first-time visitor
  I want to access the application landing page
  So that I can confirm the application has loaded correctly

  Scenario: Visitor views the main heading on the home page
    Given a visitor accesses the application
    Then the home page heading should be visible
