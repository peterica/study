#!/bin/bash
echo "산술연산자: 같으면"
if [ 100 -eq 100 ]
then
    echo "참"
else
    echo "거짓"
fi

echo "산술연산자: 다르면"
if [ 200 -ne 100 ]
then
    echo "참"
else
    echo "거짓"
fi

echo "산술연산자: 값1 > 값2"
if [ 200 -gt 100 ]
then
    echo "참"
else
    echo "거짓"
fi


echo "산술연산자: 값1 >= 값2"
if [ 200 -ge 100 ]
then
    echo "참"
else
    echo "거짓"
fi

echo "산술연산자: 값1 < 값2"
if [ 200 -lt 100 ]
then
    echo "참"
else
    echo "거짓"
fi

echo "산술연산자: 값1 <= 값2"
if [ 200 -le 100 ]
then
    echo "참"
else
    echo "거짓"
fi
