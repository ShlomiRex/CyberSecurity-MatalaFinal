#!/bin/bash
sudo apt install python-pip
pip install numpy scipy sklearn pebble joblib==0.11 networkx glob2 androguard
pip install -U androguard[magic,graphing,GUI]
sudo apt install androguard
