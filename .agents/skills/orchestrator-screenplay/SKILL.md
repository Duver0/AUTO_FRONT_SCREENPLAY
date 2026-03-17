```skill
---
name: orchestrator-screenplay
description: Orchestrate ConfigAgent, ScreenplayStructureAgent and ScenarioAgent in the right order for Serenity Screenplay automation.
---

# Orchestrator Skill — Serenity Screenplay

## Execution order

1. `config-serenity`
2. `screenplay-structure`
3. `screenplay-scenarios`

## Validation

- Run `./gradlew clean test`
- Ensure no `@FindBy`
- Ensure README reflects real architecture and scenarios
```
