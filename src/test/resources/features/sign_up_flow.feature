Feature: Sign up flow
  As a visitor
  I want to validate the sign up process
  So that I can create an account with secure credentials

  @positive_signup
  Scenario: Registering with valid information shows success message
    Given the user opens the application in the home page
    When the user clicks the sign in button
    And the user clicks the register link
    And the user selects the user type empleado
    And the user enters sign up data with name "Juan", email "juan<DATE_DIGIT_SUM>@gmail.com" and password "Duver123--"
    And the user submits the sign up form
    Then the user should see the feedback message "¡Cuenta creada exitosamente! Inicia sesión para continuar."

  @negative_signup
  Scenario: Registering with a weak password shows validation message
    Given the user opens the application in the home page
    When the user clicks the sign in button
    And the user clicks the register link
    And the user selects the user type empleado
    And the user enters sign up data with name "Juan", email "juan@gmail.com" and password "12345678"
    And the user submits the sign up form
    Then the user should see the feedback message "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial."