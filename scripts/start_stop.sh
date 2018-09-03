
#!/bin/bash
# Usage: Hello World Bash Shell Script Using Variables
# Author: Vivek Gite
# -------------------------------------------------
# Define bash shell variable called var
# Avoid spaces around the assignment operator (=)

command=$1
comp_name=$2
APP_NAME=$comp_name
echo ""
echo "==> Received command: $1, comp_name:$comp_name"

APP_HOME_DIR=/media/amaris/datalake/java_code_repo
APP_CODE_BASE=$APP_HOME_DIR/document-upload/$comp_name
LOG_DIR=$APP_HOME_DIR/logs
FILE_UPLOAD_DIR=$APP_HOME_DIR/upload_doc
FILE_DOWNLOAD_DIR=$APP_HOME_DIR/download_doc

SERVER_HOST=amarisai-server1.com.sg
NODE_SERVER_HOST=192.168.1.119
NODE_SERVER_PORT=3080
CONFIG_SERVER_PORT=3081
EUREKA_SERVER_PORT=3082

CONFIG_SERVER_URI=http://$SERVER_HOST:$CONFIG_SERVER_PORT
EUREKA_SERVER_URL=http://$SERVER_HOST:$EUREKA_SERVER_PORT
ADMIN_SERVER_URL=$EUREKA_SERVER_URL


SERVER_PORT=$CONFIG_SERVER_PORT
APP_PARAM=""

if [[ "$comp_name" == "service-registry" ]]
then
   SERVER_PORT=3082
fi

if [[ "$comp_name" == "api-gateway" ]]
then
   SERVER_PORT=3083
fi

if [[ "$comp_name" == "db-service" ]]
then
   SERVER_PORT=3086
   APP_PARAM="-Dspring.profiles.active=dev -Dspring.cloud.refresh.refreshable=none"
fi

if [[ "$comp_name" == "file-handler" ]]
then
   SERVER_PORT=3087
fi

if [[ "$comp_name" == "search-service" ]]
then
   SERVER_PORT=3088
fi

if [[ "$comp_name" == "config-server" ]]
then
   SERVER_PORT=3081
   APP_PARAM="-Dspring.profiles.active=native  -DEUREKA_CLIENT_ENABLED=false"
fi


stop_component () {
   comp_name=$1
   pid=$(ps -ef | grep document-upload | grep java | grep $comp_name | grep -v grep | awk '{print $2}')
  
   #echo "[$comp_name]: process id:[$pid]"
   count=1
   while [ $count -le 5 ]
    do
       pid=$(ps -ef | grep document-upload | grep java | grep $comp_name | grep -v grep | awk '{print $2}')
       if [[ ! -z $pid ]]
         then
            echo "   ==> attempt [$count], issing soft kill command component:[$comp_name], process-id:[$pid] "
            kill -3 $pid
            sleep 5
         else
            echo "   ==> Component [$comp_name] has been shutdown gracefully.."
            break           
       fi
            
       (( count++ ))
   done

   pid=$(ps -ef | grep document-upload | grep java | grep $comp_name | grep -v grep | awk '{print $2}')
   if [[ ! -z $pid ]]
   then
     echo " $comp_name still found to be running issuing hard kill command "
     kill -9 $pid
     sleep 2
   fi
   
}


shutdown_all () {
  echo ""
  component_array=("config-server" "api-gateway" "web-api" "service-registry" "search-service" "file-handler" "db-service")
  echo "========================================================="
  for comp_name in "${component_array[@]}"
  do
     stop_component $comp_name
  done
  echo "================================================="
  echo ""
}

start_component () {
  comp_name=$1
  echo " starting component $comp_name"
  JAVA_OPTS="-Xms512M -Xmx2048M"
  URI_PARAM="$APP_PARAM -DCONFIG_SERVER_URI=$CONFIG_SERVER_URI -DEUREKA_SERVER_URL=$EUREKA_SERVER_URL -DADMIN_SERVER_URL=$ADMIN_SERVER_URL -DFILE_UPLOAD_DIR=$FILE_UPLOAD_DIR -DFILE_DOWNLOAD_DIR=$FILE_DOWNLOAD_DIR"
  SERVER_PARAM="-DSERVER_HOST=$SERVER_HOST -DCONFIG_SERVER_PORT=$CONFIG_SERVER_PORT -DEUREKA_SERVER_PORT=$EUREKA_SERVER_PORT -DNODE_SERVER_HOST=$NODE_SERVER_HOST -DNODE_SERVER_PORT=$NODE_SERVER_PORT"

  CMD_ARGS="-DAPP_NAME=$APP_NAME -DLOG_DIR=$LOG_DIR $JAVA_OPTS $URI_PARAM $SERVER_PARAM -Djava.net.preferIPv4Stack=true -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/$LOG_DIR/$APP_NAME"

  JAR_NAME="$APP_CODE_BASE/target/$APP_NAME-0.0.1-SNAPSHOT.jar"
  COMMAND="nohup java $CMD_ARGS -jar $JAR_NAME &"
  #echo "Running command $COMMAND"
  nohup java $CMD_ARGS -jar $JAR_NAME & > /dev/null 2>&1
  sleep 5
}

restart () {
  comp_name=$1
  echo "==> restarting component : $comp_name" 
  stop_component $comp_name
  start_component $comp_name
}

restart_all () {
  echo ""
  component_array=("config-server" "api-gateway" "service-registry" "search-service" "file-handler" "db-service")
  echo "========================================================="
  for comp_name in "${component_array[@]}"
  do
     start_component $comp_name
  done
  echo "================================================="
  echo ""
}

echo " Supported commands stop_all, start_all, stop <component-name>"

if [[ "$command" == "stop" ]]
then
   stop_component $comp_name 
fi

if [[ "$command" == "stop_all" ]]
then
   shutdown_all 
fi

if [[ "$command" == "restart" ]]
then
   restart $comp_name
fi

if [[ "$command" == "restart_all" ]]
then
   shutdown_all
   restart_all
fi

rm nohup.out
#exit 0
