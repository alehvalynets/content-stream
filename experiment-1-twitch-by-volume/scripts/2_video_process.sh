#!/bin/bash

FREQ=10000

mkdir -p data/ts_fixed

for f in `cat data/tsfiles.list`; do ffmpeg -i data/ts_download/$f -c:v copy -c:a copy data/ts_fixed/$f; done

cat data/tsfiles.list | sed -e "s#^#file '#" -e "s#\$#'#" > data/ts_fixed/tsfiles.ffmpeg.list

ffmpeg -y -f concat -i data/ts_fixed/tsfiles.ffmpeg.list -acodec pcm_s16le -ac 1 -ar $FREQ data/audio.wav

ffmpeg -y -f concat -i data/ts_fixed/tsfiles.ffmpeg.list -c:v copy -c:a copy data/video.ts
