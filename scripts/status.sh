
#!/bin/bash
# Usage: Hello World Bash Shell Script Using Variables
# Author: Vivek Gite
# -------------------------------------------------
# Define bash shell variable called var
# Avoid spaces around the assignment operator (=)

status_component () {
   component_name=$1
   pid=$(ps -ef | grep java | grep $component_name | grep -v grep | awk '{print $2}')

   if [[ -z $pid ]]
   then
     echo " $component_name :[DOWN]"
   else
     echo " $component_name :[UP]"
   fi
}

check_status_component () {
  echo ""
  echo "============ component statuses ================="
  component_array=("config-server" "api-gateway" "web-api" "service-registry" "search-service" "file-handler" "db-service")
  for comp_name in "${component_array[@]}"
  do
     status_component $comp_name
  done
  echo "================================================="
  echo ""
}

check_status_component

