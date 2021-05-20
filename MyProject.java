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

    public int[] closestInSubnet(int[][] adjlist, short[][] addrs, int src, short[][] queries) {
        boolean[] visited = new boolean[adjlist.length];
        int[] nodedist = new int[adjlist.length];
        int[] querydist = new int[queries.length];
        for (int x = 0; x < querydist.length; x++) {
            querydist[x] = Integer.MAX_VALUE;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();

        nodedist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int currentnode = queue.pop();
            visited[currentnode] = true;
            for (int neighbour : adjlist[currentnode]) {
                if (!visited[neighbour] && !queue.contains(neighbour)) {
                    queue.add(neighbour);
                    nodedist[neighbour] = (nodedist[currentnode] + 1);
                }
            }
            for (int x = 0; x < queries.length; x++) {
                if (queries[x].length != 0) {
                    short[] tester = Arrays.copyOfRange(addrs[currentnode], 0, queries[x].length);
                    if ((querydist[x] == Integer.MAX_VALUE) && Arrays.equals(tester, queries[x])) {
                        querydist[x] = nodedist[currentnode];
                    }
                } else {
                    querydist[x] = 0;
                }
            }
        }
        return querydist;
    }

    public int maxDownloadSpeed(int[][] adjlist, int[][] speeds, int src, int dst) {
        // TODO
        // push stuff through the graph
        //
        return 0;
    }
}