# urcast Android Client README

> A centralized, community-driven local weather monitoring system. This repository contains the client-side **Kotlin Android application** built with **Jetpack Compose** and designed with strict adherence to **Universal Design (UD)** and accessibility standards.

---

## About the Client Application

The **urcast Android Client** serves two primary user types:

1. **Viewers:** Local residents who want simple, quick, and accurate local weather information for their exact geographic location without unnecessary clutter.
2. **Contributors:** Community members who set up, register, and monitor Raspberry Pi weather nodes to contribute environmental data to the system.

The app interfaces directly with the central FastAPI backend via RESTful APIs, providing real-time measurements (temperature, humidity, pressure, and light), historical charts, node diagnostics, and customizable alerts.

---

## Key Features & Functional Requirements

### For Viewers

* **Live Weather Dashboard:** View current temperature, humidity, atmospheric pressure, and light levels from nearby contributor nodes.
* **Interactive Map:** Explore nearby active weather stations using an interactive map.
* **Historical Trends:** View 24-hour and 7-day environmental trends via interactive graphs.
* **Favorites:** Save and pin preferred weather nodes for quick access from the home screen.
* **Short-Term Predictions:** Simple local forecasts calculated from barometric pressure and temperature trends.
* **Custom Notifications & Alerts:** Configure threshold alerts (e.g., temperature dropping below freezing) and update reminders *(Could Have feature)*.

### For Contributors

* **Account Management:** Secure registration and token-based authentication.
* **Node Registration & Configuration:** Link and configure new Raspberry Pi weather stations to a contributor profile.
* **Node Diagnostics & Health:** Monitor node uptime, Wi-Fi connectivity, and individual sensor statuses (DHT22, BMP280, BH1750, GPS).

---

## Universal Design (UD) & Accessibility

The app is built from the ground up to support diverse digital literacy levels, screen sizes, and accessibility needs:

* **Perceptible Information:** Clear layouts, familiar icons, and distinct text labels. Weather conditions never rely solely on color to communicate state.
* **Equitable & Intuitive Use:** Consistent iconography and minimal navigation steps to reach essential data.
* **High Contrast & Typography:** Supports high-contrast color palettes (supporting both light and dark themes) and dynamic text sizing.
* **Tolerance for Error:** Clear validation messages, inline form error feedback, and confirmation prompts for critical actions.

---

## Technology Stack

* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Declarative UI)

---

## Getting Started

### Prerequisites

* **Android Studio:** Hedgehog or newer recommended.
* **JDK:** Version 17 or higher.
* **Android SDK:** API level 24 (Nougat) minimum, targeting API level 34+.

### Installation & Setup

1. Clone the repository:
```bash
git clone https://github.com/urcast/urcast-client.git

```


2. Open the project in **Android Studio**.
3. Sync Gradle dependencies.
4. Build and run the app on an Android emulator or physical device.

---

## Team & Roles

* **Anastasija** – Front-End, UI/UX/UD, Database
* **Kornel** – UI/UX/UD, Testing, Hardware
* **Leonas** – Hardware, Front-End
* **Pijus** – Back-End/API, Version Control
* **Raivis** – Database, Back-end
