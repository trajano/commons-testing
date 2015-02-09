Commons Testing
===============

This project sets up the organizational standard testing libraries and
utilities.  The focus of this library is unit testing.  Integration testing
with tools such as [Selenium] is out of scope of this project.

Normally, library versions are dependent on the project, but testing code 
tends to have several common functions that are not available in other popular 
projects such as [JUnit][], [Mockito][] or [Apache Commons][].  However, like
[Mockito][] and [Selenium][], this project does not introduce any dependency
to the project.

This project implements [Utility class validation][1] and 
[SSL certificate check bypass][2].  The [SSL certificate check bypass][2] code
can also be used to [bypass certificate checks in JAX-RS][3] 

From version 2.0.0 and above, Java 7 is required for the library to function.
The older version of this artifact which works with Java 6 can be obtained with
the following dependency.

    <dependency>
        <groupId>net.trajano.commons</groupId>
        <artifactId>commons-testing</artifactId>
        <version>1.0.1</version>
    </dependency>
 

[1]: http://www.trajano.net/2013/04/covering-utility-classes/
[2]: http://www.trajano.net/2006/07/ssl-bypass-with-httpunit/
[3]: http://www.trajano.net/2015/02/ssl-certificate-bypass-for-jax-rs/
[JUnit]: http://junit.org/
[Mockito]: http://code.google.com/p/mockito/
[Apache Commons]: http://commons.apache.org/
[Selenium]: http://www.seleniumhq.org/
