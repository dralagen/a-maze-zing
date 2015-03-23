package org.alma.aMazeZing.history;

import org.alma.aMazeZing.achievement.Achievement;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.dependency.Dependency;
import org.alma.aMazeZing.quest.Quest;
import org.alma.aMazeZing.requirement.Requirement;
import org.alma.aMazeZing.reward.Reward;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

public class LittleStory implements History
{

    public String getName() {
        return "Little...big...planet";
    }

    public List<ItemStack> getItems() {
        return new ArrayList<ItemStack>();
    }

}