/* Name: Diego Gonzalez Reyes
 * email: dgonz348@mtroyal.ca
 * Student ID: 201724348
 * Course Name: Information Structure
 * Course: 2631-002
 * Intructor: Mariam Elhussein
 * Assignment 2
 * 
 * DETAILS: This program test three Sorting Algorithms
 * insertionSort
 * HeapSort
 * TournamentSort
 * 
 * It uses different data sets of different sizes to test the efficiency of the algorithm.
 * The test and the data from the results are them written in a CSV file. This Folder contains pdf with the
 * conclusion from the tested data.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
	public static int insertionComp = 0;// insertion sort comparisons
	public static int insertionMv = 0;	// insertion sort data movements
	public static int heapComp = 0;		// heap sort comparisons
	public static int heapMv = 0;		// heap sort data movements
	public static int tourComp = 0;		// tournament sort comparisons
	public static int tourMv = 0;		// tournament sort data movements

	public static void main(String[] args) {
		Integer[] sample1_insertion = new Integer[50];
		Integer[] sample2_insertion = new Integer[100];

		Integer[] sample3_heap = new Integer[500];
		Integer[] sample4_heap = new Integer[10000];

		Integer[] sample5_tournament = new Integer[5000];
		Integer[] sample6_tournament = new Integer[10000];

		Comparator<Integer> comp = Comparator.naturalOrder();

		// reading the sample files and giving them to the arrays
		fillArray(sample1_insertion, "sample100_1.txt");
		fillArray(sample2_insertion, "sample100_2.txt");

		fillArray(sample3_heap, "sample500_1.txt");
		fillArray(sample4_heap, "sample10000_2.txt");

		fillArray(sample5_tournament, "sample10000_1.txt");
		fillArray(sample6_tournament, "sample10000_2.txt");
		
		// ------------------------------------------------------------------------------------------------
		// Block where Sorting happens
		insertionSort(sample1_insertion, comp);
		int sampleOneComp = insertionComp;
		int sampleOneMv = insertionMv;

		//restting the counters
		insertionComp = 0;
		insertionMv = 0;
		insertionSort(sample2_insertion, comp);
		int sampleTwoComp = insertionComp;
		int sampleTwoMv = insertionMv;


		heapSort(sample3_heap);
		int sampleThreeComp = heapComp;
		int sampleThreeMv = heapMv;

		//restting the counters
		heapComp = 0;
		heapMv = 0;
		heapSort(sample4_heap);
		int sampleFourComp = heapComp;
		int sampleFourMv = heapMv;

		tournamentSort(sample5_tournament);
		int sampleFiveComp = tourComp;
		int sampleFiveMv = tourMv;

		//restting the counters
		tourComp = 0;
		tourMv = 0;
		tournamentSort(sample6_tournament);
		int sampleSixComp = tourComp;
		int sampleSixMv = tourMv;

		//-------------------------------------------------------------------------------------------------------------------------
		// ***RESULTS***
		writeToCSV("Array size, 50, sample 1 comparison, ", sampleOneComp, false);
		writeToCSV("Array size, 50, sample 1 data mv, ", sampleOneMv, true);

		writeToCSV("Array size, 100, sample 2 comparison, ", sampleTwoComp, true);
		writeToCSV("Array size, 100, sample 2 data mv, ", sampleTwoMv, true);

		writeToCSV("Array size, 500, sample 3 comparison, ", sampleThreeComp, true);
		writeToCSV("Array size, 500, sample 3 data mv, ", sampleThreeMv, true);

		writeToCSV("Array size, 10000, sample 4 comparison, ", sampleFourComp, true);
		writeToCSV("Array size, 10000, sample 4 data mv, Array size, 10000, ", sampleFourMv, true);
		
		writeToCSV("Array size, 5000, sample 5 comparison, ", sampleFiveComp, true);
		writeToCSV("Array size, 5000, sample 5 data mv, ", sampleFiveMv, true);
		
		writeToCSV("Array size, 10000, sample 6 comparison, ", sampleSixComp,true);
		writeToCSV("Array size, 10000, sample 6 data mv, ", sampleSixMv,true);	
	}
	/* Function: fillArray -> void
	 * Reads the sample text files and fill in the array sample.
	 * I was able to use some of the method like split and trim by 
	 * asking ChatGPT how to add String elements to an Integer array 
	 * 
	 * Args:
	 * sample (Integer[]): The array that is going to be fill.
	 * fileName (String): The name of the file to be read.
	 * 
	 * results in the array to be filled
	 */
	static void fillArray(Integer[] sample, String fileName){
		// try block to read the file
		try {
			File toSample = new File(fileName);			
			Scanner sc = new Scanner(toSample);
				
				
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				// Removes the commas and split every element, resulting in an array of String
				String[] numbersArray = line.split(","); 

				for (int i = 0; i < sample.length; i++) { 
					// Appends the element to the sample array. 
					// trim is used to remove any character that might've been missed by the split method		
					sample[i] = Integer.parseInt(numbersArray[i].trim());
				}
			}
			sc.close();
			}catch (FileNotFoundException e){ //to catch the error if file does not open
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	}

	/* Function: writeToCSV -> void
	 * Writes a file named "Results" with extension csv 
	 * 
	 * Args:
	 * sample (String): The message that the function will write to the file
	 * data (Integer): The data from the program that will be written into the file
	 * append(boolean): To indicate whether or not you want to rewrite the file or append to it.
	 * 					if false, then it will rewrite the file, otherwise it will append.
	 * 
	 * results in a file called Results.csv with the data to compare
	 */
    public static void writeToCSV(String sample, Integer data, boolean append) {

        try (FileWriter writer = new FileWriter("Results.csv", append);) {
			writer.write(sample); // print the message 
			writer.write(String.valueOf(data)); // print de data 
			writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/***********************************************************************************************************************************************************************/
	/* Insertion Sort 
	 * Sorting Algorithm #1
	 * Time Complexity: O(n^2)
	 * 
	 * InsertionSort has been implemented from the class notes and slides.
	 * I used chatGPt to understand the generic <K> better, as before it was a bit confusing
	 * 
	 * Args:
	 * array (K[]): generic array to be corted.
	 * comp (Comparator<K>): used to compare values with the key
	 */
	static <K> void insertionSort(K[] array, Comparator<K> comp){
		
		for (int j = 1; j < array.length; j++) {
			K key = array[j];
			int i = j-1; // i takes the previous value's position to the key so we can compare 
			
			// This compares the current position and the key, if the key is less than the current 
			// position the loop move the elements one by one. 
			// At the end of the loop, the key value is then put in the right position
			while ((i > -1) && (comp.compare(array[i], key) > 0 )){
				insertionComp++;
				insertionMv++;
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = key; // Puts the key value in the right position
		}
	}

/************************************************************************************************************************************************************************/
	/* Heap Sort 
	 * Sorting Algorithm #2
	 * Time Complexity: O(n*log(n))
	 * 
	 * HeapSort has been implemented from the class notes and slides.
	 * I used chatGPt to understand better the heapify function.
	 * 
	 * Args:
	 * array (Integer[]): generic array to be sorted.
	 * 
	 * Auxiliary functions:
	 * heapify
	 */
	static <K> void heapSort(K[] arr){
		int size = arr.length;

		//Build the MaxHeap tree
		for(int i = size/2; i >=0; i--)
			heapify(arr, size, i);

		//Swap the elements in the array
		for (int i = size - 1; i > 0; i--){
			K swap = arr[0];
			arr[0] = arr[i];
			arr[i] = swap;
			heapMv++;

			//Recursively update the root to the next largest element
			heapify(arr, i, 0);
		}
	}

	/* Function: heapify -> void
	 * Contructs a binary tree with a Max Heap Structure. it does this recursively
	 * 
	 * Args:
	 * arr (K[]): generic array to be sorted
	 * pos (int): position of the current parent node
	 * parent (int): curretn parent
	 *  
	 * Results in array that is being sorted recursively
	 */
	static <K> void heapify(K[] arr, int size, int parent){
		int largest = parent; //index of the current
		int left = 2 * parent;
		int right = 2 * parent + 1;

		// Compares the values and find the largest, as long as the left child, or right child are within the length of the array 
		if (left < size && (Integer)arr[left] > (Integer)arr[largest]){
			largest = left;
			heapComp++;
		}
		if(right < size && (Integer)arr[right] > (Integer)arr[largest]){
			largest = right;
			heapComp++;
		}

		// Swap the values between the parent and the child found
		if(largest != parent){
			K swap = arr[parent];
			arr[parent] = arr[largest];
			arr[largest] = swap;

			heapify(arr, size, largest);
		}
	}

/************************************************************************************************************************************************************************/
	/* Tournament Sort
	 * Algorithm #3
	 * Time Complexity: O(n*log(n))
	 * 
	 * This algortihm has been implemented thanks to the following links:
	 * Concept of the algorithm: https://www.youtube.com/watch?v=7WGaT0-gYCQ&ab_channel=OggiAI-ArtificialIntelligenceToday
	 * Implementation and structure of the algorithm: https://www.baeldung.com/cs/tournament-sort-algorithm
	 * Another useful link: https://www.geeksforgeeks.org/tournament-tree-and-binary-heap/
	 * ChatGPT didn't implement correctly the algorithm at first. So most of the algorithm was mostly my work, but some details were from ChatGPT. 
	 * The logic behind the functions was between tracing and asking ChatGPT how it works. This way I was able to understand the logic.
	 * I had to modified most functions to adjust the logic as ChatGPT's was for the most part wrong, except tourTree and replay functions. 
	 * 
	 * Args:
	 * array (Integer[]): array to be sorted.
	 * 
	 * Auxiliary functions:
	 * matchResult
	 * min
	 * backTrack
	 * playMatch
	 * tourTree
	 */
	static void tournamentSort(Integer[] array){
		Integer[] winners = new Integer[array.length]; // to store the sorted array
		Integer[] tournament = tourTree(array);

		// the root has the winner, and set that winner to the winners array
		for (int i = 0; i < array.length; i++){
			winners[i] = tournament[0]; // tournament[0] = root, i.e. the winner
			backTrack(tournament, winners[i]); //To go back and trace the path of the winner
			playMatch(tournament); // Compares and set the winner within the tournament tree
		}

		// Copy the elements from the winners array to the original array
		for (int i = 0; i < array.length; i++)
			array[i] = winners[i];
	}

	
	/* Function: matchResult -> Integer
	 * Simulates a match between two elements and returns the winner. 
	 * 
	 * Args: 
	 * playerOne (Integer): the left child from a parent node
	 * playerTwo (Integer): the right child from a parent node
	 * 
	 * return(Integer): 
	 * if both are null, the node becomes null
	 * else if one or the other are null, returns the elements that isn't null
	 * if both aren't null, it returns the smallest element using the min function
	 */
	static Integer matchResult(Integer playerOne, Integer playerTwo){
		// I didn't know that Integer objects can be null, so I asked chatGPT lol
		if (playerOne == null && playerTwo == null)
			return null;         
		else if (playerOne == null){
			tourComp++;
			return playerTwo;
		}
		else if (playerTwo == null){
			tourComp++;
			return playerOne;
		}
		return min(playerOne, playerTwo);
	}

	/* Function: min -> Integer
	 * Compares two Integer objects and returns the smallest
	 * 
	 * Args:
	 * playerOne (Integer): the left child from a parent node
	 * playerTwo (Integer): the right child from a parent node
	 * 
	 * return (Integer):
	 * The smallest element 
	 */
	static Integer min(Integer playerOne, Integer playerTwo){
		if (playerOne < playerTwo)
			return playerOne;
		else
			return playerTwo;
	}

	/* Function: backTrack -> void
	 * it tracks the path from where the winner(root) came from. 
	 * Sets the the root to null since we know that it is the winner
	 * and since the root is null, it will evualate to true the first time.
	 * it then looks for where the winner came from. When found, it sets that child node to null.
	 * From there, it backtracks the winner with the null nodes, avoiding that it sets any other elements that
	 * are equal to the winner to null(so others paths aren't lost)
	 *  
	 * Args:
	 * path (Integer[]): it is the tree competition that was built with tourTree. 
	 * winner (Integer): the winner of the competition, i.e. the smallest element found in the round. 
	 * 
	 * The result is a updated tree competition with the the winner's path set to null. 
	 */
	static void backTrack(Integer[] path, Integer winner){
		path[0] = null;

		// Will loop through it till array's size - 1 
		for (int i = 0; i < (path.length / 2); i++){ 
			// if True then it finds the path by checking the childs
			if (path[i] == null){
				if (path[2*i + 1] == winner) // to check the left child
					path[2*i + 1] = null;
				else if(path[2*i + 2] == winner) // to check the right child
					path[2*i + 2] = null; 
			}
		}
	}

	/* Function: replay -> void 
	 * The function takes the parent node and replay all the matches 
	 * with the rest of the players, i.e. the elements. It then goes one parent node down
	 * until the root, it sets the winner and the winners of the matches in the tree.
	 * 
	 * Args: 
	 * tree (Integer[]): the tournament tree
	 * 
	 * The results is a tournament tree with the winner's path and all the matches held, 
	 */
	static void playMatch(Integer[] tree){
		// From the root, goes all the way down and replay the matches
		tourMv++;
		
		for (int i = (tree.length - 1); i >= 0; i--){
			Integer parentIndex = (i-1)/2; // parent at that index
			tree[parentIndex] = matchResult(tree[2*parentIndex + 1], tree[2*parentIndex + 2]);
			
			
		}
	}

	/* Function: tourTree -> Integer[]
	 * Build the tree tournament using a Min Heap structure, and uses playMatch to get the winner
	 * 
	 * Args: 
	 * array (Integer[]): array of elements that will compete to be sorted
	 * 
	 * return (Integer[]):
	 * A tournament tree with the winners in every round. 
	 */
	static Integer[] tourTree(Integer[] array){
		int size = array.length;
		Integer[] tree = new Integer[2*size-1]; //Binary tree

        
		// To build the tree, and sets the players in their positions
		for (int i = 0; i < size; i++) 
			tree[i + size-1] = array[i];
		
		playMatch(tree);

		return tree;
	}
}
