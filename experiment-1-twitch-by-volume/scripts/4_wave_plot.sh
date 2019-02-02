#!/bin/bash

gnuplot -e "set terminal png size 100000,1000; set output 'data/plot.png'; plot 'data/wave.data'"

