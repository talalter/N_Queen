import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


public class Problem {

    // example for path input:   C:/Users/talal/Downloads/Ass1array.txt
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path C:/Username.../array.txt:");
        String path = input.nextLine();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
        System.out.println("Enter the size of matrix");
        int size = input.nextInt();
        System.out.println("Enter the starting Node state");
        char start_char = input.next().charAt(0);
        Node start = new Node((int) start_char, null, 0, 0);
        System.out.println("Enter the ending Node state");
        char end_char = input.next().charAt(0);
        Node end = new Node((int) end_char, null, 0, 0);
        int[][] myArray = new int[size][size];
        while (sc.hasNextLine()) {
            for (int i = 0; i < size; i++) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < size; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        Algorithm alg = new Algorithm(myArray,start,end);
        alg.setSearchType(new DFS(myArray,start,end));
        alg.search();
        alg.setSearchType(new BFS(myArray,start,end));
        alg.search();
        alg.setSearchType(new UCS(myArray,start,end));
        alg.search();


        }
    }
}
