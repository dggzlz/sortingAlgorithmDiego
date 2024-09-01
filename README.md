# Sorting Algorithms Efficiency Testing

## Overview

This project tests the efficiency of three sorting algorithms: Insertion Sort, Heap Sort, and Tournament Sort. The program uses different data sets of varying sizes to evaluate and compare the performance of these algorithms. The results, including the number of comparisons and data movements for each sorting operation, are recorded and written into a CSV file. A PDF with the conclusions drawn from the test data is also included in this folder.

## Project Structure

- **Main.java**: Contains the main program and implementations of the sorting algorithms.
- **Results.csv**: The output file containing the results of sorting operations, such as the number of comparisons and data movements for each data set.
- **Conclusion.pdf**: A PDF document summarizing the conclusions drawn from analyzing the sorting results.

## Algorithms Tested

1. **Insertion Sort**: A simple sorting algorithm with a time complexity of O(n^2). It builds the sorted array one item at a time by repeatedly inserting the next unsorted item into its correct position.

2. **Heap Sort**: A comparison-based sorting algorithm that uses a binary heap data structure. It has a time complexity of O(n log n) and is known for its efficiency and good performance.

3. **Tournament Sort**: An advanced sorting algorithm that builds a tournament tree to repeatedly find and remove the smallest element. This algorithm also has a time complexity of O(n log n).

## Usage

1. Ensure Java is installed on your machine.
2. Compile the Java code using:

   javac Main.java

3. Run the compiled program:

   java Main

4. The results of the sorting tests will be saved in `Results.csv`.

## File Descriptions

- **`sample100_1.txt`, `sample100_2.txt`, `sample500_1.txt`, `sample10000_1.txt`, `sample10000_2.txt`**: Input files containing data sets of different sizes used for sorting.
  
## Output

The program outputs a CSV file named `Results.csv`, which contains the following data for each sorting operation:

- Array size
- Number of comparisons
- Number of data movements

## Notes

- The program resets comparison and movement counters between tests to ensure accuracy.
- Some helper functions, like `fillArray`, are used to read input files and populate arrays for sorting.
- The sorting algorithms are implemented with generic types to accommodate various data types, although this implementation specifically uses integers.

## Acknowledgments

- The implementation of Heap Sort and Tournament Sort was inspired by class notes and various online resources, including tutorials from YouTube and articles from Baeldung and GeeksforGeeks.
- ChatGPT was used for guidance on Java generics and specific algorithmic concepts.

## Author

- Name: Diego Gonzalez Reyes
- Email: dgonz348@mtroyal.ca
- Course: COMP 2631 - 002
- Instructor: Mariam Elhussein

## License

This project is for educational purposes under the supervision of the instructor Mariam Elhussein as part of COMP 2631 at Mount Royal University.