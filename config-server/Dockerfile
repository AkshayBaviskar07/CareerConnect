FROM openjdk:17
ADD target/config-server-0.0.1-SNAPSHOT.jar confing-server.jar
ENTRYPOINT ["java", "-jar", "confing-server.jar"]
ENV SPRING_PROFILES_ACTIVE=dev