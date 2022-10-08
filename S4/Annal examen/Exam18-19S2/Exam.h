#ifndef EXAM_H
#define EXAM_H
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

typedef bool CharPredicate(unsigned char c);
typedef unsigned char CharFunction (unsigned char c);
int CompareAsUnsignedChar(void const * ptr1, void const * ptr2);
char * String_sorted(char const string[]);
bool String_isAnagram(char const string1[], char const string2[]);
size_t String_filter (char target [], char const source[], CharPredicate * accept, CharFunction * convert);
char * String_filtered (char const  source[],CharPredicate * accept, CharFunction * convert);
bool String_isQuasiAnagram ( char const string1[], char const string2[]);

typedef struct Link {
    char * string;
    struct Link * next;
} Link;

typedef struct List {
    Link * first, * last ;
    int length;
} List;

Link * Link_create (char const string[]); 
void List_addLast (List * list, char const string[]);
void List_print (List const * list, FILE * file) ;
void Link_destroy (Link * link);
void List_clean(List * list) ;
void readAnagrams (FILE * file,char const string[], List * list);
void List_initEmpty ( List * list);

#endif
