FROM openjdk:11
MAINTAINER Mehmet Kelleci <mehmetsura@gmail.com>
ADD ./target/hb-mars-rover-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "/app/hb-mars-rover-0.0.1-SNAPSHOT.jar"]