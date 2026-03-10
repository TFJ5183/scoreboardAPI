# TFJ's Scoreboard API: A Scoreboard Management API for Kotlin Paper Plugins

[![](https://jitpack.io/v/TFJ2021/tfjs-scoreboardAPI.svg)](https://jitpack.io/#TFJ2021/tfjs-scoreboardAPI)

## Introduction

Welcome to the world of Paper plugin development with `tfjs-scoreboardAPI`! This advanced scoreboard management API
allows you to create, manage, and customize scoreboards in your Minecraft servers with ease. Whether you're building a
simple trivia game or an intricate team-based RPG, this library has got you covered.

## Key Features

- **Dynamic Content**: Supports both static and dynamic line entries for flexible scoreboard updates.
- **Mini-message Support**: Utilizes Mini-message syntax to enhance the appearance of your scoreboards
- **Extensibility**: Easily extend and customize existing line types or create new ones.

## Getting Started

### Installation

1. **Add JitPack as repository**

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. **Add this api as compileOnly dependency**

```
dependencies {
     implementation("com.github.TFJ2021:tfjs-scoreboardAPI:-SNAPSHOT")
}
```

> For more details visit [JitPack Docs](https://jitpack.io/#TFJ2021/tfjs-scoreboardAPI)

## Usage

### Creating a Scoreboard

To create a scoreboard, you need to define a custom `ScoreboardData` class that extends
`net.tfj.scoreboardAPI.ScoreboardData`. This class should specify the lines and their behavior.

```kotlin
class ExampleScoreboard : ScoreboardData() {
    override fun getLines(player: Player): List<LineBaseEntry> {
        return listOf(
            StaticLineEntry("Welcome to My Server"),
            DataLineEntry("Player count: {online}/{max}"),
            EmptyLine(),
            AnimatedLineEntry("Time remaining: {time}", tickInterval = 20)
        )
    }
}
```

### Customizing Line Entries

The library provides several types of line entries, including:

- **StaticLineEntry**: Displays a static text that doesn't change.
- **DataLineEntry**: Updates its content based on data, such as player count or time.
- **AnimatedLineEntry**: Animates the line entry over time.
- **EmptyLine**: Adds an empty line for spacing.

### Handling Player Joins

To ensure a fresh scoreboard for each player upon joining, reset or set a scoreboard on player on

```kotlin
fun join(event: PlayerJoinEvent) {
    scoreboardAPI.resetScoreboard(event.player)
    // or
    scoreboardAPI.setScoreboard(ExampleScoreboard)
}
```

## Contributing

Contributions to this project are welcome! If you find any issues or have ideas for new features, please open an issue
on the [GitHub repository](https://github.com/your-repo/tfjs-scoreboardAPI).

## License

This project is licensed under the [MIT](https://choosealicense.com/licenses/mit/) License. See the [LICENSE](LICENSE)
file for details.
