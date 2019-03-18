#!/bin/bash
find . -name "*.java" > sources.txt
javac -cp "./../lib/*:." -target 1.8 @sources.txt
rm -rf sources.txt
jar cfm '../dist/wob-lobby.jar' '../manifests/lobby.manifest.txt' *
jar cfm '../dist/sdv-lobby.jar' '../manifests/sdv.manifest.txt' *
chmod -x '../dist/wob-lobby.jar'
chmod -x '../dist/sdv-lobby.jar'

