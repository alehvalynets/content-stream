#!/bin/bash

mkdir -p data/ts_fragments

FFMPEG=$(javac -d bin/ src/*.java && java -cp bin/ FindMoments data/wave.data data/video.ts data/ts_fragments data/ts_fragments/tsfragments.ffmpeg.list)

echo $FFMPEG
eval $FFMPEG

ffmpeg -y -f concat -i data/ts_fragments/tsfragments.ffmpeg.list -c copy data/final_video.mp4
