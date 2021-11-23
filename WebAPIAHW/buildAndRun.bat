@echo off
call mvn clean package
call docker build -t com.mycompany/WebAPIAHW .
call docker rm -f WebAPIAHW
call docker run -d -p 9080:9080 -p 9443:9443 --name WebAPIAHW com.mycompany/WebAPIAHW