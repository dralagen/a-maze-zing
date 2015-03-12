package org.alma.aMazeZing.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Properties;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    HashMap<String, List<String> > pluginsList = new HashMap<>();

    //Stores the metadatas for each plugin jar
    HashMap<String, PluginMetadata> metadatas = new HashMap<>();
    
    //Constructor
    public PluginsHandler(ModuleLoader loader) {

        System.out.println("Initialisation du launcher");
        this.loader = loader;

        System.out.println(loader.getFolder());
        //listDirectory(loader.getFolder());
        listPluginsMetadata(loader.getFolder());

        System.out.println("Récupération des métadonnées des plugins");
        System.out.println("----------------------------------------");
        Set<String> keys = metadatas.keySet();
        for (String key : keys) {
        	System.out.println(metadatas.get(key));
        	System.out.println("----------------------------------------");
        }
    }

    /**
     * List the available plugins in the plugins directory
     * @param dir the folder where the plugins are
     */
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

                                if (pluginsList.get(key) == null) {
                                	pluginsList.put(key, new ArrayList<String>());
                                }
                                pluginsList.get(key).add(p.getProperty(s));
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

    /**
     * List the available plugins in the plugins directory
     * @param dir the folder where the plugins are
     */
    private void listPluginsMetadata(String dir) {
        File file = new File(dir);

        System.out.println("Dossier "+ dir);


        File[] files = file.listFiles();

        if (files != null) {
            for (File f: files) {
                if (!f.isDirectory() && f.getAbsolutePath().endsWith(".jar")) {
                    System.out.println("Plugin " + f.getAbsolutePath());
                    try {
                        JarFile jf = new JarFile(f.getAbsolutePath());
                        InputStream in = jf.getInputStream(jf.getEntry("plugin.xml"));
                        
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(in);
                        
                        Element root = document.getDocumentElement();

                	    NodeList racineNoeuds = root.getChildNodes();
                	    int nbRacineNoeuds = racineNoeuds.getLength();
                	    
                	    for (int i = 0; i < nbRacineNoeuds;i++) {
                	    	if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {	
                	    		Element plugin = (Element) racineNoeuds.item(i);
                	    		
                	    		Element category = (Element) plugin.getElementsByTagName("category").item(0);
                	    		Element className = (Element) plugin.getElementsByTagName("class").item(0);
                	    		Element description = (Element) plugin.getElementsByTagName("description").item(0);
                	    		Element version = (Element) plugin.getElementsByTagName("version").item(0);
                	    		
                	    		//Add the plugins name to the plugins list
                                if (pluginsList.get(category.getTextContent()) == null) {
                                	pluginsList.put(category.getTextContent(), new ArrayList<String>());
                                }
                                pluginsList.get(category.getTextContent()).add(className.getTextContent());
                                
                	    		//Then add the plugins metadatas to the plugins metadata list
                	    		metadatas.put(className.getTextContent(), new PluginMetadata(className.getTextContent(), description.getTextContent(), version.getTextContent()));
                	    	}
                	    }

                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    catch (SAXException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
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
    
    /**
     * Returns a list of class names implementing the following interface
     * @param interfaceClass the interface class for which we want the implementing classes
     * @return a list of class names implementing the following interface
     */
    public List<String> getPluginsForInterface(Class interfaceClass) {
        return pluginsList.get(interfaceClass.getSimpleName());
    }

    /**
     * Standard run behaviour. Can be overrided
     */
    public void run() {
    	//Get the first UI implementation only
    	System.out.println("Début de la politique de chargement des plugins");
    }
}
