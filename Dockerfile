FROM openliberty/open-liberty:21.0.0.10-full-java11-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Tyler Senter" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  name="pdf-validator" \
  version="$VERSION-$REVISION"

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/
COPY --chown=1001:0 config.json /config/validator/config.json

RUN configure.sh