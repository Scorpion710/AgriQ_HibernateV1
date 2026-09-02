FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn -B clean package -DskipTests \
    && artifact="$(find target -maxdepth 1 -type f \( -name '*.jar' -o -name '*.war' \) ! -name 'original-*' | head -n 1)" \
    && test -n "$artifact" \
    && cp "$artifact" target/app.jar

FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=build /app/target/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
