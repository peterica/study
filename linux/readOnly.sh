#!/bin/bash
echo "--------------------------"
echo "선언된 변수 삭제금지"

readOnlyValue="readOnly value"
echo $readOnlyValue

#readoly 설정
readonly readOnlyValue

#삭제도 불가능하다.
unset readOnlyValue

#수정이 불가능하다.
readOnlyValue="change value"

echo $readOnlyValue

echo "--------------------------"
