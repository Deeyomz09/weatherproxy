# build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# copy maven wrapper + pom first (better caching)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# download dependencies (cache layer)
RUN ./mvnw -q -DskipTests dependency:go-offline

# now copy source
COPY src/ src/

# build
RUN ./mvnw -q -DskipTests package

# run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
