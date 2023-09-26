FROM maven:3.9.3-eclipse-temurin-20-alpine AS maven-build
ENV MAVEN_MIRROR_URL="http://nexus-repo-app-nexus-repo.apps.ocpbarr.correo.local/nexus/content/groups/public/"
ARG MVN_SETTINGS="./settings.xml"
WORKDIR /code
COPY ./ /code/
RUN mvn install -DskipTests=true -e --settings $MVN_SETTINGS

# Build image for deploy
FROM eclipse-temurin:20-jre-alpine AS server
WORKDIR /app
COPY cert/webmaster-dev.crt /app/webmaster.crt
RUN /opt/java/openjdk/bin/keytool -importcert -trustcacerts -keystore /opt/java/openjdk/lib/security/cacerts -noprompt -alias CAOCP3 -file /app/webmaster.crt -storepass changeit
COPY --from=maven-build /code/postal-code-service/target/postal-code-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=development -Xmx256m -Xms256m"
ENTRYPOINT exec java $JAVA_OPTS -jar /app/app.jar
EXPOSE 8080
