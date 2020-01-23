#!/bin/bash   
cur_dir=$(pwd)
project_folder="/home/shlomi/AndroidStudioProjects/Test"
cd $project_folder
./gradlew assembleRelease #Compile, and build, the app (java to apk)
apktool d -f $project_folder/app/build/outputs/apk/release/app-release-unsigned.apk -o $cur_dir/apktool_output/ #Depackage apk to smali
