package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class HashTable {
    public ArrayList<LinkedList<MultiSet>> hashTable;
    private final int length;



    public HashTable(File filePath, int filling) throws FileNotFoundException {
        long start = System.currentTimeMillis() ;
        this.length = findPrimeNumber(filePath,filling);
        initialize();
        fillTable(filePath);
        long end = System.currentTimeMillis();
        double temp = (end-start)/1000.0;
        System.out.println("The programme takes "+ temp + "second");
    }

    public void initialize(){
        this.hashTable = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            hashTable.add(new LinkedList<>());
        }
    }

    public int getLength() {return length;}
    public ArrayList<LinkedList<MultiSet>> getHashTable() {
        return hashTable;
    }
    public LinkedList<MultiSet> get(int index) {
        return this.hashTable.get(index);
    }

    public void fillTable(File filePath) throws FileNotFoundException {
        Scanner file= new Scanner(filePath);
        try {
            while (file.hasNextLine()) {
                MultiSet word = new MultiSet(file.nextLine());
                int key = (int) findKey(word.getArrayOfLetters());
                this.hashTable.get(key).addFirst(word);
            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    // k mod findPrimenumber
    public double findKey(char[] word){
        return (computeAlphabetValue(word)% this.length);
    }


    public double computeAlphabetValue(char[] word){
        double value = 0;
        for(int index =0; index < word.length; index++){
          int pow= (word.length-(index+1));
          double val = ( Math.pow(256,pow));
            value= value+ ((word[index]*val) % this.length);
        }
        return value;
    }

    // compte le nombre d'élements du file
    public int computeNbOfElements(File filePath) throws FileNotFoundException {
        Scanner file = new Scanner(filePath);
        int length = 0;
        while (file.hasNextLine()){
            file.nextLine();
            length++;

        }
        return length;
    }

    // le premier nb premier sup à nb d'élements/le taux de remplissage ça sera la longueure de la hashtable
    public int findPrimeNumber(File filePath,int filling) throws FileNotFoundException {
        int nbOfElements = computeNbOfElements(filePath);
        int start = (int) Math.ceil((double) nbOfElements/ filling);
        System.out.println("the filling is: "+filling);
        for(int index = start; index< computeNbOfElements(filePath); index++) {
            if (isPrimeNumber(index)) {
                return (index);
            }
        }
        return 0;
    }

// utilisé dans findPrimeNumeber
    public boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= sqrt(number); ++i) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public String toString() {
        String toString = "{\n";
        for(LinkedList<MultiSet> list : hashTable) {
            if (list.size() > 0) {
                toString += "  "+ hashTable.indexOf(list)+": ";
                for(Object word : list) {

                    toString += word.toString()+", ";
                }
                toString += "\n";
            }
        }
        return toString+"}";
    }
//-------------------------------------------------------------------------LA 2-SOMME-----------------------------------------------------------------------------------

    public MultiSet[] findTwoWords(MultiSet mixedWord) {
        final MultiSet[] foundWords = new MultiSet[2];
        for (LinkedList<MultiSet> list : hashTable) {
            for (MultiSet word : list) {
                if (word.contains(mixedWord)) {
                    foundWords[0] = word;
                    char[] complementary = mixedWord.complementary(word);
                    MultiSet comp = this.findWord(complementary);
                    if(comp!=null){
                        foundWords[1] = comp;
                        return foundWords;
                    }
                }
            }
        }
        return null;
    }

// utilisé pour trouver le complémentaire dans findTwoWords
    public MultiSet findWord(char[] word) {
        if (word == null) return null;

        int key = (int) findKey (word);
        for (MultiSet letters : this.hashTable.get(key)) {
            if ( Arrays.equals( word ,letters.getArrayOfLetters()) ) return letters;
        }
        return null;
    }

}