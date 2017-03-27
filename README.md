This is an example project to test and demonstrate how to create self contained (single binary) CLI in Java. 

I know, I know. Why Java? Well, a lot of people that don't, but definitely should write CLIs have Java as their primary 
language. They should write CLIs to make their life easier. It's time to stop with all the Maven/Gradle/Whatever 
plugins that  shoehorns functionality that have nothing to do with builds and releases into it. 

In additiion:
- People seems more used to writing tests in Java. Yes, you should have automated tests for your CLI.
- You can create single distributable binary
- It's cross platform

You can do it. Just write a CLI. ;)

These are of course traits you can find other places, but I really wanted to make it easy in Java too. ;)

# What's used

- We use the [Maven Shade plugin](https://github.com/brianm/really-executable-jars-maven-plugin) to create one binary that can easily be distributed.
- We use the [Maven Really Executable Jars plugin](https://github.com/brianm/really-executable-jars-maven-plugin) to make the jar a "true" executable.
- We use the [Docopt for Java library](https://github.com/docopt/docopt.java) to create, validate and parse the command line.

There are other libraries used as well, but they are only for demo purposes.

# Distribution of the binary

Hopefully you release your binaries from Maven to some kind of repository. That makes it available over HTTP so I
usually do something like:

    curl http://myrepo/org/my/group/artifact/version/artifact-version.jar > ~/bin/mycli && chmod u+x ~/bin/mycli
    
# Testing

You can build and run locally:

    mvn clean install && ./target/executable-json-util-1.0-SNAPSHOT.jar
    
Or you test it easily like this:

    curl https://dl.dropboxusercontent.com/u/122923/executable-json-util-1.0-SNAPSHOT.jar > ~/bin/json-util && chmod u+x ~/bin/json-util
    ~/bin/json-util
    
You probably shouldn't download random binaries off the internet and run them, but if you like to you can. :)