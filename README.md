# urcast Android Client README

> A centralized, community-driven local weather monitoring system. This repository contains the client-side **Kotlin Android application** built with **Jetpack Compose** and designed with consideration for **Universal Design (UD)** and accessibility standards.

---

## About the Client Application

The **urcast Android Client** serves two primary user types:

1. **Viewers:** Local residents who want simple, quick, and accurate local weather information for their geographic location.
2. **Contributors:** Community members who register and monitor Raspberry Pi weather nodes to contribute environmental data to the system.

The app interfaces with the central FastAPI backend via RESTful APIs, retrieving weather measurements (temperature, humidity, pressure, and light), displaying registered nodes, and allowing contributors to manage their weather stations.

---

## Key Features & Functional Requirements

### For Viewers

* **Weather Dashboard:** View temperature, humidity, atmospheric pressure, and light levels from contributor nodes.
* **Interactive Map:** Explore registered weather stations using an interactive map.
* **Node Selection:** Select different weather stations to view their associated weather measurements.
* **Historical Trends:** View 24-hour and 7-day environmental trends via interactive graphs *(Further integration required)*.
* **Short-Term Predictions:** Local forecasts based on weather measurements *(Planned feature)*.
* **Custom Notifications & Alerts:** Configure threshold alerts and update reminders *(Could Have feature)*.

### For Contributors

* **Account Management:** Create contributor accounts and log in using token-based authentication (JWT).
* **Node Registration:** Register new Raspberry Pi weather stations by providing a node name, location, latitude, and longitude.
* **Node Monitoring:** View registered weather stations and their associated environmental readings.

---

## Universal Design (UD) & Accessibility

The app is designed to support diverse digital literacy levels, screen sizes, and accessibility needs:

* **Perceptible Information:** Clear layouts, familiar icons, and distinct text labels. Weather conditions do not rely solely on colour to communicate information.
* **Equitable & Intuitive Use:** Consistent iconography and minimal navigation steps to reach essential data.
* **High Contrast & Typography:** Consideration for readable text, colour contrast, and accessible layouts.
* **Tolerance for Error:** Input validation and inline error messages for contributor registration and node registration.

---

## Technology Stack

* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Declarative UI)
* **API Integration:** Retrofit
* **HTTP Client:** OkHttp
* **JSON Conversion:** Gson
* **Authentication:** JWT (JSON Web Tokens)
* **Backend:** FastAPI (REST API)

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

* **Pijus (PM)** – Back-End, Version Control
* **Raivis** – Database, Back-End
* **Anastasija** – Front-End, UI/UX/UD, Database
* **Leonas** – Hardware, Front-End
* **Kornel** – UI/UX/UD, Testing, Hardware
