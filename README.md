Commons Testing
===============

This project sets up the organizational standard testing libraries and
utilities.  The focus of this library is unit testing.  Integration testing
with tools such as Selenium is out of scope of this project.

Normally, library versions are dependent on the project, but testing code 
tends to have several common functions that are not available in other popular 
projects such as [JUnit][], [Mockito] or [Apache Commons][].

libraries.  This project addresses the common testing functionality
at the expense of version locking some dependencies.

This library is also built to work with Java SE 6 environments in order to
maximize compatibility.

At present this implements [Utility class validation][1] and 
[SSL certificate check bypass][2].

[1]: http://www.trajano.net/2013/04/covering-utility-classes/
[2]: http://www.trajano.net/2006/07/ssl-bypass-with-httpunit/
[JUnit]: http://junit.org/
[Mockito]: http://code.google.com/p/mockito/
[Apache Commons]: http://commons.apache.org/
