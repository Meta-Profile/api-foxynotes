FROM openjdk:17-jdk-alpine

COPY . /src
WORKDIR /src

EXPOSE 8080

RUN /src/gradlew build

COPY --from=build /src/build/libs/api-0.0.1-SNAPSHOT.jar /src/api.jar

ENTRYPOINT ["java","-jar","/src/api.jar"]
