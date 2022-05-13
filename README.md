# Battleships

A simple, console-based Java implementation of the game Battleships, developed using Test-Driven Development (TDD).
The game starts with two battleships and one destroyer.

Before starting this project, a class diagram was drawn up to brainstorm the structure of the code and to use as a guide in development.
Comparing the initial and the finished project class diagrams, they are quite different!

Initially, the GameState class was added for the intention of being used to control the game loop, and the UserInterface class to separate this into one class.

In the end, the GameState class was removed in favour of a simpler game loop in the Game class, and the UserInterface class merged into the Game class.

The GameState and UserInterface classes made this simple game more complex and overengineered than it needed to be, something realised when thinking about the unit tests for the Game, GameState and UserInterface classes.
There was also a high level of coupling between these classes.

To further reduce the coupling between classes, the PlayerInterface and ShipInterface were implemented.

## Building
```
git clone https://github.com/schapm/Battleships.git
cd Battleships
mvn package
```

## Running
1) Run the game from the target classes;
```
cd target/classes
java org.schapm.battleships.BattleshipsApplication
```

or

2) Run the game from the JAR;
```
cd target
java -jar Battleships-1.2-SNAPSHOT.jar
```

You can also download a pre-built JAR from [Releases](https://github.com/schapm/Battleships/releases), and run it with;

`java -jar Battleships-1.2-SNAPSHOT.jar`


## Potential Improvements
There are a number of improvements that could be made to the game, such as;
- Improving the styling of the grid, such as using different colours and text styles and making it easier to differenciate between hits, misses and own ships.
- Currently, each guess the opponent makes is completely random - even if the last guess resulted in a hit.
- Currently, the grid size is fixed at 10x10. The tests and code were developed with this potential improvement in mind.

## Class Diagrams
### Initial Diagram
![Initial class diagram](/images/1-initial-class-diagram.jpg)

### Finished Project Diagram
![Finished project class diagram](/images/2-finished-project-class-diagram.jpg)

## Game Screenshots
### Starting The Game
![Starting the game](/images/3-game-start.png)

### Player Guess
![Player guessing](/images/4-player-guess.png)

### Player Wins
![Player wins](/images/5-game-win.png)

## Credits
Class diagrams created with [yUML](https://yuml.me/).

## License
Licensed under the [GNU GPLv3](LICENSE).