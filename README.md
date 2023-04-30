# Demo spring-boot timescaledb app with Raspberry PI and I2C sensor

Used timescaledb docker image from docker hub https://hub.docker.com/r/timescale/timescaledb timescale/timescaledb:latest-pg11  

Container run: docker run --name some-timescale -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 timescale/timescaledb:latest-pg11  

created db demosht21  

CREATE TABLE conditions (
  time        TIMESTAMPTZ       NOT NULL,
  temperature DOUBLE PRECISION  NULL,
  humidity    DOUBLE PRECISION  NULL
);  
SELECT create_hypertable('conditions', 'time');  

To build this spring-boot app was used docker image from docker hub https://hub.docker.com/_/openjdk amd64/openjdk:11.0.5-jdk-slim-buster (see Dockerfile)  
Container run: docker run --name mybootapp -d -p 8080:8080 demosht21s  

App in C language from https://github.com/leon-anavi/rpi-examples/tree/master/HTU21D/c on Raspberry Pi 3 B+ get temperature and humidity from Sensirion SHT21 sensor via I2C.  
 https://www.sensirion.com/en/environmental-sensors/humidity-sensors/humidity-temperature-sensor-sht2x-digital-i2c-accurate/  

Form data in json-style which sending with curl as body for POST request with basic security.  
 Request body example: {"timestamp": 1571836945795, "temperature": 21.6, "humidity": 45.0}  
 Timestamp in milliseconds.  
App run in cron each minute:  
/home/pi/rpi-examples/HTU21D/c/HTU21D_test | curl --request POST 'server_ip:8080/api/measures' --header 'Content-Type: application/json' --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' --data @-  

## Request examples:
### Add measures
curl --location --request POST 'localhost:8080/api/measures' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data-raw '	{
		"timestamp": 1571836945795,
		"temperature": 21.6,
		"humidity": 45.0
	}'

### Get measures from start date to end date
curl --location --request GET 'localhost:8080/api/measures?start=1571836944793&end=1571836944796' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
