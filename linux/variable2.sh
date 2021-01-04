#!/bin/bash
echo ""
echo "--------------------------"
echo "여기는 자식 shell입니다."

#지역변수 테스트
echo "--------------------------"
# null 이면 true 
if [  -z $localValue ]
then
    echo "로컬변수 참조값이 없습니다."
else
    echo $test
fi

#환경변수 테스트
echo "--------------------------"
# null이 아니면 true
if [  -n $publicValue ]
then
    echo "public 변수는 자식 쉘에서도 실행이 가능한 $publicValue"
else
    echo "환경변수 참조 실패"
fi
echo "--------------------------"
