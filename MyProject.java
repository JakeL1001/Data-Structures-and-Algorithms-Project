// Jake Lyell (22704832), Jordan Lee (22705507)
// CITS2200 Project 1

import java.util.*;

public class MyProject implements Project {

    public boolean allDevicesConnected(int[][] adjlist) { // Performs a Depth First Search to determine if all devices in the network are
        int nodeslength = adjlist.length; // connected or not, connected being able to transmit to every other device
        boolean[] visited = new boolean[nodeslength]; // Array to hold the boolean value of each nodes visited status
        Stack<Integer> stack = new Stack<Integer>(); // Stack to hold the next node to be visited

        visited[0] = true; // Sets start node as 0
        stack.push(0); // Adds the start node to the stack
        int discovered = 0; // Sets the total number of connected nodes to 0

        while (!stack.isEmpty()) { // Loops whilst there are still nodes to search
            int pop = stack.pop(); // Pops the top node from the stack to analyze
            visited[pop] = true; // marks it as visited and then increments the discovered variable
            discovered++;
            for (int neighbour : adjlist[pop]) { // For each node that the popped node can transmit to, it adds to the stack if that node...
                if (!visited[neighbour] && !stack.contains(neighbour)) { // Is not already visited or is not already on the stack 
                    stack.push(neighbour);
                }
            }
        }

        return discovered == nodeslength; // After every node has been searched, if the number of discovered nodes is
                                          // equal to the total number of nodes in the network, the function returns true, if else, it returns false
    }

    public int numPaths(int[][] adjlist, int src, int dst) { // Returns the total number of paths between 2 vertices where each step only gets closer to the destination
        int[] distance = new int[adjlist.length];
        for (int x = 0; x < distance.length; x++) { // sets all the values of the distance array to MAX_VALUE
            distance[x] = Integer.MAX_VALUE;
        }
        int[] paths = new int[adjlist.length]; // initiates arrays and linked lists used by the algorithm
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[adjlist.length];

        queue.add(src); // adds the source to the queue, sets the distance to the source as 0, and sets the number of paths to source as 1
        distance[src] = 0;
        paths[src] = 1;
        visited[src] = true; // sets source as visited

        while (!queue.isEmpty()) { // Loops whilst the queue still has nodes to search/analyze
            int current = queue.pop(); // sets top of queue to the current node to be analyzed
            for (int neighbour : adjlist[current]) { // for each adjacent node, if it hasn't been visited, it is added to the queue, and is labelled as visited
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
                if (distance[neighbour] > (distance[current] + 1)) { // If the distance from the source of the neighbour, is larger than the distance to get to
                                                                     // its parent + 1, its distance is now set to the distance of its parent + 1
                    distance[neighbour] = distance[current] + 1;
                    paths[neighbour] = paths[current]; // Number of paths to the neighbour node is set to the number of paths to its parent
                } else if (distance[neighbour] == distance[current] + 1) { // If distance is equal, sets length of paths to the number of paths to its parent +itself
                    paths[neighbour] = paths[neighbour] + paths[current];
                }
            }
        }
        return paths[dst]; // Returns the number of paths to the destination node
    }

    public int[] closestInSubnet(int[][] adjlist, short[][] addrs, int src, short[][] queries) { // Searches for the closest node that fits each subnet query

        boolean[] visited = new boolean[adjlist.length];
        int[] nodedist = new int[adjlist.length];
        int[] querydist = new int[queries.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int x = 0; x < querydist.length; x++) { // Sets the value for each query's distance to MAX_VALUE, if the algorithm cannot change it, it is returned in the final array
            querydist[x] = Integer.MAX_VALUE;
        }

        nodedist[src] = 0;
        queue.add(src); // Adds source to list and sets its distance from the source (itself) to 0

        while (!queue.isEmpty()) { // Loops while queue has nodes left to search
            int currentnode = queue.pop();
            visited[currentnode] = true; // pops top of queue and marks it as visited
            for (int neighbour : adjlist[currentnode]) { // For all neighbours of the current node
                if (!visited[neighbour] && !queue.contains(neighbour)) { // if the neighbour is not visited and not
                                                                         // already in the queue
                    queue.add(neighbour); // it's added to the queue
                    nodedist[neighbour] = (nodedist[currentnode] + 1); // its distance to the source is 1 plus the
                                                                       // previous distance
                }
            }
            for (int x = 0; x < queries.length; x++) { // For each of the queries
                if (queries[x].length != 0) { // If the query is empty, it's distance is always equal to the source node
                    short[] tester = Arrays.copyOfRange(addrs[currentnode], 0, queries[x].length); // Creates a tester array, the same size as the query, from the node's address
                    if ((querydist[x] == Integer.MAX_VALUE) && Arrays.equals(tester, queries[x])) { // If the query hasn't been tested (MAX_VALUE), and the tester is equal to the query
                        querydist[x] = nodedist[currentnode]; // makes the distance to the query equal to the distance to the node
                    }
                } else {
                    querydist[x] = nodedist[src];
                }
            }
        }
        return querydist; // Returns the list of distances to each query from the source
    }

    public int maxDownloadSpeed(int[][] adjlist, int[][] speeds, int src, int dst) {
        if (src == dst) {
            return -1;
        }
        int numNodes = adjlist.length;
        int[][] rspeeds = Arrays.copyOf(speeds, numNodes);
        int[] parent = new int[numNodes];
        int maxDLSpeed = 0;

        while (bfs(adjlist, rspeeds, src, dst, parent)) {
            // System.out.println("loop catch 2");
            int pathspeed = Integer.MAX_VALUE;
            for (int x = dst; x != src; x = parent[x]) {
                int xindex = -1;
                int parentofX = parent[x];
                // System.out.println("parent of" + x + "is " + parentofX);
                for (int y = 0; y < adjlist[parentofX].length; y++) {
                    //System.out.println("does this run?");
                    if (adjlist[parentofX][y] == x) {
                        // System.out.println("adjlist[parentofX][y] (" + adjlist[parentofX][y] + ")" + "==" + x);
                        xindex = y;
                    }
                    //System.out.println("adjlist[parentofX][y] (" + adjlist[parentofX][y] + ")" + "!=" + x);
                }
                // System.out.println(xindex);
                pathspeed = Math.min(pathspeed, rspeeds[parentofX][xindex]);
                // System.out.println("pathspeed =" + pathspeed);
            }
            for (int x = dst; x != src; x = parent[x]) {
                int xindex = -1;
                // System.out.println("loop catch 1");
                int parentofX = parent[x];
                // System.out.println("#2# parent of" + x + "is " + parentofX);
                for (int y = 0; y < adjlist[parentofX].length; y++) {
                    if (adjlist[parentofX][y] == x) {
                        // System.out.println("#2# adjlist[parentofX][y] (" + adjlist[parentofX][y] + ")" + "==" + x);
                        xindex = y;
                    }
                }
                // System.out.println(xindex);
                // System.out.println("pathspeed =" + pathspeed);
                // System.out.println("rspeeds[parentofX][xindex] before subtraction = " + rspeeds[parentofX][xindex]);
                rspeeds[parentofX][xindex] -= pathspeed;
                // System.out.println("rspeeds[parentofX][xindex] after subtraction = " + rspeeds[parentofX][xindex]);
                // System.out.println("" + parentofX + "" + xindex + "" + rspeeds[parentofX][xindex] + "-" + pathspeed);
                // rspeeds[xindex][parentofX] += pathspeed; //TODO fix
            }
            maxDLSpeed += pathspeed;
        }
        return maxDLSpeed;
    }

    public boolean bfs(int[][] adjlist, int[][] rspeeds, int src, int dst, int[] parent) {
        // System.out.println("loopcatcher 3");
        boolean[] visited = new boolean[adjlist.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(src);
        visited[src] = true;
        parent[src] = -1;

        while (!queue.isEmpty()) {
            int currentnode = queue.pop();
            int counter = 0;
            for (int neighbour : adjlist[currentnode]) {
                //System.out.println("rspeeds" + rspeeds[currentnode][counter]);
                if (!visited[neighbour] && rspeeds[currentnode][counter] > 0) {
                    if (neighbour == dst) {
                        parent[neighbour] = currentnode;
                        return true;
                    }
                    queue.add(neighbour);
                    parent[neighbour] = currentnode;
                    visited[neighbour] = true;
                }
                counter++;
            }
        }
        return false;
    }

}