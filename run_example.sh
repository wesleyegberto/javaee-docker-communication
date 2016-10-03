# Create the Docker network
docker network create tinynet

# Go to project
cd customer-service
./docker_build.sh
./docker_run.sh

cd ../store
./docker_build.sh
./docker_run.sh

cd ..

# Add the containers to network
docker network connect tinynet customerservice
docker network connect tinynet store

