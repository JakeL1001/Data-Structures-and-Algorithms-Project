// Jake Lyell (22704832) Jordan Lee (Student Number)

import java.util.*;

public class MyProject implements Project {
    public boolean allDevicesConnected(int[][] adjlist) {
        int nodeslength = adjlist.length;
        boolean[] visited = new boolean[nodeslength];
        Stack<Integer> stack = new Stack<Integer>();

        visited[0] = true;
        stack.push(0);
        int discovered = 0;

        while (!stack.isEmpty()) {
            int pop = stack.pop().intValue();
            visited[pop] = true;
            discovered++;
            for (int neighbour : adjlist[pop]) {
                if (!visited[neighbour] && !stack.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }

        return discovered == nodeslength;
    }

    public int numPaths(int[][] adjlist, int src, int dst) {
        int[] distance = new int[adjlist.length];
        for (int x = 0; x < distance.length; x++) {
            distance[x] = 1000;
        }
        int[] paths = new int[adjlist.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[adjlist.length];

        queue.add(src);
        distance[src] = 0;
        paths[src] = 1;
        visited[src] = true;

        while (!queue.isEmpty()) {
            int current = queue.pop();
            for (int neighbour : adjlist[current]) {
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
                if (distance[neighbour] > (distance[current] + 1)) {
                    distance[neighbour] = distance[current] + 1;
                    paths[neighbour] = paths[current];
                } else if (distance[neighbour] == distance[current] + 1) {
                    paths[neighbour] = paths[neighbour] + paths[current];
                }
            }
        }
        return paths[dst];
    }
    // int[] dists = new int[adjlist.length];
    // int level = dst;
    // int dist = 1;
    // while (dists[src] == 0){
    // for (int x = 0; x < adjlist.length; x++){
    // for (int children : adjlist[x]){
    // if (children == level){
    // dists[x] = dist;
    // add children to stack
    // }
    // }
    // }
    // dist++;
    // }

    // return 0;
    // }
    // ####################33
    // int numpaths = 0;
    // boolean[] visited = new boolean[adjlist.length];

    // numpaths = VisitNode(src, adjlist, numpaths, visited, dst); // Start node
    // traversal at node 0
    // // (Note: any other node will work also)
    // return numpaths;
    // }

    // public int VisitNode(int x, int[][] adjlist, int numpaths, boolean[] visited,
    // int dst) {
    // // System.out.println("visited " + x + "= true");
    // if (x == dst) {
    // System.out.println(x + "== destination, numpaths++");
    // numpaths++;
    // } else {
    // visited[x] = true; // Mark x as visited
    // System.out.println(x + "=/=" + dst);
    // for (int neighbour : adjlist[x]) {
    // if (!visited[neighbour]) {
    // numpaths = VisitNode(neighbour, adjlist, numpaths, visited, dst); // visit
    // node j;
    // }
    // }
    // }
    // // System.out.println("returning numpaths =" + numpaths);
    // return numpaths;
    // }

    // int nodeslength = adjlist.length;
    // Stack<Integer> stack = new Stack<Integer>();
    // boolean[] visited = new boolean[nodeslength];
    // int numpaths = 0;

    // if (src != dst) {
    // stack.push(0);
    // while (!stack.isEmpty()) {
    // int popped = stack.pop().intValue();
    // visited[popped] = true;
    // for (int neighbour : adjlist[popped]) {
    // if (!visited[popped] && !stack.contains(neighbour) && neighbour != dst) {
    // stack.push(neighbour);
    // } else if (neighbour == dst) {
    // numpaths++;
    // }
    // }
    // }
    // } else {
    // numpaths = 1;
    // }
    // return numpaths;
    // }

    public int[] closestInSubnet(int[][] adjlist, short[][] addrs, int src, short[][] queries) {
        // TODO
        return null;
    }

    public int maxDownloadSpeed(int[][] adjlist, int[][] speeds, int src, int dst) {
        // TODO
        // push stuff through the graph
        //
        return 0;
    }
}