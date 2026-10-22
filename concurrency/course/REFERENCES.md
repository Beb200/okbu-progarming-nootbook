# References

Bracketed keys (for example **[MK06]**) are used in the tutorials, instructor guides, slide notes and slide footers.

## Primary text and tools

- **[MK06]** Magee, J., & Kramer, J. (2006). *Concurrency: State Models & Java Programs* (2nd ed.). Wiley.
  The structure of this module, the FSP notation, and many of the models (SWITCH, TRAFFICLIGHT, DRINKS, COIN, CHAN, BUFF, SUM, COUNT, COUNTDOWN, WRITER, the thread life-cycle model, FILTER, and the race-condition interleaving table) are taken from this book and its lecture slides.
- **[MK-site]** Magee, J., & Kramer, J. Companion website: lecture slides (revised 2015), LTSA, FSP quick reference, and the book's example programs. <https://www.doc.ic.ac.uk/~jnm/book/>
- **[LTSA]** Labelled Transition System Analyser (LTSA) v3.0, download page. <https://www.doc.ic.ac.uk/~jnm/book/ltsa/download.html>

## Java

- **[JEP444]** Pressler, R., & Bateman, A. (2023). *JEP 444: Virtual Threads* (delivered in JDK 21). OpenJDK. <https://openjdk.org/jeps/444>
- **[JDK-Thread]** Oracle. *Java SE 21 API: `java.lang.Thread` and `Thread.State`.* <https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.State.html>
- **[JTut]** Oracle. *The Java Tutorials: Concurrency.* <https://docs.oracle.com/javase/tutorial/essential/concurrency/>
- **[Goe06]** Goetz, B., Peierls, T., Bloch, J., Bowbeer, J., Holmes, D., & Lea, D. (2006). *Java Concurrency in Practice.* Addison-Wesley. (Background and further reading for instructors; also used in Weeks 4–6.)

## Motivation and case studies (Week 1)

- **[Sut05]** Sutter, H. (2005). The free lunch is over: A fundamental turn toward concurrency in software. *Dr. Dobb's Journal, 30*(3). <http://www.gotw.ca/publications/concurrency-ddj.htm>
- **[Pik12]** Pike, R. (2012, January). *Concurrency is not parallelism.* Talk at Heroku Waza. Slides and video: <https://go.dev/blog/waza-talk>
- **[Kos18]** Kosaka, M. (2018). *Inside look at modern web browser (part 1).* Chrome for Developers blog. <https://developer.chrome.com/blog/inside-browser-part1>
- **[LT93]** Leveson, N. G., & Turner, C. S. (1993). An investigation of the Therac-25 accidents. *IEEE Computer, 26*(7), 18–41. <https://ieeexplore.ieee.org/document/274940>
- **[Ree97]** Reeves, G. E. (1997, December). *Re: What really happened on Mars?* (Email from the Mars Pathfinder flight software lead, responding to M. Jones's summary.) Archived at <https://www.cs.unc.edu/~anderson/teach/comp790/papers/mars_pathfinder_long_version.html>
- **[Jon97]** Jones, M. B. (1997). *What really happened on Mars Rover Pathfinder.* Archived at <https://www.cs.cornell.edu/courses/cs614/1999sp/papers/pathfinder.html>
- **[TF04]** U.S.–Canada Power System Outage Task Force. (2004). *Final Report on the August 14, 2003 Blackout in the United States and Canada: Causes and Recommendations.*
- **[Reg04]** Poulsen, K. (2004, April 8). Software bug contributed to blackout. *The Register* (reprinted from SecurityFocus, "Tracking the blackout bug"). <https://www.theregister.com/2004/04/08/blackout_bug_report/>

## Abstraction and models (Week 1)

- **[Dev03]** Devlin, K. (2003). Why universities require computer science students to take math. *Communications of the ACM, 46*(9), 37–39. (Source of the "computing is all about … abstractions" quotation on the Magee & Kramer slides.)
- **[Kra07]** Kramer, J. (2007). Is abstraction the key to computing? *Communications of the ACM, 50*(4), 36–42.
- **[Gar94]** Garland, K. (1994). *Mr Beck's Underground Map.* Capital Transport. (History of Harry Beck's 1931–33 schematic London Underground map.)
- **[NRZ+15]** Newcombe, C., Rath, T., Zhang, F., Munteanu, B., Brooker, M., & Deardeuff, M. (2015). How Amazon Web Services uses formal methods. *Communications of the ACM, 58*(4), 66–73. <https://cacm.acm.org/magazines/2015/4/184701-how-amazon-web-services-uses-formal-methods/fulltext>
- **[Turing]** ACM A.M. Turing Award citations: E. M. Clarke, E. A. Emerson & J. Sifakis (2007, model checking); A. Pnueli (1996, temporal logic); R. Milner (1991, CCS); C. A. R. Hoare (1980). <https://amturing.acm.org/>
- **[Hoa78]** Hoare, C. A. R. (1978). Communicating sequential processes. *Communications of the ACM, 21*(8), 666–677.
- **[Mil80]** Milner, R. (1980). *A Calculus of Communicating Systems.* Lecture Notes in Computer Science 92. Springer.

## Later weeks (outlines)

- **[Lee06]** Lee, E. A. (2006). The problem with threads. *IEEE Computer, 39*(5), 33–42.
- **[Dij65]** Dijkstra, E. W. (1965). *Cooperating sequential processes* (EWD 123). Semaphores. <https://www.cs.utexas.edu/~EWD/transcriptions/EWD01xx/EWD123.html>
- **[Hoa74]** Hoare, C. A. R. (1974). Monitors: An operating system structuring concept. *Communications of the ACM, 17*(10), 549–557.
- **[CES71]** Coffman, E. G., Elphick, M., & Shoshani, A. (1971). System deadlocks. *ACM Computing Surveys, 3*(2), 67–78.
- **[Dij71]** Dijkstra, E. W. (1971). Hierarchical ordering of sequential processes. *Acta Informatica, 1*(2), 115–138. (The dining philosophers problem.)
- **[JLS17]** Gosling, J., et al. *The Java Language Specification, Java SE 21 Edition*, Chapter 17: Threads and Locks. <https://docs.oracle.com/javase/specs/jls/se21/html/jls-17.html>

## Note on reuse

Content adapted from [MK06] and its slides is credited on each slide where it is used. The original slide decks (`ch1.ppt` … `ch6.ppt`) are © Magee/Kramer. Check the companion site's terms before redistributing adapted slides outside your own course.
