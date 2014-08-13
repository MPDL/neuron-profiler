neuron-profiler
===============
Introduction: 
"neuron-profiler" is the User Interface which is used to upload a swc file and show the generated 3D-View(and screenshot) in the browser.
It's the presentation layer of the "swc-service"(https://github.com/MPDL/swc-service)




Installation guide:
	-Prerequisites: 
		--Java 7 Development Kit
		--Tomcat 7.0.55 Server
		--Maven 2.2.1 or Maven 3
	--Install:
		--clone neuron-profiler source code from GitHub "https://github.com/MPDL/neuron-profiler"
		--set Tomcat installation directory in your local settings.xml file:
			<tomcat.install.dir>TOMCAT_HOME</tomcat.install.dir>
		--(optional) set your own swc service in the neuron-profiler.properties file under "neuron-profiler/src/main/resources/":
			swc.3Dview.targetURL = http://YOUR_SWC_SERVICE_DOMAIN/swc/api/view
			swc.screenshot.targetURL = http://YOUR_SWC_SERVICE_DOMAIN/swc/api/thumb
	--Run:
		--compile the .war and deploy it to the server with "mvn clean install"
		--start your tomcat with running "TOMCAT_HOME/bin/startup.xx"
		--visit the webseit to goto "Http://server:port/neuron-profiler"
		--stop your tomcat with running "TOMCAT_HOME/bin/shutdown.xx"
 