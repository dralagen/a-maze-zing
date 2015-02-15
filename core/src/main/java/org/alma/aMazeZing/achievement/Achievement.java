package org.alma.aMazeZing.achievement;

/**
 * Achievement superclass
 * 
 * @author Maxime
 * @version 1.0 [12/02/2015]
 */
public class Achievement
{
	
	private String name;
	
	private String description;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
