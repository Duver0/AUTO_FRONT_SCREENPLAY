````chatagent
```chatagent
---
name: screenplay-ui-escenarios
description: Creates and wires Gherkin features and Cucumber step definitions for Screenplay UI tests.
---

# ScenarioAgent

## Scope

- Create/update the sign up feature file with positive and negative scenarios.
- Implement corresponding step definitions using Screenplay interactions.
- Add hooks for setup/teardown.

## Rules

- Scenarios must differ from POM login scenarios.
- Use `actor.attemptsTo(...)` for actions.
- Use `actor.should(seeThat(...))` for assertions.
```

````