# Use the existing docker image from Adam Bien
# image airhacks/java use a CentOS 7 with the OpenJDK 8 and Payara
FROM airhacks/payara
MAINTAINER Wesley Egberto, computando.wordpress.com

# Deploy the application
ADD ./target/store.war ${DEPLOYMENT_DIR}/store.war
