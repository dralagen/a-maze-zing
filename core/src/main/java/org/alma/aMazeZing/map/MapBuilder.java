package org.alma.aMazeZing.map;

import org.alma.aMazeZing.quest.Quest;

import java.util.List;


/**
 * Created on 2/12/15.
 *
 * @author 
 */
public interface MapBuilder {
	public Map getMap(List<Quest> l);
}
