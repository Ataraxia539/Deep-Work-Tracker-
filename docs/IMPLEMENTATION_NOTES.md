# Implementation Notes and Guardrails

This file supplements the authoritative blueprint. It does not replace or modify it.

## Repository state

This is a new project handoff. The Android application source has not yet been generated in this repository. Google AI Studio must create the Kotlin/Jetpack Compose project and verify the build before this can be called a working app.

## Decisions already captured in the blueprint

- Platform: Android native.
- Stack: Kotlin + Jetpack Compose.
- Backend: Firebase.
- Authentication: Google Sign-In first.
- Languages: English and Bengali.
- Design: Midnight Glass foundation.
- Modes: Normal Mode and Challenge Mode with strict data separation.
- Shared Challenge Setup flow.
- Exact coin earning: 640 Coins per valid hour.
- Separate Coin and XP progression.
- 14 approved avatar categories; three free onboarding avatars; Novice, Advanced and Legendary stages.
- 30 static artwork items: 5 Houses, 5 Bikes, 5 Cars, 5 Libraries, 5 Study Rooms, 5 Workplaces.
- More contains exactly five cards in the approved order.
- Deep Work Course is a placeholder.
- No important text clipping or truncation.

## Marketplace art direction

| Category | Approved direction |
|---|---|
| Houses | Modern Minimalist + Biophilic / Nature-Inspired |
| Bikes | Sport, Naked/Streetfighter, Cruiser, Adventure/Touring, Commuter/Standard |
| Cars | Luxury Sedan, Sports/Supercar, Electric/Futuristic; balanced five-item selection |
| Libraries | Classic Grand, Nature-Inspired, Luxury Modern |
| Study Rooms | Nature-Inspired + Cozy + Minimalist; natural light, greenery, airflow, uncluttered furniture |
| Workplaces | Luxury Executive |

Artwork must be original or properly licensed. Use static images only. Exact marketplace prices are TBD.

## Explicit TBD items — do not guess

See Part 22 of the blueprint. Particularly important:
- Google Sign-In copy and legal URLs.
- Additional authentication providers.
- Session edit/delete policy after reward issuance.
- Timer recovery after force-stop/reboot.
- Missed daily target copy/state.
- Streak qualifying threshold and timezone-change policy.
- Multiple concurrent active Challenges.
- Challenge pause/cancel/end-date adjustment.
- Challenge completion rewards and thresholds.
- XP earning and avatar/stage XP costs.
- Marketplace prices.
- Quest generation, rotation, expiry and rewards.
- Yearly quest boundary.
- Course content.
- Notification schedules/quiet hours.
- Backup/export/restore.
- Final Firebase database/security deployment details.
- Custom Challenge duration validation boundary.
- Duplicate item/gifting policy.
- Account deletion and retention policy.

## Implementation cautions

- A value marked TBD may be represented in code as a nullable/configurable setting, not as a guessed constant.
- Keep catalog data, XP costs, coin prices, theme mappings and quest settings centralized.
- Avoid hard-coded sample records in production repositories.
- Separate demo/preview data from user-owned data and make demo mode impossible to confuse with live mode.
- If a backend transaction is unavailable, do not fake a successful wallet or purchase update.
- A security rule that allows a client to write arbitrary wallet balances is not acceptable.
- Do not commit real Firebase configuration or secret credentials to this public repository.
