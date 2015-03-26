package org.alma.aMazeZing.item;

import org.alma.aMazeZing.core.Player;

/**
 * Item class
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public interface Item {

	public String getName();

    public char getChar();

    public void onContact(Player p);

    public boolean canWalkOn();

    public boolean isRemovedAfterContact();

}
