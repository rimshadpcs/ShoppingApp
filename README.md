# ShoppingApp

**ShoppingApp** is a Kotlin-based Android application designed as a robust example of modern Android development practices. This project emphasizes the use of the MVVM (Model-View-ViewModel) architectural pattern, clean architecture concepts, and strict adherence to SOLID principles. It is ideal for developers who want to learn or demonstrate best practices in scalable, maintainable Android app development.

---

## Purpose

The main goal of this project is to:
- Showcase a clean separation of concerns using MVVM and clean architecture layers (presentation, domain, and data)
- Illustrate the application of SOLID principles throughout the codebase
- Serve as a learning and reference project for building scalable Android applications

---

## Features

- Full MVVM architecture with clear ViewModel, Repository, and UseCase layers
- Clean Architecture: Presentation, Domain, and Data modules
- Strong adherence to SOLID principles for maintainable and testable code
- Modular and testable components
- Dependency injection (DI) ready (e.g., with Hilt/Dagger, if implemented)
- Sample flows for product browsing, cart management, and checkout
- Comprehensive use of Kotlin features and best practices

---

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/rimshadpcs/ShoppingApp.git
   ```

2. **Open in Android Studio**
   - Import the project as an existing Android Studio project.
   - Let Gradle sync and resolve dependencies.

3. **Build and Run**
   - Connect an Android device or start an emulator.
   - Build and install the app.

---

## Technologies & Concepts Used

- **Kotlin**
- **MVVM Architecture**
- **Clean Architecture**
- **SOLID Principles**
- **Android Jetpack Components (LiveData, ViewModel, Navigation, etc.)**
- **Coroutines/Flow**
- **Dependency Injection** (if present, e.g., Hilt or Dagger)
- **Repository and UseCase patterns**
- **Unit & UI Testing (if present)**

---

## Project Structure

```
/presentation    - UI layer (Activities, Fragments, ViewModels)
/domain          - Business logic (UseCases, Entities, Repository interfaces)
/data            - Data sources (API, database, Repository implementations)
```

---

## Author

- [rimshadpcs](https://github.com/rimshadpcs)

---

## License

This project currently does not specify a license. Please contact the repository owner for more information.

---

**Note:** This is a learning and reference project. For more details, review the code and comments throughout the repository.