
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Uses the BFS algorithm on adjacency matrices to output the connected
 * components of an undirected simple graph.
 *
 * @author Mark Pinto
 *
 */
public final class lab2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private lab2() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void breadthFirstSearch(int[] v, int[][] a, int[] p, int k) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == 0) { //for each vertex not visited
                v[i] = 1;
                p[i] = 1;

                lab2.Queue q = new lab2.Queue();
                q.enqueue(1);

                while (q.size() > 0) {
                    int newI = q.dequeue();
                    for (int j = 0; j < a[newI].length; j++) {
                        if (a[newI][j] == 1) {
                            if (v[j] == 0) {
                                v[j] = 1;
                                p[j] += 1;
                            }
                        }
                    } //END INNER FOR
                } // END WHIlE
            } // END IF
        } // END OUTER FOR
    }

    /**
     * Implementation of Queue using arrays.
     *
     * @author Mark Pinto
     *
     */
    private static class Queue {

        /**
         * Entries included in {@code this}.
         */
        private int[] q;

        /**
         *
         * @param n
         *            length of array
         */
        private void createNewRep(int n) {
            this.q = new int[0];
        }

        /**
         * int-argument constructor.
         *
         */
        Queue() {
            this.createNewRep(0);
        }

        /**
         * Enqueues the variable into the queue.
         *
         * @param x
         *            integer to be enqueued
         */
        public final void enqueue(int x) {
            int[] temp = new int[this.q.length + 1];
            temp[0] = x;
            for (int i = 1; i <= this.q.length; i++) {
                temp[i] = this.q[i - 1];
            }

            this.q = temp;
        }

        /**
         * Dequeues the queue.
         *
         * @return dequeue queue {@value x}
         */
        public final int dequeue() {
            int[] temp = new int[this.q.length - 1];
            int returned = this.q[0];

            for (int i = 1; i < this.q.length; i++) {
                temp[i - 1] = this.q[i];
            }

            this.q = temp;
            return returned;
        }

        /**
         * Returns size of the queue.
         *
         * @return size of queue
         */
        public final int size() {
            return this.q.length;
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        File file = new File("data1.txt");

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //initialization of variables.
        int n = in.nextInt();
        int[][] arr = new int[n][n]; //this models the Adjacency Matrix
        int[] vList = new int[n]; //this models a list of visited vertices.
        int[] pLen = new int[n];
        in.nextLine();

        //Populates Adjacency Matrix row by row.
        for (int i = 0; i < n; i++) {
            String rowEntry = in.nextLine();

            //Populates Adjacency Matrix columns.
            int pos = 0;
            for (int k = 0; k < rowEntry.length(); k++) {
                char c = rowEntry.charAt(k);
                if (Character.isDigit(rowEntry.charAt(k))) {
                    arr[i][pos] = Character.getNumericValue(c);
                    pos++;
                }
            }
        }

        int numComponents = 1;
        System.out.print("Component:");

        breadthFirstSearch(vList, arr, pLen, 0);

        for (int k = 0; k < vList.length; k++) {
            if (vList[k] == 0) {
                numComponents++;
                System.out.println();
                System.out.print("Component:");
                breadthFirstSearch(vList, arr, pLen, k);
            }
        }

        in.close();
    }

}
