FROM mcr.microsoft.com/devcontainers/java:21-bookworm

RUN apt-get update && apt-get install -y \
    git \
    sudo \
    zip \
    curl \
    && rm -rf /var/lib/apt/lists/*

ARG MAVEN_VERSION=3.9.6
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven

ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

USER vscode
WORKDIR /workspaces/technical.assessment/java
