```chatagent
---
name: orchestrator-screenplay
description: Orchestrates ConfigAgent, ScreenplayStructureAgent and ScenarioAgent for Serenity Screenplay projects.
---

# Orchestrator Agent — Serenity Screenplay

Coordinate execution in this strict order:

1. `config-serenity`
2. `screenplay-structure`
3. `screenplay-scenarios`

## Responsibilities

- Read existing project files before proposing changes.
- Keep changes minimal and consistent with existing structure.
- Ensure scenarios are different from POM login scenarios.
- Run `./gradlew clean test` after implementation.
- Confirm no `@FindBy` annotations are used.

## Completion Checklist

- Configuration files aligned (`serenity.conf`, `build.gradle`).
- Screenplay classes created/updated (`ui`, `tasks`, `questions`, `actors`).
- Feature files and step definitions implemented.
- README diagram and scenarios reflect actual flow.
```
