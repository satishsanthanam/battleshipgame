package com.interview.battleshipgame;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleFieldInitializer {

  private int totalShipStrengh = 0;
  private int width;
  private int height;
  private Integer[][] battleShipStrength = new Integer[9][26];
  private int totalShips;

  public Integer[][] initializeBattleField(int inputWidth, int inputHeight, int inputTotalShips, String[] shipDetails)  throws InvalidInputException
  {
    initBattleShipStrengthAsZero();
    width = inputWidth;
    height = inputHeight;
    totalShips = inputTotalShips;
    populateBattleShipStrength(shipDetails);
    return battleShipStrength;
  }
  
  public List<CartesianCoordinate> initializeMissiles(String[] missilesPosition) throws InvalidInputException
  {
    List<CartesianCoordinate> positions = new ArrayList<>();
    for (String missilePosition: missilesPosition)
    {
      positions.add(ConvertThoughtworksCoordinateToCartCoordinate(missilePosition));
    }
    return positions;
  }
  
  private void initBattleShipStrengthAsZero()
  {
    for (int i = 0; i < 9; ++i)
    {
      Arrays.fill(battleShipStrength[i], 0);
    }
  }
  
  private void populateBattleShipStrength(String[] shipDetails)  throws InvalidInputException
  {
    if (shipDetails.length != totalShips * 4)
    {
      throw new InvalidInputException("ShipDetails not input in proper format");
    }
    for (int i = 0, j = 0; i < totalShips; i++, j+=4)
    {
       populateBattleStrengthForThisShip(shipDetails[j], shipDetails[j+1], shipDetails[j+2], shipDetails[j+3]);
    }
  }
  
  private void populateBattleStrengthForThisShip(String type, String shipWidth, String shipHeight,
                                                String shipStartCoordinate) throws InvalidInputException
  {
    int shipStrength = getShipStrength(type);
    
    int iShipWidth;
    int iShipHeight;
    
    try {
      iShipWidth = Integer.parseInt(shipWidth);
      iShipHeight = Integer.parseInt(shipHeight);
    }
    catch (NumberFormatException e)
    {
      throw new InvalidInputException("ship coordinates invalid" + e.getMessage());
    }
    
    CartesianCoordinate shipCartCoordinate = getShipCoordinate(iShipWidth, iShipHeight, shipStartCoordinate);
    populateBattleStrengthForThisShipForCartCoodinate(shipStrength, iShipWidth, iShipHeight, shipCartCoordinate);
    
  }
  
  private void populateBattleStrengthForThisShipForCartCoodinate(int shipStrength, int iShipWidth, 
                                                                int iShipHeight,
                                                                CartesianCoordinate shipCartCoordinate) throws InvalidInputException
  {
    int startX = shipCartCoordinate.getX();
    int startY = shipCartCoordinate.getY();
    
    for (int i = startX; i < startX + iShipHeight; ++i)
    {
      for (int j = startY; j < startY + iShipWidth; ++j) 
      {
          if (battleShipStrength[i][j] != 0)
          {
            throw new InvalidInputException("Ship coordinates overlapping");
          }
          battleShipStrength[i][j] = shipStrength;
      }
    }
    
    
  }
  
  private CartesianCoordinate getShipCoordinate(int iShipWidth, int iShipHeight, String shipStartCoordinate) throws InvalidInputException
  {
    CartesianCoordinate shipCartCoordinate = ConvertThoughtworksCoordinateToCartCoordinate(shipStartCoordinate);
    if (shipCartCoordinate.getX() + iShipWidth > width ||
          shipCartCoordinate.getY() + iShipHeight > height)
    {
      throw new InvalidInputException("Ship size exceeds battle area" + " ShipWidth " + iShipWidth
                                     + " ShipHeight " + iShipHeight + " X " + shipCartCoordinate.getX()
                                     + " Y " + shipCartCoordinate.getY()
                                     + " Width " + width
                                     + " Height " + height);
    }
    return shipCartCoordinate;
  }
  
  private int getShipStrength(String type) throws InvalidInputException
  {
    if (type == null || !(type.equals("P") || type.equals("Q")))
    {
      throw new InvalidInputException("Type of ship invalid");
    }
    int shipStrength = 1;
    if (type.equals("Q"))
    {
      shipStrength = 2;
    }
    return shipStrength;
  }
  
  private CartesianCoordinate ConvertThoughtworksCoordinateToCartCoordinate(String shipStartCoordinate) 
    throws InvalidInputException
  {
    if (shipStartCoordinate == null || shipStartCoordinate.length() > 3)
    {
      throw new InvalidInputException("coordinate invalid");
    }
    int x = shipStartCoordinate.charAt(0) - 'A';
    int y = 0;
    try {
      y = Integer.parseInt(shipStartCoordinate.substring(1));
      y--;
    }
    catch (NumberFormatException e)
    {
      throw new InvalidInputException("ship coordinate invalid" + e.getMessage());
    }
    
    if (x < 0 || x > height || y < 0 || y > width)
    {
      throw new InvalidInputException("Ship Coordinate invalid " + " X " + x
                                      + " Y " + y + " MaxWidth " + width +
                                     " MaxHeight " + height);
    }
    return new CartesianCoordinate(x, y);
  }
}