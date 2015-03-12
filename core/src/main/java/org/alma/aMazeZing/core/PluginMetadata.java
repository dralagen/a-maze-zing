package org.alma.aMazeZing.core;

import java.lang.StringBuffer;

/**
 * Container for plugins metadata
 * 
 * @author Maxime
 * @version 1.0 <12/03/2015>
 */
public class PluginMetadata {

	String className;
	
	String description;
	
	String version;
	
	public PluginMetadata(String className, String desc, String version) {
		this.className = className;
		this.description = desc;
		this.version = version;
	}
	
	/**
	 * Return the plugin class name
	 * @return the plugin class name
	 */
	public String getClassName() {
		return this.className;
	}
	
	/**
	 * Return the plugin description
	 * @return the plugin description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Return the plugin current version
	 * @return the plugin current version
	 */
	public String getVersion() {
		return this.version;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Plugin : ").append(this.className).append("\n")
			.append("Description : ").append(this.description).append("\n")
			.append("Version : ").append(this.version);
		return sb.toString();
	}
}
