# Wave Function Collapse

## File Information
- Current classes:
  - [Main](src/Main.java) - Holds the main code, calling certain methods to execute the program.
  - [Options](src/Options.java) - Options for length and width for the map
  - [Map](src/Map.java) - Holds all the data and some operations to do with displaying the map
  - [Logic](src/Logic.java) - Logic for calculating possible characters
  - [Sweep](src/Sweep.java)* *(not used)*
  - [MenuScreens](src/MenuScreens.java)* *(not used)*

## Procedure
### Start
1. Create the map (use [Options file](src/Options.java) to set)
2. Generate the first tile randomly `calcInitial`
   1. Obtains a random x and y within the range of the map size
   2. Generates a random number from the possible tile values
   3. Assigns that value to the chosen tile
### General (Repeats until fully done)
1. Sweep the map to generate the possible options for the next generation
   1. Check tile per tile for if the current value is 0 (not yet generated)
      1. If it isn't, check the next tile
      2. If it is, assign the curret `worldMapValue` to `worldMapOptions` and continue below
   2. Once a tile that isn't generated is found, check the surroundings:
      1. Check if it's out of bounds (Up, Down, Left & Right)
      2. Compare the current possible values for the tile with the [rules](src/Rules.java)
      3. Remove any current occurrences in the possibilities found in the [rule set](src/Rules.java)
2. Do a second sweep to collect the tile(s) for the next generation
   1. Collect the total number of possibilities (within `worldMapOptions`),
   and only keep those tiles with the lowest possibilities (that are greater than 1 option)
   2. If multiple tiles have been collected once it's swept, choose randomly between them.
3. Generate a random value within the possibilities of `worldMapOptions`
4. Print map in screen (using `showMap()` method)












<br><br><br><br><br><br><br><br><br><br>
Hello!