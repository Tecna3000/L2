#ifndef EXAM_H
#define EXAM_H
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
char *   String_dup (char  const  string []);
int      String_charIndex     (char  const  string[], char  character );
bool     String_containsChar (char  const  string[], char  character );



typedef struct Content {
    char ** strings;
    size_t length;
    size_t capacity;
} Content;
#define MIN_CAPACITY 10

void  Content_initEmpty (Content * c);
void  Content_doubleCapacity (Content * c);
bool  Content_isFull (Content  const * c);
void  Content_addString (Content * c, char  const  string []);
void  Content_clean (Content * c);
void  Content_addDirEntryNames (Content * c, DIR * dir);
void  Content_revSort (Content * c);
int  revCompareAsString (void  const * p1, void  const * p2);
bool  Content_isRevSorted (Content  const * c);
bool  String_isSpaceFree ( char * string); 
typedef bool StringPredicate  (char  string []);

void  Content_print (Content  const * c,StringPredicate * accept , FILE * file);

#endif