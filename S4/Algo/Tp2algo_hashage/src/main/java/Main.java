package main.java;

import java.io.File;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {
          File file1 = new File("src/main/resources/minidico.txt");
          HashTable minidico = new HashTable(file1,3);


        File file2 = new File("src/main/resources/dico.txt");
        HashTable dico = new HashTable(file2,3);

        long start = System.currentTimeMillis() ;
        showSums(minidico,dico);
        long end = System.currentTimeMillis();
        double temp = (end-start)/1000.0;

        System.out.println("To find twosums the programme takes "+ temp + "second");

    }
    public static void showSums(HashTable miniDico,  HashTable dico) {
        System.out.println("\n         ---------Sets of twoSums found for minidico--------- \n");
        System.out.println(twoSums(miniDico, "adeeiilnnnorux"));

        System.out.println("\n         ----------Sets of twoSums found for dico---------- \n");
        System.out.println(twoSums(dico, "abeeiiillnnorrsstyzé"));

    }

    public static String twoSums(HashTable hashTable, String word) {
        MultiSet mixedWord = new MultiSet(word);
        MultiSet[] twoSums  = hashTable.findTwoWords(mixedWord);

        if (twoSums != null)
            return  "The twoSums set found for word '"+ mixedWord.getWord() + "' is: " + Arrays.toString(twoSums);
        else
            return "No twoSums set found for word: ' " + mixedWord.getWord() + "'.";
    }

}