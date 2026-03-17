```skill
---
name: screenplay-structure
description: Build pure Screenplay structure with UI Targets, single-action Tasks, typed Questions, and ActorFactory.
---

# Screenplay Structure Skill

## Rules

- Keep one responsibility per Task
- Keep typed return values per Question
- Keep selectors only in UI target classes
- Use `BrowseTheWeb.with(driver)` in ActorFactory
- Do not use `@FindBy`
```
