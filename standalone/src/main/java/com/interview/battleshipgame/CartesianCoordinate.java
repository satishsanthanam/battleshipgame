package com.interview.battleshipgame;

public class CartesianCoordinate {
  private int x;
  private int y;
  
  public CartesianCoordinate(int inputX, int inputY)
  {
    x = inputX;
    y = inputY;
  }
  
  public int getX()
  {
    return x;
  }
  
  public int getY()
  {
    return y;
  }
  
  public void setX(int inputX)
  {
    x = inputX;
  }
  
  public void setY(int inputY)
  {
    y = inputY;
  }
  
  public String toString()
  {
    return Integer.toString(x) + Integer.toString(y);
  }
}