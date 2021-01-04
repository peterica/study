#!/bin/bash
echo "--------------------------"
echo "local 변수 설정"
localValue="로컬에서 참조됩니다."

#참조방법
echo "local변수는 $localValue"

echo "--------------------------"
echo "환변변수 설정"
export publicValue="환경변수입니다."

echo "public변수는 $publicvalue"

#자식 쉘 실행
sh variable2.sh
