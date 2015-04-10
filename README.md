#jqcdm Java Library

##Description
jqcdm is a very small library (well more a random bundle of classes) to get started with the QCDM protocol in Java. It
was initially written by using data, code and concepts from ModemManager libqcdm library.


##Credits
All credits should go to the [ModemManager project] (http://www.freedesktop.org/wiki/Software/ModemManager/). This
project alone made it possible to start working on this small Java port.


##Dependencies
Currently the library depends on following external libraries to build correctly:
* [jssc](https://code.google.com/p/java-simple-serial-connector/), Version 2.8.0, GNU LGPL
* [Apache log4j](http://logging.apache.org/log4j/), Version 2.1, Apache License 2.0

The log4j Framework is used, as logging framwork. Because the jqcdm lib isn't big, if anyone doesn't like
the log4j Framework it should be doable to remove every reference in a reasonable amount of time.
The above list may not be correct at all times. Have a look at the `ivy.xml` file to be sure.

The build system depends on the following elements:
* a Java JDK (of course)
* Apache Ant
* Apache Ivy (will be downloaded by the ant file, if `ivy.jar` is not present)
