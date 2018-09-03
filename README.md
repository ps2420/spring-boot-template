
 # Welcome to document-upload github repo

## Installation location
```
git clone <this-repo>
cd document-upload
mvn clean install -Dmaven.test.skip=true
```
 
## UI dashboard-portal
```
git clone <this-repo>
cd document-upload
cd dashboard-portal
npm install
ng serve --port=3080(?) --host=<ip-address on back end>
it should be served and run behind api-gateway (to-do..)
``` 

## Back end java code
```
git clone <this-repo>
cd document-upload/scripts
./status.sh 
(modify the required parameters in start_stop.sh)
component orders config-server service-registry (followed by component in any orders..)
./start_stop.sh start <comp-name>. (printed from ./status.sh on console)
TO-DO dockerize and docker compose..
``` 