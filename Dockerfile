FROM openjdk:15
ADD target/quiz-app.jar quiz-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","quiz-app.jar"]