#!/bin/bash
main_drebin_script="/home/shlomi/Desktop/CyberSecurity-MatalaFinal/drebin/src/Main.py"

#Drebin example apks
example_dir="/home/shlomi/Desktop/example-apks/example"
maldir="$example_dir/mal_train"
gooddir="$example_dir/ben_train"
testmaldir="$example_dir/mal_test"
testgooddir="$example_dir/ben_test"

#Run drebin
python2 $main_drebin_script --holdout 1 --maldir $maldir --gooddir $gooddir --testmaldir $testmaldir --testgooddir $testgooddir
