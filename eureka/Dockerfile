FROM openjdk:8-jre-alpine

RUN apk --no-cache add \
    curl \
    openssl && \
    addgroup -g 1000 eureka && \
    adduser -u 1000 -D -s /sbin/nologin -g "Eureka" -G eureka eureka

COPY target/eureka-0.0.1-SNAPSHOT.jar .

EXPOSE 8761

USER eureka

CMD ["java","-jar","eureka-0.0.1-SNAPSHOT.jar","--server.port=8761"]
