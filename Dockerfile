FROM openjdk:8

COPY build/distributions/hideapic.tar .
COPY hideapic-ete.yml hideapic.yml

RUN tar xf hideapic.tar

CMD ["hideapic/bin/hideapic", "server", "hideapic.yml"]
