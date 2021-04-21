// Jake Lyell (22704832) Jordan Lee (Student Number)

import java.util.*;

public class MyProject implements Project {
    public boolean allDevicesConnected(int[][] adjlist) { // CURRENTLY ALL BROKEN LOL

        boolean[] visited = new boolean[adjlist.length];
  
        // Start the DFS from vertex 0
        DFS(0, adjlist, visited);
  
        // Check if all the vertices are visited
        // Set connected to False if one node is unvisited
        boolean connected = true;
        
        for (int i = 0; i <visited.length ; i++) {
            if(!visited[i]){
                connected = false;
                break;
            }
        } //finish the return section
        return connected;
    }

    public void DFS(int source, int[][] adjlist, boolean[] visited){
  
        // Mark the vertex visited as True
        visited[source] = true;
  
        // Travel the adjacent neighbours
        for (int i = 0; i <adjlist.length ; i++) {
            
            int neighbour = adjlist[i];
            
            if(!visited[neighbour]){
                
                // Call DFS from neighbour
                DFS(neighbour, adjlist, visited);
            }
        }
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
        return 0;
    }
}
