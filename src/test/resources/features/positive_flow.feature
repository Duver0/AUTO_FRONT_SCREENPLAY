Feature: Positive sign up flow
  As a visitor
  I want to create a new account with valid data
  So that I can continue to sign in

  @positive_signup
  Scenario: Registering with valid information shows success message
    Given the user opens the application in the home page
    When the user clicks the sign in button
    And the user clicks the register link
    And the user enters sign up data with name "Juan", email "juan-<RANDOM_LONG>@gmail.com" and password "Duver123--"
    And the user submits the sign up form
    Then the user should see the feedback message "¡Cuenta creada exitosamente! Inicia sesión para continuar."
