package com.interview.battleshipgame;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Map;
import java.util.HashMap;

public class Player extends Observable implements Observer {
  private Queue<String>  missiles = new LinkedList<>();
  private Map<String, Integer> shipStrength = new HashMap<>();
  private int totalShipStrengh = 0;
  
  public void update(Observable observable, Object eventData)
  {
    
  }
}