package com.interview.battleshipgame;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
/**
 * Unit test for simple App.
 */
public class GameInitializerTest
{
  @Test
  public void TestJUnitSetup_SUCCESS()
  {
     assertEquals(1,1);
  }
  
  @Test
  public void initializeInputFile_AbleToReadInputFile_SUCCESS() throws IOException
  {
    GameInitializer gameInitializer = new GameInitializer();
    GameErrorCode code = gameInitializer.initializeInputFile("/home/cabox/workspace/inputToGame.txt");
    assertEquals(code, GameErrorCode.SUCCESS);
  }
  
  @Test
  public void initializeInputFiles_CountOfLinesReadIsValid_SUCCESS() throws IOException
  {
    GameInitializer gameInitializer = new GameInitializer();
    GameErrorCode code = gameInitializer.initializeInputFile("/home/cabox/workspace/inputToGame.txt");
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.isLinesCountValid());
  }
 
  @Test
  public void initializeLines_Line1ValidInput_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    gameInitializer.setInputLines(inputLines);
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeBattleArea());
    
    inputLines.clear();
    inputLines.add("9 Z");
    gameInitializer.setInputLines(inputLines);
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeBattleArea());
  
    inputLines.clear();
    inputLines.add("1 A");
    gameInitializer.setInputLines(inputLines);
  
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeBattleArea());
  }

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line1InValidInputNegativeCoordinate() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("-5 E");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
  }

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line1InValidInputWidthLarge() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("10 E");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
  }

  @Test
  public void initializeLines_Line2ValidInput_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("5");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeNoOfShips());    
  }

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line2ValidInputNegativeRangeShip() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("-1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();    
  }

  @Test
  public void initializeLines_Line3Line4ValidInput_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("1");
    inputLines.add("Q 1 1 A1");
    inputLines.add("P 1 1 C2");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeShips());
  }

  @Test
  public void initializeLines_Line3Line4ValidInput2_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("1");
    inputLines.add("Q 1 1 E5");
    inputLines.add("P 1 1 A1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeShips());
  }

  @Test
  public void initializeLines_Line3Line4ValidMultipleInput2_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("2");
    inputLines.add("Q 1 1 E5 P 2 1 B1");
    inputLines.add("P 1 1 A1 Q 2 1 C1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeShips());
  }

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line3Line4InvalidInputShipLargeHeight() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("1");
    inputLines.add("Q 1 1 F2");
    inputLines.add("P 1 1 C2");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
  }

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line3Line4InvalidInputShipLargeWidth() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("1");
    inputLines.add("Q 1 1 A23");
    inputLines.add("P 1 1 C2");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
  }

  @Test (expected = InvalidInputException.class)
  public void initializeLines_Line3Line4InvalidMultipleInput() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("1");
    inputLines.add("Q 1 1 E5 P 2 1 B1");
    inputLines.add("P 1 1 A1 Q 2 1 C1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
  }

  @Test (expected = InvalidInputException.class)
  public void initializeLines_Line3Line4InvalidMultipleInput_InvalidPosition() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("2");
    inputLines.add("Q 1 1 E5 P 2 2 E1");
    inputLines.add("P 1 1 A1 Q 2 1 C1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
  }

  @Test
  public void initializeLines_Line5ValidInput_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("2");
    inputLines.add("Q 1 1 E5 P 2 2 D1");
    inputLines.add("P 1 1 A1 Q 2 1 C1");
    inputLines.add("A1 B2 C3 D4");
    inputLines.add("C3 B4 D1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
    assertEquals(GameErrorCode.SUCCESS, gameInitializer.initializeMissilePositions());
  }
   

  @Test(expected = InvalidInputException.class)
  public void initializeLines_Line6ValidInput_SUCCESS() throws InvalidInputException
  {
    GameInitializer gameInitializer = new GameInitializer();
    List<String> inputLines = new ArrayList<>();
    inputLines.add("5 E");
    inputLines.add("2");
    inputLines.add("Q 1 1 E5 P 2 2 D1");
    inputLines.add("P 1 1 A1 Q 2 1 C1");
    inputLines.add("21 B2 C3 D4");
    inputLines.add("C3 B4 D1");
    gameInitializer.setInputLines(inputLines);
    gameInitializer.initializeBattleArea();
    gameInitializer.initializeNoOfShips();
    gameInitializer.initializeShips();
    gameInitializer.initializeMissilePositions();
  }
}
