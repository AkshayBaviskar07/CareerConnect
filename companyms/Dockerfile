FROM openjdk:17
ADD target/companyms-0.0.1-SNAPSHOT.jar company-service.jar
ENTRYPOINT ["java", "-jar", "company-service.jar"]
ENV SPRING_PROFILES_ACTIVE=dev