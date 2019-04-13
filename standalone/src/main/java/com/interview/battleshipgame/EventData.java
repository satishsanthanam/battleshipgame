package com.interview.battleshipgame;

public class EventData
{
  private EventCategory eventCategory;
  private CartesianCoordinate missileTargetCoordinate;
  private int playerId;
  
  public int getPlayerId()
  {
    return playerId;
  }
  
  public void setPlayerId(int input)
  {
    playerId = input;
  }
  
  public EventCategory getEventCategory()
  {
    return eventCategory;
  }
  
  public void setEventCategory(EventCategory input)
  {
    eventCategory = input;
  }
  
  public CartesianCoordinate getMissileTargetCoordinate()
  {
    return missileTargetCoordinate;
  }
  
  public void setMissileTargetCoordinate(CartesianCoordinate input)
  {
    missileTargetCoordinate = input;
  }  
}