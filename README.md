[![Java v15][shield-java]](https://openjdk.java.net/projects/jdk/15/)
[![Spring Boot v2.4.4][shield-spring-boot]](https://spring.io/projects/spring-boot)

#How to run project

###Clone repo

```shell
git clone https://github.com/blejson/QuizApp-Server.git
cd QuizApp-Server
```

###Build docker image

```shell
docker build -f Dockerfile -t quiz-app .
```

###Run docker container

```shell
docker run -p 8080:8080 quiz-app
```

[shield-java]: https://img.shields.io/badge/Java-15-blue.svg
[shield-spring-boot]: https://img.shields.io/badge/Spring_Boot-2.4.4-blue.svg
