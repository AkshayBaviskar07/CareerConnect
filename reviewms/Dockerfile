FROM openjdk:17
ADD target/reviewms-0.0.1-SNAPSHOT.jar review-service.jar
ENTRYPOINT ["java", "-jar", "review-service.jar"]
ENV SPRING_PROFILES_ACTIVE=dev