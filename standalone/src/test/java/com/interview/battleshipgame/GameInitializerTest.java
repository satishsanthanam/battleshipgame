package com.interview.battleshipgame;

import org.junit.Test;
import java.io.IOException;

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
  public void initializeLines_Line1ValidInput_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeLines_Line2ValidInput_SUCCESS()
  {
    
  }

  @Test
  public void initializeLines_Line3ValidInput_SUCCESS()
  {
    
  }

  @Test
  public void initializeLines_Line4ValidInput_SUCCESS()
  {
    
  }

  @Test
  public void initializeLines_Line5ValidInput_SUCCESS()
  {
    
  }

  @Test
  public void initializeLines_Line6ValidInput_SUCCESS()
  {
    
  }

  @Test
  public void initializeArea_WidthOfBattleAreaWithinRange_SUCCESS()
  {
    
  }

  @Test
  public void initializeArea_HeightOfBattleAreaWithinRange_SUCCESS()
  {
    
  }

  @Test
  public void initializeBattleshipCount_countWithinRange_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_TypeValid_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_HeightWithinRange_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_WidthWithinRange_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_XCordinateWithinRange_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_YCordinateWithinRange_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeShipLocation_PerfectlyAlignedWithNoOverlapping_SUCCESS()
  {
    
  }
  
  @Test
  public void initializeTargetLocation_WithinEnemyBattleFieldArea_SUCCESS()
  {
    
  }
}
