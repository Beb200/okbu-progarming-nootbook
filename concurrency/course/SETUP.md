# Setup: Java and Maven

You need two tools for this module: a modern **Java JDK** and **Maven**, to build and run the code exercises. Please set them up before the first class: the exercises happen in class, and the first Maven run downloads files, which takes a minute or two.

## 1. Java 21 or later

The exercises use features from Java 21 (virtual threads, records, `Thread.ofVirtual()`).

1. Check what you have:
   ```
   java -version
   ```
   If it says `21` or higher, you are done.
2. Otherwise install a JDK 21+ build, for example Eclipse Temurin from <https://adoptium.net>.

## 2. Maven

Check with `mvn -v`. Maven 3.9 or later is recommended; install it from <https://maven.apache.org/download.cgi> (or `brew install maven` on macOS). Maven 3.8 also works but may print harmless `WARNING: ... sun.misc.Unsafe` lines on recent JDKs.

### Running the exercises

Each week's `exercises/starter/` folder is a **Maven project** with several small programs. Run commands **from inside that folder** (where `pom.xml` is). Choose the program with `-Dmain` (just the class name) and pass command-line arguments with `-Dargs`:

```
cd week1-introduction/exercises/starter
mvn -q compile exec:exec -Dmain=LikeCounter
mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="4 500000"
```

The optional challenge projects come with JUnit tests that check your work. They fail until you finish the TODOs:

```
cd challenge
mvn test                          # run all the tests
mvn test -Dtest=InterleavingsTest # run one test class
```

The first Maven run downloads plugins and JUnit, so it needs an internet connection and takes a minute.

Project layout (Week 1 shown):

```
exercises/
├── starter/                            the in-class exercises
│   ├── pom.xml
│   └── src/main/java/concurrency/week1/   LikeCounter.java, Downloads.java
└── challenge/                          optional, for fast finishers
    ├── pom.xml
    └── src/
        ├── main/java/concurrency/week1/   Interleavings.java
        └── test/java/concurrency/week1/   InterleavingsTest.java
```

### Using an IDE

Open the week's `exercises/starter` folder (or its `pom.xml`) in IntelliJ IDEA, Eclipse, or VS Code with the *Extension Pack for Java*; each imports it as a Maven project. You can then run any class's `main` method or any test with the IDE's run buttons. If the IDE underlines `Thread.ofVirtual()`, set the project JDK to 21 or later.
