#!/bin/bash
derbin_folder="/home/shlomi/Desktop/MatalaFinal/drebin-master/src/"
cd "$derbin_folder"

example_dir="/home/shlomi/Desktop/MatalaFinal/example/"
maldir="/home/shlomi/Desktop/MatalaFinal/example/mal_train/"
gooddir="/home/shlomi/Desktop/MatalaFinal/example/ben_train/"
testmaldir="/home/shlomi/Desktop/MatalaFinal/example/mal_test/"
testgooddir="/home/shlomi/Desktop/MatalaFinal/example/ben_test/"


echo python Main.py --holdout 1 --maldir $maldir --gooddir $gooddir --testmaldir $testmaldir --testgooddir $testgooddir

python Main.py --holdout 1 --maldir $maldir --gooddir $gooddir --testmaldir $testmaldir --testgooddir $testgooddir
