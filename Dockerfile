 FROM maven:3.9-amazoncorretto-17 AS build
 COPY . .
 RUN mvn clean package -Pprod -DskipTests
 FROM amazoncorretto:17-alpine-jdk
 COPY --from=build /target/*.jar app.jar
 ENTRYPOINT ["java","-jar","app.jar"]