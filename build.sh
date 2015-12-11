#!/bin/sh
javac -classpath "./jogamp-all-platforms/jar/gluegen-rt.jar:./jogamp-all-platforms/jar/jogl-all.jar" src/Main.java
exit $?

