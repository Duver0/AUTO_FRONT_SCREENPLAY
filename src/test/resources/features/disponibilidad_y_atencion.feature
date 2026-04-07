@disponibilidad_y_atencion
Feature: Disponibilidad y atencion del consultorio medico
  As a medico autenticado
  I want gestionar la disponibilidad y el cierre de atencion
  So that la operacion del consultorio respete el flujo medico real

  @positivo_disponibilidad_atencion
  Scenario: Finalizar atencion limpia la vista del paciente y deja el consultorio disponible
    Given el medico inicia sesion desde "Iniciar sesión" con credenciales validas
    And el medico se vincula con el boton "Tomar consultorio" a un consultorio disponible
    And el medico lleva el consultorio a "En atencion" usando "Iniciar atencion" con un paciente en espera
    When el medico finaliza la atencion desde "Finalizar atencion"
    Then el estado del consultorio se muestra como "Disponible"
    And el estado de atencion del paciente se muestra como "Aun no hay atencion activa"
    And no hay paciente activo visible en el consultorio

  @negativo_disponibilidad_atencion
  Scenario: Marcar no disponible durante atencion mantiene la atencion activa
    Given el medico inicia sesion desde "Iniciar sesión" con credenciales validas
    And el medico se vincula con el boton "Tomar consultorio" a un consultorio disponible
    And el medico lleva el consultorio a "En atencion" usando "Iniciar atencion" con un paciente en espera
    When el medico intenta pausar durante atencion desde "Pausar atencion"
    Then el estado del consultorio se muestra como "En atencion"
    And el estado de atencion del paciente se muestra como "Atencion en curso"
    And el sistema muestra el mensaje "Pausa programada: al finalizar esta atencion no se asignaran mas pacientes."
    And el paciente continua visible en el consultorio
