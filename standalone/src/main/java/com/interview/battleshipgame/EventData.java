package com.interview.battleshipgame;

public class EventData
{
  public EventCategory eventCategory;
  public String missileTargetCoordinate;
  
  public EventCategory getEventCategory()
  {
    return eventCategory;
  }
  
  public void setEventCategory(EventCategory input)
  {
    eventCategory = input;
  }
  
  public String getMissileTargetCoordinate()
  {
    return missileTargetCoordinate;
  }
  
  public void setMissileTargetCoordinate(String input)
  {
    missileTargetCoordinate = input;
  }  
}