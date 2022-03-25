# Pull base image 
From tomcat:jre8-temurin

# Maintainer 
LABEL maintainer="valaxytech@gmail.com" 
ADD webapp.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
