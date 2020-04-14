FROM maven:3.6.3-jdk-13

COPY . .

RUN mvn clean package
RUN mvn clean test

CMD ["mvn"]
