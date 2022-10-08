#include "Exam.h"
#include "ExamTest.h"
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include <stdbool.h>


void ExamTest_runAll(void) {
    StringTest_Sorted();
    StringTest_isAnagram();
    StringTest_isKasiAnagram();
}
void * StringTest_Sorted(void){
        char * result = String_sorted("cdba");
        assert( strcmp(result,"abcd") == 0);
        free(result);
}

void StringTest_isAnagram(void){
    assert(String_isAnagram("abcd","cbda"));
    assert(! String_isAnagram("abcdd","adbc"));
    assert(! String_isAnagram("iuhj","polm"));
}
void StringTest_isKasiAnagram(void){
    assert(String_isQuasiAnagram("The Classroom!","Schoolmaster.."));
    assert(! String_isQuasiAnagram("abcm","adbc"));
    
}
