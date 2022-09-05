#!/bin/bash
DIRECTORY=data/directories
if [ ! -d "$DIRECTORY" ]; then
  echo "$DIRECTORY does not exist 1."
  mkdir -p data/directories
fi

DIRECTORY_SSL=data/testssl
if [ ! -d "$DIRECTORY_SSL" ]; then
  echo "$DIRECTORY_SSL does not exist."
  mkdir -p data/testssl
fi

DIRECTORY_NMAP=data/nmap
if [ ! -d "$DIRECTORY_NMAP" ]; then
  echo "$DIRECTORY_NMAP does not exist."
  mkdir -p data/nmap
fi

docker-compose up -d web testsssl firmware firmwalker directories nmap
