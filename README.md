# 20-21_rodego

## Title

<h1 align="center">audIoTvuln</h1>

## Project description

audIoTvuln is a tool that allows users to analyze and audit vulnerabilities or bad configurations to the IoT devices connected to the network.

## Architecture

This tool use Docker to run and http protocol for the communication between services.
All services mount a shared Docker volume in order to share data and files between them.

## Prerequisites

You need Docker and Docker Compose installed.

## How to run

To run the project execute the following commands from the main directory.

```
docker-compose up -d web nmap firmware firmwalker directories testssl

docker-compose run --rm cli
```

## Basic usage

Once the tool is running you can choose between one of the next options:

- Run analysis
- Firmware analysis: Firmware file should be in the data folder within the project.

When analysis is finisehd you could see the results on your browser by opening:

[Application home]](http://localhost:9090/home)

### Notes:

You need to run the service cli each time you want to use the tool.

This tool has been tested in a Linux environment, it should run on Windows and Mac but you could face some limitations with Docker on those OS.
