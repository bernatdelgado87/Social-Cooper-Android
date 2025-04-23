# KMP-Social-Cooper-Android-iOS

![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF)
![Platforms](https://img.shields.io/badge/Platforms-Android%20%7C%20iOS-lightgrey)
![Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4)

## Description

KMP-Social-Cooper is a cooperative social network application developed with Kotlin Multiplatform. This project allows sharing code between Android and iOS platforms using Kotlin, reducing code duplication and facilitating application maintenance.

## Features

- 🌐 **Multiplatform**: Shared code between Android and iOS
- 🎨 **Compose Multiplatform**: Declarative UI on both platforms
- 📱 **Native Experience**: Integration with platform-specific functionalities
- 🧩 **Clean Architecture**: Clear separation of layers (domain, data, presentation)
- 🔄 **Reactive**: Reactive programming for data flow management

## Project Structure

```
KMP-Social-Cooper-Android-iOS/
├── composeApp/            # Shared UI code (Compose Multiplatform)
│   ├── commonMain/        # Common code for all platforms
│   ├── androidMain/       # Android-specific code
│   └── iosMain/           # iOS-specific code
├── dataShared/            # Shared data layer
├── domainShared/          # Shared domain layer
├── iosApp/                # iOS project (entry point)
├── gradle/                # Gradle configuration
├── build.gradle.kts       # Main build script
└── gradle.properties      # Configuration properties
```

## Requirements

- Android Studio Arctic Fox or higher
- XCode 14 or higher (for iOS development)
- JDK 17 or higher
- Kotlin 1.9.0 or higher

## Environment Setup

### Android

1. Clone the repository:
   ```bash
   git clone https://github.com/bernatdelgado87/KMP-Social-Cooper-Android-iOS.git
   ```

2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the Android configuration

### iOS

1. Make sure XCode is installed
2. After cloning the repository, navigate to the project folder
3. Run `./gradlew podInstall` to install CocoaPods dependencies
4. Open the `iosApp/iosApp.xcworkspace` file in XCode
5. Build and run the application

## Architecture

The project follows Clean Architecture principles with three main layers:

- **domainShared**: Contains business logic, models, and platform-independent use cases
- **dataShared**: Implements shared repositories and data sources
- **composeApp**: Implements UI with Compose Multiplatform and presentation logic

## Technologies

- **Kotlin Multiplatform**: For sharing code across platforms
- **Compose Multiplatform**: For shared UI
- **Ktor**: For network communication
- **Kotlinx.Serialization**: For JSON serialization
- **Kotlinx.Coroutines**: For asynchronous programming
 
## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some amazing feature'`)
5. Push to the branch (`git push origin feature/amazing-feature`)
6. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

Bernat Delgado - [@bernatdelgado87](https://github.com/bernatdelgado87)

Project Link: [https://github.com/bernatdelgado87/KMP-Social-Cooper-Android-iOS](https://github.com/bernatdelgado87/KMP-Social-Cooper-Android-iOS)

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
