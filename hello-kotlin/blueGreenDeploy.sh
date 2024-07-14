#!/bin/bash

# 프로세스명
readonly PROC_NAME="appName"

# jar 파일
readonly DAEMON="/home/peterica/server/appName*.jar"
# srping.pid.file
readonly PROC_PID="/home/peterica/server/server.pid"
# spring.active.profiles
readonly SPRING_PROFILE="dev"
readonly JAVA_OPT="-Xms2048m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=1024m"

# pinpoint
readonly AGENT_PATH="/home/peterica/pinpoint/pinpoint-agent"

# REAL, BACKUP PORT
readonly REAL_PORT=8060
readonly BACKUP_PORT=8090

readonly NGINX_PID_FILE="/home/peterica/server/nginx.pid"
INDEX=0

start()
{
    APP_PID=0
    BACKUP_PID=0
    echo "Starting  ${PROC_NAME}..."
    if [ -f $PROC_PID ]; then
        echo "${PROC_NAME} is already running"
        APP_PID=$(cat $PROC_PID)
        nohup java -jar -Dspring.profiles.active=$SPRING_PROFILE -Dserver.port=$BACKUP_PORT backup/backup.jar > /dev/null 2>&1 &
        wait_server $BACKUP_PORT
        if [ $INDEX -gt 200 ]; then
            echo "failed to sttart bakup server. process is stopped."
            return 111
        fi
        BACKUP_PID=$(cat $PROC_PID)
        change_nginx 2
        echo "change nginx server to backup server"
    fi

    if [ $APP_PID -gt 100 ];
    then
        echo "try kill real server..."
        kill -9 $APP_PID
        echo " - killed PID is $APP_PID"
    fi
    nohup java -jar -javaagent:$AGENT_PATH/pinpoint-bootstrap-1.8.4.jar -Dpinpoint.agentId=$PROC_NAME -Dpinpoint.applicationName=$PROC_NAME -Dspring.profiles.active=$SPRING_PROFILE -Dserver.port=$REAL_PORT $JAVA_OPT $DAEMON > /dev/null 2>&1 &
    wait_server $REAL_PORT
    if [ $INDEX -gt 200 ];
    then
        echo "failed to start original server."
        return 112;
    fi
    APP_PID=$(cat $PROC_PID)
    change_nginx 1
    echo "change nginx server to original server"

    if [ $BACKUP_PID -gt 100 ];
    then
        echo "try kill backup server..."
        kill -9 $BACKUP_PID
        echo " - killed backup server PID is $BACKUP_PID"
        echo $APP_PID > server.pid
    fi
    echo "server is successfully started. PID: [$APP_PID], PORT: [$REAL_PORT]"
}

# 중지
stop()
{
    echo "Stopping $(cat $PROC_PID)..."
    local DAEMON_PID=$(cat $PROC_PID)

    if [ -z "$PROC_PID" ] || [ ${DAEMON_PID} -lt 3 ]
    then
        echo "${PROC_NAME} was not  running."
    else
        kill -9 $DAEMON_PID
        rm -f $PROC_PID
        echo " - Shutdown ...."
    fi
}

# 상태
status()
{
    if [ -f $PROC_PID ]; then
        echo "${PROC_NAME} is running"
    else
        echo "${PROC_NAME} is stopped"
    fi
}

get_status()
{
    ps ux | grep ${PROC_NAME} | grep -v grep | awk '{print $2}'
}

wait_server()
{
    local STATUS=""
    INDEX=0
    until [ "$STATUS" == "200" ]
        do
            STATUS=`curl -H "Content-Type: application/json" http://localhost:$1/health/check | jq '.return_code'`
            INDEX=$((INDEX + 1))
            # limit time 2*30= 60min
            if [ $INDEX -gt 30 ]; then
                break
            fi
            sleep 2
       done
}

# nginx1.conf, nginx2.conf를 교체한다.
change_nginx()
{
    if [ -f $NGINX_PID_FILE ]; then
        NGINX_PID=$(sudo cat $NGINX_PID_FILE)
        sudo cp /etc/nginx/nginx_$1.conf /etc/nginx/nginx.conf
        sudo /bin/kill -s HUP $NGINX_PID
        echo "* change nginx $NGINX_PID"
    else
        sudo /usr/sbin/nginx -c /etc/nginx/nginx.conf
        echo "* no nginx process. start new nginx process"
    fi
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        sleep 3
        ;;
    restart)
        start
        ;;
    status)
    status "${PROC_NAME}"
    ;;
    *)
    echo "Usage: $0 {start | stop | status  | restart }"
esac
exit 0