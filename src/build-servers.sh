#!/bin/bash
find . -name "*.java" > sources.txt
javac -cp "./../lib/*:." -target 1.8 @sources.txt
rm -rf sources.txt

rm -rf ../dist/
mkdir -p ../dist/

jar cfm '../dist/wob-lobby.jar' '../manifests/lobby.manifest.txt' *
jar cfm '../dist/sdv-lobby.jar' '../manifests/sdv.manifest.txt' *

