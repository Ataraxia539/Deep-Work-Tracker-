DEEP WORK TRACKER
FINAL MASTER BLUEPRINT
Complete Product, UI/UX, Functional, Data, Reward Economy & Development Specification

Version: 1.0 — Consolidated Final Blueprint
Date: 25 November 2026
Platform: Android
Development Environment: Google AI Studio
Backend: Firebase
Primary Language: English + Bengali (BN)
Product Type: Personal Deep Work Tracker with Gamification
Project Status: New application built from scratch

======================================================================
0. DOCUMENT PURPOSE, AUTHORITY & IMPLEMENTATION RULES
======================================================================

This document is the consolidated master specification for building the Deep Work Tracker Android application from scratch. It combines the original application brief, subsequent approved design decisions, product clarifications, and the latest UI updates.

This is the authoritative implementation brief. Where an older draft conflicts with this document, this document takes precedence.

0.1 Product objective
The application helps a user:
- Record and measure genuine Deep Work sessions.
- Understand focus, energy, satisfaction, distractions and productivity patterns.
- Build consistency through goals, quests and long-term challenges.
- Earn XP, Coins and achievement rewards.
- Unlock avatars and purchase static collectible artwork.
- Review progress through meaningful, data-driven analytics.

The app must reward real, recorded work rather than fabricated activity. It must not invent missing session details, analytics, milestones or progress.

0.2 Build-from-scratch requirement
- Create a new Android application from scratch.
- Do not assume an existing production app, database schema or codebase.
- Do not preserve obsolete implementation assumptions from earlier drafts.
- Use Firebase as the backend.
- Keep the architecture practical and maintainable for a small user group (the owner and at most a few friends).
- Implement appropriate user-level data isolation and basic security without unnecessary enterprise complexity.

0.3 Non-negotiable principles
1. Functional implementation is required; static mockups alone are not sufficient.
2. Every visible interactive control must perform its stated action.
3. Do not fabricate data, charts, progress, ratings, course completion or rewards.
4. Never invent missing information in a session log.
5. Never mix Normal Mode session records with Challenge Mode session records in mode-specific screens or analytics.
6. Historical records and completed purchases must not be silently deleted.
7. No text may be clipped, truncated, hidden behind ellipses, or broken by layout overflow.
8. Support English and Bengali text.
9. Responsive Android layouts are required, including small screens and increased font scaling.
10. Use a shared, reusable Challenge Setup workflow regardless of where a challenge is started.
11. Static marketplace artwork only: no 3D/2.5D interactive rooms, houses, kingdoms or environments.
12. Coin and XP are separate currencies/progression values.
13. User approval is mandatory before activating an AI-generated Challenge Plan.
14. Do not invent unspecified Deep Work Course lessons, quizzes or content. Keep that feature as a placeholder until separately specified.

0.4 Requirement labels
- CONFIRMED: explicitly selected or approved.
- IMPLEMENTATION RULE: required technical behavior inferred directly from confirmed product requirements.
- TBD: intentionally unresolved; do not silently invent a final product rule. Use a safe placeholder or request a decision before implementation if it materially affects behavior.

======================================================================
1. PRODUCT STRUCTURE & GLOBAL NAVIGATION
======================================================================

1.1 Application modes
The app has two main working modes:
A. Normal Mode (Simple Mode)
B. Challenge Mode (Journey / Long-term Challenge)

Normal Mode is the standard everyday tracking environment.
Challenge Mode is a goal-oriented environment linked to one particular active Challenge.

Short daily, weekly, monthly and yearly objectives belong to Quests. A multi-day structured program lasting 7–100 days belongs to Take a Journey / Long-term Challenge.

1.2 Authentication-first launch sequence
The first screen shown to a signed-out user must be the Google Sign-In page.

App Launch
  -> Check Firebase Authentication state
      -> Signed out: Google Sign-In Page
      -> Signed in, onboarding incomplete: resume onboarding
      -> Signed in, onboarding complete:
          -> Active Challenge exists: Challenge Dashboard
          -> Otherwise: Normal Mode Home Dashboard

IMPORTANT:
- Onboarding must not begin before successful Google authentication.
- Do not create an anonymous user path as the default onboarding path.
- Returning authenticated users must not be forced through onboarding again.
- If the user cancels Google Sign-In, remain on the sign-in screen and show a clear, non-alarming message.
- If authentication fails, show a retry action and preserve the entered/selected state where possible.
- Sign-out returns to the Google Sign-In page.
- Account deletion behavior and provider unlinking are TBD and must not be implemented destructively without a separately approved policy.

1.3 Main navigation — Normal Mode
Use a floating glass bottom navigation with exactly five destinations:

1. Home
2. Calendar
3. Focus
4. Analytics
5. More

The former standalone Profile tab is removed. Profile is accessed from More.

1.4 Main navigation — Challenge Mode
Challenge Mode has its own dedicated dashboard and challenge-specific views. The Challenge UI must retain its active duration theme. It must not accidentally navigate into Normal Mode data when the user opens Challenge Calendar, challenge analytics or challenge session logs.

The exact Challenge Mode bottom-navigation item labels/order must follow the approved Challenge Dashboard reference design. Do not add an unapproved new navigation destination merely to fill space.

1.5 Header coin shortcut — latest approved UI update
Both Normal Mode Dashboard and Challenge Mode Dashboard must show:
- Settings label/icon.
- Coin balance displayed beside Settings in the dashboard header.

Coin balance is tappable.
On tap:
  -> Open Collection / Reward Center
  -> Navigate directly to the Coin-purchasable marketplace section
  -> User can browse item categories and purchase with Coins.

This behavior must be identical in Normal Mode and Challenge Mode.
Do not route the user to a generic Collection landing page if that would require another tap to reach the Coin marketplace. Open the relevant purchase section directly.

Insufficient-balance behavior:
- Disable or reject purchase.
- Clearly show current balance, item price and coin shortfall.
- Do not deduct a partial amount.
- Do not allow the balance to become negative.

======================================================================
2. AUTHENTICATION — GOOGLE SIGN-IN
======================================================================

2.1 First-launch screen
Before any onboarding page, show a dedicated Google Sign-In screen.

Suggested screen content:
- App logo / Deep Work Tracker identity.
- A concise welcome headline.
- Short supporting line explaining that the account securely stores the user's Deep Work progress.
- Primary “Continue with Google” button with Google mark.
- Privacy/Terms links only if actual pages/URLs are supplied and implemented.
- Loading state while authentication is processing.
- Error message and retry action.

Do not add email/password sign-up unless separately requested.

2.2 Authentication flow
1. User taps Continue with Google.
2. Start Google OAuth / Firebase Authentication provider flow using the officially configured Android Firebase project.
3. Receive Google credential.
4. Authenticate with Firebase Auth.
5. Obtain Firebase UID.
6. Create or retrieve the user's profile document.
7. Load onboarding completion state and app data.
8. Route according to Section 1.2.

2.3 Account data
Use Firebase UID as the stable owner key. Never use display name or email as a database ownership key.

Store only necessary identity data:
- uid
- displayName
- email
- photoURL (optional)
- provider
- createdAt
- lastLoginAt
- onboardingCompleted

Do not expose private account information in public collections.

2.4 Authentication security
- Enable Google provider in Firebase Authentication.
- Configure Android package name and SHA fingerprints in Firebase.
- Use the correct Firebase Android configuration file for the actual Firebase project.
- Never commit private service-account credentials into the Android client or public repository.
- Do not treat a hidden UI button as a security control.
- Every user-owned Firestore document must be protected by authenticated UID ownership rules.
- Validate all reward, purchase and balance-changing operations in trusted backend logic or Firestore transactions.

======================================================================
3. ONBOARDING — EXACTLY THREE PAGES
======================================================================

Onboarding starts only after Google Sign-In succeeds.

There are exactly THREE onboarding pages. Do not create a fourth onboarding page.
Page 3 must not display pagination dots.
The final “You are all set” confirmation is a completion state, not a fourth onboarding page.

Onboarding content must be responsive and scroll when necessary. Never shrink text to an unreadable size to force everything onto one screen.

----------------------------------------------------------------------
3.1 PAGE 1 — THE ART OF DEEP WORK
----------------------------------------------------------------------

Purpose:
Introduce the importance of Deep Work through a premium, cinematic, minimal visual experience.

Design:
- Dark midnight-navy background.
- Main background: #0B1220.
- Secondary surface: #151F30.
- Primary text: #E8EDF5.
- Secondary text: #AAB5C5.
- Accent gold: #F5C66A.
- Manrope English typography.
- Use the supplied Deep Work illustration/reference asset where available.
- Illustration occupies approximately 35–40% of the screen while adapting responsively.
- Image should blend naturally into the background; avoid a harsh card boundary.

Exact body copy — do not rewrite:
Paragraph 1:
“The ability to concentrate deeply is becoming increasingly rare in a world full of distractions.”

Paragraph 2:
“Yet, the most valuable work demands sustained, undivided attention.”

Paragraph 3:
“Without deep focus, learning becomes slower, thinking becomes superficial, and meaningful progress becomes harder.”

Paragraph 4:
“Deep Work enables you to master difficult skills and produce work of genuine value.”

Typography:
- Body text Manrope Bold 700.
- Highlighted phrases ExtraBold 800.
- Main text #E8EDF5.
- “concentrate deeply” — #38BDF8.
- “valuable work” — #A78BFA.
- “slower” and “superficial” — #86EFAC.
- “master difficult skills” — #F5C66A.
- No other words should be highlighted.

Typewriter effect:
- Approximately 30–40 ms per character.
- Paragraphs appear sequentially.
- Brief pause between paragraphs.
- Subtle blinking cursor, #38BDF8, approximately 500 ms interval.
- Cursor disappears after each paragraph completes.
- Animation must not cause layout jumping or lag.
- Provide a reduced-motion/accessibility alternative if system motion preferences require it.

Final message:
“Don't just spend your time.”
“Make it count.”
First line white; second line gold, larger and ExtraBold.
A subtle gold border/glow may be used.

Bottom navigation:
- Four pagination dots on Page 1.
- First dot active.
- Next button with right arrow.
- Transparent button background, subtle border.
- Safe-area aware.

The original brief specified four dots on Page 1. Later approved onboarding structure is exactly three pages, and Page 3 has no dots. Reconcile the actual pagination implementation to the final three-page structure; do not leave a misleading four-dot indicator.

----------------------------------------------------------------------
3.2 PAGE 2 — MEET YOUR DEEP WORK COMPANION
----------------------------------------------------------------------

Purpose:
Show a visual preview of the product and its key capabilities.

Headline:
“Meet Your Deep Work Companion”
“One place to track. Understand. Improve.”
“A powerful tool to build a focused mind and achieve more.”

Design:
- Continue the same dark navy visual identity.
- Main background #071226.
- Secondary background #0B1830.
- Glass card #14233C.
- Card border #263B5C.
- Primary text #E8EDF5.
- Secondary text #AAB8D0.
- Gold #F5C66A.
- Blue #38BDF8.
- Purple #A78BFA.
- Green #34D399.
- Manrope font.

Dashboard showcase is a promotional mockup, not live user data:
- App identity: Deep Work Tracker.
- Sample Today's Deep Work: 7h 20m.
- Sample Sessions: 5.
- Sample Focus Score: 8.6/10.
- Sample Weekly Performance chart Monday–Sunday.
- Friday may be highlighted as the tallest sample bar.
- Sample Current Streak: 12 Days.
- Mark these values as illustrative in implementation data; never write them into the user's actual records.

Four feature cards:
FOCUS
“Timed sessions and session logs.”

INSIGHTS
“Focus, energy, satisfaction and performance analytics.”

CHALLENGES
“Daily, weekly and multi-day challenges.”

REWARDS
“Earn Coins, badges and customize your collection.”

The old Kingdom visual in the original concept is obsolete. Replace it with a static-artwork/collection-oriented reward visual. Do not imply interactive 3D Kingdom customization.

Reference fidelity:
- Follow the supplied Page 2 reference layout as closely as possible.
- Build all UI components independently; do not use a full-screen screenshot as the UI.
- Keep cards, charts, text and navigation responsive.
- No text clipping or ellipsis.

----------------------------------------------------------------------
3.3 PAGE 3 — LET'S ANSWER SOME QUESTIONS
----------------------------------------------------------------------

Page 3 is a sequential setup experience. Show one question/step at a time rather than presenting every question simultaneously.

IMPORTANT:
- Exactly three onboarding pages overall.
- No pagination dots on Page 3.
- Page 3 is the final onboarding page.
- Save each completed answer immediately so the user can resume after app interruption.

Approved sequential steps:
1. Daily Deep Work Target.
2. Week's First Day.
3. Avatar Selection.
4. Challenge Recommendation.

A. Daily Deep Work Target
- User can enter Hours and Minutes.
- Validate non-negative values.
- Normalize minutes appropriately.
- Store the daily target as a consistent duration value (e.g. total seconds/minutes).
- Do not silently impose a target if the user has not entered one.
- The earlier challenge notes mention a 7-hour target as an example, not a universal mandatory default.

B. Week's First Day
- User chooses the first day of their week.
- Save the preference.
- Use it consistently in weekly calendar, weekly analytics and weekly quest boundaries.
- Do not assume Monday if the user selected another day.

C. Avatar Selection
Show the three completely free onboarding avatars:
1. Ordinary Boy.
2. Ordinary Girl.
3. Mysterious Hooded Human — face hidden.

These three avatars are free at the Novice stage.
User selects one avatar.
Other avatar categories are unlocked later using XP.
The avatar carousel behavior must not be confused with the Challenge duration carousel.

D. Challenge Recommendation
Built-in durations:
- 7 Days
- 14 Days
- 21 Days
- 30 Days
- 60 Days
- 90 Days
- 100 Days

Challenge carousel:
- Finite, not infinite.
- Maximum three cards visible at once.
- Initial center: 21 Days.
- Initial left: 14 Days.
- Initial right: 30 Days.
- 7 Days is the left boundary.
- 100 Days is the right boundary.
- Swipe left/right or tap a side card to center it.
- Center card is larger and highlighted.
- No circular wrap-around.
- At the first/last item, swiping beyond the boundary does nothing.
- Challenge theme and description update with selected duration.

Accept / Skip:
- Accept stores the selected duration as pending selection.
- Accepting a duration does NOT activate a Challenge.
- Accept routes into the shared Challenge Setup workflow (Part 7).
- Skip routes to Normal Mode completion.
- Skip stores no selected active Challenge; challengeAccepted=false and duration=null.

E. Completion
After setup approval/skip is complete, show:
“You are all set”
- Centered, bold, white/gold.
- Smooth fade-in.
- Approximately 1.5–2 seconds before routing.
- If Challenge was approved and activated, open Challenge Dashboard.
- If Challenge was skipped, open Normal Mode Home Dashboard.

======================================================================
4. GLOBAL DESIGN SYSTEM & ACCESSIBILITY
======================================================================

4.1 Overall visual direction
Premium Dark Glassmorphism / Midnight Glass with restrained accent colors. Avoid excessive gradients, neon glow, clutter and unnecessary decorative elements.

Core palette:
- Main background: #10182B (global Midnight Navy foundation).
- Deep background: #071426.
- Secondary background: #0B1C30.
- Card surface: #202C43.
- Elevated surface: #12243B.
- Royal gold: #D9AE62.
- Warm ivory / primary text: #F4F0E8.
- Secondary text: #AAB8D0.
- Success green: #79B69B.
- Coral: #D98579.
- Electric blue: #568BFF.
- Teal: #36D9C5.

Use Midnight Glass as the shared foundation. Electric Blue and Teal are limited secondary accents, not a competing full theme.

4.2 Typography
- English: Manrope.
- Bengali: Noto Sans Bengali.
- Page heading: approximately 24sp, ExtraBold.
- Section heading: approximately 18sp, Bold.
- Card text: approximately 16sp.
- Main metrics: approximately 26–32sp.
- Supporting text: approximately 13–14sp.
- Small labels should generally remain at least 12sp.
- Use dynamic sizing and wrapping where necessary.

4.3 Absolute no-clipping rule
No text anywhere may:
- Be cut off.
- Be truncated.
- End with ellipsis when the full text is important.
- Overflow its card.
- Be hidden under navigation or system bars.
- Break awkwardly due to English/Bengali font differences.

Implementation:
- Use natural wrapping.
- Use content-driven/dynamic card height.
- Avoid fixed heights for text-heavy cards.
- Support long display names, task names, challenge names and descriptions.
- Support small Android screens and increased system font size.
- Allow vertical scrolling where needed.
- Test both English and Bengali.
- Never solve overflow by making text unreadably small.

4.4 Interaction and animation
- Animations should be subtle, smooth and performant.
- Avoid heavy blur on large areas.
- Avoid repeated re-render loops.
- Every tap target must be usable.
- Respect Android safe areas and system bars.
- Provide clear loading, success, empty and error states.

======================================================================
5. NORMAL MODE — HOME DASHBOARD
======================================================================

5.1 Purpose
The Home Dashboard is the user's everyday overview. It should motivate action while showing real data only.

5.2 Header
- User greeting.
- Selected avatar.
- Display name.
- Settings action.
- Coin balance beside Settings (Part 1.5).
- Coin tap opens Collection's Coin Purchase section directly.

5.3 Dashboard sections
1. Greeting / Profile header.
2. Wallet / Coin summary.
3. Today's Deep Work.
4. Current Streak.
5. Weekly Performance carousel.
6. Active Journey preview (only if an active Challenge exists and the mode context supports it).
7. Today's Quests preview.
8. Achievements preview.
9. Prominent Start Deep Work action.

Do not show fabricated sample values on a real dashboard.
If a section has no data, use a meaningful empty state or hide it according to the section's purpose.

5.4 Today's Deep Work card
Show:
- Total valid Deep Work duration today.
- User's daily target.
- Progress toward target.
- Session count.
- Clear target completion state.

If the user has no target, do not invent one.
If target is missed, show the actual progress without shaming or false completion.
Exact wording and visual state for missed target can be finalized during UI implementation, but the data must remain truthful.

5.5 Current Streak
- Show current streak based on the approved global streak rules.
- Do not count duplicate session days more than once.
- Do not let challenge and normal records double-count a calendar date.
- Streak calculation rules are detailed in Part 12; any unresolved day-boundary policy must be configured explicitly.

5.6 Weekly Performance carousel
Exactly five cards:
1. Weekly Performance (overall).
2. Deep Work Time Trend.
3. Focus Trend.
4. Energy Trend.
5. Satisfaction Trend.

Behavior:
- Infinite looping.
- Auto-slide every 4 seconds.
- Right-to-left progression.
- User can swipe.
- Press-and-hold pauses auto-slide.
- Releasing resumes auto-slide.
- Show pagination dots.
- Tapping a card opens the relevant Analytics detail.
- Period selector: Week / Month / Year.
- Period selection updates all five cards.
- Remove instructional text from the carousel.
- Do not show “Average Time Before First Distraction”.

5.7 Coin balance shortcut
The coin amount and coin icon are shown beside Settings. Tapping them opens Collection directly at the marketplace section where Coin-purchasable items are listed.

======================================================================
6. NORMAL MODE — CALENDAR, FOCUS TIMER & SESSION LOGGING
======================================================================

6.1 Normal Calendar
- Dedicated Calendar tab.
- Show Normal Mode session activity only.
- Select a date to view that date's Normal Mode session logs.
- Multiple sessions on one day appear as separate log cards.
- Calendar should reflect the user's selected week-start day.
- Do not display Challenge-only session logs in Normal Calendar.
- Calendar date status must be based on actual saved records.

6.2 Focus tab / timer
A functional Deep Work timer is required.
Core states:
- Idle / Ready.
- Running.
- Paused.
- Completed.
- Cancelled.
- Saved.

Core actions:
- Start.
- Pause.
- Resume.
- Complete.
- Cancel.
- Save session.

Timer must not lose track of elapsed time when the app moves to background or the screen turns off.
Use reliable timestamps/elapsed-time logic rather than relying only on a continuously running UI counter.
On process recreation, recover the session state safely.
Do not count paused time as Deep Work duration.
Do not reward cancelled or invalid time.
The exact recovery prompt and behavior for a session interrupted by force-stop/device reboot must be implemented conservatively and must never fabricate duration.

6.3 Session logging workflow
A saved session should support:
- Session title / task name.
- Start time.
- End time (when available).
- Duration.
- Focus score /10.
- Energy score /10.
- Satisfaction score /10.
- Distractions.
- Factors that helped focus.
- Factors that helped energy.

Do not invent missing information.
If start and end times are both provided, validate the duration against them.
If times are absent, preserve the supplied duration and do not manufacture timestamps.
The user should be able to review a session before final save where appropriate.

6.4 Score input
Focus, Energy and Satisfaction are each scored out of 10.
Use a clear input control (e.g. slider or rating selector) with readable numeric value.
No score should be silently defaulted to a fabricated value.
If a field is optional, retain null/unknown distinctly from zero.

6.5 Session edit/delete
TBD: exact edit window, delete confirmation, and whether completed sessions can be edited after rewards have been issued.
Implementation must preserve auditability:
- Do not silently delete a rewarded record.
- If deletion is supported, use a transaction/reversal policy for linked rewards.
- Do not permit duplicate reward claims for the same session.

======================================================================
7. CHALLENGE MODE — SHARED SETUP, DASHBOARD & LIFECYCLE
======================================================================

7.1 Challenge definition
A Challenge (also called Journey / Long-term Challenge) is a multi-day, goal-oriented program lasting 7–100 days or a custom duration where supported.

Built-in durations:
7, 14, 21, 30, 60, 90, 100 days.

Custom durations:
- Supported by the architecture.
- Must use the same Challenge Engine.
- Do not create a separate engine for custom duration.
- Theme resolver must select the correct palette based on duration (Part 8).

7.2 One shared Challenge Setup workflow
There must be one reusable setup flow, used from:
A. Onboarding Challenge Accept.
B. Normal Mode / Take a Journey / Start Challenge.

Only the entry point differs. The following must be identical:
- Components.
- Validation.
- Policy selection.
- Goal configuration.
- Daily targets.
- Milestone editor.
- AI plan generation.
- Plan review.
- Approval.
- Activation.
- Theme resolver.
- Data model.

7.3 Challenge setup steps
1. Confirm selected duration.
2. Select Challenge Policy.
3. Define primary Challenge Goal.
4. Configure Daily Targets.
5. Create/edit Milestones.
6. Generate personalized plan.
7. Review the plan.
8. Edit allowed plan details.
9. Explicitly approve.
10. Activate Challenge.
11. Open Challenge Dashboard.

7.4 Challenge Policies
User selects one:
- Strict.
- Flexible.
- Milestone-oriented.

Policy must affect evaluation/progress behavior in a transparent way.
Do not force one policy on every user.
Exact scoring thresholds and penalty semantics must be specified in the policy configuration rather than hidden in UI code.

7.5 Goals
Examples:
- Reach a defined Deep Work duration.
- Improve Focus Score.
- Complete a project.
- Study consistently.
- Learn a skill.
- Custom goal.

The user must be able to enter their own goal. AI recommendations must not overwrite the user's goal without consent.

7.6 Daily Targets
Challenge-specific target settings may include:
- Daily Deep Work duration.
- Daily session count.
- Minimum Focus Score.
- Other explicit challenge conditions.

Challenge targets are independent from Normal Mode daily target.
Do not overwrite the Normal Mode target when creating a Challenge.

7.7 Milestones
Each user-created milestone requires:
- Title: required.
- Description: optional.
- Target Day: required.

Rules:
- Sort milestones by Target Day.
- Target Day must be within the Challenge duration.
- Target Day passing does not automatically complete a milestone.
- Milestone completion requires its actual achievement condition to be satisfied.
- Support create/edit/delete where allowed by Challenge state.
- Changing a fixed plan after activation requires confirmation.
- Preserve completed milestone history.

7.8 AI-generated Challenge Plan
AI uses:
- User's selected duration.
- Policy.
- Primary goal.
- Daily targets.
- Milestones.
- Any relevant user-provided constraints.

Plan may include:
- Goal summary.
- Daily targets.
- Milestone schedule.
- Strategy.
- Recommendations/tips.

User must be able to review the plan.
User approval is mandatory before activation.
AI-generated suggestions must not be presented as authoritative facts.
The Tips card must NOT display an “AI” label or AI badge.
Tips should be concise, approximately 1–2 sentences, with light typography.

7.9 Activation
- Challenge becomes active only after explicit plan approval.
- Save activation timestamp and planned start date.
- Apply duration theme.
- Create challenge progress state.
- Navigate to Challenge Dashboard.
- Do not activate merely because the user selected a duration or tapped Accept in onboarding.

7.10 Active Challenge count
TBD: Whether multiple concurrent active Challenges are allowed.
Do not silently assume an unlimited number. For initial implementation, use a single active Challenge slot only if the user confirms this constraint; otherwise keep the data model capable of multiple records while the UI policy remains unfinalized.

7.11 Challenge Dashboard
Dedicated Challenge dashboard sections:
1. Challenge Header.
2. Personalized Tips card.
3. Challenge Progress card.
4. Milestones.
5. Today's Challenge Performance.
6. Challenge Calendar preview.
7. Floating glass navigation as specified by the Challenge reference.

Show actual values only:
- Challenge name.
- Current day number.
- Duration.
- Progress.
- Milestone progress/count.
- Today's Deep Work performance.
- Relevant session count.
- Challenge streak/consistency as defined.
- Challenge calendar status.

The old reference preview values (e.g. 8.4/10 Focus, 4/5 sessions, 8 Days) are sample design values only. Never display them as real user data.

7.12 Challenge Calendar
- Dedicated Challenge Calendar view.
- Theme-specific background, cards, borders, highlights and markers.
- Show completed, current, upcoming, missed and milestone days where supported by actual state.
- Selecting a date displays only sessions linked to the active/historical Challenge being viewed.
- Challenge session cards use the same log layout as Normal Mode.
- Challenge Calendar must never display Normal Mode logs.
- Every Challenge session must carry its Challenge ID.
- Do not merge Normal Mode and Challenge Mode analytics.

Challenge session log fields:
- Task/session title.
- Start time.
- End time, if saved.
- Duration.
- Focus score.
- Energy score.
- Satisfaction score.
- Distractions.
- Factors that helped focus.
- Factors that helped energy.

7.13 Challenge completion
The original product intent includes:
- Challenge completion report.
- Performance summary.
- Goal and milestone outcomes.
- Challenge rewards.
- Historical Challenge record.

Exact completion-report layout, reward thresholds and early termination rules are TBD and must not be invented.
At completion, preserve the full Challenge record as history.
Do not delete the Challenge or its sessions.

7.14 Pause, cancel, edit and historical challenges
TBD:
- Whether a Challenge can be paused.
- Maximum pause duration.
- Whether pausing shifts end date.
- Early cancellation policy.
- Whether completed Challenge goals can be edited.
- Exact reward handling for cancellation.
- Historical Challenge reopen/read-only rules.

Until specified, avoid destructive actions and preserve records. Any irreversible action requires a confirmation dialog.

======================================================================
8. CHALLENGE DYNAMIC THEME SYSTEM
======================================================================

8.1 Principle
Challenge duration controls the Challenge Mode color identity.
Theme changes affect:
- Main background.
- Secondary background.
- Card background.
- Elevated card.
- Border.
- Primary and secondary accent.
- Glow.
- Progress ring/bar.
- Calendar highlight.
- Navigation accent.
- Chart colors.
- Glass tint.

Theme changes must not alter layout, component structure, typography or spacing.

8.2 Built-in themes
Built-in themes at exact built-in durations must remain unchanged:
- 7 Days: Arctic Cyan.
- 14 Days: Emerald.
- 21 Days: existing built-in 21-day theme.
- 30 Days: existing built-in 30-day theme.
- 60 Days: existing built-in 60-day theme.
- 90 Days: existing built-in 90-day theme.
- 100 Days: existing built-in 100-day theme.

Do not invent replacement palettes for built-in themes. Retain the approved palette definitions in the source specification/configuration.

8.3 Custom duration palette ranges
For durations between built-in durations, use one shared accent palette per interval:
- 1–6 days: Sunset Amber.
- 7 days: built-in Arctic Cyan.
- 8–13 days: Ocean Teal.
- 14 days: built-in Emerald.
- 15–20 days: Cobalt Blue.
- 21 days: built-in 21-day theme.
- 22–29 days: Royal Magenta.
- 30 days: built-in 30-day theme.
- 31–59 days: Burnt Orange.
- 60 days: built-in 60-day theme.
- 61–89 days: Ice Teal.
- 90 days: built-in 90-day theme.
- 91–99 days: Copper Rose.
- 100 days: built-in 100-day theme.

Built-in exact-duration themes always take precedence over custom interval themes.
Future built-in durations require only configuration/theme additions, not a new Dashboard implementation.

8.4 Theme resolver
Implement one centralized function/configuration:
- Input: Challenge duration.
- If duration exactly matches a built-in duration, return its built-in theme.
- Otherwise return the theme assigned to its interval.
- Invalid durations must be rejected or handled with a safe fallback.
Do not duplicate theme selection logic across screens.

======================================================================
9. ANALYTICS — NORMAL MODE
======================================================================

9.1 Filters
Analytics supports:
- Daily.
- Weekly.
- Monthly.
- Yearly.
- Custom date range.

Week boundaries must respect the user's selected week-start day.

9.2 Overview metrics
- Total Deep Work Time.
- Total Sessions.
- Average Session Duration.
- Average Focus.
- Average Energy.
- Average Satisfaction.
- Goal Achievement Rate.
- Consistency.

Use only valid Normal Mode sessions for Normal Mode Analytics.

9.3 Performance analysis
- Best Day.
- Best Week.
- Best Month.
- Focus trend.
- Energy trend.
- Satisfaction trend.
- Deep Work time trend.
- Session count trend.
- Period comparison.

9.4 Focus and distraction analysis
- Top 3 Focus Amplifiers.
- Top 3 Distractions.
- Top 3 Energy Boosters.
- Top 3 Satisfaction Factors.
- Distraction Impact Analysis.
- Focus Consistency Analysis.

Rank factors only when enough actual data exists. Do not invent rankings from missing or insufficient data.

9.5 Five approved insights
1. Peak Productivity Hours.
2. Session Duration Analysis.
3. Distraction Impact.
4. Focus Consistency.
5. Goal Achievement History.

Do not add the removed metric “Average Time Before First Distraction”.

9.6 Score labels
Focus score interpretation:
- 1–4: Poor.
- 5–7: Moderate.
- 8–8.9: Good.
- 9–10: Excellent.

For exact numeric boundaries, preserve the scale and do not round a score into a different category incorrectly.

9.7 Data integrity
- No data means “No data yet”, not zero.
- A missing score is not equivalent to 0.
- Partial data should produce partial analytics with a clear indication.
- Avoid unsupported causal claims.
- Period comparison must compare equivalent date ranges.
- Exclude Challenge-only sessions from Normal Mode Analytics.

======================================================================
10. ANALYTICS — CHALLENGE MODE
======================================================================

Challenge Analytics must be Challenge-specific:
- Challenge Deep Work duration.
- Challenge session count.
- Challenge Focus/Energy/Satisfaction trends.
- Daily target achievement.
- Milestone progress.
- Consistency.
- Period performance within the Challenge.
- Challenge completion history when available.

Strict isolation:
- Query only sessions carrying the relevant Challenge ID.
- Do not include Normal Mode session records.
- Do not include sessions from another Challenge.
- Do not treat missing data as zero.
- Use the active Challenge's duration theme for the interface.

The exact Challenge analytics screen layout can reuse Normal Analytics components where appropriate, but data scope and theme must remain Challenge-specific.

======================================================================
11. QUEST SYSTEM
======================================================================

11.1 Purpose
Quests are short-horizon goals that encourage daily, weekly, monthly and yearly progress.
They are distinct from 7–100 day Journey Challenges.

11.2 Quest periods
- Daily Quests.
- Weekly Quests.
- Monthly Quests.
- Yearly Quests.

11.3 Suggested active quest counts from prior planning
The earlier planning draft mentioned:
- 3 Daily Quests.
- 5 Weekly Quests.
- 5 Monthly Quests.
- 3 Yearly Quests.

Treat these counts as the intended baseline only if they remain in the approved final implementation. Quest generation/rotation rules and whether counts are fixed or configurable remain TBD.

11.4 Quest behavior
- Show title, description, period, progress, completion state and reward.
- Progress should derive from real session and activity data.
- Completed quests must remain in history.
- Expired quests must not be falsely marked completed.
- Avoid duplicate reward claims.
- Quest progress should update when eligible records are saved.
- Quest rewards must be validated before wallet changes.
- User should be able to inspect completed and active quests.

11.5 Quest difficulty and reward
Difficulty tiers, exact reward amounts, quest generation algorithm, reroll rules and expiration timezone are TBD. Do not invent final reward values.

11.6 Navigation
More contains the Quests/Challenges card. It must provide access to Quests and the Take a Journey entry point without mixing the two systems.

======================================================================
12. STREAK, DAILY BOUNDARIES & DUPLICATE PREVENTION
======================================================================

12.1 Global streak
A global streak represents consecutive qualifying calendar days according to the app's configured streak rule.
Challenge days may be highlighted while preserving the user's existing global streak.

12.2 No duplicate-day counting
- Multiple sessions on one date count as one qualifying streak day.
- A date must not be counted twice because the user has both Normal and Challenge activity.
- Do not double-count the same underlying session in two sources.
- Challenge participation should not reset a valid Normal streak merely because the user enters Challenge Mode.

12.3 Date boundary
Use a consistent user-local timezone for day boundaries. The app's timezone must be stored or derived reliably.
Exact qualification threshold for a streak day (e.g. minimum duration or any valid session) remains TBD and must be explicitly configured before final production release.

12.4 Streak history
Preserve historical streak data or enough source records to recompute it.
Do not fabricate a streak after missing data.

======================================================================
13. REWARD ECONOMY — COINS, XP & ACHIEVEMENTS
======================================================================

13.1 Separate progression systems
Maintain separate balances/progress for:
- Coins: marketplace currency.
- XP: avatar unlock/evolution progression.
- Achievements: badges, crests, medals, titles and trophies.

Do not merge Coins and XP into one currency.

13.2 Coin reference value
- 1 Coin = ৳1 as an in-app reference value only.
- Coins are virtual.
- No cash withdrawal or real-money redemption is implied.
- Marketplace prices are game-economy prices, not literal real-world item prices.

13.3 Exact Deep Work Coin earning rate
Fixed universal rate:
640 Coins per valid Deep Work hour.

Formula:
CoinsEarned = ValidSeconds * (640 / 3600)

Equivalent:
CoinsEarned = ValidSeconds * 640 / 3600

Important:
- The exact hourly rate is 640.
- 0.178 is only an approximate display of the per-second rate; it must NOT be used as the exact calculation.
- Preserve fractional precision internally.
- Define and consistently implement fractional carry/display behavior.
- Do not round every second in a way that causes the hourly total to drift from exactly 640 Coins.
- Use an integer-safe or decimal-safe representation for calculations and transaction updates.

13.4 Universal rate
Coin earning rate is the same for all users.
It is independent of:
- Monthly income.
- Career.
- Working-hours setting.
- Personal salary target.
Changing a user's career or monthly income target must never change their existing wallet balance or historical Coin transactions.

13.5 Valid time
Only valid Deep Work time earns Coins.
- Paused time is excluded.
- Cancelled time is excluded unless an explicitly approved recovery rule validates it.
- Duplicate session submissions must not award twice.
- Session ownership and session validity must be checked.
- Challenge and Normal session reward eligibility must not double-count the same duration.

13.6 XP
XP is used for avatar unlocks and avatar evolution.
Exact XP earning rate, XP per session, XP for quests and XP reward thresholds are TBD unless separately approved.
Do not convert Coins to XP or XP to Coins unless a future explicit feature is approved.

13.7 Achievement types
The collection/reward system may contain:
- Badges.
- Crests.
- Medals.
- Titles.
- Trophies.
- Other approved achievement artwork.

Challenge rewards may include Coin and XP bonuses, and special achievements depending on impact, difficulty, duration and consistency.
Exact reward thresholds must be configured and approved; do not invent arbitrary amounts.

13.8 Wallet ledger
Every balance change must have a transaction record:
- Transaction ID.
- User UID.
- Type (earning, purchase, bonus, correction/reversal).
- Amount.
- Currency.
- Source record/reference.
- Timestamp.
- Description.
- Idempotency key.

Wallet balance should be consistent with its transaction ledger.
Do not allow negative balance.
Do not trust client-side balance updates.

======================================================================
14. AVATAR SYSTEM
======================================================================

14.1 Avatar visual style
- Realistic, high-quality game-style character artwork.
- Not cartoon style.
- Each avatar category has a distinct visual identity.
- Static artwork assets; no requirement for a 3D interactive avatar environment.

14.2 Approved avatar categories — 14
1. Cat.
2. Scientist.
3. Student.
4. Knight.
5. King.
6. Tree.
7. Fire.
8. Futuristic Hero.
9. Gladiator.
10. Stoic Wiseman.
11. Robot.
12. Octopus.
13. Ninja.
14. Astronaut.

14.3 Free onboarding avatars
Three free Novice avatars:
1. Ordinary Boy.
2. Ordinary Girl.
3. Mysterious Hooded Human (face hidden).

These three Novice stages are completely free.

14.4 Unlocking
- Other avatar categories unlock using XP.
- Each avatar may have a different XP unlock price.
- Do not use Coins for avatar unlock unless explicitly approved.
- Different avatar prices should be stored as catalog configuration, not hard-coded throughout the UI.
- XP cost values are TBD; do not invent final prices.

14.5 Three evolution stages
Each avatar has three stages:
1. Novice.
2. Advanced.
3. Legendary.

Rules:
- The three free avatars have a free Novice stage.
- All other avatar Novice stages require XP.
- Advanced and Legendary stages require XP.
- Evolution artwork must visibly communicate progression.
- Each stage should be a separate static artwork asset or asset variant.
- Evolution must not erase the user's previously unlocked stage.
- Exact XP costs and evolution requirements are TBD.

14.6 Avatar selection
- User selects one avatar during onboarding.
- User can later change to another unlocked avatar.
- Selecting an avatar does not delete other unlocked avatars.
- Show locked/unlocked states and XP requirement.
- Do not let users equip an avatar stage they have not unlocked.

======================================================================
15. COIN MARKETPLACE & COLLECTION — 30 STATIC ARTWORK ITEMS
======================================================================

15.1 Core decision
The marketplace contains exactly 30 initial collectible artwork items:
- 5 Houses.
- 5 Bikes.
- 5 Cars.
- 5 Libraries.
- 5 Study Rooms.
- 5 Workplaces.

These are static images/artworks.
No 3D scene, no 2.5D environment, no interactive house/kingdom/study room and no environment-building system.

15.2 Approved category style preferences

HOUSES — 5 items
Preferred styles:
- Modern Minimalist.
- Biophilic / Nature-Inspired.
Design direction:
- Bright, airy.
- Natural light.
- Large windows.
- Clean modern architecture.
- Surrounding greenery.
Use the two preferred styles as the design direction across five distinct house artworks.

BIKES — 5 items
Include five broadly popular types:
1. Sport Bike.
2. Naked / Streetfighter.
3. Cruiser.
4. Adventure / Touring.
5. Commuter / Standard.
These are category choices, not personal style preferences.

CARS — 5 items
Preferred categories:
- Luxury Sedan.
- Sports / Supercar.
- Electric / Futuristic.
Distribute five designs across these categories with a balanced selection. Exact model names are not required; use original generic designs and avoid trademark-dependent copying.

LIBRARIES — 5 items
Preferred styles:
- Classic Grand Library.
- Nature-Inspired Library.
- Luxury Modern Library.
Visual direction:
- Classic: dark wood shelves, traditional grand atmosphere.
- Nature: large glass windows, greenery, natural light.
- Luxury Modern: premium furniture, marble, glass, sophisticated finish.

STUDY ROOMS — 5 items
Preferred combined style:
Nature-Inspired + Cozy + Minimalist.
Must include:
- Strong connection to nature.
- Greenery or a natural outdoor view.
- Ample natural daylight.
- Large/open windows and good airflow.
- Cozy atmosphere.
- Minimal furniture and uncluttered layout.
- Natural wood and warm details.
- Comfortable study seating.
Avoid excessive decoration and crowded furniture.

WORKPLACES — 5 items
Preferred style:
Luxury Executive.
Visual direction:
- Premium private office.
- Sophisticated desk and seating.
- Elegant wood, glass and/or marble.
- Refined lighting.
- High-end executive atmosphere.
Use five distinct static artwork variations.

15.3 Marketplace UI
- Collection / Reward Center entry from More.
- Coin balance visible.
- Category navigation for all six categories.
- Product artwork, item name, price and owned/locked state.
- Item details view.
- Purchase confirmation.
- Success state.
- Insufficient Coins state.
- Purchased items appear in Owned / Purchased Collection.
- Purchase history is retained.

15.4 Dashboard shortcut
On both Normal and Challenge dashboards:
- Coin balance is displayed beside Settings.
- Tapping Coin opens Collection directly at the Coin-purchasable marketplace section.
- User can shop without navigating through multiple unrelated screens.

15.5 Purchase logic
1. Load current authoritative Coin balance.
2. Load item price and ownership state.
3. Confirm user intent.
4. Verify item is not already owned.
5. Verify sufficient balance.
6. Atomically deduct Coins and create ownership record/transaction.
7. Update UI only after successful backend confirmation.
8. If failure occurs, do not show success and do not leave a partial deduction.
9. Prevent double tap / duplicate transaction.
10. An already owned item cannot be purchased again unless a future explicit duplicate-item policy is approved.

15.6 Pricing
Exact prices for the 30 items are TBD.
Do not assign arbitrary final prices in this blueprint.
Use a centralized marketplace catalog so prices can be balanced later without changing the purchase architecture.

15.7 Static artwork constraints
- Use original or properly licensed artwork.
- Maintain consistent aspect ratio and quality within each category.
- Optimize file size for Android.
- Provide loading and fallback artwork.
- Artwork is a display asset, not a functional environment.

======================================================================
16. MORE NAVIGATION
======================================================================

More is the Simple Mode Feature Hub.
It contains exactly five cards, in this order:

1. Profile.
2. Deep Work Course.
3. Reward Center / Collection.
4. Quests / Challenges.
5. Take a Journey.

The former Profile bottom-navigation tab is removed and Profile is accessed here.

16.1 Profile
- User identity/profile information.
- Avatar selection and currently equipped avatar.
- Relevant preferences.
- Monthly income/career target may be stored only as reference information if that section is included.
- Changing income/career reference data must not affect Coin earning rate or existing balance.

16.2 Deep Work Course
Card:
Title: Deep Work Course
Subtitle: “Learn, practice, and master Deep Work”
Accent: Cyan Teal #36D9C5.
Card background: #12243B.
Icon container: #153B43.
Icon: book-open.
Icon color: #36D9C5.
Border: #263C57.
Subtle low-opacity teal glow.

On tap:
- Open Deep Work Course page.
- Current implementation should provide a placeholder page structure only.
- Do not invent modules, lessons, quizzes, course progress or completion rewards.
- Do not display fake progress percentages.
- Course content and learning workflow will be specified separately.

16.3 Reward Center / Collection
- Access wallet.
- Access achievements.
- Access avatar unlock/evolution.
- Access marketplace and owned items.
- Dashboard coin shortcut opens the Coin Purchase section directly.

16.4 Quests / Challenges
- Provide access to short-term Quests and the Challenge/Journey entry point.
- Do not confuse daily/weekly/monthly/yearly Quests with long-term Challenges.

16.5 Take a Journey
- Opens the shared Challenge Setup workflow.
- Same flow as onboarding Challenge acceptance.
- Do not create a second Challenge creation architecture.

======================================================================
17. FIREBASE DATA ARCHITECTURE
======================================================================

17.1 General rules
- Use Firebase Authentication for identity.
- Use Cloud Firestore (or another explicitly selected Firebase database service) for structured app records.
- Use Firebase Storage for artwork only if needed.
- Use trusted backend logic (e.g. Cloud Functions) for wallet and purchase transactions where required.
- Keep schema simple, user-scoped and maintainable.
- Do not assume or migrate an existing schema; this is a new app.
- Use server timestamps for authoritative transaction times.
- Use stable document IDs and idempotency keys.

17.2 Suggested logical structure

users/{uid}
  profile
  preferences
  onboarding
  wallet
  walletTransactions/{transactionId}
  xp
  avatarInventory/{avatarStageId}
  achievements/{achievementId}
  marketplaceInventory/{itemId}
  purchaseHistory/{purchaseId}
  normalSessions/{sessionId}
  normalDailyStats/{dateKey}
  challenges/{challengeId}
      milestones/{milestoneId}
      sessions/{sessionId}
      dailyProgress/{dateKey}
      analyticsSnapshots/{snapshotId}
  quests/{questId}
  questHistory/{questHistoryId}
  courseProgress/{courseId}
  streakSummary
  settings

This is a logical starting structure, not a mandate to place every field in a separate document. Choose the simplest Firestore model that supports secure queries and atomic operations.

17.3 Session record minimum fields
- sessionId.
- uid.
- mode: normal or challenge.
- challengeId: required for challenge sessions; null for normal sessions.
- title/taskName.
- startAt (optional if unavailable).
- endAt (optional if unavailable).
- durationSeconds.
- focusScore (nullable).
- energyScore (nullable).
- satisfactionScore (nullable).
- distractions (structured list or text).
- focusAmplifiers (structured list or text).
- energyBoosters (structured list or text).
- createdAt.
- updatedAt.
- validity/status.
- rewardProcessed / idempotency reference.

17.4 Challenge record minimum fields
- challengeId.
- uid.
- name/title.
- durationDays.
- startAt.
- plannedEndAt.
- actualEndAt (nullable).
- policy.
- goal.
- dailyTargets.
- milestones.
- planVersion.
- approvedAt.
- activatedAt.
- status (draft/pendingApproval/active/completed/cancelled/paused as supported).
- themeId.
- createdAt.
- updatedAt.

17.5 Wallet and transaction integrity
- Store current balance for efficient display.
- Store an immutable transaction ledger.
- Use atomic transaction/batched write or trusted backend logic.
- Every transaction must be idempotent.
- A purchase must atomically deduct balance and create ownership record.
- A session reward must be awarded once only.
- Client cannot directly set arbitrary Coin balance or XP balance.

17.6 Security rules
- Require Firebase Authentication.
- User may read/write only their own user-owned records, subject to protected fields.
- Protect wallet balance, XP and purchase ownership from arbitrary client writes.
- Validate UID ownership and document relationships.
- Challenge sessions must reference a Challenge belonging to the same UID.
- Prevent cross-user reads and writes.
- Public static catalog data may be readable by authenticated users.
- Never ship service-account private keys in the Android app.
- Test security rules with at least two different accounts.

17.7 Data consistency
- Keep Normal and Challenge session queries separate.
- Use explicit mode and challengeId fields.
- Do not duplicate the same session record into both collections.
- If using a shared sessions collection, enforce equivalent query filters and security checks.
- Preserve historical data.
- Do not silently replace old data with computed summaries.

======================================================================
18. SETTINGS, NOTIFICATIONS & ACCOUNT PREFERENCES
======================================================================

18.1 Settings entry
Settings is accessible from both Normal and Challenge dashboards.
The coin shortcut sits beside Settings; it is a separate action.

18.2 Preferences
At minimum support:
- Language: English / Bengali, when language switching is implemented.
- Week start day.
- Daily Normal Mode target.
- Notification preferences.
- Theme preference only if global theme customization is approved.
- Account/session sign-out.

18.3 Notifications
Potential notification categories:
- Session/timer reminders.
- Quest progress/expiry.
- Challenge milestone reminders.
- Challenge day reminders.
- Achievement/reward notifications.

Exact schedules, quiet hours, permission prompts and notification copy are TBD.
Never send notifications without appropriate Android permission.
Do not create excessive or spam-like notifications.

18.4 Backup/export/restore
A future backup/export/restore feature may be added, but exact formats and user flow are TBD.
Do not claim data is backed up externally unless the actual implementation confirms it.
Firebase persistence is not a substitute for a user-facing export feature.

======================================================================
19. LOADING, EMPTY, ERROR & EDGE STATES
======================================================================

Every major screen must define:
- Initial loading.
- Refresh/loading.
- Empty state.
- Error state.
- Retry behavior.
- Offline or network-unavailable behavior where applicable.
- Permission-denied state.
- Partial-data state.

Examples:
- No sessions yet: invite the user to start a session; do not show zero-valued fabricated charts.
- No active Challenge: show a Take a Journey entry point.
- No marketplace ownership: show empty Owned Collection state.
- Insufficient Coins: show shortfall and return to marketplace.
- No analytics sample: explain that more sessions are needed.
- Authentication failure: show retry.
- Purchase failure: preserve wallet and ownership state.
- AI plan generation failure: preserve setup inputs and allow retry/manual review.
- No network: never claim that a server-side transaction succeeded.

======================================================================
20. QUALITY ASSURANCE & ACCEPTANCE TESTS
======================================================================

20.1 Authentication
- First launch opens Google Sign-In.
- Onboarding cannot open before successful authentication.
- Returning user with completed onboarding goes to correct dashboard.
- Sign-out returns to sign-in.
- Authentication cancellation and failure are handled.

20.2 Onboarding
- Exactly three pages.
- Page 3 has no pagination dots.
- Sequential Page 3 questions.
- Three free avatars are available.
- Challenge duration carousel begins at 21 with 14 left and 30 right.
- Carousel is finite; no wrap-around.
- Accept does not activate Challenge before setup approval.
- Skip enters Normal Mode.
- State persists if app closes mid-onboarding.
- No text clipping in English or Bengali.

20.3 Normal Mode
- Home shows actual records.
- Coin beside Settings is visible and tappable.
- Coin tap opens Coin marketplace section directly.
- Calendar shows only Normal Mode logs.
- Timer pause excludes paused duration.
- Session save does not duplicate records or rewards.
- Analytics uses only Normal Mode sessions.

20.4 Challenge Mode
- Onboarding and Take a Journey use the same setup module.
- AI plan cannot activate without user approval.
- Milestone Title and Target Day are required; Description optional.
- Challenge Dashboard uses correct duration theme.
- Challenge Calendar shows only logs for the selected Challenge.
- Challenge Analytics excludes Normal Mode sessions.
- Historical records are preserved.
- Coin shortcut works exactly as in Normal Mode.

20.5 Rewards and purchases
- 640 Coins per valid hour exactly.
- Fractional precision does not create hourly drift.
- Income/career changes do not alter wallet.
- Duplicate session processing does not duplicate Coins.
- Duplicate purchase cannot deduct Coins twice.
- Insufficient balance blocks purchase.
- Purchase and balance update are atomic.
- Purchased item appears in Owned Collection.
- Coin and XP remain separate.

20.6 Avatar
- 14 categories exist.
- Three onboarding avatars are free at Novice.
- Other Novice stages require XP.
- Advanced and Legendary require XP.
- Locked stages cannot be equipped.
- XP cannot be spent twice due to duplicate requests.

20.7 UI and accessibility
- No clipping, truncation or ellipses on important text.
- Test small and large Android screens.
- Test increased font scaling.
- Test English and Bengali.
- Test system safe areas.
- Buttons remain reachable above bottom navigation.
- Animation does not cause jank or block reading.
- Contrast and touch targets are usable.

20.8 Firebase/security
- User A cannot read or write User B's private data.
- Client cannot arbitrarily increase wallet or XP.
- Purchase transaction is atomic.
- Challenge session must belong to the same user and Challenge.
- Historical records remain available.
- No secrets/service-account key embedded in app.

======================================================================
21. IMPLEMENTATION PHASES
======================================================================

Recommended build sequence:

PHASE 1 — Foundation
- Create new Android project.
- Configure Firebase project and Android app.
- Configure Google Sign-In and Firebase Authentication.
- Establish UID-based Firestore structure and security rules.
- Build global design tokens and localization foundation.

PHASE 2 — Authentication and Onboarding
- Google Sign-In screen.
- Authentication state routing.
- Three onboarding pages.
- Persistent sequential setup.
- Avatar selection.
- Challenge duration selection and skip/accept.

PHASE 3 — Normal Mode Core
- Home Dashboard.
- Header coin shortcut and Settings.
- Focus timer.
- Normal session logging.
- Normal Calendar.
- Normal Analytics.

PHASE 4 — Challenge Engine
- Shared Challenge Setup.
- Policy, goal, daily targets and milestones.
- AI plan generation and approval.
- Activation.
- Challenge Dashboard.
- Theme resolver.
- Challenge Calendar and Challenge Analytics.
- Completion/history foundations.

PHASE 5 — Rewards
- Coin calculation and wallet ledger.
- XP ledger/progression.
- Achievement catalog.
- Avatar catalog and evolution.
- Reward Center / Collection.

PHASE 6 — Marketplace
- 30 static artworks.
- Six category screens.
- Item details and purchase confirmation.
- Atomic purchase logic.
- Owned Collection.
- Dashboard coin shortcut integration.

PHASE 7 — More, Quests & Supporting Screens
- Profile.
- Deep Work Course placeholder.
- Reward Center.
- Quests.
- Take a Journey.
- Settings and notification preferences.

PHASE 8 — QA and Release Preparation
- Functional tests.
- Security tests.
- Small-screen and large-font tests.
- English/Bengali tests.
- Offline/error-state tests.
- Reward calculation verification.
- Android build/signing/release configuration.

======================================================================
22. UNRESOLVED ITEMS — DO NOT INVENT FINAL VALUES
======================================================================

The following require separate explicit decisions before their final behavior is locked:

1. Exact Google Sign-In screen copy, illustration and legal-link destinations.
2. Whether to support any authentication provider besides Google.
3. Exact Normal Mode session edit/delete policy after reward issuance.
4. Exact timer recovery behavior after force-stop/reboot.
5. Exact daily target missed-state copy and visual.
6. Exact global streak qualifying threshold and timezone-change policy.
7. Whether multiple concurrent active Challenges are allowed.
8. Challenge pause rules, cancellation rules and end-date adjustment.
9. Challenge completion report layout and reward thresholds.
10. Exact XP earning formula and XP costs for each avatar/stage.
11. Exact Coin prices for the 30 marketplace items.
12. Exact quest generation, rotation, expiration and reward values.
13. Exact yearly quest period boundary.
14. Exact Deep Work Course modules, lessons, quizzes and rewards.
15. Exact notification schedules and quiet hours.
16. Backup/export/restore formats and flow.
17. Final Firebase database choice and deployed security rules.
18. Any custom Challenge duration minimum/maximum and validation boundary.
19. Whether item duplicates or gifting will ever be supported.
20. Account deletion and data-retention policy.

Until these are approved:
- Keep configuration values centralized.
- Avoid destructive behavior.
- Preserve source records.
- Do not show fake prices, XP, rewards or progress.
- Use clear placeholders where appropriate.
- Do not silently convert a proposal into a confirmed requirement.

======================================================================
23. FINAL DEFINITION OF DONE
======================================================================

The application is considered complete only when:

- Google Sign-In is the first gate before onboarding.
- Exactly three onboarding pages work and Page 3 has no pagination dots.
- Normal Mode tracking, timer, Calendar and Analytics work with real data.
- Challenge Mode uses the same shared setup workflow from onboarding and Take a Journey.
- Challenge Mode has its own data scope, calendar, dashboard, theme and analytics.
- The Home Dashboard and Challenge Dashboard both show Coin balance beside Settings.
- Tapping Coin opens the Coin-purchasable Collection section directly.
- Coin earning uses the exact formula: ValidSeconds * (640 / 3600).
- Coin and XP are separate.
- Avatar catalog includes all 14 approved categories and three free onboarding avatars.
- Avatar evolution has Novice, Advanced and Legendary stages.
- Marketplace includes 30 static artwork items across six approved categories.
- Purchase operations are secure, atomic and non-duplicating.
- More contains the five approved cards in the correct order.
- Deep Work Course remains a placeholder until content is approved.
- No screen clips or truncates important text.
- English and Bengali layouts are usable.
- Firebase data is isolated by authenticated UID.
- No fake data, fake progress or fabricated insights appear in production.
- All unresolved items are explicitly handled as TBD rather than guessed.

END OF FINAL MASTER BLUEPRINT