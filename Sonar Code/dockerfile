#FROM openjdk:8
#EXPOSE 8089
#ADD target/Ccbp.jar Ccbp.jar 
#ENTRYPOINT ["java","-jar","Ccbp.jar"]


#For Docker in Azure
FROM komljen/ubuntu
RUN \

  apt-get update && \

  apt-get -y install \

          apache2 && \

  rm /var/www/html/index.html && \

  rm -rf /var/lib/apt/lists/*
