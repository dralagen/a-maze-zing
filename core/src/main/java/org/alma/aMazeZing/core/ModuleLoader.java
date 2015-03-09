package org.alma.aMazeZing.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

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

        Set<URL> urls = new LinkedHashSet<>();

        for (String path : properties.getProperty("path").split(",")) {
            try {
                urls.add(new File(path).toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        URL[] urlsArray = new URL[urls.size()];
        urls.toArray(urlsArray);

        classLoader = new URLClassLoader(urlsArray);

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

    public String getFolder() {
        return (properties.getProperty("folder") != null) ? properties.getProperty("folder") : "";
    }
}