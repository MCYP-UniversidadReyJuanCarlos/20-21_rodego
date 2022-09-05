#!/bin/bash
DIRECTORY=data/directories
if [ ! -d "$DIRECTORY" ]; then
  mkdir -p data/directories
fi

DIRECTORY_SSL=data/testssl
if [ ! -d "$DIRECTORY_SSL" ]; then
  mkdir -p data/testssl
fi

DIRECTORY_NMAP=data/nmap
if [ ! -d "$DIRECTORY_NMAP" ]; then
  mkdir -p data/nmap
fi

docker-compose up -d web testsssl firmware firmwalker directories nmap
