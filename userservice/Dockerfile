FROM openjdk:15-alpine
WORKDIR /app
COPY ./build/libs/userservice-0.0.1.jar /app
CMD ["cd","/app"]
EXPOSE 8080
CMD ["java","-jar","userservice-0.0.1.jar"]