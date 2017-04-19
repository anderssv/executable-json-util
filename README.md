This is an example project to test and demonstrate how to create self contained (single binary) CLI in Java. 

I know, I know. Why Java? Well, a lot of people that don't, but definitely should write CLIs have Java as their primary 
language. They should write CLIs to make their life easier. It's time to stop with all the Maven/Gradle/Whatever 
plugins that  shoehorns functionality that have nothing to do with builds and releases into it. 

In additiion:
- People seems more used to writing tests in Java. Yes, you should have automated tests for your CLI.
- You can create single distributable binary
- It's cross platform
- It starts up much faster than I feared. ;)

You can do it. Just write a CLI. ;)

These are of course traits you can find other places, but I really wanted to make it easy in Java too. ;)

# Requirements

To build/test this project you'll need:

- JDK (8 and above)
- Maven

Running the resulting code requires a JRE installed.

# What's used

- We use the [Maven Shade plugin](https://github.com/brianm/really-executable-jars-maven-plugin) to create one binary that can easily be distributed.
- We use the [Maven Really Executable Jars plugin](https://github.com/brianm/really-executable-jars-maven-plugin) to make the jar a "true" executable.
- We use the [Docopt for Java library](https://github.com/docopt/docopt.java) to create, validate and parse the command line.

There are other libraries used as well, but they are only for demo purposes.

**NOTE:** The Shade plugin might get you into problems with some libraries. A friend of mine pointed me in the direction of [Capsule](http://www.capsule.io/) which looks promising. You might want to look into that if Shade gives you problmems. There are some minor drawbacks to how it does stuff, but I'll certainly keep it at hand.

# Distribution of the binary

Hopefully you release your binaries from Maven to some kind of repository. That makes it available over HTTP so I
usually do something like:

    curl http://myrepo/org/my/group/artifact/version/artifact-version.jar > ~/bin/mycli && chmod u+x ~/bin/mycli
    
# Awesomeness with completion

Since DocOpt is a machine readable format you can even generate Bash completion for your command automatically. Have a look at this link to see how: https://github.com/Infinidat/infi.docopt_completion

# Testing

**NOTE:** As this is Java you'll of course need a JRE installed. Nothing will work witout it. :)

You can build and run locally (requires all the usual Maven, JDK things):

    mvn clean install && ./target/executable-json-util-1.0-SNAPSHOT.jar
    
Or you test it easily like this (just need the JRE):

    curl https://dl.dropboxusercontent.com/u/122923/executable-json-util-1.0-SNAPSHOT.jar > ~/bin/json-util && chmod u+x ~/bin/json-util
    ~/bin/json-util
    
You probably shouldn't download random binaries off the internet and run them, but if you like to you can. :)