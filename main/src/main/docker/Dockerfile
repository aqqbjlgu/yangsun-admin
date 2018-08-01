FROM hub.c.163.com/xbingo/jdk8:latest
VOLUME /tmp
ADD main-1.0-SNAPSHOT.jar admin.jar
CMD ["java","-Xms10m","-Xmx150m","-jar","/admin.jar"]
