FROM tomcat:9-jdk8
WORKDIR /usr/local/tomcat

ADD ./target/camel-no-spring-1.0.war $CATALINA_HOME/webapps/camel-no-spring.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
