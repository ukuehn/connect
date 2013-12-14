Connect README
===

Connect is a simple connection tester. It tries to open a tcp
connection to the given host and given port, and if successful, closes
it. The return value is 0 on success or 1 on failure.

Installation
---

Type "ant".


Normal usage
---

To test connectivity just run

	java -jar connect.jar host:port

or, for quiet output

	java -jar connect.jar -q host:port



Credits
---

connect is developed by Ulrich Kuehn (ukuehn AT acm.org) and released
under GPL v2 or later.
