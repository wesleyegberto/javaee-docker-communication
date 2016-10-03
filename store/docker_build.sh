# Remove any previous running container
docker kill store
docker rm store

# Build the application
mvn clean install

# Build the image
docker build -t payara/store .