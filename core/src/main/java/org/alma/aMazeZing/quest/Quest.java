package org.alma.aMazeZing.quest;

import org.alma.aMazeZing.achievement.Achievement;
import org.alma.aMazeZing.item.ItemStack;
import org.alma.aMazeZing.core.Player;
import org.alma.aMazeZing.dependency.Dependency;
import org.alma.aMazeZing.requirement.Requirement;
import org.alma.aMazeZing.reward.Reward;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Quest interface
 * Define some standard methods to respect
 *
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class Quest implements Observer {

    private String title;

    private String description;

    private List<Dependency> dependencies;

    private List<Requirement> requirements;

    private List<Reward> rewards;

    private List<Achievement> achievements;

    private List<ItemStack> items;

    private Player player;

	public String getTitle() {
        return title;
    }
	
	public String getDescription() {
        return description;
    }
	
	public List<Dependency> getDependencies() {
        return dependencies;
    }
	
	public List<Requirement> getRequirements() {
        return requirements;
    }
	
	public List<Reward> getRewards() {
        return rewards;
    }
	
	public List<Achievement> getAchievements() {
        return achievements;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        //Hey, something changed !
    }
}