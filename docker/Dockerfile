FROM openjdk:8-jre


RUN apt-get update && \
  apt-get clean

#
# When we build the image, we copy the executable jar in the image file system.
#
COPY MockMock.jar /opt/app/MockMock.jar

# SMTP default port
EXPOSE 25
# mockmock web interface default port
EXPOSE 8282

#
# This is the command that is executed when the Docker container starts
#
CMD ["java", "-jar", "/opt/app/MockMock.jar"]