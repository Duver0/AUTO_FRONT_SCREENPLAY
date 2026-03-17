```skill
---
name: screenplay-scenarios
description: Create and wire positive/negative Gherkin scenarios with Cucumber step definitions in Screenplay style.
---

# Scenarios Skill

## Rules

- Positive and negative scenarios must be independent
- They must be different from POM login scenarios
- Actions through `actor.attemptsTo(...)`
- Assertions through `actor.should(seeThat(...))`
- Include setup and teardown hooks
```
