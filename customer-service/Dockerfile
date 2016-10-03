# Use the existing docker image from Adam Bien
# image airhacks/java use a CentOS 7 with the OpenJDK 8
FROM airhacks/payara
MAINTAINER Wesley Egberto, computando.wordpress.com

# Deploy the application
ADD ./target/customer-service.war ${DEPLOYMENT_DIR}/customer-service.war