# GPU-support-in-Apache-Spark
•	Tests the performance of sorting in Spark w/ and w/o GPU.  
•	Used 'Boost.Compute' GPU library for sorting primitive data types within Spark.  
•	Concluded that sorting of inbuilt data types is much faster on GPU.  
•	However, sorting of user defined data types requires extra overhead and significantly slows down the sorting.  
