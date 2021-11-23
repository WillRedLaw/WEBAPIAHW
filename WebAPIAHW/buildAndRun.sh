#!/bin/sh
mvn clean package && docker build -t com.mycompany/WebAPIAHW .
docker rm -f WebAPIAHW || true && docker run -d -p 9080:9080 -p 9443:9443 --name WebAPIAHW com.mycompany/WebAPIAHW