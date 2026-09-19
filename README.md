# 🗺️ RouteLens

**RouteLens** is an offline-first Android public transit and global navigation application. Designed with a modern Google Maps-inspired UI, RouteLens provides global origin-destination routing, multi-modal transport selection, preset world routes, and real-time turn-by-turn trip monitoring using offline pathfinding algorithms and location services.

---

## ✨ Features

- **Google Maps-Style UI**: Floating input cards, transport mode selectors (Drive, Transit, Walk), and persistent bottom sheet navigation controls.
- **Offline Route Calculation**: Built-in `OfflineRouteEngine` using Haversine-based algorithms for zero-network distance and route estimation.
- **Global Origin-Destination Routing**: Flexible location input supporting arbitrary coordinate pairs or named global locations.
- **Preset World Routes**: One-tap navigation presets for iconic global routes (e.g., New York to LA, London to Paris, Tokyo to Kyoto).
- **Turn-by-Turn Guidance**: Live `ActiveNavigationScreen` displaying direction instructions, remaining trip distance, and live ETA tracking.
- **Modern Jetpack Compose Architecture**: Declarative UI built fully with Material 3 components and smooth state management.

---

## 🏗️ Architecture & Tech Stack

RouteLens follows **Clean Architecture** and Android's recommended architectural practices (MVVM/Unidirectional Data Flow):

- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose (BOM `2024.10.01`), Material 3
- **Architecture**: MVVM + Clean Architecture, Single Source of Truth
- **Dependency Injection**: Hilt
- **Database**: Room (via KSP) for local persistence
- **Location Services**: Google Play Services Location (`FusedLocationProviderClient`)
- **Asynchronous Work**: Kotlin Coroutines & `StateFlow`

---

## 📁 Project Structure

```text
com.routelens/
├── core/
│   ├── data/          # Route repositories & data sources
│   ├── database/      # Room database entities, DAOs, and DI modules
│   └── location/      # FusedLocationProviderClient trackers & services
└── feature/
    └── navigation/
        └── ui/        # RoutePlannerScreen, ActiveNavigationScreen, NavigationViewModel
