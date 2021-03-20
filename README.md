docker build -f Dockerfile -t quiz-app .
docker run -p 8080:8080 quiz-app