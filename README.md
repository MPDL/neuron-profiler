neuron-profiler
===============


"neuron-profiler" is the User Interface which is used to upload a swc file, show the generated 3D-View and metadata in the browser.
It's the presentation layer of the [swc-service][1]

----------

Installation guide:
--------------------------------
#### **Prerequisites:**<br />
 - Java **7** Development Kit<br />
 - Tomcat **7.0.55** Server<br />
 - Maven **2.2.1** or higher<br />

#### **Install:**<br />
1. clone neuron-profiler source code from [GitHub][2] <br />
2. set Tomcat installation directory in your local settings.xml file:<br />
    ```
    <tomcat.install.dir>TOMCAT_HOME</tomcat.install.dir>
    ```
3. (optional) set your own swc service in the neuron-profiler.properties file under "neuron-profiler/src/main/resources/":
    ```
    swc.3Dview.targetURL = http://YOUR_SWC_SERVICE_DOMAIN/swc/api/view
    swc.analyze.targetURL = http://YOUR_SWC_SERVICE_DOMAIN/swc/api/analyze
    swc.screenshot.targetURL = http://YOUR_SWC_SERVICE_DOMAIN/swc/api/thumb
    ```

#### **Run:**<br />
1. compile the .war and deploy it to the server with "mvn clean install"<br />
2. start your tomcat with running "TOMCAT_HOME/bin/startup.xx"<br />
3. visit the webseit to goto "Http://server:port/neuron-profiler"<br />
4. stop your tomcat with running 'TOMCAT_HOME/bin/shutdown.xx'<br />
 
[1]: https://github.com/MPDL/swc-service
[2]: https://github.com/MPDL/neuron-profiler
