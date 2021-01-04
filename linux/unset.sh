#!/bin/bash
echo "--------------------------"
echo "선언된 변수 삭제하기"

localValue="local value"

echo $localValue

unset localValue

echo $localValue

echo "--------------------------"

