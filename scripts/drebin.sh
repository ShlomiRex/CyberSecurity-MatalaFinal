#!/bin/bash
main_drebin_dir="/home/shlomi/Desktop/CyberSecurity-MatalaFinal/drebin/src"

#Drebin example apks
example_dir="/home/shlomi/Desktop/example-apks/example"
maldir="$example_dir/mal_train"
gooddir="$example_dir/ben_train"
testmaldir="$example_dir/mal_test"
testgooddir="$example_dir/ben_test"

#Run drebin
cd $main_drebin_dir
python2 "Main.py" --holdout 1 --maldir $maldir --gooddir $gooddir --testmaldir $testmaldir --testgooddir $testgooddir
