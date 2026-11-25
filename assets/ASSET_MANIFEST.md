# Static Artwork Asset Manifest

This is the asset plan for the initial catalogue. It is a checklist and art-direction guide, not a claim that image files already exist.

## General visual requirements

- Original or properly licensed artwork only.
- Consistent crop, aspect ratio, lighting and visual quality within each category.
- High-quality, realistic visual direction; no cartoon look unless a future explicit decision changes it.
- Static artwork only. Do not build 3D or 2.5D interactive environments.
- Optimize image file sizes for Android; provide a fallback image.
- Use stable asset IDs and a centralized catalogue mapping ID → title → category → image path → price (price remains TBD).

## Marketplace: 30 items

### Houses (5)
- house_01 — Modern Minimalist House
- house_02 — Biophilic Glass House
- house_03 — Minimalist Courtyard House
- house_04 — Nature-Integrated Modern Villa
- house_05 — Bright Minimalist Family Home

### Bikes (5)
- bike_01 — Sport Bike
- bike_02 — Naked / Streetfighter
- bike_03 — Cruiser
- bike_04 — Adventure / Touring
- bike_05 — Commuter / Standard

### Cars (5)
- car_01 — Luxury Sedan
- car_02 — Executive Luxury Sedan
- car_03 — Sports Coupe
- car_04 — Supercar
- car_05 — Electric Futuristic Car

Use generic original designs; do not copy protected brand logos or signature model designs.

### Libraries (5)
- library_01 — Classic Grand Library
- library_02 — Nature-Inspired Glass Library
- library_03 — Luxury Modern Library
- library_04 — Classic Dark-Wood Reading Hall
- library_05 — Contemporary Luxury Library

### Study Rooms (5)
- study_01 — Nature Minimal Study
- study_02 — Cozy Garden-View Study
- study_03 — Warm Wood & Daylight Study
- study_04 — Minimal Greenhouse-Inspired Study
- study_05 — Airy Cozy Academic Study

### Workplaces (5)
- workplace_01 — Luxury Executive Office
- workplace_02 — Modern Private Executive Suite
- workplace_03 — Premium Wood & Glass Office
- workplace_04 — Marble Executive Office
- workplace_05 — Warm Luxury Corner Office

## Avatars

Approved categories:
Cat, Scientist, Student, Knight, King, Tree, Fire, Futuristic Hero, Gladiator, Stoic Wiseman, Robot, Octopus, Ninja, Astronaut.

Free onboarding avatars:
- Ordinary Boy (Novice)
- Ordinary Girl (Novice)
- Mysterious Hooded Human (Novice; face hidden)

Each avatar has Novice, Advanced and Legendary artwork variants. Exact asset count and file naming can be finalized during asset production; XP costs remain TBD.

## Suggested directory structure

```
assets/
  avatars/
    ordinary_boy/novice.webp
    ordinary_girl/novice.webp
    mysterious_hooded_human/novice.webp
    <avatar_id>/{novice,advanced,legendary}.webp
  marketplace/
    houses/
    bikes/
    cars/
    libraries/
    study_rooms/
    workplaces/
  onboarding/
  icons/
  fallbacks/
```

Do not create placeholder art and present it as final art. If artwork is missing, show a deliberate neutral fallback and keep the item clearly marked as artwork pending.
