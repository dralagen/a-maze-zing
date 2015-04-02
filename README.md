a-maze-zing  [![Build Status](https://travis-ci.org/dralagen/a-maze-zing.svg?branch=master)](https://travis-ci.org/dralagen/a-maze-zing)
===========

Compilation & launch
---------------------

```
mvn clean install && java -jar platform/target/platform-*.jar
```

By default the platform watch in `platform/plugin`, to load all plugin.
But this path are not fixed, you can add other path on `platform/src/main/resources/modules.properties` separared by coma.


How to develop a plugin
-----------------------

On A-Maze-Zing platform, all plugins are a jar who contains all class of the plugin and a file `plugin.xml` who contains all information of plugin.
A jar contain one or more plugin but a single file.
This file are structured as this :
```
<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
<plugins>
    <plugin>
        <category>UI</category>
        <name>ConsoleUI</name>
        <classname>org.alma.aMazeZing.UI.ConsoleUI</classname>
        <description>A small and light console display</description>
        <version>1.0</version>
    </plugin>
</plugins>
```

- **Category**:  Correspond at an interface of input point plugins without packages
- **name**: Correspond at a name of my plugin
- **classname**: the launcher of my plugin who implement the interface of category
- **description**: a describe of my plugin for user when choice plugin
- **version**: version of my plugin

For create a plugin you need to implement an interface. If you don't want error you need to add a dependency the plugin to extents. You can use maven with add this dependency
```
<dependency>
    <groupId>org.alma.a-maze-zing</groupId>
    <artifactId>core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
