FROM openjdk:17-jdk-alpine

COPY . /src
WORKDIR /src

EXPOSE 8080

RUN /src/gradlew build

RUN ls build/**/*

COPY /src/build/libs/src-0.0.1-SNAPSHOT.jar /src/api.jar

ENTRYPOINT ["java","-jar","/src/api.jar"]
