Sorting :

----------------------------------------------------------------------------

> Description : 

> How to Run :

> How to 'Work' with Program : 

> Protocol Description : 

----------------------------------------------------------------------------

> Task 1 : 
(1)
	Main structure :
	Advantages :
		- The system makes best use of the code provided; by making Node.java an abstract class, the Sorter.java and Branch.java classes are able to extend the Node.java class to implement the core behavior and then their varying behavior thereafter.
		

	Disadvantages :
		- The system is static; there is no dynamic interaction with the system allowing the end-user to make requests on their own accord. It runs the tests specified in main and then hangs afterwards.
		- Moreover on the dynamic-level of the system: It does not handle new, incoming requests for added branches and sorters to the system. In allowing dynamic requests, more tests could be created for the system as well as build the system to a greater scale. 
		- The tests provided, while testing different options for numbers of branches and sorters, do not test different methods of the programs or different scales of data. I.E. the system should have multiple tests to see how the varied branch numbers handle different volumes of incoming data. 

(2)
	Experiment : Create two more arrays of size 100 and 1,000 respectively. These are then populated with random numbers between 0-100 and 0-1,000 respectively. Each of current 3 variations of Branch and Sorter combinations will then run the 3 different types of arrays and print out the amount of milliseconds each respective test takes to finish. The following table summarizes the data:

| Branch/Sorter Combination     | Array Size | Time (ms) |
| ----------------------------- | ---------- | --------- |
| 0 Branch(es) / 1 Sorter(s)    | 14         | 67        |
| 0 Branch(es) / 1 Sorter(s)    | 100        | 116       |
| 0 Branch(es) / 1 Sorter(s)    | 1000       | 1043      |

| 1 Branch(es) / 2 Sorter(s)    | 14         | 44        |
| 1 Branch(es) / 2 Sorter(s)    | 100        | 239       |
| 1 Branch(es) / 2 Sorter(s)    | 1000       | 1958      |

| 3 Branch(es) / 4 Sorter(s)    | 14         | 113       |
| 3 Branch(es) / 4 Sorter(s)    | 100        | 613       |
| 3 Branch(es) / 4 Sorter(s)    | 1000       | 5433      |

	Result/Explanation : The distribution does not aid in the runtime speed of sorting the data provided. With larger volumes of data, having more branches and sorters actually produces almost 5x as long of a speed to do what 1 sorter would have done. This is due to sheer latency of requests, as well as the overhead of merging the data back together. At this point, it becomes increasingly more inefficient to use the sorting algorithm on the data. In fact, the only instance in which using more sorters/branches helped was with the smaller array of size 14. This actually produced faster results than the single sorter on its own showing some manner of value in sorting smaller arrays. 

(3)
	Experiment : Create two more branches to append to the existing setup. One branch will connect to a pre-existing branch and a new sorter creating a dynamic of 2 branches and 3 sorters. The other will be connected to this previously created branch as well as branch that has only 2 starters. In doing so, this will elicit 3 branches and 5 sorters. The following table summarizes the data:

| Branch/Sorter Combination     | Array Size | Time (ms) |
| ----------------------------- | ---------- | --------- |
| 2 Branch(es) / 3 Sorter(s)    | 14         | 66        |
| 2 Branch(es) / 3 Sorter(s)    | 100        | 357       |
| 2 Branch(es) / 3 Sorter(s)    | 1000       | 3548      |

| 3 Branch(es) / 5 Sorter(s)    | 14         | 122       |
| 3 Branch(es) / 5 Sorter(s)    | 100        | 755       |
| 3 Branch(es) / 5 Sorter(s)    | 1000       | 7505      |

	Result/Explanation : As 0 branches / 1 starter & 1 branch / 2 starters are the smallest means to run this system, the created branches fell inside of the existing range and one extending it. As expected, the two new tests fall within the expected range and scale that the previous tests had followed. The increasing size had minimal effect on the smaller array size, but grew exponentially in time for the greater branch/sorter combinations. In theory, spreading the work across multiple servers should be increasing the efficiency as the array size increases, however this is all being done by one computer across multiple servers. Theory : the times will be reduced when work begins to be spread across multiple systems on multiple servers. 

(4)
	With WireShark open and then running 'gradle Starter', WireShark displayed thousands of frames regarding the communication between the different connections. Prior to this, WireShark only displayed several hundred lines for active connections on the WiFi connection, so there is a massive amount of communication going on for the program. 