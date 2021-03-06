package com.interview.battleshipgame;

import java.util.Observable;
import java.util.Observer;

public class WarMonitorer extends Observable implements Observer {
  private EventData eventData;
  private Player activePlayer;
  private Player dormantPlayer;
  private String finalResult = null;
  
  public WarMonitorer(Player actPlayerIn, Player dorPlayerIn)
  {
    eventData = new EventData();
    activePlayer = actPlayerIn;
    dormantPlayer = dorPlayerIn;
    addObserver(activePlayer);
    addObserver(dormantPlayer);
  }
  
  public String getFinalResult()
  {
    return finalResult;  
  }
  
  public void setActivePlayer(Player player)
  {
    activePlayer = player;
  }
  
  public void setDormantPlayer(Player player)
  {
    dormantPlayer = player;
  }
  
  public void fireMissile()
  {
    eventData.setEventCategory(EventCategory.FIRE_MISSILE);
    eventData.setPlayerId(activePlayer.getId());
    setChanged();
    notifyObservers(eventData);
  }
  
  public void update(Observable observable, Object eventData)
  {
    EventData localEventData = (EventData) eventData;
    if (localEventData.getPlayerId() != 0)
    {
      return;
    }
   
    System.out.println("Received event in WarMonitorer");
    EventCategory currentEventCategory = localEventData.getEventCategory();
    switch (currentEventCategory)
    {
      case WAR_DRAW:
        finalResult = new String("WAR is OVER and Result is DRAW");
        break;
      case PLAYER_WON:
        Player p = (Player)observable;
        finalResult = new String("WAR is OVER and Result is Player with Id " + p.getId() + " WON");
        break;
    }
    System.out.println("***FINAL RESULT***" + finalResult);
  }
}