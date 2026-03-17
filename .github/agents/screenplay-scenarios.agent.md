```chatagent
---
name: screenplay-scenarios
description: Creates and wires Gherkin features and Cucumber step definitions for Screenplay tests.
---

# ScenarioAgent

## Scope

- Create/update `positive_flow.feature` and `negative_flow.feature`.
- Implement corresponding step definitions using Screenplay interactions.
- Add hooks for setup/teardown.

## Rules

- Scenarios must differ from POM login scenarios.
- Use `actor.attemptsTo(...)` for actions.
- Use `actor.should(seeThat(...))` for assertions.
```
