#!/bin/bash
echo "비교연산자: 같으면"
if [ "조건"="조건" ]
then
    echo "참"
else
    echo "거짓"
fi

echo "비교연산자: 다르면"
if [ "조건"!="조건" ]
then 
    echo "참"
else
    echo "거짓"
fi

echo "비교연산자: 널이 아니면"
if [ -n "" ]
then
    echo "참"
else
    echo "거짓"
fi

echo "비교연산자: 널이면"
if [ -z "" ]
then
    echo "참"
else
    echo "거짓"
fi
