FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV EMAIL_HOST="my host"
ENV EMAIL_PORT="port number"
ENV EMAIL_USERNAME="my username"
ENV EMAIL_PASSWORD="my password"
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]