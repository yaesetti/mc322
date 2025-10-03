#!/bin/bash

# Clean and create bin directory
rm -rf bin
mkdir -p bin

# Compile all source and test files with JUnit in classpath
find . -name "*.java" -not -path "./bin/*" | xargs javac --release 21 -cp "lib/junit-platform-console-standalone-6.0.0.jar" -d bin

# Run tests
java -cp "bin:lib/junit-platform-console-standalone-6.0.0.jar" org.junit.platform.console.ConsoleLauncher execute --scan-classpath