
#!/bin/bash
# Usage: Hello World Bash Shell Script Using Variables
# Author: Vivek Gite
# -------------------------------------------------
# Define bash shell variable called var
# Avoid spaces around the assignment operator (=)

command=$1
app_component=$2
echo ""
echo "==> Received command: $1, component_name:$app_component"

APP_HOME_DIR=/media/amaris/datalake/java_code_repo/document-upload/spring-boot-template
LOG_DIR=/media/amaris/datalake/java_code_repo/document-upload/logs

FILE_UPLOAD_DIR=/media/amaris/datalake/java_code_repo/document-upload/upload-dir
FILE_DOWNLOAD_DIR=/media/amaris/datalake/java_code_repo/document-upload/download-dir
FILE_DOWNLOAD_DIR=$FILE_UPLOAD_DIR

SERVER_PORT=3081
CONFIG_SERVER_PORT=3081
EUREKA_SERVER_PORT=3082

SERVER_HOST=192.168.1.119

CONFIG_SERVER_URI=http://$SERVER_HOST:$CONFIG_SERVER_PORT
EUREKA_URL=http://$SERVER_HOST:$EUREKA_SERVER_PORT


if [[ "$command" == "stop" ]]
then
#   stop_component $app_component
fi



stop_component () {
   component_name=$1
   pid=$(ps -ef | grep java | grep $component_name | grep -v grep | awk '{print $2}')
  
   #echo "[$component_name]: process id:[$pid]"
   count=1
   while [ $count -le 5 ]
    do
       pid=$(ps -ef | grep java | grep $component_name | grep -v grep | awk '{print $2}')
       if [[ ! -z $pid ]]
         then
            echo "   ==> attempt [$count], issing soft kill command component:[$component_name], process-id:[$pid] "
            kill -3 $pid
            sleep 5
         else
            echo "   ==> Component [$component_name] has been shutdown gracefully.."
            break           
       fi
            
       (( count++ ))
   done

   pid=$(ps -ef | grep java | grep $component_name | grep -v grep | awk '{print $2}')
   if [[ ! -z $pid ]]
   then
     echo " $component_name still found to be running issuing hard kill command "
     kill -9 $pid
     sleep 2
   fi
   
}


shutdown_all () {
  echo ""
  component_array=( "kylo-ui" "kylo-services" "nifi" "elasticsearch" "active_mq")
  echo "========================================================="
  echo "                 Stopping components "
  echo ""  
  for comp_name in "${component_array[@]}"
  do
     stop_component $comp_name
  done
  echo "================================================="
  echo ""
}

echo " Supported commands stop_all, start_all, stop <component-name>"

if [[ "$command" == "stop" ]]
then
#   stop_component $app_component 
fi

if [[ "$command" == "stop_all" ]]
then
 #  shutdown_all 
fi

if [[ "$command" == "start_all" ]]
then
   echo "=========================    To start components run following commands ==================="
   echo " active_mq=[/media/amaris/datalake/active_mq/apache-activemq-5.13.0/bin/activemq restart]"
   echo " kylo-ui=[/media/amaris/datalake/kylo/kylo_ui_start.sh]"
   echo " kylo-services=[/media/amaris/datalake/kylo/kylo_services_start.sh]"
   echo " nifi=[/media/amaris/datalake/nifi/bin/nifi.sh restart]"
   echo " ELK=[ nohup /media/amaris/datalake/ELK/elasticsearch-6.2.4/bin/elasticsearch&]"
fi




