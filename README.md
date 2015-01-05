Ratpack project - unified asset checksum calculation
-----------------------------

## Requirements

Project requries new Ratpack functionality developed in [ratpack-fork](https://github.com/zedar/ratpack)

## Build

1. Build ratpack-fork project

    $ git clone https://github.com/zedar/ratpack ratpack-fork
    $ cd ratpack-fork
    $ ./gradlew install

Output maven repo is installed in ```ratpack-fork/build/localrepo```

2. Set local maven repo in ```build.gradle```

    buildscript {
      repositories {
        jcenter()
        maven {
          url "file:///YOUR_PATH_TO/ratpack-fork/build/localrepo"
        }
      }
      dependencies {
        classpath "io.ratpack:ratpack-gradle:0.9.13-SNAPSHOT"
        classpath "com.github.jengelman.gradle.plugins:shadow:1.2.0"
      }
    }
    
    repositories {
      jcenter()
      maven { url 'http://clinker.netty.io/nexus/content/repositories/snapshots' }
      maven { url "file:///YOUR_PATH_TO/ratpack-fork/build/localrepo" }
    }
    
3. Start server

    $ ./gradlew run


