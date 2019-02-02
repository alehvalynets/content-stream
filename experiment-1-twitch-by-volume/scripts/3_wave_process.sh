#!/bin/bash

mkdir -p bin
javac -d bin/ src/*.java && java -cp bin/ WaveProcess data/audio.wav data/wave.data
