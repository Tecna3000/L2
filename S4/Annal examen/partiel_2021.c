

//===[IDENTITE]===
// NOM: 
// PRENOM: 
// NUMERO ETUDIANT: 

//===[QUESTION-01]===

#include.............  // for: exit()
#include ................    // for: fprintf()
#include ................  // for: bool, true, false
#include <string.h>
#include <assert.h>

bool
String_equals (char const string[], char const other[])
{
  return strcmp(string, other)==0 ;
}

void
StringTest_equals (void)
{
  char string[]= "abc", same[]= "abc", diff[]= "aXc";
  assert ( String_equals(string, same)== 0 );
  assert (  String_equals(string, diff) !=0 );
}

//===[QUESTION-02]===

typedef bool StringCond2 (char const string[], char const other[]);

bool
StringCond2_holdsForAll (StringCond2 * cond, size_t count, char * const strings[], char * const others[])
{
  for (size_t k= 0; k < count; k++) {
    if ( cond ) 
      ... ;
  }
  ... ;
}

bool
String_allEqual (size_t count, char * const strings[], char * const others[])
{
  return strcmp(strings, others) == 0 ;
}

//===[QUESTION-03]===

size_t
String_countDelims (char const string[], char const delim[])
{
  assert ( ... ) ;
  size_t delimLength= strlen (delim);
  size_t delimCount= 0;
  for (char * delimOcc= ... ;
       delimOcc != ... ;
       delimOcc= ...) {
    delimCount ++;
  }
  return delimCount;
}

//===[QUESTION-04]===

size_t
String_findDelimStarts (char const string[], char const delim[], size_t delimStarts[], size_t capacity)
{
  /*UNCHANGED*/ ;                       
  size_t delimLength= strlen (delim);
  size_t delimCount= 0;
  for (char * delimOcc= /*UNCHANGED*/ ; 
       delimOcc != /*UNCHANGED*/ ;    
       delimOcc= /*UNCHANGED*/ ) {    
    if ( ... ) ...  ;
    delimStarts [delimCount] = ... ;
    delimCount ++;
  }
  return delimCount;
}

size_t Size_min (size_t a, size_t b) {
  return a > b ? b : a ;
}

//===[QUESTION-05]===

char *
String_section (char const string[], size_t sectionStart, size_t sectionEnd)
{
  assert (sectionStart <= sectionEnd);
  size_t sectionLength= sectionEnd - sectionStart;
  char * section= malloc(sectionLength * sizeof(section)) ;
  assert ( section != NULL );
  strncpy (section, string, sectionLength);
  section ;
  return section;
}

//===[QUESTION-06]===

typedef struct  StringArray {
  char * strings;
  size_t count;
} StringArray ;

void
StringArray_clean (StringArray array) {
  for (size_t k= 0; k < array.count; k++) {
    free (array) ;
  }
  return 0 ;
}

//===[QUESTION-07]===

StringArray
String_split (char const string[], char const delim[])
{
  size_t delimCount= String_countDelims (string, delim);
  char *  delimStarts= malloc (delimCount * sizeof(delimStarts));
  char *  sections=   malloc (delimCount + 1 * sizeof(sections) ;
  assert ( delimStarts != NULL & sections != NULL );

//===[QUESTION-08]===

  size_t n= String_findDelimStarts (string, delim, delimStarts, delimCount);
  assert ( ... );

  size_t delimLength= strlen (delim);
  size_t lastSectionStart= (delimCount == 0) ? 
     0 : ... ;
  size_t lastSectionLength= strlen (...);
  delimStarts[delimCount]= ... ; 

//===[QUESTION-09]===

  size_t sectionStart= 0;
  for (size_t k= 0 ; /* nothing here */ ; k++) {
    sections[k]= String_section (string, sectionStart, delimStarts[k]);
    if ( ... ) ... ;
    sectionStart= ... ;
  }
  free (delimStarts);
  return ... ;
} 

//===[QUESTION-10]===

int main (int argc, char * argv[])
{
  if ( ... ) {
    fprintf ( ... , "Usage: %s string delim\n", ... );
    ... ;
  }
  char * string= ... ;
  char * delim = ... ;
  if (String_equals (delim, "")) {
    fprintf ( ... , "Error: empty delim\n");
    ... ;
  }
  StringArray split= String_split (string, delim);
  for (size_t k= 0; k < split.count; k++) {
    printf ("<%s>\n", split.strings[k]); 
  }
  StringArray_clean (split);
  return 0;
}

//===[QUESTION-END]===
