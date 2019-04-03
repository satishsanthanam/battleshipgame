package com.interview.battleshipgame;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GameInitializer
{
  Player p1 = new Player();
  Player p2 = new Player();
  WarMonitorer wm = new WarMonitorer();
  
  public static void main(String[] args) throws IOException
  {
    GameInitializer gi = new GameInitializer();
    gi.initializeInputFile(args[1]);
  //  gi.validateInput();
    gi.initializePlayers();
    
  }
  
  public void initializePlayers()
  {
    String[] missilesOfPlayer1 = inputLines.get(4).split(" ");
    String[] missilesOfPlayer2 = inputLines.get(5).split(" ");
    
   // p1.initializeMissiles(missilesOfPlayer1);
   // p2.initializeMissiles(missilesOfPlayer2);
  }
  
  private List<String> inputLines = new ArrayList<>();
  
  public GameErrorCode initializeInputFile(String pathToFile) throws IOException
  {
    try (Stream<String> stream = Files.lines(Paths.get(pathToFile))) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			inputLines = stream
          .filter(str -> str.length() > 0)
					.collect(Collectors.toList());

		} catch (IOException e)
    {
      throw e;
    }
    
    return GameErrorCode.SUCCESS;
  }
  
  public GameErrorCode isLinesCountValid()
  {
    return inputLines.size() == 6 ? GameErrorCode.SUCCESS : GameErrorCode.RANGE_OUT_OF_BOUNDS; 
  }
}