FROM openjdk:12
MAINTAINER cbravor@ext.vectoritcgroup.com
VOLUME /tmp
ADD ./target/springboot-servicio-zuul-server-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-jar","/zuul-server.jar"] 