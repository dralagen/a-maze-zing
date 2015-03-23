package org.alma.aMazeZing.plugins;

import org.alma.aMazeZing.map.Map;
import org.alma.aMazeZing.history.History;

import java.util.List;


/**
 * Created on 2/12/15.
 *
 * @author 
 */
public interface MapBuilder {
	public Map getMap(List<History> l);
}
