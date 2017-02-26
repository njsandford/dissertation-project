#!/bin/bash

EXE="org.jgrapht.demo.PopulateGraph"
RAM="-Xmx512M"

CP="target/dependency/*"
CP="$CP:target/genomecomparisons-1.0-SNAPSHOT.jar"

java $RAM -cp $CP $EXE $@ 


