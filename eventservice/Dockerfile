FROM openjdk:15-alpine
WORKDIR /app
COPY ./build/libs/eventservice-0.0.2.jar /app
CMD ["cd","/app"]
EXPOSE 8200
CMD ["java","-jar","eventservice-0.0.2.jar"]