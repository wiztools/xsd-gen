#!/bin/sh

docker run --rm -v "$PWD":/build -w /build snapcore/snapcraft bash -c "apt update && snapcraft"

