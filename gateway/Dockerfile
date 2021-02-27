FROM openjdk:15-alpine
WORKDIR /app
COPY ./build/libs/gateway-0.0.1.jar /app
CMD ["cd","/app"]
EXPOSE 8765
CMD ["java","-jar","gateway-0.0.1.jar"]