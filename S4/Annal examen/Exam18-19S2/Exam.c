#include "ExamTest.h"
#include "Exam.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>



int main (int argc, char * argv[]) {
    ExamTest_runAll();
    if( argc != 2) {
        fprintf(stderr,"Usage : %s word\n",argv[0]);
        exit(1);
    }
    char * word = argv[1];
    List list; List_initEmpty(&list);
    readAnagrams(stdin,word,&list);
    
    List_print(&list,stdout);
    List_clean(&list);
    return 0 ;
    
}

int CompareAsUnsignedChar(void const * ptr1, void const * ptr2){
    unsigned char const *  c1 = ptr1;
    unsigned char const *  c2 = ptr2;
    return *c1 - *c2;
}

char * String_sorted(char const string[]){
    size_t length = strlen (string);
    char * copy = malloc( (length+1) * sizeof * copy );
    assert (copy != NULL);
    strcpy(copy,string);
    qsort(copy,length,sizeof(char),CompareAsUnsignedChar);
    return copy;
}

bool String_isAnagram(char const string1[], char const string2[]){
    if(strlen(string1) != strlen(string2)) return false;
    char * sorted1 = String_sorted(string1) ;
    char * sorted2 = String_sorted(string2) ;
    int comparison = strcmp(sorted1,sorted2);
    free(sorted1);
    free(sorted2);
    return comparison == 0 ;
}

bool Char_isLower(unsigned char c){
    return c>='a' && c<='z';
}
bool Char_isUpper(unsigned char c){
    return c>='A' && c<='Z';
}
bool Char_isAlpha(unsigned char c){
    return Char_isLower(c)  || Char_isUpper(c);
}

unsigned char Char_toLower (unsigned char c){
    if(!Char_isUpper(c)) return c;
    int rank= c - 'A';
    return 'a' + rank;
}
size_t String_filter (char target [], char const source[], CharPredicate * accept, CharFunction * convert){
    size_t length = 0;
    for(size_t k = 0 ; source[k] != '\0' ; k++) {
        if(!accept(source[k])) continue;
        target[length] = convert(source[k]);
        length ++;
    }
    target[length]='\0';
    return length;
}

char * String_filtered (char const  source[],CharPredicate * accept, CharFunction * convert){
    char * target = malloc((strlen(source)+1) * sizeof * target);
    size_t length = String_filter(target,source,accept,convert);
    target = realloc(target,(length+1)*sizeof*target);
    return target;
}

bool String_isQuasiAnagram ( char const string1[], char const string2[]){
    char * filter = String_filtered(string1, Char_isAlpha, Char_toLower );
    char * filter2 = String_filtered(string2, Char_isAlpha, Char_toLower );
    bool isAnagram = String_isAnagram(filter,filter2);
    free(filter2);
    free(filter);
    return isAnagram;
}

void List_initEmpty ( List * list){
    list -> first = NULL;
    list -> last = NULL;
    list -> length = 0;
}
Link * Link_create (char const string[]) {
    Link * link = malloc(sizeof * link);
    link -> string = strdup(string);
    link -> next = NULL;
    return link;
}

void List_addLast (List * list, char const string[]){
    Link * link = Link_create(string);
    if(list -> length == 0) list -> first=link;
    else list -> last -> next = link;
    list -> length += 1;
    list -> last = link;
}

void List_print (List const * list, FILE * file) {

    for(Link * l = list -> first ; l != NULL ; l= l -> next){
        fprintf(file,"%s\n",l->string);
    }
    char * itemWord = (list->length <2) ? "item" : "items";
    fprintf(file,"%d %s\n",list->length,itemWord);
}

void Link_destroy (Link * link) {
    free(link->string);
    free(link);
}

void List_clean(List * list) {
    Link * next;
    for(Link * l = list -> first ; l != NULL ; l = next){
        next = l -> next;
        Link_destroy(l);
    }
}

void readAnagrams (FILE * file,char const string[], List * list){
    for(;;){
        char line[1024];
        char * result = fgets(line,1024,file);
        if(result == NULL){   
             break ;
        }
        size_t length= strlen(line);
        if(line[length-1] == '\n')
            line[length-1] = '\0';
        if(String_isQuasiAnagram(line,string))
            List_addLast(list,line);
    }
}
