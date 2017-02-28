FROM openjdk:8

COPY build/distributions/hideapic-server.tar .
COPY hideapic-ete.yml hideapic.yml

RUN tar xf hideapic-server.tar

CMD ["hideapic-server/bin/hideapic-server", "server", "hideapic.yml"]
