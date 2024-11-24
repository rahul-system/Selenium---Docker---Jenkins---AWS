FROM bellsoft/liberica-openjdk-alpine:17-cds

#install curl and jq
RUN apt-get update && apt-get install -y curl jq

#Workspace
WORKDIR /home/selenium-docker

#Add the required files
ADD target/docker-resources ./
ADD runner.sh               runner.sh

#Environment variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT


##Run the tests
#ENTRYPOINT java -cp 'libs/*' \
#           -Dselenium.grid.enabled=true \
#           -Dselenium.grid.hubHost=${HUB_HOST} \
#           -Dbrowser=${BROWSER} \
#           org.testng.TestNG \
#           -threadcount ${THREAD_COUNT} \
#           test-suites/${TEST_SUITE}


# docker run -e HUB_HOST=192.168.137.1 -e BROWSER=firefox -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=4 thakurkumarrahul/selenium

# Fix for windows
#RUN dos2unix runner.sh

#start the runner.sh
ENTRYPOINT sh runner.sh
