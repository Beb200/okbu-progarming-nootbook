# AI Learning Prompts for the Game-Playing Agent

This guide maps every functional requirement in `PRD.md` to a prompt that a student can submit to an AI model while learning. The prompts are deliberately designed to support understanding without asking for a project implementation.

## How to use these prompts

- Replace bracketed text such as `[chosen game]` with your own decision.
- Ask the model to explain the idea before showing code.
- Treat every code sample as a small, unrelated learning example. Do not paste it into the project as the implementation of a requirement.
- Ask follow-up questions about design choices, edge cases, and tests before writing your own code.

## Java vocabulary used in this guide

The examples use Java syntax that is available in older Java versions where practical. The project requires Java 17+, but students should not need to know every modern language feature before starting.

- A **class** is a definition for objects: it lists their data and the methods that operate on that data.
- A **constructor** is a special method used with `new` to create an object and initialize its fields.
- A **field** is a variable stored inside an object. `private` means outside code cannot access it directly.
- A **method** is a named operation. A method's **return type** says what value it gives back; `void` means it gives back no value.
- An **interface** is a contract listing methods that an implementing class must provide. It describes what an object can do, not how it does it.
- An **enum** is a type whose value must be one of a fixed set of named constants, such as `HOST` or `GUEST`.
- A **generic type** uses angle brackets to say what a collection or helper works with. For example, `List<String>` means a list of strings.
- A **list** is an ordered collection. A `Map` stores key-value pairs, and a `Set` stores values without duplicates.
- A **static method** belongs to a class rather than to one object. `Math.max(a, b)` is an example.
- `final` prevents a variable from being assigned a different value after initialization. It does not automatically make an object immutable.
- `Optional<T>` represents either one value of type `T` or no value. It is useful when “not found” is a normal result.
- A **lambda** is a short unnamed function, such as `value -> value * 2`. A **method reference**, such as `Book::getId`, is a shorter way to refer to an existing method.
- A **stream** is a pipeline for processing collection elements with operations such as `filter`, `map`, and `sum`. Students may replace stream code with ordinary loops while learning.
- A **switch expression** uses `->` and produces a value. The examples below use ordinary `if` statements or traditional `switch` statements instead when possible.
- A **method parameter** is an input listed between a method's parentheses. A **return value** is the result produced by the method.

Whenever a prompt contains a term that is new to you, add this sentence to the prompt: “Define each Java term before using it, show the equivalent beginner-friendly version, and check my understanding with one question.”

## Week 1: Core Abstractions

### F1.1 - `get_legal_moves(state)`

**Prompt:**

> Explain how an abstract game interface can expose legal actions without knowing the details of a particular game. Use a small Java example of a `Library` that returns available book IDs, not a game or move generator. Explain what the caller may assume about the returned collection, how illegal actions should be excluded, and what edge cases I should test for `[chosen game]`. Do not implement `get_legal_moves` or any part of my game.

**Learning example to request:**

```java
class Book {
    private String id;
    private boolean checkedOut;

    public Book(String id, boolean checkedOut) {
        this.id = id;
        this.checkedOut = checkedOut;
    }

    public String getId() { return id; }
    public boolean isCheckedOut() { return checkedOut; }
}

List<String> availableBookIds(List<Book> books) {
    List<String> result = new ArrayList<String>();
    for (Book book : books) {
        if (!book.isCheckedOut()) result.add(book.getId());
    }
    return result;
}
```

### F1.2 - `apply_move(state, move)`

**Prompt:**

> Teach me the difference between mutating an object and returning a successor object when applying an action. Use a Java example of moving a character between rooms in an immutable `WorldState`; do not use a board, game, or move named in my project. Explain why search algorithms usually need independent successor states and list tests I should design for `[chosen game]`. Do not write `apply_move`.

**Learning example to request:**

```java
class WorldState {
    private String room;
    private int keys;

    public WorldState(String room, int keys) {
        this.room = room;
        this.keys = keys;
    }

    public String getRoom() { return room; }
    public int getKeys() { return keys; }
}

WorldState enterRoom(WorldState state, String destination) {
    return new WorldState(destination, state.getKeys());
}
```

### F1.3 - `is_terminal(state)`

**Prompt:**

> Explain how to define a terminal predicate for a finite process. Use a Java example for a package-delivery workflow with statuses such as `IN_TRANSIT`, `DELIVERED`, and `RETURNED`. Explain why terminal detection should be separate from result classification, then give questions I should answer when designing terminal detection for `[chosen game]`. Do not implement my game method.

**Learning example to request:**

```java
enum DeliveryStatus { IN_TRANSIT, DELIVERED, RETURNED }

boolean isClosed(DeliveryStatus status) {
    return status == DeliveryStatus.DELIVERED
            || status == DeliveryStatus.RETURNED;
}
```

### F1.4 - `get_result(state)`

**Prompt:**

> Explain how a state can be mapped to a small, documented result domain such as `-1`, `0`, and `+1`. Use a Java example that classifies a delivery as failed, pending, or successful, and explain why result classification should only be used after terminal detection. Ask me to reason through every terminal outcome in `[chosen game]`; do not implement the method.

**Learning example to request:**

```java
enum DeliveryOutcome { FAILED, PENDING, SUCCESS }

int score(DeliveryOutcome outcome) {
    if (outcome == DeliveryOutcome.FAILED) return -1;
    if (outcome == DeliveryOutcome.SUCCESS) return 1;
    return 0;
}
```

### F1.5 - `get_current_player(state)`

**Prompt:**

> Explain how state should record whose turn or responsibility is active. Use a Java example of alternating speakers in a dialogue, with an enum and a function that returns the next speaker. Discuss invariants such as “exactly one active participant” and ask me how to test turn changes in `[chosen game]`. Do not implement my game state.

**Learning example to request:**

```java
enum Speaker { HOST, GUEST }

Speaker nextSpeaker(Speaker current) {
    return current == Speaker.HOST ? Speaker.GUEST : Speaker.HOST;
}
```

### F1.6 - `display(state)`

**Prompt:**

> Teach me how to separate a state’s data from its human-readable rendering. Use a Java example that formats a small seating chart or inventory table, including labels and empty positions. Explain how stable formatting helps debugging and testing, then ask me to design display requirements for `[chosen game]`. Do not write my display method.

**Learning example to request:**

```java
String formatShelf(String[] labels) {
    return "[" + String.join(" | ", labels) + "]";
}
```

### F1.7 - `GameState`

**Prompt:**

> Explain how to choose fields for a game-state value object and how to distinguish essential state from derived data. Use an ordinary Java class with private fields and a constructor for a simple traffic intersection state, not a game. Briefly explain that a `record` is newer compact syntax for a class that mainly stores data, but do not require me to use one. Explain copying, equality, validation, and the effect of storing the active participant. End with a checklist I can use to design `GameState` for `[chosen game]`; do not design or implement it for me.

**Learning example to request:**

```java
class TrafficState {
    private int waitingCars;
    private boolean pedestrianCrossing;

    public TrafficState(int waitingCars, boolean pedestrianCrossing) {
        this.waitingCars = waitingCars;
        this.pedestrianCrossing = pedestrianCrossing;
    }

    public int getWaitingCars() { return waitingCars; }
    public boolean hasPedestrianCrossing() { return pedestrianCrossing; }
}
```

### F1.8 - Six interface methods

**Prompt:**

> Explain interface-based design in Java using a `Workflow<S, A>` interface whose methods inspect a workflow state and produce successor states. Use only a tiny non-game example and explain how an implementation can satisfy a contract without leaking implementation details to callers. Ask me to map the six game-interface responsibilities to my own design, but do not write the interface implementation.

**Learning example to request:**

```java
interface Workflow<S, A> {
    List<A> actions(S state);
    S next(S state, A action);
    boolean finished(S state);
}
```

### F1.9 - `TreeNode`

**Prompt:**

> Explain parent and child links in a rooted tree using a Java example of an organization chart. Show a minimal generic node shape and explain ownership, traversal, equality, and when children should be created. Do not use game states and do not implement my `TreeNode`; give me design questions and invariants to verify instead.

**Learning example to request:**

```java
final class OrgNode {
    final String name;
    OrgNode parent;
    final List<OrgNode> reports = new ArrayList<>();

    OrgNode(String name) { this.name = name; }
}
```

## Week 2: Game Tree Construction and Traversal

### F2.1 - `generate_children(node)`

**Prompt:**

> Explain the relationship between an action provider, a successor function, and child-node construction. Use a Java example that expands possible next steps in a recipe planner, with placeholder methods and no game logic. Discuss duplicate states, parent links, and whether expansion should mutate the parent. Give me a checklist for implementing the idea for `[chosen game]`, but do not implement `generate_children`.

**Learning example to request:**

```java
class RecipeStep {
    private String name;
    public RecipeStep(String name) { this.name = name; }
    public String getName() { return name; }
}

List<RecipeStep> nextSteps(RecipeStep step) {
    List<RecipeStep> result = new ArrayList<RecipeStep>();
    result.add(new RecipeStep(step.getName() + " -> next"));
    return result;
}
```

### F2.2 - `count_nodes(node, depth)`

**Prompt:**

> Teach recursive counting over a tree bounded by depth. Use a Java example counting folders in a directory tree only to a specified level. Explain the base case, inclusive versus exclusive depth conventions, and how to verify counts with a hand-built tree. Do not implement the game-tree counter.

**Learning example to request:**

```java
int countFolders(Folder folder, int remainingDepth) {
    if (remainingDepth == 0) return 1;
    return 1 + folder.children().stream()
            .mapToInt(child -> countFolders(child, remainingDepth - 1))
            .sum();
}
```

### F2.3 - BFS at depth 2

**Prompt:**

> Explain breadth-first search and how a queue can process exactly one tree level at a time. Use a Java example traversing a museum exhibit graph represented as a tree, and show how to stop after a chosen depth without printing any game states. Ask me to identify the invariant maintained by the queue for `[chosen game]`; do not write the BFS implementation.

**Learning example to request:**

```java
Deque<OrgNode> queue = new ArrayDeque<>();
queue.add(root);
while (!queue.isEmpty()) {
    OrgNode current = queue.removeFirst();
    queue.addAll(current.reports);
}
```

### F2.4 - DFS to a terminal state

**Prompt:**

> Explain depth-first search with recursion and how a search can stop at the first satisfying node. Use a Java example finding the first completed task in a nested task list. Discuss traversal order, backtracking, and the meaning of “first”; then ask me how those choices affect `[chosen game]`. Do not implement DFS for my project.

**Learning example to request:**

```java
Optional<Task> firstCompleted(Task task) {
    if (task.completed()) return Optional.of(task);
    for (Task child : task.children()) {
        Optional<Task> result = firstCompleted(child);
        if (result.isPresent()) return result;
    }
    return Optional.empty();
}
```

## Week 3: Win Detection and Terminal Validation

### F3.1 - `check_winner(state)`

**Prompt:**

> Explain systematic pattern detection on a finite grid. Use a Java example that detects a matching run of colored tiles in a non-game puzzle board, with the directions represented as data. Explain boundary handling and how to avoid missing or double-counting patterns. Do not implement winner detection for `[chosen game]`; instead give me a design worksheet and test cases to create.

**Learning example to request:**

```java
int[][] directions = {{0, 1}, {1, 0}};
for (int[] direction : directions) {
    System.out.println(Arrays.toString(direction));
}
```

### F3.2 - `is_terminal(state)` validation

**Prompt:**

> Teach me how to validate a terminal predicate against mutually exclusive outcomes. Use a Java example for a download job that can be complete, failed, or still running. Include a truth-table style testing strategy and discuss false positives and false negatives. Apply the reasoning questions to `[chosen game]`, but do not write game code.

**Learning example to request:**

```java
class Download {
    private boolean allBytesReceived;
    private boolean failed;
    public Download(boolean allBytesReceived, boolean failed) {
        this.allBytesReceived = allBytesReceived;
        this.failed = failed;
    }
    public boolean hasAllBytes() { return allBytesReceived; }
    public boolean hasFailed() { return failed; }
}
```

### F3.3 - `get_result(state)` validation

**Prompt:**

> Explain result classification as a total mapping over terminal outcomes. Use a Java example that maps traffic-light outcomes to labels and numeric scores. Show how to test every outcome and how to handle a non-terminal state without silently inventing a winner. Do not implement `get_result` for my game.

**Learning example to request:**

```java
enum Signal { CLEAR, WAIT, HAZARD }

String label(Signal signal) {
    return switch (signal) {
        case CLEAR: return "go";
        case WAIT: return "hold";
        case HAZARD: return "stop";
    };
}
```

### F3.4 - Unit tests for win conditions

**Prompt:**

> Teach me how to derive unit tests from a rule table. Use JUnit 5 tests for a non-game `isValidPassword` function, including boundary cases and invalid inputs. Explain test naming, arrange-act-assert, parameterized cases, and coverage of every win pattern in `[chosen game]`. Do not write my game tests or game logic.

**Learning example to request:**

```java
@Test
void rejectsPasswordThatIsTooShort() {
    assertFalse(isValidPassword("abc"));
}
```

## Week 4: Game Engine and Human Interface

### F4.1 - `Game` controller

**Prompt:**

> Explain the controller pattern for coordinating a stateful workflow while depending on an interface rather than concrete rules. Use a Java example of a `CheckoutController` that delegates pricing and inventory decisions to interfaces. Identify what the controller should not know. Help me sketch responsibilities for my game controller without implementing it.

**Learning example to request:**

```java
interface Inventory {
    boolean available(String itemId);
}

final class CheckoutController {
    private final Inventory inventory;
    CheckoutController(Inventory inventory) { this.inventory = inventory; }
}
```

### F4.2 - `human_turn(state)`

**Prompt:**

> Explain how to separate input acquisition, parsing, validation, and state transition. Use a Java example that asks for a menu option and returns an `Optional<Integer>`, but do not use game moves or implement a human turn. Discuss retry behavior, end-of-input, and how to test input without a real console. Give me a design checklist for `[chosen game]`.

**Learning example to request:**

```java
Optional<Integer> parseMenuChoice(String text) {
    try {
        return Optional.of(Integer.parseInt(text.trim()));
    } catch (NumberFormatException ex) {
        return Optional.empty();
    }
}
```

### F4.3 - Full game loop

**Prompt:**

> Teach me the structure of a turn-based event loop using a Java example for alternating turns in a debate simulator. Explain the loop invariant, when state is displayed, when termination is checked, and why result announcement belongs at the end. Do not implement a game loop; ask me to write pseudocode for `[chosen game]` first.

**Learning example to request:**

```java
while (!session.finished()) {
    session = session.nextTurn();
}
System.out.println(session.summary());
```

### F4.4 - Invalid input and re-prompting

**Prompt:**

> Explain robust retry loops for user input. Use a Java example that repeatedly asks for a temperature within a valid range and reports a helpful error. Cover malformed input, valid-but-unavailable choices, end-of-file, and avoiding infinite loops. Do not implement game input handling; give me cases to test for `[chosen game]`.

**Learning example to request:**

```java
int readTemperature(Scanner scanner) {
    while (true) {
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Enter a whole number.");
            continue;
        }
        int value = scanner.nextInt();
        if (value >= -20 && value <= 50) return value;
        System.out.println("Enter a value from -20 to 50.");
    }
}
```

## Week 5: Random AI, Timing, and Priority Queues

### F5.1 - `random_ai(state)`

**Prompt:**

> Explain uniform random selection from a finite collection in Java. Use an example selecting a random song from a playlist, including how a seeded `Random` object improves testing. Discuss the empty-collection case and why the selector should only choose from valid options. Do not implement a game AI.

**Learning example to request:**

```java
String chooseSong(List<String> songs, Random random) {
    return songs.get(random.nextInt(songs.size()));
}
```

### F5.2 - Integrating a random choice into a loop

**Prompt:**

> Explain dependency injection for plugging a decision policy into an existing loop. Use a Java example where a `PlaylistRunner` receives a `Supplier<String>` for the next song. Show how a fake supplier helps tests, but do not integrate an AI into my game loop.

**Learning example to request:**

```java
interface ChoicePolicy<T> {
    T choose(List<T> options);
}

void runOnce(List<String> options, ChoicePolicy<String> policy) {
    System.out.println(policy.choose(options));
}
```

### F5.3 - Timer utility

**Prompt:**

> Teach me how to measure elapsed time around an arbitrary Java operation. Use a `Runnable` that simulates sorting a list and explain `System.nanoTime()`, units, exception behavior, and measurement overhead. Do not write a timer for my AI or choose the project’s logging format.

**Learning example to request:**

```java
long measureNanos(Runnable operation) {
    long start = System.nanoTime();
    operation.run();
    return System.nanoTime() - start;
}
```

### F5.4 - CSV performance logging

**Prompt:**

> Explain how to design a small CSV record and append rows safely in Java. Use an example logging weather observations with a header, escaping concerns, and a fixed column order. Discuss timestamps and reproducibility, but do not write the project’s performance logger.

**Learning example to request:**

```java
class WeatherRow {
    private int minute;
    private double temperature;
    public WeatherRow(int minute, double temperature) {
        this.minute = minute;
        this.temperature = temperature;
    }
    public int getMinute() { return minute; }
    public double getTemperature() { return temperature; }
}

String toCsv(WeatherRow row) {
    return row.getMinute() + "," + row.getTemperature();
}
```

### F5.5 - `rank_moves(state)` with a priority queue

**Prompt:**

> Explain priority queues and comparators in Java using an emergency-room triage example. Show how ordering differs from insertion order and how ties should be handled. Then ask me to define a game-specific ranking policy and tests for it; do not implement `rank_moves` or reveal a Connect Four ranking.

**Learning example to request:**

```java
PriorityQueue<Integer> urgency = new PriorityQueue<>(Comparator.reverseOrder());
urgency.add(2);
urgency.add(5);
System.out.println(urgency.remove());
```

## Week 6: Serialization and Move History

### F6.1 - `serialize_state(state)`

**Prompt:**

> Explain how to design a compact, unambiguous serialization format for a small immutable Java object. Use a `Coordinate` example with delimiters and discuss escaping, field order, versioning, and uniqueness. Do not serialize a game state or design my format; give me questions I must answer for `[chosen game]`.

**Learning example to request:**

```java
class Coordinate {
    private int x;
    private int y;
    public Coordinate(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
}

String encode(Coordinate coordinate) {
    return coordinate.getX() + ":" + coordinate.getY();
}
```

### F6.2 - `deserialize_state(string)`

**Prompt:**

> Teach me defensive parsing and round-trip properties. Use a Java example that parses a serialized `Coordinate`, validates the number and range of fields, and reports malformed input without constructing an invalid object. Do not implement game-state deserialization.

**Learning example to request:**

```java
Optional<Coordinate> decodeCoordinate(String text) {
    String[] parts = text.split(":", -1);
    if (parts.length != 2) return Optional.empty();
    try {
        return Optional.of(new Coordinate(
                Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
    } catch (NumberFormatException ex) {
        return Optional.empty();
    }
}
```

### F6.3 - Move-history trie

**Prompt:**

> Explain trie nodes and prefix sharing using a Java example storing autocomplete words. Show insertion and lookup concepts for strings, not move sequences, and explain why a trie is useful when many histories share prefixes. Ask me to design the node invariants and operations for `[chosen game]`; do not implement a move-history trie.

**Learning example to request:**

```java
final class TrieNode {
    final Map<Character, TrieNode> children = new HashMap<>();
    boolean completesWord;
}
```

### F6.4 - Save and load a game

**Prompt:**

> Explain persistence as a boundary between in-memory state and a file. Use a Java example saving and loading a `Note` record with try-with-resources and validation. Discuss atomicity, missing files, corrupted data, and compatibility. Do not implement save/load for my game; give me a failure-mode checklist.

**Learning example to request:**

```java
class Note {
    private String title;
    private String body;
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public String getTitle() { return title; }
    public String getBody() { return body; }
}

void saveNote(Path path, Note note) throws IOException {
    Files.writeString(path, note.getTitle() + "\n" + note.getBody());
}
```

### F6.5 - Display numbered move history

**Prompt:**

> Teach me how to format an ordered event history for humans. Use a Java example printing numbered steps from a cooking recipe and discuss one-based versus zero-based numbering, empty histories, and stable output. Do not format my game’s move history; give me formatting questions to resolve.

**Learning example to request:**

```java
for (int index = 0; index < steps.size(); index++) {
    System.out.printf("%d. %s%n", index + 1, steps.get(index));
}
```

## Week 7: Minimax AI

### F7.1 - Recursive Minimax

**Prompt:**

> Explain the Minimax idea using a tiny abstract decision tree whose leaves contain scores. Use Java code that evaluates a hard-coded non-game tree or a choice between business strategies, and label maximizing and minimizing levels. Do not generate a recursive Minimax implementation for my game. Ask me to identify the recurrence and the information each recursive call needs.

**Learning example to request:**

```java
class Leaf {
    private int score;
    public Leaf(int score) { this.score = score; }
    public int getScore() { return score; }
}

int bestOf(int first, int second) {
    return Math.max(first, second);
}
```

### F7.2 - Terminal and depth-limit base cases

**Prompt:**

> Teach me how recursive search chooses and orders base cases. Use a Java example of exploring a task dependency tree with a maximum depth and completed-task check. Explain what happens when the depth limit is reached before completion, and ask me to reason about terminal versus depth-limited states in `[chosen game]`. Do not implement search for my project.

**Learning example to request:**

```java
int inspect(Task task, int remainingDepth) {
    if (task.completed() || remainingDepth == 0) {
        return task.value();
    }
    return task.children().stream()
            .mapToInt(child -> inspect(child, remainingDepth - 1))
            .max()
            .orElse(task.value());
}
```

### F7.3 - Integrate Minimax into the game loop

**Prompt:**

> Explain how to replace one decision policy with another behind a stable interface. Use a Java example swapping a random notification policy for a rule-based notification policy. Discuss ownership of state, legal-action validation, and dependency injection. Do not integrate Minimax into my game; give me an integration plan and verification questions.

**Learning example to request:**

```java
interface NotificationPolicy {
    String choose(List<String> messages);
}

void sendNext(NotificationPolicy policy, List<String> messages) {
    System.out.println(policy.choose(messages));
}
```

### F7.4 - Search-depth parameter

**Prompt:**

> Explain how a depth parameter changes recursive work and behavior. Use a Java example exploring nested menu items with a configurable maximum depth. Discuss default values, zero depth, negative input, and how to compare runs fairly. Do not implement a depth parameter in my AI.

**Learning example to request:**

```java
List<String> visibleItems(Menu menu, int depth) {
    if (depth <= 0) return List.of(menu.title());
    return menu.children().stream()
            .flatMap(child -> visibleItems(child, depth - 1).stream())
            .toList();
}
```

### F7.5 - Nodes explored logging

**Prompt:**

> Teach me how to count recursive calls or visited nodes without confusing a count with a result. Use a Java example traversing a folder tree and returning a small `SearchReport` containing a result and count. Explain reset scope, aggregation, and tests. Do not add node counting to my search engine.

**Learning example to request:**

```java
class SearchReport {
    private Optional<String> match;
    private long visited;
    public SearchReport(Optional<String> match, long visited) {
        this.match = match;
        this.visited = visited;
    }
    public Optional<String> getMatch() { return match; }
    public long getVisited() { return visited; }
}
```

## Week 8: Alpha-Beta Pruning and Benchmarking

### F8.1 - Alpha-beta pruning

**Prompt:**

> Explain alpha-beta pruning conceptually with a hand-worked abstract tree whose leaves are numeric scores. Use short Java snippets for updating two bounds and state clearly what information makes a branch irrelevant. Do not write alpha-beta search or game code; ask me to annotate a tree by hand first.

**Learning example to request:**

```java
int alpha = Integer.MIN_VALUE;
int beta = Integer.MAX_VALUE;
alpha = Math.max(alpha, candidate);
boolean canPrune = alpha >= beta;
```

### F8.2 - Replace plain search with alpha-beta search

**Prompt:**

> Explain how to substitute an optimized implementation while preserving an observable contract. Use a Java example replacing a slow list lookup with a map-backed lookup and tests that compare returned values. Discuss what must remain identical and what may change internally. Do not replace my Minimax function or write alpha-beta code.

**Learning example to request:**

```java
interface Catalog {
    Optional<String> find(String id);
}
```

### F8.3 - Move ordering

**Prompt:**

> Explain why processing promising candidates first can improve pruning without changing the mathematical answer. Use a Java example sorting possible delivery routes by estimated cost, not game moves. Discuss stable ordering, ties, and whether sorting changes correctness. Ask me to design an ordering experiment for `[chosen game]`; do not implement it.

**Learning example to request:**

```java
routes.sort(Comparator.comparingInt(Route::estimatedMinutes));
```

### F8.4 - Benchmark plain search versus alpha-beta

**Prompt:**

> Teach me how to design a fair algorithm benchmark. Use a Java example comparing two ways to sort the same generated integer lists. Cover warm-up, repeated trials, fixed inputs, depth as an independent variable, and recording node counts separately from elapsed time. Do not benchmark or implement my game searches.

**Learning example to request:**

```java
for (int trial = 0; trial < 5; trial++) {
    long start = System.nanoTime();
    candidate.run(input);
    measurements.add(System.nanoTime() - start);
}
```

### F8.5 - Save benchmark results and print a summary

**Prompt:**

> Explain how to turn benchmark records into both CSV output and a readable summary table. Use a Java example with `AlgorithmResult(name, size, operations)` and show formatting only. Discuss consistent columns, units, missing values, and reproducibility. Do not create my benchmark logger or summary.

**Learning example to request:**

```java
class AlgorithmResult {
    private String name;
    private int inputSize;
    private long operations;
    public AlgorithmResult(String name, int inputSize, long operations) {
        this.name = name;
        this.inputSize = inputSize;
        this.operations = operations;
    }
    public String getName() { return name; }
    public int getInputSize() { return inputSize; }
    public long getOperations() { return operations; }
}

System.out.printf("%-12s %8s %12s%n", "Algorithm", "Size", "Ops");
```

## Week 9: Heuristic Evaluation and Tournament

### F9.1 - Pass an `evaluate(state)` function

**Prompt:**

> Explain how a method can receive a scoring policy without knowing its implementation. Use a Java example with a beginner-friendly `ScoreFunction` interface whose `score` method receives an integer. Define “interface” and “policy” before using them, and explain the equivalent ordinary class implementation before showing any lambda. Do not add an evaluator parameter to my search.

**Learning example to request:**

```java
interface ScoreFunction {
    int score(int value);
}

int totalScore(List<Integer> values, ScoreFunction scorer) {
    int total = 0;
    for (Integer value : values) total += scorer.score(value);
    return total;
}
```

### F9.2 - Evaluate at the depth limit

**Prompt:**

> Explain the difference between an exact terminal value and an estimated value at a cutoff. Use a Java example evaluating a partially completed project plan at a maximum inspection depth. Discuss base-case ordering and why a terminal state must not be mistaken for a heuristic estimate. Do not implement depth-limited game search.

**Learning example to request:**

```java
if (plan.isComplete()) return exactValue(plan);
if (remainingDepth == 0) return estimate(plan);
```

### F9.3 - Validate depth-limited behavior

**Prompt:**

> Teach me how to test a depth-limited decision system for legality and sensible behavior without claiming optimal play. Use a Java example choosing valid shipping options under limited lookahead. Give me property-style checks, boundary tests, and a way to compare depth 1 with depth 2. Do not implement my AI or choose its heuristic.

**Learning example to request:**

```java
assertTrue(options.contains(selected));
assertTrue(depth >= 0);
```

### F9.4 - Threat-pattern heuristic

**Prompt:**

> Explain feature-based scoring and pattern counting using a non-game example such as classifying a document by words and warning signals. Show how positive and negative features can be weighted, but do not use board windows, wins, threats, or game code. Ask me to propose candidate features for `[chosen game]` and how to test whether each feature changes the score meaningfully.

**Learning example to request:**

```java
int documentScore(Set<String> words) {
    int score = 0;
    if (words.contains("verified")) score += 3;
    if (words.contains("warning")) score -= 5;
    return score;
}
```

### F9.5 - Weight immediate and future threats

**Prompt:**

> Teach me how to choose and justify weights in a rule-based scoring model. Use a Java example scoring a cybersecurity alert where immediate danger outweighs a weak signal. Discuss scale, sign conventions, conflicting evidence, and sensitivity analysis. Do not suggest weights for my game or implement a threat heuristic.

**Learning example to request:**

```java
class Alert {
    private boolean activeBreach;
    private boolean unusualLogin;
    public Alert(boolean activeBreach, boolean unusualLogin) {
        this.activeBreach = activeBreach;
        this.unusualLogin = unusualLogin;
    }
    public boolean hasActiveBreach() { return activeBreach; }
    public boolean hasUnusualLogin() { return unusualLogin; }
}

int risk(Alert alert) {
        return (alert.hasActiveBreach() ? 100 : 0)
            + (alert.hasUnusualLogin() ? 5 : 0);
}
```

### F9.6 - Positional heuristic

**Prompt:**

> Explain weighted tables as a way to represent positional value. Use a Java example assigning values to seats in a theater based on distance from the stage, not a game board. Discuss indexing, symmetry, and how to justify weights empirically. Do not create a board table or heuristic for my project.

**Learning example to request:**

```java
int[][] seatValue = {
    {1, 2, 3},
    {2, 4, 5}
};
```

### F9.7 - Combine positional and threat scores

**Prompt:**

> Teach me how to combine independent scoring components and reason about scale. Use a Java example combining a reliability score and a latency score for a server, with named weights and normalization. Explain double-counting and how to test each component separately. Do not combine heuristics for my game.

**Learning example to request:**

```java
double overall(double reliability, double latency) {
    return 0.7 * reliability + 0.3 * latency;
}
```

### F9.8 - Heuristic tournament

**Prompt:**

> Explain how to design a reproducible head-to-head tournament between two policies. Use a Java example comparing two sorting strategies on repeated fixed inputs, alternating which strategy runs first. Discuss match count, pairing, random seeds, and avoiding order bias. Do not implement a game tournament or heuristics.

**Learning example to request:**

```java
class MatchResult {
    private String first;
    private String second;
    private boolean firstWon;
    public MatchResult(String first, String second, boolean firstWon) {
        this.first = first;
        this.second = second;
        this.firstWon = firstWon;
    }
}

List<String> players = List.of("PolicyA", "PolicyB");
```

### F9.9 - Record tournament results

**Prompt:**

> Teach me how to aggregate categorical outcomes into wins, losses, draws, and rates. Use a Java example aggregating outcomes of a debate tournament, then show how to serialize the aggregate as CSV. Explain denominator choices and checks that totals balance. Do not create my tournament-results implementation.

**Learning example to request:**

```java
class Tally {
    private int wins;
    private int losses;
    private int draws;
    public Tally(int wins, int losses, int draws) {
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }
}

int total(Tally tally) {
    return tally.getWins() + tally.getLosses() + tally.getDraws();
}
```

## Week 10: Final Polish and Submission

### F10.1 - Three CLI modes

**Prompt:**

> Explain command-line mode selection in Java using an application with `--mode import`, `--mode export`, and `--mode validate`. Show argument parsing and dispatch to placeholder methods only; do not implement any game mode or project behavior. Discuss unknown options, missing values, defaults, and how to test each mode.

**Learning example to request:**

```java
String mode = args.length >= 2 && args[0].equals("--mode")
        ? args[1]
        : "validate";

if (mode.equals("import")) {
    System.out.println("import placeholder");
} else if (mode.equals("export")) {
    System.out.println("export placeholder");
} else if (mode.equals("validate")) {
    System.out.println("validate placeholder");
} else {
    throw new IllegalArgumentException("Unknown mode");
}
```

### F10.2 - README documentation

**Prompt:**

> Help me plan a README for a small Java command-line tool. Use a fictional unit-conversion project as the example and propose headings for purpose, prerequisites, setup, commands, tests, design decisions, and limitations. Include a tiny Markdown command example, but do not write documentation for my game or invent my findings. Ask me questions that my own README must answer.

**Learning example to request:**

```markdown
## Run

```text
java -jar converter.jar --from km --to mi 10
```
```

### F10.3 - All unit tests passing

**Prompt:**

> Teach me how to interpret a unit-test suite as evidence rather than as a checkbox. Use a Java example with tests for a `TemperatureConverter`, including a failed boundary test and how to diagnose it. Give me a coverage checklist for interfaces, invariants, terminal cases, invalid inputs, and regression cases in `[chosen game]`. Do not write my tests or implementation.

**Learning example to request:**

```java
@Test
void convertsFreezingPoint() {
    assertEquals(32.0, toFahrenheit(0.0), 0.001);
}
```

### F10.4 - Code review for clarity and style

**Prompt:**

> Act as a code reviewer for a small Java utility, but do not rewrite it. Show a short fictional example with unclear names and duplicated logic, then explain how to review naming, method size, comments, cohesion, and consistency. Give me a review checklist I can apply to my project and questions that preserve my ownership of the refactoring.

**Learning example to request:**

```java
int f(int x, int y) {
    return x > y ? x : y;
}
```

## A reusable follow-up prompt

After using any prompt above, students can ask:

> Quiz me on the concept you just explained. Ask one question at a time, require me to predict behavior before showing an answer, and use a small example unrelated to my game. Do not generate implementation code for any requirement in `PRD.md`. When I answer incorrectly, explain the misconception and give me a smaller example.
