FROM openjdk:17
ADD target/jobms-0.0.1-SNAPSHOT.jar job-service.jar
ENTRYPOINT ["java", "-jar", "job-service.jar"]
ENV SPRING_PROFILES_ACTIVE=dev