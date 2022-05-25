# jdk8基础镜像
FROM openjdk:8
# 将当前目录下的jar包复制到docker容器的/目录下
COPY target/user-api.jar /tmp/user-api.jar
WORKDIR /tmp
ENTRYPOINT ["sh","-c","java -Dfile.encoding=utf-8 -jar user-api.jar ${BOOT_OPTIONS}"]
MAINTAINER wbh
#EXPOSE 8080