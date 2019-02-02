#!/bin/bash

LINK="https://vod.edgecast.hls.ttvnw.net/d7ff7bb869e74680418f_kugothemighty_31447054224_1034066320/360p30/index-muted-CUVXGO93HG.m3u8"

mkdir -p data/ts_download

PREFIX=$(sed 's#/[^/]*$#/#' <<< "$LINK")

wget "$LINK" -O - | grep -v "^#" > data/tsfiles.list

cat data/tsfiles.list | sed -e "s#^#$PREFIX#" > data/ts_download/tsfiles.download.list

wget -i data/ts_download/tsfiles.download.list -P data/ts_download
