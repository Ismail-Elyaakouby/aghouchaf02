FROM java
ADD ./target/smail-0.0.1-SNAPSHOT.war /target/smail-0.0.1-SNAPSHOT.war
ADD ./run.sh /run.sh
RUN chmod a+x /run.sh
EXPOSE 32377:32377
CMD /run.sh
