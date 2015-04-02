a-maze-zing  [![Build Status](https://travis-ci.org/dralagen/a-maze-zing.svg?branch=master)](https://travis-ci.org/dralagen/a-maze-zing)
===========

Compilation & launch
---------------------

```
mvn clean install && java -jar platform/target/platform-*.jar
```

By default the platform watch in `platform/plugin`, to load all plugin.
But this path are not fixed, you can add other path on `platform/src/main/resources/modules.properties` separared by coma.



How to add Module
------------------

Add on pom.xml the dependency of core on org.alma.a-maze-zing
```
<dependencies>
    <dependency>
        <groupId>org.alma.a-maze-zing</groupId>
        <artifactId>core</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

The core create a classloader with all class in all _path_ as a comma separated list in _modules.properties_
