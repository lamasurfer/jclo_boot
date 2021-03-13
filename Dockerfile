FROM bellsoft/liberica-openjdk-alpine
EXPOSE 8080
ADD build/libs/jclo_boot-0.0.1-SNAPSHOT.jar boot.jar
ENTRYPOINT ["java","-jar","/boot.jar"]