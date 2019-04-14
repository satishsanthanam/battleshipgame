# battleshipgame

# Design
This is designed using EventStorming technique.  Please check https://miro.com/welcomeonboard/whHUI8dqkWMkRVKDVZNZcjFikIxQtXLvkNLO9mWEp6HkJVJCWp68Y1aXHamdpYjM for Events, Commands & Aggregates design

| Event	| Who can generate	| Who can listen |
|-----------|-------------------|----------------|
| Fire Missile |	WarMonitorer/Player |	Player |
|Missiles Exhausted |	Player |	Player | 
|Ships Exhausted |	Player |	Player|
|Missile Fired |	Player |	Player|
|Target Hit |	Player | Player|
|Target Miss |	Player |	Player|
|War Drawn |	Player |	WarMonitorer|
|Player WON |	Player |	WarMonitorer|

# Scope for improvements
*   Expose GameInitializer class via interface so that it can be called via UI like Swing.
*   Add logging ability using log4j.

# input file format
5 E
2
Q 1 1 A1 P 2 1 D4
Q 1 1 B2 P 2 1 C3
A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5

*   First line: Battle Area size which is same for both players
*   Second line: number of ships
*   Third line: Ship coordinates along with strength for PLAYER 1
*   Fourth line: Ship coordinates along with strength for PLAYER 2
*   Fifth line: Player 1 missile coordinates targetting Player 2 positions
*   Sixth line: Player 2 missile coordinates targetting Player 1 positions

# How to build
*   Go to standalone folder
*   mvn install
*   The above command will run all unit tests also

# How to run the program?
java -cp standalone/target/standalone-1.0-SNAPSHOT.jar com.interview.battleshipgame.GameInitializer <pathToInputFile>
ex.,
java -cp standalone/target/standalone-1.0-SNAPSHOT.jar com.interview.battleshipgame.GameInitializer standalone/inputFileForGame.txt 

