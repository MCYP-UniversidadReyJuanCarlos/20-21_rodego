FROM maven as builder
COPY . /tfm/
WORKDIR /tfm
RUN mvn package -DskipTests=true -X

FROM openjdk:11.0.5-jre-slim
COPY --from=builder /tfm/target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "tfm-0.0.1-SNAPSHOT.jar" ]  