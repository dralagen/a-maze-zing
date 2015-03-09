package org.alma.aMazeZing.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarFile;

/**
 * Standard plugins handler included in the platform
 *
 * The PluginsHandler should be an intermediate plugin that requests the platform and
 * is looking for plugins to load.
 *
 * @author Cr3aHal0
 * @version 1.0 <04/03/15>
 */
public class PluginsHandler {

    //Protected instance of ModuleLoader
    protected ModuleLoader loader;

    //Stores the informations for each plugin jar
    HashMap<String, List<String> > metadatas = new HashMap<>();

    //Constructor
    public PluginsHandler(ModuleLoader loader) {

        System.out.println("Initialisation du launcher");
        this.loader = loader;

        System.out.println(loader.getFolder());
        listDirectory(loader.getFolder());


    }

    private void listDirectory(String dir) {
        File file = new File(dir);

        System.out.println("Dossier "+ dir);


        File[] files = file.listFiles();

        if (files != null) {
            for (File f: files) {
                if (!f.isDirectory() && f.getAbsolutePath().endsWith(".jar")) {
                    System.out.println("Plugin " + f.getAbsolutePath());
                    try {
                        JarFile jf = new JarFile(f.getAbsolutePath());
                        InputStream in = jf.getInputStream(jf.getEntry("plugin.properties"));
                        Properties p = new Properties();
                        p.load(in);

                        for (String s : p.stringPropertyNames()) {
                            if (s.contains(".class")) {
                                String key = s.substring(0, s.length()-6);

                                if (metadatas.get(key) == null) {
                                    metadatas.put(key, new ArrayList<String>());
                                }
                                metadatas.get(key).add(p.getProperty(s));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(f.isDirectory())
                {
                    listDirectory(f.getAbsolutePath());
                }
            }
        }
    }

    public List<String> getPluginsForInterface(Class interfaceClass) {


        return metadatas.get(interfaceClass.getSimpleName());
    }

}
