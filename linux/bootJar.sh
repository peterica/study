#!/bin/bash

# app 구분자
readonly APP_NAME="appName"

#jar
readonly DAEMON="/Users/deseo/server/bootJar.jar"

# pinpoint 설정
readonly PINPOINT_APP_NAME="app_name"
readonly PINPOINT_APP_GROUP="app_group"
readonly AGENT_PATH="/Users/deseo/study/pinpoint/pinpoint-agent"

# spring 설정
readonly SPRING_PROFILE="dev"
readonly JAVA_OPT="-Xms512m -Xmx1024m -XX:PermSize=256m -XX:MaxPermSize=1024m"

# log 설정
readonly LOCAL_LOG_NAME="app.log"

# 시작
# 이미 작동 중이면 stop을 실행한다. 
start()
{
    echo "----------------------"
    echo "Starting  $APP_NAME"
    if [ -n $(get_status) ]; then
        echo "$APP_NAME is already running"
        echo "try kill server..."
        stop
    fi

    # nohup java -jar -Dspring.profiles.active=$SPRING_PROFILE $JAVA_OPT $DAEMON > /dev/null 2>&1 &
    nohup java -jar -javaagent:$AGENT_PATH/pinpoint-bootstrap-1.8.4.jar \
                -Dpinpoint.agentId=$PINPOINT_APP_GROUP \
                -Dpinpoint.applicationName=$PINPOINT_APP_NAME \
                -Dspring.profiles.active=$SPRING_PROFILE \
                -Dserver.port=7070 -Dprofile=real \
                -Dproc.name=$APP_NAME  $JAVA_OPT \
                $DAEMON >> $LOCAL_LOG_NAME  2>&1 &

    INDEX=10
    while [ -z $(get_status) ]
    do
      sleep 1;
      echo ${get_status}
      INDEX=$((INDEX - 1))
      echo "$INDEX 가동 대기중"
      if [ ${INDEX} -eq 0 ]; then
        break
      fi
    done

    if [ -n $(get_status) ]; then
        echo " - Starting..."
        echo " - Created Process ID in $(get_status)"
        echo " - PID is $(get_status)"
    else
        echo " - failed to start."
    fi
}

# 중지
stop(){
    echo "----------------------"
    echo "Stoping  $APP_NAME"
    local PID=$(get_status)
    
    if [ -z ${PID} ];
    then
 	echo "$APP_NAME was not running."
    else
	kill -15 $PID
	sleep 3
	echo " Shutdown ...."
    fi
}

# 상태확인
status()
{
#    echo "[$(get_status)]";
    local PID=$(get_status)
    if [ -z ${PID} ]; 
    then
        echo "$APP_NAME is stopped"
    else
        echo "$APP_NAME is running"
    fi
}


# 프로세스 확인
get_status()
{
    ps ux | grep $APP_NAME | grep -v grep | awk '{print $2}'
}

get_jar()
{
    # jar 복사 경로
    cp ~/git/project/build/libs/bootJar.jar /Users/deseo/server/bootJar.jar
    echo "copy done"
}

case "$1" in
    start)
        start
        sleep 4
        ;;
    stop)
        stop
	status
        ;;
    restart)
        get_jar
	stop
        start
	sleep 10
	status
        ;;
    status)
        status
        ;;
    copy)
        get_jar
        ;;
    *)
    echo "Usage: $0 {start | stop | status | restart | copy }"
esac
exit 0
