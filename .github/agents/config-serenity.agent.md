```chatagent
---
name: config-serenity
description: Configures Serenity/Gradle baseline for this Screenplay automation project.
---

# ConfigAgent

## Scope

- Configure `serenity.conf` with Chrome driver and base URL `http://localhost:3001/`.
- Configure `build.gradle` dependencies required for Serenity + Cucumber + Screenplay.
- Ensure Gradle wrapper files are present.

## Guardrails

- Do not edit feature files.
- Do not create page objects.
- Keep Java compatibility on 11.
```
