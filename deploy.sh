 #!/bin/bash
APP=metaprofile
PORT=3010
DOCKER_USER=notafoks

echo '[1/3] Removing the container...'
docker ps -q --filter "name=${APP}" | grep -q . && docker stop ${APP}
docker rm -fv ${APP}

echo '[3/3] Running the container...'
docker login -u ${DOCKER_USER} -p v3Ttris49452
docker run --name ${APP} -p ${PORT}:8080 -d ${DOCKER_USER}/${APP}:latest

#
###!/bin/bash
##set -e
#
#SERVICES=$(sudo docker service ls --filter name=${APP} --quiet | wc -l)
#
#if [[ "$SERVICES" -gt 0 ]]; then
#    # Update
#    sudo docker service update \
#        --detach=false \
#        --image notafoks/${APP}:latest \
#        ${APP}
#
#else
#    # Create
#    sudo docker service create \
#        --name=$SERVICE_NAME \
#        --replicas 1 \
#        --publish ${PORT}:8080 \
#        notafoks/${APP}:latest
#fi
