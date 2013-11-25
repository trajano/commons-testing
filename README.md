Trajano Testing
===============

This project sets up the organizational standard testing libraries and
utilities.  The focus of this library is unit testing.  Integration testing
with tools such as Selenium is out of scope of this project.

Normally, library versions are dependent on the project, but testing code 
tends to have several common functions that are not available in *popular*
libraries.  This project addressess the common testing functionality
at the expense of version locking some dependencies.

This library is also built to work with Java SE 6 environments in order to
maximize compatibility.
