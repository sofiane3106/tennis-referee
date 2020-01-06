# Rubix Tennis Referee

This project should contain all java backend software of the rubix tenis referee.

## requirements 

- maven in the path (tested with 3.3.9)
- JAVA_HOME pointing to jdk 8 (tested with 1.8.0_66)
- good internet connection
- it is recommended to extend maven memory by setting the MAVEN_OPTS environment variable (MAVEN_OPTS="-Xmx1024M -Xss128M -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1024M -XX:+CMSClassUnloadingEnabled") or file>settings>build,execution,deployment>build tools>maven>runner in intellij

## IDE requirements

- Intellij is recommended;
- the project use lombok, the lombok plugin is required for Intellij
- Use the default intellij Code Formatter.
- use tab characters for indentation
- Cucumber for java plugin (https://plugins.jetbrains.com/plugin/7212-cucumber-for-java for intellij or https://cucumber.github.io/cucumber-eclipse/ for eclipse)

## installation

- mvn clean install 

## git set up

Please before committing, do the minimal git setup

```sh
$ git config --global user.name "Sofiane REBIB"
$ git config --global user.email sofiane.rebib@gmail.com
```

You might have a problem filename too long, if so try the following : 

```sh
$ git config --system core.longpaths true 
```

Made with love by Sofiane REBIB
