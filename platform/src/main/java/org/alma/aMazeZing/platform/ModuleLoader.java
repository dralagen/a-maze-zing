package org.alma.aMazeZing.platform;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarFile;

/**
 * Created on 19/01/15.
 *
 * @author dralagen
 */
public class ModuleLoader {

    private Properties properties;
    private ClassLoader classLoader;

    //Stores the informations for each plugin jar
    HashMap<String, List<String> > pluginsList = new HashMap<>();

    //Stores the metadatas for each plugin jar
    HashMap<String, PluginMetadata> metadatas = new HashMap<>();

    private static ModuleLoader instance = null;

    public static ModuleLoader getInstance() {
        if (instance == null) {
            instance = new ModuleLoader();
        }
        return instance;
    }

    private ModuleLoader () {

        properties = new Properties();
        try {

            properties.load(ModuleLoader.class.getResourceAsStream("/modules.properties"));
        } catch (IOException ignore) {
            ignore.printStackTrace();
            return;
        }

        Set<URL> urls = new LinkedHashSet<>();

        for (String path : properties.getProperty("path").split(",")) {
            listPluginsMetadata(path, urls);
        }

        URL[] urlsArray = new URL[urls.size()];
        urls.toArray(urlsArray);

        //System.out.println("nombre d'URL : " + urls.size());
        classLoader = new URLClassLoader(urlsArray);

    }

    /**
     * List the available plugins in the plugins directory
     * @param dir the folder where the plugins are
     */
    private void listPluginsMetadata(String dir, Set<URL> urls) {
        File file = new File(dir);

        System.out.println("Dossier "+ dir);


        File[] files = file.listFiles();

        if (files != null) {
            //For each jar file
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

                        //Retrieve plugins data
                        for (int i = 0; i < nbRacineNoeuds;i++) {
                            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                Element plugin = (Element) racineNoeuds.item(i);

                                Element category = (Element) plugin.getElementsByTagName("category").item(0);
                                Element name = (Element) plugin.getElementsByTagName("name").item(0);
                                Element className = (Element) plugin.getElementsByTagName("classname").item(0);
                                Element description = (Element) plugin.getElementsByTagName("description").item(0);
                                Element version = (Element) plugin.getElementsByTagName("version").item(0);

                                //Add the plugins name to the plugins list
                                if (pluginsList.get(category.getTextContent()) == null) {
                                    pluginsList.put(category.getTextContent(), new ArrayList<String>());
                                }
                                pluginsList.get(category.getTextContent()).add(className.getTextContent());

                                //Then add the plugins metadatas to the plugins metadata list
                                metadatas.put(className.getTextContent(),
                                    new PluginMetadata(name.getTextContent(),
                                        className.getTextContent(),
                                        description.getTextContent(),
                                        version.getTextContent())
                                );
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

                    try {
                        urls.add(f.toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                else if(f.isDirectory())
                {
                    listPluginsMetadata(f.getAbsolutePath(), urls);
                }
            }
        }
    }

    public Object load(String name) {
        Class cl = null;
        try {
            cl = Class.forName(name, true, classLoader);
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

    public List<String> getPluginsForInterface(Class<?> interfaceClass) {
        List<String> intfs = pluginsList.get(interfaceClass.getSimpleName());
        return (intfs != null) ? intfs : new ArrayList<String>();
    }

    //MAIN

    public static void main (String[] args) throws Exception {

        ModuleLoader loader = ModuleLoader.getInstance();

        List<String> interfaces = loader.getPluginsForInterface(Launcher.class);
        System.out.println(interfaces.size());
        if (interfaces.size() == 0) {
            System.out.println("Aucun plugin de démarrage présent, fermeture du programme...");
        }
        else
        {
            Scanner sc = new Scanner(System.in);
            int choix = -1;

            while (choix < 0 || choix > interfaces.size()+1)
            {
                System.out.println("Veuillez choisir l'un des plugins suivants ou 0 pour quitter");
                System.out.println("----------------------");
                for (int i = 0; i < interfaces.size(); i++) {
                    System.out.println("#" + (i+1) + " : " + interfaces.get(i));
                }
                System.out.println("----------------------");

                choix = sc.nextInt();
                if (choix < 0 || choix > interfaces.size()+1) {
                    System.out.println("[Erreur] Vous devez choisir l'un des plugins principaux proposés ou Quitter(0)");
                }
            }

            if (choix == 0) {
                System.out.println("Fermeture de l'application...");
                return;
            }

            Object o = loader.load(interfaces.get(choix - 1));
            ((Launcher)o).run();
        }
    }
}