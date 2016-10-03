# Java EE Docker Communication
-----

A simple implementation of Microservices in Java EE using Rest and Docker. #thanksToAdamBien

Each project is deployed to a different Docker image and both are setup to use the same Docker Network.

Bellow are the command to setup the Docker Network and start the containers:


* Create a network: 
`docker network create tinynet`

* Build and run the containers using the scripts (bash) located at project's directory (just run the script run_example.sh):
```
customer-service/docker_build.sh
customer-service/docker_run.sh
store/docker_build.sh
store/docker_run.sh
```

* Connect both containers to network
```
docker network connect tinynet custorservice
docker network connect tinynet store
```

* Test the applications
```
curl -i http://localhost:8585/customer-service/resources/customers/05211005260/verify
curl -X POST -i http://localhost:8989/store/resources/orders/prepare/05211005260
```
