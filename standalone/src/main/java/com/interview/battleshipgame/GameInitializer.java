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
  private Player p1 = new Player(1);
  private Player p2 = new Player(2);
  private WarMonitorer wm = new WarMonitorer(p1, p2);
  private List<String> inputLines = new ArrayList<>();
  private int width;
  private int height;
  private int noOfShips;
  private BattleFieldInitializer bfi = new BattleFieldInitializer();
     
  public void setInputLines(List<String> input)
  {
    inputLines.clear();
    inputLines.addAll(input);
  }
  
  public GameInitializer()
  {
    p1.addObserversToObserve(wm, p2);
    p1.setEnemyId(p2.getId());
    p2.addObserversToObserve(wm, p1);
    p2.setEnemyId(p1.getId());
  }
  
  public static void main(String[] args) throws IOException, InvalidInputException
  {
    GameInitializer gi = new GameInitializer();
    if (args.length != 1)
    {
      throw new InvalidInputException("Pass filename as input argument");
    }
    gi.initializeInputFile(args[0]);
    gi.initializeBattleArea();
    gi.initializeNoOfShips();
    gi.initializeShips();
    gi.initializeMissilePositions();
    gi.begin();
  }
  
  public void begin()
  {
    wm.fireMissile();  
  }
  
  public GameErrorCode initializeBattleArea() throws InvalidInputException
  {
    String[] battleArea = inputLines.get(0).toUpperCase().split(" ");
    try
    {
        width = Integer.parseInt(battleArea[0]);
        height = battleArea[1].charAt(0) - 'A' + 1;
        
        if (width < 1 || width > 9 || height < 1 || height > 26)
        {
          throw new InvalidInputException("ship coordinates invalid");    
        }
    }
    catch (NumberFormatException e)
    {
      throw new InvalidInputException("ship coordinates invalid" + e.getMessage());
    }

    return GameErrorCode.SUCCESS;
  }
  
  public GameErrorCode initializeNoOfShips() throws InvalidInputException
  {
    String[] noOfShipsStr = inputLines.get(1).split(" ");
    try {
      noOfShips = Integer.parseInt(noOfShipsStr[0]);
      
      if (noOfShips < 0 || noOfShips > width * (height + 1))
      {
        throw new InvalidInputException("Count of ships out of range");
      }
    }
    catch (NumberFormatException e)
    {
      throw new InvalidInputException("No of ships input is invalid" + e.getMessage());
    }
    return GameErrorCode.SUCCESS;
  }
  
  public GameErrorCode initializeMissilePositions() throws InvalidInputException
  {
    String[] missilesOfPlayer1 = inputLines.get(4).toUpperCase().split(" ");
    String[] missilesOfPlayer2 = inputLines.get(5).toUpperCase().split(" ");
    
    List<CartesianCoordinate> playerAMissilePosition = bfi.initializeMissiles(missilesOfPlayer1);
    List<CartesianCoordinate> playerBMissilePosition = bfi.initializeMissiles(missilesOfPlayer2);
    
    p1.setMissilePosition(playerAMissilePosition);
    p2.setMissilePosition(playerBMissilePosition);
    
    System.out.println("Printing missile posistion for A");
    System.out.println("Printing missile position for B");
    return GameErrorCode.SUCCESS;
  }
  
  public GameErrorCode initializeShips()  throws InvalidInputException
  {
     String[] playerAShipsDetails = inputLines.get(2).toUpperCase().split(" ");
     String[] playerBShipsDetails = inputLines.get(3).toUpperCase().split(" ");
    
     Integer[][] battleShipStrengthA = bfi.initializeBattleField(width, height, noOfShips, playerAShipsDetails);
     p1.setBattleShipStrength(battleShipStrengthA, width, height);
     
     Integer[][] battleShipStrengthB = bfi.initializeBattleField(width, height, noOfShips, playerBShipsDetails);
     p2.setBattleShipStrength(battleShipStrengthB, width, height);
    
    return GameErrorCode.SUCCESS;
  }
  
  public GameErrorCode initializeInputFile(String pathToFile) throws IOException
  {
    try (Stream<String> stream = Files.lines(Paths.get(pathToFile))) {

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