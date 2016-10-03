# Remove any previous running container
docker kill customerservice
docker rm customerservice

# Build the application
mvn clean install

# Build the image
docker build -t payara/customerservice .