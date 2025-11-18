#!/bin/bash
./gradlew classes
gnome-terminal -- java -cp build/classes/java/main core.GameManager
