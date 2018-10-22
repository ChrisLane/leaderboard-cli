# Leaderboard CLI
Leaderboard CLI is a command-line interface for tracking people's scores.

## Building
To create a Jar containing the program, run the command for your system:
- Linux/Mac: `./gradlew assemble`
- Windows: `gradlew.bat assemble`

The Jar file can be found in `./build/libs/`.

## Testing
To run the included tests run `./gradlew test` or `gradlew.bat test`

## Using the program
Run the program Jar using `java -jar {jar-name}`.\
Alternatively, build and run using `./gradlew run` or `gradlew.bat run`.

To set a person's score:\
`score <name> <score>`

To the global high score or for a person:\
`highest-alltime-score [name]`

To show the current leaderboard or leaderboard change after a play:\
`board [play-number]`

