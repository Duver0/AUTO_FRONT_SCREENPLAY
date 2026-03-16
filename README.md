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
./gradlew clean test
```

> On Windows: `gradlew.bat clean test`

---

## Generating the Serenity Report

```bash
./gradlew reports
```

The HTML report will be generated in `target/site/serenity/index.html`.

---

## Scenarios

### Positive Flow — `positive_flow.feature`

**Scenario: Visitor views the main heading on the home page**

A first-time visitor opens the application root URL and the automation verifies that a main heading (`<h1>`) is present and contains text. This confirms the application has loaded and rendered its primary content.

**Why it differs from POM scenarios:** The POM project typically validates login with valid credentials (form interaction + redirect). This scenario instead validates *passive content rendering* on the landing page — no form interaction, no authentication.

---

### Negative Flow — `negative_flow.feature`

**Scenario: User receives a required field error when submitting an empty title**

A user navigates to the new item creation page and immediately submits the form without filling in the required title field. The automation verifies that a validation error message appears.

**Why it differs from POM scenarios:** The POM project's negative flow typically tests login with wrong credentials (HTTP-level rejection). This scenario exercises *client-side form validation* on a create/add form — a completely different interaction model and application flow.

---

## Screenplay Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                          ACTOR                                  │
│               (Visitor / User + BrowseTheWeb)                   │
└──────────────────────────┬──────────────────────────────────────┘
                           │ attemptsTo(...)
           ┌───────────────┼───────────────┐
           │               │               │
           ▼               ▼               ▼
  ┌────────────────┐ ┌─────────────┐ ┌──────────────────────┐
  │ OpenHomePage   │ │ NavigateTo  │ │   SubmitEmptyForm    │
  │     Task       │ │ NewItemTask │ │        Task          │
  └───────┬────────┘ └──────┬──────┘ └──────────┬───────────┘
          │                 │                   │
          │      uses       │      uses         │      uses
          ▼                 ▼                   ▼
  ┌───────────────┐  ┌──────────────┐  ┌─────────────────┐
  │    HomeUI     │  │  NewItemUI   │  │   NewItemUI     │
  │  (Targets)    │  │  (Targets)   │  │   (Targets)     │
  └───────────────┘  └──────────────┘  └─────────────────┘
           │
           │ should(seeThat(...))
           ▼
  ┌────────────────────────┐     ┌──────────────────────────┐
  │   PageHeadingQuestion  │     │  ValidationErrorQuestion │
  │   Question<String>     │     │  Question<Boolean>       │
  └────────────────────────┘     └──────────────────────────┘
```

---

## Project Structure

```
src/
  test/
    java/
      runner/
        CucumberTestRunner.java
      screenplay/
        actors/
          ActorFactory.java
        tasks/
          OpenHomePageTask.java
          NavigateToNewItemPageTask.java
          SubmitEmptyFormTask.java
        questions/
          PageHeadingQuestion.java
          ValidationErrorQuestion.java
        ui/
          HomeUI.java
          NewItemUI.java
      steps/
        HomePageSteps.java
        NewItemSteps.java
    resources/
      features/
        positive_flow.feature
        negative_flow.feature
serenity.conf
build.gradle
settings.gradle
```

