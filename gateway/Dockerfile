FROM openjdk:17
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]
ENV SPRING_PROFILES_ACTIVE=dev