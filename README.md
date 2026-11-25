# Deep Work Tracker — AI Studio Build Kit

A source-of-truth product blueprint and implementation handoff for a new native Android application built with **Kotlin + Jetpack Compose + Firebase** in Google AI Studio.

## Start here

1. Read [AI Studio Build Instructions](AI_STUDIO_BUILD_INSTRUCTIONS.md).
2. Treat [Final Master Blueprint](docs/FINAL_MASTER_BLUEPRINT.md) as the authoritative product specification.
3. Read [Implementation Notes](docs/IMPLEMENTATION_NOTES.md) before generating code. Requirements marked TBD must remain unresolved; do not invent values.
4. Import this repository in Google AI Studio Build mode, choose **Android**, and ask the agent to follow the build instructions in order.

## Project status

This repository is the implementation handoff/starter kit. It is not yet a compiled Android application. AI Studio must generate the native Android project and verify it builds before this repository can be called an installable app.

## Confirmed product constraints

- New application from scratch; Android native.
- Google Sign-In before onboarding.
- Exactly three onboarding pages; Page 3 has no pagination dots.
- Normal Mode and Challenge Mode data are strictly isolated.
- One shared Challenge Setup workflow.
- Midnight Glass design system; English and Bengali.
- No clipping, truncation, or important-text ellipses.
- Coin earning: `ValidSeconds * 640 / 3600`, fractional precision preserved.
- Coin and XP are separate.
- 14 avatar categories plus three free onboarding avatars; three evolution stages.
- Exactly 30 initial static marketplace artworks across six categories.
- No 3D/2.5D interactive rooms, houses, kingdoms, or environments.
- Deep Work Course is a placeholder until its content is specified.

## Repository layout

- `docs/` — product blueprint and implementation notes.
- `prompts/` — staged prompts for Google AI Studio.
- `assets/ASSET_MANIFEST.md` — required artwork inventory and art direction.
- `starter-code/` — small, reviewable Kotlin domain helpers for critical calculations.
- `firebase/` — Firebase rules/indexes handoff templates.

## Important security note

Never commit `google-services.json`, service-account keys, OAuth client secrets, signing keystores, or API keys. Configure the real Firebase project through Google AI Studio/Firebase setup. The sample Firebase rules are a starting point only and must be completed and tested before real user data is stored.

## Build order

Foundation → Authentication/Onboarding → Normal Mode → Challenge Engine → Rewards/Avatars → Marketplace → More/Quests → QA and release.

See [the blueprint's implementation phases](docs/FINAL_MASTER_BLUEPRINT.md).
