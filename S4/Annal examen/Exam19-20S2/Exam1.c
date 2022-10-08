#include "Exam1.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>

int  main (void) { //  version 1
    DIR * dir= opendir (".");
    if (dir == NULL) { perror ("opendir"); exit  (1); }
    Content c;
    Content_initEmpty(&c);
    Content_addDirEntryNames(&c,dir);
    closedir (dir);
    if(!Content_isRevSorted(&c)) Content_revSort(&c);
    Content_print(&c,String_isSpaceFree,stdout);
    Content_clean(&c);
    
    return  0;
}


/*char  const  PIPELINE []=  ............................................
    int  main (void) { //  version 2FILE * 
    stream= popen (PIPELINE , "r");
    if (stream  == NULL) { perror ("popen"); exit  (1); }
    for  (;;) {
        char  filename  [256];
        ..............................
        ..............................................
        ..............................................
    }
    pclose (stream );
    return  0;
}*/

/*int  main (void) { //  version 3pid_t  
    child= fork  ();
    if (child ==  -1) { perror ("fork"); exit  (1); }
    if ( .................. ) {
        execlp ( ...................................................  );
        .....................................................................
        .........................................................
    }
    int  info;
    ............... = wait ( ................  );
    .................................................................
    bool  success= ...................................................
    return  ..........................................................
}*/

char * String_dup (char  const  string []) {
    size_t  size= strlen(string)+1 * sizeof(char) ;
    char * copy= malloc(size);
    assert(copy != NULL);
    strcpy(copy,string);
    return  copy;
}

int  String_charIndex (char  const  string[], char  character) {
    assert(character != '\0');
    char * occ= strchr(string,character);
    if(occ == NULL) return -1;
    return  occ - string ;
}

bool  String_containsChar (char  const  string[], char  character) {
    return  character =='\0' ? false : String_charIndex(string,character)!=-1;
     // if (character == '\0){ return false } else { return String_charIndex(string,character)!=-1 }
}
void  Content_initEmpty (Content * c) {
    c->strings = malloc(MIN_CAPACITY * sizeof * c->strings);
    assert(c->strings != NULL);
    c-> capacity = MIN_CAPACITY;
    c-> length = 0;
    
}
void  Content_doubleCapacity (Content * c) {
    size_t  newSize= (c->capacity * 2) * sizeof * c-> strings;  // sizeof(type = char, int, double) -- sizeof * var
    char **  newStrings= realloc(c->strings,newSize);
    assert(newStrings != NULL);
    c->strings = newStrings;
    c->capacity *= 2;
    
}
bool  Content_isFull (Content  const * c) {
    return  c->capacity == c->length;
}

void  Content_addString (Content * c, char  const  string []) {
    if  (Content_isFull(c)) {
        Content_doubleCapacity(c);
    }
    c->strings[c->length] = String_dup(string);
    c->length ++ ;
}
void  Content_clean (Content * c) {
    for (size_t k= 0 ;   k < c->length ; k++) {
        free(c->strings[k]);
    }
    free(c->strings);
}

void  Content_addDirEntryNames (Content * c, DIR * dir) {
    for  (;;) {
        struct dirent * dir_entry = readdir(dir);
        if(dir_entry == NULL) break;
        Content_addString(c,dir_entry->d_name);
    }
}
bool  Content_isRevSorted (Content  const * c) {
    for (size_t k= 0 ; k< c->length-1 ; k++) {
        if ( strcmp(c->strings[k],c->strings[k+1]) < 0 )
            return false;
    }
    return true;
}
void  Content_revSort (Content * c) {
    size_t  cellSize= sizeof * c->strings;
    qsort(c->strings,c->length,cellSize,revCompareAsString);
    assert(Content_isRevSorted(c));
}
int  revCompareAsString (void  const * p1, void  const * p2) {
    char  * const  *   string1= p1;
    char  * const  *   string2= p2;
    return  strcmp(*string2,*string1);
}

bool  String_isSpaceFree ( char * string) {
    return  !String_containsChar(string,' ');
}
void  Content_print (Content  const * c,StringPredicate * accept , FILE * file){
    for (size_t k= 0 ; k < c->length ; k++ ) {
        if(accept != NULL && !accept(c->strings[k])) continue;
        fprintf(file,"%s\n",c->strings[k]);
    }
}

