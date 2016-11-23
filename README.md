
About ??

	Hi EveryOne.

	Have you thinking before how cronJobs works..., if for exemple i said you have N Tasks to be executed periodicly (every day, just one time.....)
	how can you implement that, what's the algorithm behind...

 ++ in Linux Operating system this is the algorithm that implemented :

		1 . On start-up, look for a file named .crontab in the home directories of all account holders.
		2 . For each crontab file found, determine the next time in the future that each command is to be run.
	     3 . Place those commands on the Franta-Maly event list with their corresponding time and their time specifier.
		4 . Enter main loop:
			A . Examine the task entry at the head of the queue, compute how far in the future it is to be run.
			B . Sleep for that period of time.
			C . On awakening and after verifying the correct time, execute the task at the head of the queue (in background) with the                   privileges of the user who created it.
			D . Determine the next time in the future to run this command and place it back on the event list at that time

		source : http://stackoverflow.com/questions/3982957/how-does-cron-internally-schedule-jobs

 ++ in Hybris SAP solutions they implements another algorithm, you can check for it on their web-site :
      https://wiki.hybris.com/display/release5/Release+5+Documentation+Home

	 and so many others...

	 in the project Bellow you'll find my own implementaion of CronJobs.

	 Good luck every one.



How To Test ?

	- under the package : src.jobs.impl add you own java classes. 
		Requirment : 
			your java classes must implements the Job Interface.

	- put your logic to execute in "run" methode of your java class.

	- under the properties directory you'll find a file called schudler.properties

	- add in that file the name of your java class as key, and as value put the time in whitch the job have to be executed periodiclly (in      sec)
	 
	-be happy that's all...


Requirment ?

	JAVA 8.

External Libraries ?

	+ extCos 
		- For the matters of the Extensible Component Scanner 
		- more details : https://sourceforge.net/projects/extcos/

	+ slf4j
		- The Simple Logging Facade for Java 
		- more details : http://www.slf4j.org/

	+ Junit
		- For unit test
		- more details : http://junit.org/junit4/

Licence : 

	OpenSource





