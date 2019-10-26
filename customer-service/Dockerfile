FROM java:8
MAINTAINER pankaj.pankasin@gmail.com
EXPOSE 8080

ENV USER_NAME pankaj
ENV APP_HOME /home/$USER_NAME/app

RUN useradd -ms /bin/bash $USER_NAME
RUN mkdir -p $APP_HOME/logs

# Install maven to build project
RUN wget --no-verbose -O /tmp/apache-maven.tar.gz http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
RUN echo "516923b3955b6035ba6b0a5b031fbd8b /tmp/apache-maven.tar.gz" | md5sum -c
RUN tar xzf /tmp/apache-maven.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.3.9 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven.tar.gz

ENV MAVEN_HOME /opt/maven
ENV PATH $MAVEN_HOME/bin:$PATH

ARG tag_version
ARG app_name

ADD ${app_name}/target/customer-service-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar
RUN chown -R $USER_NAME $APP_HOME/
 
USER $USER_NAME
WORKDIR $APP_HOME
RUN bash -c 'touch app.jar'

# Run the app
ENTRYPOINT ["java", "-Dspring.profiles.active=native", "-DLOG_DIR=/home/pankaj/app/logs", "-DEUREKA_CLIENT_ENABLED=false", \
"-DAPP_NAME=customer-service", "-DAPP_VERSION=0.0.1", "-Djava.net.preferIPv4Stack=true ", \
"-Xmx2g", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/home/pankaj/app/logs", "-jar", "app.jar"]