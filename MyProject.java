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
        int nodeslength = adjlist.length;
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] visited = new boolean[nodeslength];
        int numpaths = 0;

        if (src != dst) {
            stack.push(0);
            while (!stack.isEmpty()) {
                int popped = stack.pop().intValue();
                visited[popped] = true;
                for (int neighbour : adjlist[popped]) {
                    if (!visited[neighbour] && !stack.contains(neighbour) && neighbour != dst) {
                        stack.push(neighbour);
                    } else if (neighbour == dst) {
                        numpaths++;
                    }
                }
            }
        } else {
            numpaths = 1;
        }
        return numpaths;
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