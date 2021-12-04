import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class HashTable{
    // Hash Function 
    private static long generateHashValue(String word) {
        long coeff=1;
        long hash=0;
        for (int i=0;i<word.length();i+=1) {
            hash+=coeff*((long)word.charAt(i));
            coeff*=53;
        }
        return (hash);
    }

    private static int addToHashTable(int count, int hashTableSize, String[] hashTable, List<String> words, long[] wordsHashValues) {
        int collisions=0;
        int i=0;
        int hashIndex=(int)(wordsHashValues[count]%hashTableSize);
        System.out.print(" \tword: "+words.get(count));//+"\tHash Value = "+wordsHashValues[count]);
        while(i<hashTableSize*hashTableSize){
            if (hashTable[hashIndex]==null){
                hashTable[hashIndex]=words.get(count);
                break;
            }
            else{
                collisions+=1;
                i+=1;
                // Quadratic Probing Hash Index Formula
                hashIndex=(hashIndex+(2*i)-1)%hashTableSize;
            }
        }
        System.out.print(" \tHash Index = "+hashIndex+"\t  Collision Happened?:");
        if (collisions>0) 
            System.out.println(" YES, No. of collisions="+collisions);
        else
            System.out.println(" NO") ;
        return collisions;
    }

    private static int getNewTableSize(int oldTableSize) {
        int[] primeArr={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        for (int i : primeArr) {
            if (i>oldTableSize*2)
                return (i);
        } 
        return oldTableSize;      
    }

    public static void main(String[] args) {
        // java.nio.file.Files requires a try-catch block
        try {
            // Storing list of words after reading from a file
            List<String> words=Files.readAllLines(Paths.get("./words.txt"), StandardCharsets.UTF_8);
            // Array that stores hash values of words NOT the hash indices
            long wordsHashValues[]=new long [20];
            // Iterator for the array above
            int count=0;
            for (String word : words) {
                wordsHashValues[count]=generateHashValue(word);
                count+=1;
            }
            // Hash Table array
            String [] hashTable=new String [71];
            // Hash Table Size
            int hashTableSize=31;
            System.out.println("\nHash Table Size: "+hashTableSize);
            System.out.println("----------------------------------");
            // Repurposing the variable to count number of words stored in the hash index
            count=0;
            // Stores no. of collisions
            int collisions=0;
            int numOfElemsInCollision=0;
            int temp=collisions;
            //System.out.println(hashTable[1]);
            while(count<words.size()){
                System.out.print("word #"+(count+1));
                collisions+=addToHashTable(count, hashTableSize, hashTable, words, wordsHashValues);
                count+=1;
                if (temp<collisions){
                    numOfElemsInCollision+=1;
                    temp=collisions;
                }
                if((float)(count+1)/(float)hashTableSize>0.5f){
                    System.out.println("----------------------------------");
                    System.out.println("Going to insert word #"+(count+1)+" but Load factor will become "+(float)(count+1)/(float)hashTableSize+" which exceeds 0.5!");
                    System.out.println("Doubling Table size and fixing it to smallest prime number greater than double of "+hashTableSize);
                    count=0;
                    collisions=0;
                    temp=collisions;
                    numOfElemsInCollision=0;
                    hashTableSize=getNewTableSize(hashTableSize);
                    System.out.println("\nNew Hash Table Size: "+hashTableSize);
                    System.out.println("----------------------------------");
                    hashTable=new String [71];
                }
            }
            System.out.println("----------------------------------");
            System.out.print("Number of collisions = ");
            System.out.println(collisions+" (Includes no. of collisions encountered while probing)\n");
            System.out.print("Number of words that encountered collision while being inserted into the hash table = ");
            System.out.println(numOfElemsInCollision+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    
}