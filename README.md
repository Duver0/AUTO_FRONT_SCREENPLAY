# AUTO_FRONT_SCREENPLAY

## Project Description

**AUTO_FRONT_SCREENPLAY** is a front-end automated testing project built using the **Screenplay pattern** on top of [Serenity BDD](https://serenity-bdd.info/). It targets a locally running web application at `http://localhost:3001/`.

### Why Screenplay?

The Screenplay pattern enforces **single responsibility** and **declarative intent** at every layer:

- **Actors** represent users with abilities (e.g., browse the web).
- **Tasks** describe *what* a user wants to achieve (not *how* the UI is structured).
- **Questions** verify the observable state of the application.
- **UI classes** isolate element locators in one place, with no `@FindBy` annotations.

This makes tests more readable, easier to maintain, and completely decoupled from the underlying page structure — avoiding the fragility of the traditional Page Object Model (POM).

---

## Prerequisites

| Requirement | Version |
|---|---|
| Java | 11 or higher |
| Gradle | 8.x (via wrapper) |
| Google Chrome | Latest stable |
| Target application | Running at `http://localhost:3001/` |

Make sure the web application is running locally before executing the tests:

```bash
# Example — start your app (command depends on your project)
npm start   # or yarn start, etc.
```

---

## Running the Tests

```bash
./gradlew clean test aggregate
```

> On Windows: `gradlew.bat clean test`

---

## Generating the Serenity Report

```bash
./gradlew reports
```

Open the generated report on Linux:

```bash
xdg-open target/site/serenity/index.html
```

The HTML report is generated at `target/site/serenity/index.html`.

> On macOS: `open target/site/serenity/index.html`
>
> On Windows: `start target\\site\\serenity\\index.html`

---

## Scenarios

### Sign Up Flow — `sign_up_flow.feature`

**Scenario: Registering with valid information shows success message**

A visitor opens the home page, navigates from **Iniciar sesión** to **Regístrate**, completes name/email/password with valid data, submits the form, and verifies the success feedback message.

**Why it differs from POM scenarios:** The POM project focuses on login success/failure. This scenario validates the **signup creation flow** and post-submit feedback on registration.

---

### Scenarios within `sign_up_flow.feature`

**Scenario: Registering with a weak password shows validation message**

A visitor follows the same registration navigation path but submits a weak password. The automation verifies the exact weak-password validation feedback.

**Why it differs from POM scenarios:** This scenario validates **password policy enforcement during signup**, not login authentication.

---

### Disponibilidad y atencion - `disponibilidad_y_atencion.feature`

**Scenario: Finalizar atencion limpia la vista del paciente y deja el consultorio disponible**

El medico inicia sesion, toma un consultorio libre, recibe un paciente en espera y lo pasa a atencion activa. Luego finaliza la atencion y se valida que el estado vuelva a **Disponible** sin paciente visible.

**Scenario: Marcar no disponible durante atencion mantiene la atencion activa**

El medico inicia sesion, toma consultorio y entra en **En atencion**. Durante la atencion marca el consultorio como no disponible y se valida el comportamiento diferido: la atencion no se interrumpe y se muestra la pausa programada.

**Why it differs from POM scenarios:** These scenarios focus on **estado operativo del consultorio, atencion activa y pausa diferida**, not on login success/failure as the main assertion.

---

## Screenplay Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                            ACTOR                                │
│                  (User + BrowseTheWeb)                          │
└──────────────────────────┬──────────────────────────────────────┘
                           │ attemptsTo(...)
  ┌────────────────────┼───────────────────────┐
  ▼                    ▼                       ▼
 ┌────────────────┐  ┌──────────────────┐  ┌────────────────────┐
 │ OpenHomePage   │  │ ClickSignInButton│  │ ClickRegisterLink  │
 │      Task      │  │      Task        │  │        Task        │
 └───────┬────────┘  └────────┬─────────┘  └──────────┬─────────┘
   │                    │                       │
   │ uses               │ uses                  │ uses
   ▼                    ▼                       ▼
   ┌─────────────┐      ┌─────────────┐        ┌─────────────┐
   │   HomeUI    │      │   HomeUI    │        │  SignInUI   │
   │ (Targets)   │      │ (Targets)   │        │ (Targets)   │
   └─────────────┘      └─────────────┘        └─────────────┘

         │ attemptsTo(...)
         ▼
   ┌──────────────────────────────────────────────┐
   │ EnterName / EnterEmail / EnterPassword Tasks│
   └──────────────────────┬───────────────────────┘
        │ uses
        ▼
         ┌─────────────┐
         │  SignUpUI   │
         │  (Targets)  │
         └─────────────┘

         │ should(seeThat(...))
         ▼
         ┌────────────────────────────────────┐
         │      FeedbackMessageQuestion       │
         │         Question<String>           │
         └────────────────────────────────────┘
```

### Medical availability and attention flow

```
Actor(Medico)
  -> IniciarSesionComoMedico
  -> VincularseAConsultorio
  -> AsignarPacienteEnEspera
  -> [FinalizarAtencion | MarcarNoDisponible]
  -> Questions(EstadoConsultorio, EstadoAtencionPaciente, PausaProgramadaVisible, PacienteVisible)

Supporting components
  Interactions: RegistrarMedicoDePrueba, CrearTurnoEnEspera
  UI Targets: MedicalSignInUI, MedicalPanelUI
```

---

## Project Structure

```
src/
  test/
    java/
      runner/
        CucumberTestRunner.java
      runners/
        DisponibilidadYAtencionRunner.java
      screenplay/
        actors/
          ActorFactory.java
        interactions/
          RegistrarMedicoDePrueba.java
          CrearTurnoEnEspera.java
        tasks/
          OpenHomePageTask.java
          ClickSignInButtonTask.java
          ClickRegisterLinkTask.java
          SelectEmployeeUserTypeTask.java
          FillSignUpFormTask.java
          ClickRegisterButtonTask.java
          IniciarSesionComoMedico.java
          VincularseAConsultorio.java
          AsignarPacienteEnEspera.java
          FinalizarAtencion.java
          MarcarNoDisponible.java
        questions/
          FeedbackMessageQuestion.java
          EstadoConsultorioQuestion.java
          EstadoAtencionPacienteQuestion.java
          PacienteVisibleQuestion.java
          PausaProgramadaVisibleQuestion.java
        ui/
          HomeUI.java
          SignInUI.java
          SignUpUI.java
          MedicalSignInUI.java
          MedicalPanelUI.java
        utils/
          DynamicEmailResolver.java
          TestMedicalDataFactory.java
          MedicalBackendClient.java
      steps/
        SignUpSteps.java
        DisponibilidadYAtencionSteps.java
    resources/
      features/
        sign_up_flow.feature
        disponibilidad_y_atencion.feature
serenity.conf
build.gradle
settings.gradle
```

---

## Agentes y Skills (GitHub Copilot)

Este proyecto incluye una configuración de agentes especializados con su skill asociado para mantener implementación y mantenimiento ordenados:

- **OrchestratorAgent** (`$screenplay-ui-orquestacion`): coordina el flujo completo y el orden de ejecución de subagentes.
- **ConfigAgent** (`$screenplay-ui-configuracion`): configura `serenity.conf`, `build.gradle` y validación de wrapper.
- **ScreenplayStructureAgent** (`$screenplay-ui-estructura`): define `UI`, `Tasks`, `Questions` y `ActorFactory` en Screenplay puro.
- **ScenarioAgent** (`$screenplay-ui-escenarios`): define `features` y `steps` con hooks y aserciones.

### Ubicación

- Agentes: `.github/agents/`
- Skills: `.github/skills/`

### Fórmula usada

- Cada agente usa formato `chatagent` con frontmatter (`name`, `description`)
- Cada skill usa formato `skill` con frontmatter (`name`, `description`)

