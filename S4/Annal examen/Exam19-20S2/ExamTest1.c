#include "ExamTest1.h"
#include <string.h>
void run_All(){
    StringTest_dup();
    StringTest_containsChar();
}
void  StringTest_dup (void) {
    char * hello = String_dup("hello");
    assert(strncmp("hello",hello,6) == 0);
    free(hello);
}
void  StringTest_containsChar (void) {
    assert(String_containsChar("hello",'h'));
    assert(!String_containsChar("hello",'z'));
    assert(!String_containsChar("hello",'\0'));
}