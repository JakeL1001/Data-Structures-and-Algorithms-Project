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
        int numpaths = 0;
        Stack<Integer> path = new Stack<Integer>();
        ArrayList<Integer> onPath  = new ArrayList<Integer>();
        enumerate(adjlist, src, dst, numpaths, path, onPath);
        return numpaths;
    }

    // use DFS
    private int enumerate(int[][] adjlist, int x, int dst, int numpaths, Stack<Integer> path, ArrayList<Integer> onPath) {

        // add node v to current path from s
        path.push(x);
        onPath.add(x);

        // found path from s to t - currently prints in reverse order because of stack
        if (x == dst){ 
            numpaths++;
        }

        // consider all neighbors that would continue path with repeating a node
        else {
            for (int i : adjlist[x]) {
                if (!onPath.contains(i)) enumerate(adjlist, i, dst, numpaths, path, onPath);
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath.remove(x);
        return numpaths;
    }
    //     int nodeslength = adjlist.length;
    //     Stack<Integer> stack = new Stack<Integer>();
    //     boolean[] visited = new boolean[nodeslength];
    //     int numpaths = 0;

    //     if (src != dst) {
    //         stack.push(0);
    //         while (!stack.isEmpty()) {
    //             int popped = stack.pop().intValue();
    //             visited[popped] = true;
    //             int loopcatcher = 0;
    //             for (int neighbour : adjlist[popped]) {
    //                 if (!stack.contains(neighbour) && neighbour != dst) {
    //                     stack.push(neighbour);
    //                 } else if (neighbour == dst) {
    //                     numpaths++;
    //                 }
    //                 loopcatcher++;
    //                 if (loopcatcher >= 20){
    //                     continue;
    //                 }
    //             }
    //         }
    //     } else {
    //         numpaths = 1;
    //     }
    //     return numpaths;
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