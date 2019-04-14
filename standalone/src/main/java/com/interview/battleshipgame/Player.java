package com.interview.battleshipgame;

import java.util.Queue;
import java.util.Observable;
import java.util.Observer;
import java.util.List;
import java.util.ArrayList;

public class Player extends Observable implements Observer {
  private Integer[][] battleShipStrength = new Integer[9][26];
  private int totalShips;
  private List<CartesianCoordinate> missilePosition = new ArrayList<>();
  private int id;
  private int totalShipStrength;
  private int enemyId;
  private boolean isEnemyMissileExhausted = false;
  
  public Player(int input)
  {
    id = input;  
  }
  
  public void setEnemyId(int id)
  {
    enemyId = id;
  }
  
  public int getEnemyId()
  {
    return enemyId;  
  }
  
  public void setId(int input)
  {
    id = input;
  }
  
  public int getId()
  {
    return id;
  }
  
  public void addObserversToObserve(WarMonitorer wm, Player p)
  {
    addObserver(wm);
    addObserver(p);
  }
  
  
  public void setBattleShipStrength(Integer[][] input, int width, int height)
  {
    for (int i = 0; i < width; ++i)
    {
      for (int j = 0; j < height; ++j)
      {
        battleShipStrength[i][j] = input[i][j];
        totalShipStrength += battleShipStrength[i][j];
      }
    }
  }
  
  public void setMissilePosition(List<CartesianCoordinate> input)
  {
    missilePosition.addAll(input);
  }
  
  public void update(Observable observable, Object eventData)
  {
    EventData localEventData = (EventData) eventData;
    
    if (localEventData.getPlayerId() != getId()) //Not for me then discard
    {
      return;
    }
    
    CartesianCoordinate cd = localEventData.getMissileTargetCoordinate();
    System.out.println("Received event in Player " + localEventData.getPlayerId() + 
                       " category is " + localEventData.getEventCategory());
    if (cd != null)
    {
      System.out.println("missile fired at " + cd.getX() + " " + cd.getY());
      System.out.println("ShipStrength at fired position is " + battleShipStrength[cd.getX()][cd.getY()]);
    }
    
    EventCategory currentEventCategory = localEventData.getEventCategory();
    switch (currentEventCategory)
    {
      case FIRE_MISSILE:
      case TARGET_HIT:
        fireNextMissile();
        break;
      case MISSILE_FIRED:
        checkTarget(localEventData);
        break;
      case TARGET_MISSED:
        checkIfEnemyStillHasMissilesAndFire();
        break;
      case SHIPS_EXHAUSTED:
        fireEventForCategory(EventCategory.PLAYER_WON, null, 0);
        break;
      case MISSILES_EXHAUSTED:
        checkAndFireMissile();
        break;
      default:
        break;
    }
  }
  
  private void checkIfEnemyStillHasMissilesAndFire()
  {
    if (isEnemyMissileExhausted && missilePosition.size() == 0)
    {
      fireEventForCategory(EventCategory.WAR_DRAW, null, 0);
    }
    else if (isEnemyMissileExhausted)
    {
      fireNextMissile();
    }
    else
    {
      fireEventForCategory(EventCategory.FIRE_MISSILE, null, enemyId);
    }
  }
  
  private void checkTarget(EventData localEventData)
  {
    CartesianCoordinate coordinate = localEventData.getMissileTargetCoordinate();
    int targetShipStrength = battleShipStrength[coordinate.getX()][coordinate.getY()];
    if (targetShipStrength == 0)
    {
      fireEventForCategory(EventCategory.TARGET_MISSED, null, enemyId);
    }
    else
    {
      totalShipStrength--;
      battleShipStrength[coordinate.getX()][coordinate.getY()]--;
      
      if (totalShipStrength == 0)
      {
        fireEventForCategory(EventCategory.SHIPS_EXHAUSTED, null, enemyId);
      }
      else
      {
        fireEventForCategory(EventCategory.TARGET_HIT, null, enemyId);
      }
    }
  }
  
  private void fireEventForCategory(EventCategory cat, CartesianCoordinate pos, int playerIdToWhomThisEventIsTargetted)
  {
      EventData ed = new EventData();
      ed.setEventCategory(cat);
      if (pos != null)
      {
        ed.setMissileTargetCoordinate(pos);
      }
      ed.setPlayerId(playerIdToWhomThisEventIsTargetted);
      setChanged();
      notifyObservers(ed);
  }
  
  private void checkAndFireMissile()
  {
    isEnemyMissileExhausted = true;
    if (missilePosition.size() == 0)
    {
      fireEventForCategory(EventCategory.WAR_DRAW, null, 0);
    }
    else
    {
      fireNextMissile();
    }
  }
  
  private void fireNextMissile()
  {
    if (missilePosition.size() == 0)
    {
      fireEventForCategory(EventCategory.MISSILES_EXHAUSTED, null, enemyId);
    }
    else
    {
      CartesianCoordinate position = missilePosition.get(0);
      missilePosition.remove(0);
      fireEventForCategory(EventCategory.MISSILE_FIRED, position, enemyId);
    }
  }
}