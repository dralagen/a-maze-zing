package org.alma.aMazeZing.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * Created on 19/01/15.
 *
 * @author dralagen
 */
public class ModuleLoader {

    private Properties properties;
    private ClassLoader classLoader;

    public ModuleLoader () {

        properties = new Properties();
        try {

            properties.load(ModuleLoader.class.getResourceAsStream("/modules.properties"));
        } catch (IOException ignore) {
            ignore.printStackTrace();
            return;
        }

        try {
            URL[] urls = new URL[1];
            urls[0] = new File(properties.getProperty("UI.path")).toURI().toURL();
            classLoader = new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            classLoader = this.getClass().getClassLoader();
        }

    }

    Object load(String name) {
        Class cl = null;
        try {
            cl = Class.forName(properties.getProperty(name), true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return cl.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}