package com.interview.battleshipgame;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BattleFieldInitializerTest {

  @Test
  public void testCoordinateConversion_SUCCESS() throws InvalidInputException
  {
    BattleFieldInitializer bfi = new BattleFieldInitializer();
    bfi.setWidth(5);
    bfi.setHeight(5);
    CartesianCoordinate cd = bfi.ConvertThoughtworksCoordinateToCartCoordinate("A1");
    assertEquals("00", cd.toString());
    cd = bfi.ConvertThoughtworksCoordinateToCartCoordinate("A1");
    assertEquals("00", cd.toString());
    cd = bfi.ConvertThoughtworksCoordinateToCartCoordinate("D1");
    assertEquals("30", cd.toString());
    cd = bfi.ConvertThoughtworksCoordinateToCartCoordinate("A3");
    assertEquals("02", cd.toString());
    cd = bfi.ConvertThoughtworksCoordinateToCartCoordinate("C2");
    assertEquals("21", cd.toString());
  }
  
  @Test
  public void testShipStrength_SUCCESS() throws InvalidInputException
  {
    BattleFieldInitializer bfi = new BattleFieldInitializer();
    
    String[] shipDetails = {"Q", "1", "1", "A1"};
    Integer[][] battleShipStrength = bfi.initializeBattleField(5, 5, 1, shipDetails);
    assertEquals("2000000000000000000000000", formString2DIntegerArray(battleShipStrength, 5, 5));
    shipDetails = new String[]{"Q", "1", "1", "C1"};
    battleShipStrength = bfi.initializeBattleField(5, 5, 1, shipDetails);
    assertEquals("0000000000200000000000000", formString2DIntegerArray(battleShipStrength, 5, 5));
    shipDetails = new String[]{"Q", "2", "1", "C1"};
    battleShipStrength = bfi.initializeBattleField(5, 5, 1, shipDetails);
    assertEquals("0000000000220000000000000", formString2DIntegerArray(battleShipStrength, 5, 5));
    shipDetails = new String[]{"Q", "1", "2", "D4"};
    battleShipStrength = bfi.initializeBattleField(5, 5, 1, shipDetails);
    assertEquals("0000000000000000002000020", formString2DIntegerArray(battleShipStrength, 5, 5));
    shipDetails = new String[]{"Q", "1", "2", "D4", "P", "2", "1", "B2"};
    battleShipStrength = bfi.initializeBattleField(5, 5, 2, shipDetails);
    assertEquals("0000001100000000002000020", formString2DIntegerArray(battleShipStrength, 5, 5));
    shipDetails = new String[]{"Q", "1", "3", "C4", "P", "3", "1", "B2"};
    battleShipStrength = bfi.initializeBattleField(5, 5, 2, shipDetails);
    assertEquals("0000001110000200002000020", formString2DIntegerArray(battleShipStrength, 5, 5));

  }
  
  private String formString2DIntegerArray(Integer[][] strength, int width, int height)
  {
    StringBuffer result = new StringBuffer();
    for (int x = 0; x < height; ++x)
    {
      for (int y = 0; y < width; ++y)
      {
        result.append(strength[x][y]);
      }
    }
    return result.toString();
  }
}