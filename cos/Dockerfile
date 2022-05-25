# jdk8基础镜像
FROM openjdk:8
# 将当前目录下的jar包复制到docker容器的/目录下
COPY target/cos.jar /tmp/cos.jar
WORKDIR /tmp
ENTRYPOINT ["java","-Dfile.encoding=utf-8", "-jar","cos.jar"]
MAINTAINER wbh
#EXPOSE 8082