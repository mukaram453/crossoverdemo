# crossoverdemo

Instructions to install and configure prerequisites or dependencies
	
	Please follow the below links to install the required softwares

	a. Java Installation 
	   https://www.wikihow.com/Install-the-Java-Software-Development-Kit
	   
	b. Maven Installation
	   http://www.java2s.com/Tutorials/Java/Maven_Tutorial/1010__Maven_Setup.htm

	c. Git Installation
	   https://gist.github.com/derhuerst/1b15ff4652a867391f03

	d. Firefox Browser (Latest Version 57.0.2 (64-bit))
	   http://kb.mozillazine.org/Installing_Firefox

	e. Chrome Browser (Latest Version 63.0.3239.84 (Official Build) (64-bit))
		https://support.google.com/chrome/answer/95346?co=GENIE.Platform%3DDesktop&hl=en-GB
	   
	f. xvfb installation for linux only (headless mode test case execution) 
	   apt-get install -y xvfb
	   
	   
	   
Instructions to configure and prepare the source code to build and run properly
	
	Once installed all the above required softwares in section 1 then
	
	The source code folder ATC contains one folder named "src" and one file named "pom.xml"
	
	Go inside the ATC folder and run the below commands to build the source code and run the automated test cases
	
		commands:			
		
			i.  mvn clean install test -Dbrowser=firefox     (To run the automated test cases in firefox browser in non headless mode i.e full display mode) 
			ii. mvn clean install test -Dbrowser=chrome     (To run the automated test cases in chrome browser in non headless mode i.e full display mode) 
			
			iii.  mvn clean install test -Dbrowser=firefox -Dheadless=true     (To run the automated test cases in firefox browser in headless mode i.e xfvb mode) 
			iv.   mvn clean install test -Dbrowser=chrome  -Dheadless=true   (To run the automated test cases in chrome browser in headless mode i.e xfvb mode) 
			
			For linux environments to run the automated test cases in headless mode please run the below two commands before running the above iii. or iv.

				export DISPLAY=:20
				Xvfb :20 -screen 0 1366x768x16 &
