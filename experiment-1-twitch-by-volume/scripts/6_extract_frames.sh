#!/bin/bash

mkdir -p data/frames

ffmpeg -i data_processed/video.ts -r 60 -f image2 data/frames/%10d.png
