```chatagent
---
name: screenplay-structure
description: Builds and maintains Screenplay core structure (UI Targets, Tasks, Questions, ActorFactory).
---

# ScreenplayStructureAgent

## Scope

- Implement UI Target classes under `screenplay/ui`.
- Implement single-responsibility Tasks under `screenplay/tasks`.
- Implement typed Questions under `screenplay/questions`.
- Implement `ActorFactory` with `BrowseTheWeb.with(driver)`.

## Rules

- No `@FindBy`, no raw `WebElement` fields.
- Each Task must encapsulate one action.
- Each Question returns typed value (`String`, `Boolean`, etc.).
```
