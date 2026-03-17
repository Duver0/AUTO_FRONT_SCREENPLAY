```skill
---
name: orchestrator-screenplay
description: Orchestrate ConfigAgent, ScreenplayStructureAgent and ScenarioAgent in the right order for Serenity Screenplay automation.
---

# Orchestrator Skill — Serenity Screenplay

## Execution order

1. `agent-config-serenity-Screenplay`
2. `agent-screenplay-structure-Screenplay`
3. `agent-screenplay-scenarios-Screenplay`

## Validation

- Run `./gradlew clean test`
- Ensure no `@FindBy`
- Ensure README reflects real architecture and scenarios
```
