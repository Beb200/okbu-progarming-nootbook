# Product Requirements Document
## Data Structures Library: Queues & Stacks
**Course:** Sophomore-Level Computer Science — Data Structures
**Version:** 1.2
**Date:** August 27, 2026

---

## 1. Overview

This document defines the requirements for a Java-based data structures library implementing **Queue** and **Stack** abstract data types (ADTs). The library is intended as a hands-on programming project for students to demonstrate understanding of linear data structures, object-oriented design, and software engineering best practices using Maven as the build system.

> ⚠️ **Academic Integrity Notice:** Students must implement all Stack and Queue data structures **from scratch**. The use of any existing Java stack or queue libraries — including but not limited to `java.util.Stack`, `java.util.Queue`, `java.util.LinkedList`, `java.util.ArrayDeque`, or any third-party equivalents — is **strictly prohibited**. Submissions found to be using these libraries will receive a grade of zero.

---

## 2. Goals & Learning Objectives

By completing this project, students will be able to:

- Implement Stack and Queue ADTs from scratch in Java **without relying on existing library implementations**
- Apply object-oriented principles (interfaces, generics, encapsulation)
- Write and execute unit tests using JUnit within a Maven project
- Follow standard Java project structure and build tooling conventions
- Use Git and GitHub for version control and branch-based development workflows

---

## 3. Scope

| In Scope | Out of Scope |
|---|---|
| Stack implementation (from scratch) | GUI or web interface |
| Queue implementation (from scratch) | Persistent storage / databases |
| Deque (optional stretch goal) | Concurrent/thread-safe variants (optional stretch) |
| JUnit test harness | External library dependencies beyond JUnit |
| Maven build configuration | Alternative build tools (Gradle, Ant) |
| Javadoc documentation | Full API documentation site |
| GitHub repository with branch setup | CI/CD pipeline configuration |
| Custom array and linked list backing structures | Use of `java.util.Stack`, `java.util.Queue`, or equivalent libraries |

---

## 4. Prohibited Libraries & Academic Integrity

### 4.1 Prohibited Java Standard Library Classes

The following Java standard library classes **must not** be used anywhere in the `src/main/java/` source tree:

| Prohibited Class | Why It's Prohibited |
|---|---|
| `java.util.Stack` | Directly implements a stack — defeats the purpose of the assignment |
| `java.util.Queue` | Interface that pre-implements queue behavior |
| `java.util.LinkedList` | Provides ready-made linked list with queue/deque operations |
| `java.util.ArrayDeque` | Provides ready-made array-backed deque with stack/queue operations |
| `java.util.PriorityQueue` | Pre-built queue implementation |
| `java.util.concurrent.*` queue/stack classes | Pre-built concurrent queue/stack implementations |

> ✅ **Permitted:** Primitive arrays (e.g., `Object[]`), basic Java language constructs, and custom node/linked list classes written by the student are all allowed and encouraged.

### 4.2 Permitted Dependencies

The only external dependency permitted in `pom.xml` is:

- **JUnit 5 (Jupiter)** — for the test harness only (`test` scope)
- **JaCoCo Maven Plugin** — for code coverage reporting only

Any other dependency added to `pom.xml` must be approved in writing by the instructor before use.

### 4.3 Enforcement

- Instructors will review `pom.xml` and `import` statements during grading
- Any `import java.util.Stack`, `import java.util.Queue`, `import java.util.LinkedList`, `import java.util.ArrayDeque`, or equivalent found in student source files will result in **automatic disqualification of the affected implementation**
- Students are encouraged to ask the instructor if they are unsure whether a particular class is permitted

---

## 5. Functional Requirements

### 5.1 Stack ADT

The library **must** implement a `Stack<T>` using a generic interface with the following operations:

| Method | Signature | Description |
|---|---|---|
| Push | `void push(T item)` | Add item to top of stack |
| Pop | `T pop()` | Remove and return top item |
| Peek | `T peek()` | Return top item without removing |
| isEmpty | `boolean isEmpty()` | Return true if stack is empty |
| size | `int size()` | Return number of elements |
| clear | `void clear()` | Remove all elements |

**Required Implementations (at least one):**
- `ArrayStack<T>` — backed by a **student-implemented** resizable array (using `Object[]`)
- `LinkedStack<T>` — backed by a **student-implemented** singly linked list using a custom `Node<T>` class

**Error Handling:**
- `pop()` and `peek()` on an empty stack must throw `EmptyStackException`
- Array-backed implementation must handle dynamic resizing (doubling strategy)

---

### 5.2 Queue ADT

The library **must** implement a `Queue<T>` using a generic interface with the following operations:

| Method | Signature | Description |
|---|---|---|
| Enqueue | `void enqueue(T item)` | Add item to rear of queue |
| Dequeue | `T dequeue()` | Remove and return front item |
| Peek | `T peek()` | Return front item without removing |
| isEmpty | `boolean isEmpty()` | Return true if queue is empty |
| size | `int size()` | Return number of elements |
| clear | `void clear()` | Remove all elements |

**Required Implementations (at least one):**
- `ArrayQueue<T>` — backed by a **student-implemented** circular array (using `Object[]`)
- `LinkedQueue<T>` — backed by a **student-implemented** singly linked list using a custom `Node<T>` class

**Error Handling:**
- `dequeue()` and `peek()` on an empty queue must throw `NoSuchElementException`
- Circular array implementation must handle wrap-around indexing correctly

---

### 5.3 Shared Interface Requirements

- All ADTs must be defined as **Java interfaces** (e.g., `StackInterface<T>`, `QueueInterface<T>`)
- All implementations must use **Java Generics** (`<T>`)
- `null` values **must not** be permitted as elements; throw `IllegalArgumentException` if attempted
- All classes must include **Javadoc comments** on public methods
- Backing data structures (arrays, nodes) must be **written by the student** — not sourced from `java.util` or any third-party library

---

## 6. Non-Functional Requirements

| Category | Requirement |
|---|---|
| Language | Java 17 or later |
| Build Tool | Apache Maven 3.8+ |
| Testing Framework | JUnit 5 (Jupiter) |
| Code Style | Google Java Style Guide or equivalent |
| Documentation | All public methods must have Javadoc |
| Packaging | Deliverable as a `.jar` via `mvn package` |
| Version Control | GitHub repository with required branch structure |
| Library Restrictions | No `java.util` stack/queue classes; all implementations must be original |

---

## 7. GitHub Repository Requirements

### 7.1 Repository Setup

- Students must create a GitHub repository named **`ds-library`**
- The repository must be initialized with a `README.md`
- The default branch (`main`) must remain intact and represent the stable baseline

### 7.2 Branch Requirements

- Students must create a branch named exactly **`ds-library`** from `main`
- All project development work must be committed to the `ds-library` branch
- The `ds-library` branch must be pushed to the remote GitHub repository and be visible at:
  `https://github.com/<username>/ds-library/tree/ds-library`

### 7.3 Branch Creation Instructions

Students may create the required branch using either method below:

**Via Git CLI:**
```bash
git clone https://github.com/<username>/ds-library.git
cd ds-library
git checkout -b ds-library
git push -u origin ds-library
```

**Via GitHub UI:**
1. Navigate to the repository on GitHub
2. Click the branch dropdown (defaults to `main`)
3. Type `ds-library` in the search box
4. Select **"Create branch: ds-library from main"**

### 7.4 Commit Expectations

- Students must make **at least 5 meaningful commits** on the `ds-library` branch
- Commit messages must be descriptive (e.g., `Add ArrayStack implementation`, `Add JUnit tests for Queue`)
- A single "dump all code" commit is not acceptable

### 7.5 Final Submission

- The `ds-library` branch must be up to date with all deliverables before the submission deadline
- Students must submit the URL to their `ds-library` branch as part of their assignment submission

---

## 8. Test Harness Requirements

### 8.1 Structure

The test harness must be implemented using **JUnit 5** and located in `src/test/java/`. Maven's `mvn test` command must execute all tests successfully.

### 8.2 Required Test Cases

**Stack Tests** (`StackTest.java`):

- [ ] Push single element, verify size = 1
- [ ] Push multiple elements, verify LIFO order on pop
- [ ] Peek does not remove element
- [ ] Pop on empty stack throws `EmptyStackException`
- [ ] Peek on empty stack throws `EmptyStackException`
- [ ] isEmpty returns true on new stack, false after push
- [ ] clear() results in empty stack
- [ ] Push null throws `IllegalArgumentException`
- [ ] Dynamic resizing works correctly (array implementation)
- [ ] Large volume test (1,000+ elements)

**Queue Tests** (`QueueTest.java`):

- [ ] Enqueue single element, verify size = 1
- [ ] Enqueue multiple elements, verify FIFO order on dequeue
- [ ] Peek does not remove element
- [ ] Dequeue on empty queue throws `NoSuchElementException`
- [ ] Peek on empty queue throws `NoSuchElementException`
- [ ] isEmpty returns true on new queue, false after enqueue
- [ ] clear() results in empty queue
- [ ] Enqueue null throws `IllegalArgumentException`
- [ ] Circular array wrap-around works correctly (array implementation)
- [ ] Large volume test (1,000+ elements)

### 8.3 Test Coverage Expectation

- Minimum **80% code coverage** (measured via JaCoCo Maven plugin)
- All edge cases (empty structure, single element, capacity boundary) must be covered

---

## 9. Maven Project Structure

```
ds-library/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── edu/course/ds/
    │           ├── interfaces/
    │           │   ├── StackInterface.java
    │           │   └── QueueInterface.java
    │           ├── stack/
    │           │   ├── ArrayStack.java
    │           │   └── LinkedStack.java
    │           └── queue/
    │               ├── ArrayQueue.java
    │               └── LinkedQueue.java
    └── test/
        └── java/
            └── edu/course/ds/
                ├── stack/
                │   └── StackTest.java
                └── queue/
                    └── QueueTest.java
```

---

## 10. Maven `pom.xml` Requirements

The `pom.xml` must include:

- **Group ID:** `edu.course`
- **Artifact ID:** `ds-library`
- **Java version:** 17
- **Dependencies:**
  - `junit-jupiter` (JUnit 5) — test scope only
- **Plugins:**
  - `maven-compiler-plugin` (Java 17)
  - `maven-surefire-plugin` (JUnit 5 support)
  - `jacoco-maven-plugin` (code coverage)

> ⚠️ No additional dependencies may be added without instructor approval.

---

## 11. Deliverables

| Deliverable | Description |
|---|---|
| Source Code | All `.java` files in proper Maven structure — no prohibited library imports |
| `pom.xml` | Fully configured Maven build file with only permitted dependencies |
| Test Suite | JUnit 5 tests passing via `mvn test` |
| Coverage Report | JaCoCo report generated via `mvn verify` |
| `README.md` | Build instructions, project description, design decisions |
| Javadoc | Generated via `mvn javadoc:javadoc` |
| GitHub Branch | `ds-library` branch pushed to remote repository |
| Branch URL | Submitted link to `ds-library` branch on GitHub |

---

## 12. Stretch Goals *(Optional)*

- Implement a `Deque<T>` (double-ended queue) supporting both stack and queue operations
- Add a thread-safe wrapper using `synchronized` or `java.util.concurrent` locks *(instructor approval required)*
- Implement an iterator for each data structure
- Benchmark performance comparison between array-backed and linked-list-backed implementations
- Open a Pull Request from `ds-library` into `main` with a descriptive summary

---

## 13. Acceptance Criteria

The project is considered complete when:

1. ✅ All required Stack and Queue operations are implemented **from scratch**
2. ✅ Both array-backed and linked-list-backed implementations exist
3. ✅ No prohibited `java.util` stack/queue classes are imported in source files
4. ✅ `pom.xml` contains only permitted dependencies
5. ✅ `mvn test` runs with **zero failures**
6. ✅ JaCoCo reports **≥ 80% code coverage**
7. ✅ All public methods have Javadoc comments
8. ✅ `mvn package` produces a valid `.jar` artifact
9. ✅ `README.md` is present and complete
10. ✅ GitHub repository exists with a `ds-library` branch pushed to remote
11. ✅ At least 5 meaningful commits exist on the `ds-library` branch
12. ✅ Branch URL has been submitted as part of the assignment
