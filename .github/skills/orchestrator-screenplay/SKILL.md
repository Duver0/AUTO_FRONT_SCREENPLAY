```skill
---
name: orchestrator-screenplay
description: Orchestrate screenplay-ui-configuracion, screenplay-ui-estructura and screenplay-ui-escenarios in the right order for Serenity Screenplay UI automation.
---

# Orchestrator Skill — Serenity Screenplay

## Execution order

1. `screenplay-ui-configuracion`
2. `screenplay-ui-estructura`
3. `screenplay-ui-escenarios`

## Validation

- Run `./gradlew clean test`
- Ensure no `@FindBy`
- Ensure README reflects real architecture and scenarios
```
