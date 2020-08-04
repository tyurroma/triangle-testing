FROM maven:3.6.3-jdk-8
WORKDIR /app
COPY src ./src
COPY pom.xml .
ENTRYPOINT ["mvn", "--no-transfer-progress", "clean", "test"]
CMD ["allure:install", "allure:serve"]