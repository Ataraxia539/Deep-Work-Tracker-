# Google AI Studio — Native Android Build Instructions

## Goal

Generate a complete, working native Android application called **Deep Work Tracker**, using Kotlin and Jetpack Compose, with Firebase Authentication and Cloud Firestore. Build from scratch. The authoritative product requirements are in `docs/FINAL_MASTER_BLUEPRINT.md`.

## Before generating code

1. Import this GitHub repository into Google AI Studio Build mode.
2. Select **Android** as the platform (not Web).
3. Attach/open these repository files in the agent context:
   - `docs/FINAL_MASTER_BLUEPRINT.md`
   - `docs/IMPLEMENTATION_NOTES.md`
   - `assets/ASSET_MANIFEST.md`
   - `prompts/PHASED_BUILD_PROMPT.md`
4. Confirm that the agent has read the full 23-part blueprint, including the TBD list and final definition of done.
5. Create a new Android app. Do not assume an existing app, old database, old code, or old Firebase schema.

## Core technical direction

- Native Android application.
- Kotlin + Jetpack Compose.
- Single-activity, single-module architecture compatible with AI Studio's Android project workflow.
- Firebase Authentication with Google Sign-In.
- Cloud Firestore for user-scoped records.
- Firebase integration should be provisioned through the supported AI Studio/Firebase flow.
- Use ViewModel/state-flow/repository separation where supported by the generated project.
- Use Material 3 foundations but implement the approved Midnight Glass design system.
- English and Bengali localization; Manrope and Noto Sans Bengali typography.
- Use stable IDs, server timestamps where authoritative, and idempotency keys for reward/purchase operations.

## Build incrementally — do not generate everything in one pass

Follow `prompts/PHASED_BUILD_PROMPT.md` in order. Complete one phase, compile, run, inspect, and test before proceeding. Keep a working build after each phase. Never replace a working project wholesale just to add one feature.

Recommended sequence:
1. Foundation, navigation, design tokens, localization, Firebase setup.
2. Google Sign-In and exactly three onboarding pages.
3. Normal Mode timer, session logging, calendar and home dashboard.
4. Shared Challenge Setup, Challenge Mode dashboard, calendar and analytics.
5. Reward economy, XP, achievements and avatar progression.
6. Static artwork marketplace with exactly 30 initial catalogue entries.
7. More, Quests, Profile, Course placeholder and Settings.
8. QA, security review and release preparation.

## Non-negotiable implementation rules

- Blueprint is the source of truth. If code-generation suggestions conflict with it, follow the blueprint.
- Do not invent unresolved prices, XP costs, quest rewards, streak thresholds, pause/cancel rules, account deletion rules or notification schedules.
- Mark unresolved values as TODO/TBD in a central configuration and keep affected actions disabled or safely incomplete until approved.
- Never show fake production data, fabricated charts, invented analytics, fake completion percentages or demo values in actual user records.
- Never mix Normal Mode sessions with Challenge Mode sessions in mode-specific calendar, analytics or logs.
- Challenge sessions must be scoped to their Challenge ID.
- Reuse one Challenge Setup workflow for onboarding acceptance and Take a Journey.
- Do not activate a Challenge before explicit user approval of the generated plan.
- Coin formula must be exact: `ValidSeconds * (640 / 3600)`. Preserve fractional precision; do not round per second. Coins and XP are separate.
- Client-side code must not be trusted to arbitrarily modify wallet balances, XP or ownership. Use a secure, atomic, idempotent transaction path supported by the configured Firebase architecture.
- Marketplace is exactly 30 initial static artwork items across six categories. Do not create 3D/2.5D environments.
- Deep Work Course is a placeholder only; do not invent lessons or progress.
- No important text may clip, truncate or end in ellipses. Support long English/Bengali text, small screens and increased font scaling.
- Every visible control must work. If a feature is not implemented, do not display a fake working control.
- Preserve historical records. Avoid destructive operations while policies remain TBD.

## Firebase setup and secrets

- Use the real Firebase project owned/selected by the user.
- Enable Google as an Authentication provider.
- Register the exact Android application ID and required SHA fingerprints in Firebase.
- Use the real `google-services.json` supplied/generated for that Firebase Android app. It belongs in the Android app module locally; it must not be committed to this public repository.
- Never commit service-account JSON, private keys, OAuth client secrets, signing keystores, API keys or local secret files.
- Confirm Firestore region and project before creating production data.
- Apply and test security rules before using real user records.
- Test data isolation with two separate authenticated accounts.

## Required validation after every phase

1. Build/compile succeeds.
2. App launches and navigation works.
3. No placeholder action falsely reports success.
4. No fake production data.
5. English and Bengali render correctly.
6. Small-screen and large-font layouts remain readable.
7. Loading, empty, offline, permission-denied and error states are present.
8. No data leakage between Normal and Challenge modes or between users.
9. Critical reward/purchase operations are idempotent and atomic.

## Final handoff

Before calling the app complete:
- Run the blueprint's full acceptance checklist.
- Report exactly what is implemented, tested, not tested and still TBD.
- Provide build output and installation instructions.
- Do not claim an APK/AAB exists unless it was actually built successfully.
- Commit generated source code and safe resources back to this repository.
