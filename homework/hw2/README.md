# README
## Project Structure
/
- README.md    : this file
- build.gradle : file for gradle build system
- src          : folder containing source code

## Compilation Instructions
`Gradle`: This project can be compiled using gradle. Simply call `gradle assemble`
to build the assignment. This will construct a jar file in /build/lib which can
be run using `java -jar <jar file>` or alternatively just run `gradle run`
Alternatively it builds all the class files in /build/classes and can be run
using `java <classpath>` ("in this case `java edu.stevens.jisrael.cs511.assignment2.Assignment2`")  
  
`javac`: From the projects root directory  
1. `cd src/main/java`
2. `javac /<full classpath>/*.java`
3. `java edu.stevens.jisrael.cs511.assignment2.Assignment2`
