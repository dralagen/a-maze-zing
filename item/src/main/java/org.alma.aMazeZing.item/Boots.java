package org.alma.aMazeZing.item;

import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.item.Item;

public class Boots implements Item
{
    public String getName() {
        return "Boots";
    }

    public char getChar() {
        return 'b';
    }

    @Override
    public void onContact(Player p) {
        p.addInventory(this);
    }

    @Override
    public boolean canWalkOn() {
        return true;
    }

    @Override
    public boolean isRemovedAfterContact() {
        return true;
    }

}