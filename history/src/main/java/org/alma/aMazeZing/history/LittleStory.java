package org.alma.aMazeZing.history;

import org.alma.aMazeZing.achievement.Achievement;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.dependency.Dependency;
import org.alma.aMazeZing.quest.Quest;
import org.alma.aMazeZing.requirement.Requirement;
import org.alma.aMazeZing.reward.Reward;

import org.alma.aMazeZing.item.Boots;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

public class LittleStory implements History
{

    public String getName() {
        return "Little...big...planet";
    }

    private boolean finished = false;

    public List<ItemStack> getItems() {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();

        stacks.add(new ItemStack(new Boots(), 1));

        return stacks;
    }

    @Override
    public void setPlayer(Player player) {
        player.addObserver(this);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        if (o instanceof Player) {
            System.out.println("Changement d'état du joueur");
            List<ItemStack> inventory = ((Player)o).getInventory();
            int i = 0;
            while(i < inventory.size() && !finished) {
                finished = "Boots".equals(inventory.get(i).getItem().getName());
            }
            if (finished == true) {
                System.out.println("Le joueur a les bottes");
            }
        }
    }

}