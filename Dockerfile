FROM ibmjava:8-jre

COPY src /aStar
WORKDIR /aStar
ENTRYPOINT ["java", "-jar", "aStar.jar"]
