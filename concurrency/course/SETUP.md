# Setup: Java, Maven and LTSA

You need three tools for this module: a modern **Java JDK** and **Maven** (to build and run the code exercises) and **LTSA** (to draw, animate and check models). Do this before Week 1's lab, or at the latest before Week 2.

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

Some exercises come with JUnit tests that check your work. They fail until you finish the TODOs:

```
mvn test                          # run all the tests
mvn test -Dtest=InterleavingsTest # run one test class
```

The first Maven run downloads plugins and JUnit, so it needs an internet connection and takes a minute.

Project layout (Week 1 shown):

```
starter/
├── pom.xml
└── src/
    ├── main/java/concurrency/week1/   LikeCounter.java, Downloads.java, Interleavings.java
    └── test/java/concurrency/week1/   InterleavingsTest.java
```

### Using an IDE

Open the week's `exercises/starter` folder (or its `pom.xml`) in IntelliJ IDEA, Eclipse, or VS Code with the *Extension Pack for Java*; each imports it as a Maven project. You can then run any class's `main` method or any test with the IDE's run buttons. If the IDE underlines `Thread.ofVirtual()`, set the project JDK to 21 or later.

## 3. LTSA (Labelled Transition System Analyser)

LTSA is a free tool from the textbook authors [MK-site]. It compiles FSP models, draws them as state machines, animates them, and checks them for problems such as deadlock.

1. Download `ltsatool.zip` from <https://www.doc.ic.ac.uk/~jnm/book/ltsa/download.html> and unzip it.
2. Start it from a terminal in the unzipped `ltsatool` folder:
   ```
   java -jar ltsa.jar
   ```
   (Double-clicking `ltsa.jar` also works on most machines.)
3. **macOS:** if the system blocks the jar, right-click it → *Open*, or use the terminal command above.

### Five-minute LTSA tour

1. *File → New*, type
   ```
   SWITCH = (on -> off -> SWITCH).
   ```
2. *Build → Compile*. The output pane should say `Compiled: SWITCH`.
3. *Window → Draw* (or the **Draw** tab) shows the state machine.
4. *Check → Run* opens the **Animator**: tick an action to "perform" it and watch the trace grow.
5. *Check → Safety* reports deadlocks. A process that reaches `STOP` reports a "potential DEADLOCK". That is expected for processes designed to terminate.

The lecture models are in `week2-processes-threads/models/lecture-models.lts`: open the file and pick a process from the drop-down list in the toolbar.

LTSA also ships with the textbook's examples (*File → Examples*), and *File → Export…* saves the compiled state machine in the `.aut` text format, which you can load into the Week 2 `LtsAnimator` exercise.

> LTSA is an older (2005) Java Swing application. Its model compiler was used to check every model in this course on JDK 25. If the window misbehaves on your machine, tell your instructor, and use a lab machine in the meantime.
