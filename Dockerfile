FROM amd64/openjdk:11.0.5-jdk-slim-buster
COPY /target/demosht21-0.0.1-SNAPSHOT.jar /usr/demosht21s/demosht21s.jar
WORKDIR /usr/demosht21s
ENTRYPOINT ["java", "-jar", "/usr/demosht21s/demosht21s.jar", "--spring.security.user.name=user", "--spring.security.user.password=password", "--spring.datasource.url=jdbc:postgresql://192.168.0.10:5432/demosht21", "--spring.datasource.username=postgres", "--spring.datasource.password=mysecretpassword"]

