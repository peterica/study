#!/bin/bash
# 값을 대입할 경우 = 앞뒤 공백이 없어야 한다.
dname=/Users/peterseo/study/gitStudy/linux
if [ -d $dname ]
then echo "디렉토리입니다."
else echo "디렉토리 아니에요."
fi

fname=/Users/peterseo/study/gitStudy/linux/ifStudy.sh
if [ -f $fname ]
then echo "파일입니다."
else echo "파일 아니에요."
fi

if [ -e $fname ]
then echo "파일이 있으면 참"
else echo "파일이 있으면 거짓"
fi


if [ -r $fname ]
then echo "파일이 읽기 가능하면 참"
else echo "파일이 읽기 가능하면 거짓"
fi

if [ -s $fname ]
then echo "파일의 크기가 0이 아니면 참"
else echo "파일의 크기가 0이 아니면 거짓"
fi

if [ -u $fname ]
then echo "파일이 user-id가 설정되면 참"
else echo "파일이 user-id가 설정되면 거짓"
fi

if [ -w $fname ]
then echo "파일이 쓰기 가능하면 참"
else echo "파일이 쓰기 가능하면 거짓"
fi

if [ -x $fname ]
then echo "파일이 실행 가능 상태이면 참"
else echo "파일이 실행 가능 상태이면 거짓"
fi

fname2=/Users/peterseo/study/gitStudy/linux/ifStudy2.sh
if [ $fname -nt $fname2 ]
then echo "파일이 파일2보다 최신이면 참"
else echo "파일이 파일2보다 최신이면 거짓"
fi

if [ $fname -ot $fname2 ]
then echo "파일이 파일2보다 이전이면 참"
else echo "파일이 파일2보다 이전이면 거짓"
fi

if [ $fname -ef $fname2 ]
then echo "파일이 파일2과 같으면 참"
else echo "파일이 파일2과 같으면 거짓"
fi
