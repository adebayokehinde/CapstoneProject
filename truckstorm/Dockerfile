
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17.0.1-jdk-slim
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring
COPY --from=build /app/target/truckstorm-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

