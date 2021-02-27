FROM openjdk:15-alpine
WORKDIR /app
COPY ./build/libs/serverdiscovery-0.0.1.jar /app
CMD ["cd","/app"]
EXPOSE 8761
CMD ["java","-jar","serverdiscovery-0.0.1.jar"]