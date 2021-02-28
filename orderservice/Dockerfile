FROM openjdk:15-alpine
WORKDIR /app
COPY ./build/libs/orderservice-0.0.1.jar /app
CMD ["cd","/app"]
EXPOSE 8100
CMD ["java","-jar","orderservice-0.0.1.jar"]