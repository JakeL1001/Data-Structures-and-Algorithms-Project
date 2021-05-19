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
        // TODO
        return 0;
    }

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

// public boolean allDevicesConnected(int[][] adjlist) { // CURRENTLY ALL BROKEN
// LOL

// boolean[] visited = new boolean[adjlist.length];
// for (int i = 0; i < visited.length; i++) {
// visited[i] = false;
// }
// dfs(0, adjlist, visited);

// return true;
// }

// public static void dfs(int i, int[][] adjlist, boolean[] visited) {
// System.out.println(i + " " + adjlist[i][0]);
// if (visited[i]) {
// System.out.println(i + "= returned");
// return;
// }

// visited[i] = true; // Mark node as "visited"
// System.out.println(i + " " + "test");

// for (int j = i; j < adjlist[j].length; j++) {
// if (!visited[j]) {
// dfs(j, adjlist, visited); // Visit node
// }
// }
// }