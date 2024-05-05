FROM java:17
VOLUME /tmp
ADD ms-sky-high-rmm.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]