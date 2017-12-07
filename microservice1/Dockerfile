FROM openjdk:8-jre-alpine
MAINTAINER Luigi Leoni

# Run commands
RUN apk --no-cache add \
    curl \
    openssl && \
    addgroup -g 1000 microuser && \
    adduser -u 1000 -D -s /sbin/nologin -g "MicroUser" -G microuser microuser

WORKDIR /usr/src/app
ADD target/microservice1-0.0.1-SNAPSHOT.jar . 
ENV SERVER_PORT=8080
EXPOSE 8080
USER microuser

CMD ["java", "-jar","microservice1-0.0.1-SNAPSHOT.jar","--server-port=$SERVER_PORT"]
