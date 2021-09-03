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
docker-compose up -d web nmap firmware firmwalker

docker-compose run cli
```

## Basic usage

Once the tool is running you can choose between one of the next options:

- List and identify devices
- Firmware analysis
- Analyze open ports and exposed services
- List possible vulnerabilities.



