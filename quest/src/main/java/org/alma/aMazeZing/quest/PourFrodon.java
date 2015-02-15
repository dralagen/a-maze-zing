package org.alma.aMazeZing.quest;

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

public class PourFrodon extends Quest
{

    @Override
	public String getTitle() {
		return "Pour Frodon";
	}
	
	@Override
	public String getDescription() {
		return "[LSDA] Pour Frodooooooooooooooon";
	}
	
	@Override
	public List<Dependency> getDependencies() {
		return new ArrayList<Dependency>();
	}
	
	@Override
	public List<Requirement> getRequirements() {
		return new ArrayList<Requirement>();
	}
	
	@Override
	public List<Reward> getRewards() {
		return new ArrayList<Reward>();
	}
	
	@Override
	public List<Achievement> getAchievements() {
		return new ArrayList<Achievement>();
	}

    @Override
    public List<ItemStack> getItems() {
        return new ArrayList<ItemStack>();
    }

}