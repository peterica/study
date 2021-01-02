#!/bin/bash
echo "값을 입력하세요."
read param
case $param in
    # 패턴
    ??02??)
	echo "2월이군요";;
    # or 조건
    Yes | yes | Y | y)
	echo "Yes!!";;
    [nN]*)
	echo "n 또는 N으로 시작, 정규식";;
    b?)
	echo "b로 시작하는 2글자";;
    c*)
	echo "c로 시작하는 모든 문자";;
    *end)
	echo "end로 끝나는 경우";;	
    d+(1|2)e)
	echo "d1213";;
    *)
	echo $param;;
esac
exit 0
