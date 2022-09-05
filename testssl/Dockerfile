FROM python:3-alpine

WORKDIR /home/testssl

ARG BUILD_VERSION=3.0
ARG ARCHIVE_URL=https://github.com/drwetter/testssl.sh/archive/
ARG URL=https://github.com/drwetter/testssl.sh.git

RUN test -n "${BUILD_VERSION}" \
    && apk update \
    && apk add --no-cache bash procps drill git coreutils libidn curl socat openssl xxd \
    && git clone --depth 1 --branch ${BUILD_VERSION} $URL /home/testssl \
    && addgroup testssl \
    && adduser -G testssl -g "testssl user" -s /bin/bash -D testssl \
    && ln -s /home/testssl/testssl.sh /usr/local/bin/ \
    && mkdir -m 777 -p /home/testssl/etc /home/testssl/bin /home/testssl/data

RUN pip install flask flask-restful

USER testssl

COPY . .

ENTRYPOINT ["python3", "ssl.py"]
