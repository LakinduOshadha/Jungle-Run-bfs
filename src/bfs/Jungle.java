package bfs;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

/**
 * Creates a jungle to find the shortest path to the city
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class Jungle {
    // Global variables
    private int N;  // Stores the matrix length
    private Node start; // Stores the Start node
    private Node[][] jungle;    // bfs.Jungle created with nodes
    private Queue<Node> queue = new LinkedList<Node>(); // To use in BFS
    private Stack<Node> stack = new Stack<>();  // To find the minimum No. of moves

    /**
     * Constructor for bfs.Jungle class
     * Creates a jungle with given data
     * Adds nodes to the jungle according to inputArr
     *
     * @param jungle InputMatrix
     * @param N length of Matrix
     */
    public Jungle(char[][] jungle,int N) {
        this.N = N;
        createJungle();
        // Assigns the char values to the created jungle
        for(int i = 0; i != N; i++) {
            for(int j = 0; j != N; j++) {
                // Creates a temp node
                Node temp = new Node();
                temp.r = i + 1; temp.c = j + 1;
                // Assigns values to temp node
                if(jungle[i][j] == 'P') {temp.val = 'P'; temp.canMove = true;}
                else if(jungle[i][j] == 'S') {temp.val = 'S'; this.start = temp;temp.canMove = true;}
                else if(jungle[i][j] == 'E') {temp.val = 'E'; temp.canMove = true; }
                else temp.val = 'T';
                // Setting the temp node to corresponding bfs.Jungle matrix node
                this.jungle[i + 1][j + 1] = temp;
            }
        }
    }

    /**
     * Creates jungle with nodes
     *
     */
    public void createJungle() {
        this.jungle = new Node[N + 2][N + 2];
        for(int i = 0; i != N + 2; i++) {
            for(int j = 0; j != N + 2; j++) {
                Node temp = new Node();
                this.jungle[i][j] = temp;
            }
        }
    }

    /**
     * Prints the jungle
     *
     */
    public void printJungle() {
        for(int i = 0; i != N; i++) {
            for(int j = 0; j != N; j++) {
                System.out.print(jungle[i + 1][j + 1].val + " ");
            }
            System.out.println();
        }
    }

    /**
     *  Calculates the the minimum number of moves from S to E
     *
     * @return the minimum number of moves from S to E
     */
    public int noOfMovesToEnd() {
        Node nodeExplore = start;   // The node to explore
        stack.push(start);  // Adds the S node to the stack
        int count = 0;  // Setting the count to 0

        // Finds the shortest path from S to E
         do {
             // Getting the position of the exploring node
            int i = nodeExplore.r;
            int j = nodeExplore.c;
            // Adding the movable, not visited nodes to the queue
            if(jungle[i + 1][j].canMove && !jungle[i + 1][j].isVisited) queue.add(jungle[i + 1][j]);
            if(jungle[i - 1][j].canMove && !jungle[i - 1][j].isVisited) queue.add(jungle[i - 1][j]);
            if(jungle[i][j + 1].canMove && !jungle[i][j + 1].isVisited) queue.add(jungle[i][j + 1]);
            if(jungle[i][j - 1].canMove && !jungle[i][j - 1].isVisited) queue.add(jungle[i][j - 1]);

            nodeExplore.isVisited = true;   // Setting the explored node isVisited to true
            nodeExplore = queue.remove();   // Getting entered node from the queue to explore
            stack.push(nodeExplore);    // Adding the current exploreNode to the stack
         } while(nodeExplore.val != 'E');

         // Finds the minimum number of moves from S to E
         // Getting 2 temporary nodes which is used to trace the path back (from E to S)
         Node temp1 = stack.pop();
         Node temp2;
         // Finds the No.of moves by tracing the path back from the created stack
         while(temp1.val != 'S') {
             // Tracing back to find the adjacent node
             while (true) {
                 temp2 = stack.pop();
                 if (isAdjacent(temp1, temp2)) {
                     count++; // Increases the count
                     break;
                 }
             }
             if(temp2.val == 'P') temp2.val = '-';  // Marking the path back to S
             temp1 = temp2;
         }
         return count;
    }

    /**
     * Finds whether given two nodes are adjacent.
     *
     * @param A bfs.Node 1
     * @param B bfs.Node 2
     * @return Returns true when bfs.Node 1 and bfs.Node 2 are adjacent
     */
    public boolean isAdjacent(Node A, Node B) {
        // To find the adjacent nodes
        int i = A.r;
        int j = A.c;
        // Comparing the bode B with the adjacent nodes of A
        if(jungle[i + 1][j] == B || jungle[i - 1][j] == B || jungle[i][j + 1] == B || jungle[i][j - 1] == B)
            return true;
        else return false;
    }
}

/**
 * bfs.Node class
 */
class Node {
    // Global variables
    char val;
    boolean isVisited;
    boolean canMove;
    int r;
    int c;

    /**
     * Constructor for bfs.Node
     *
     */
    public Node() {
        val = ' ';
        isVisited = false;
        canMove = false;
        r = 0;
        c = 0;
    }
}


