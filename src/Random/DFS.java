import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    int V;
    ArrayList<Integer>[] adj;

    DFS(int noOfvertex){
        V= noOfvertex;
        adj  = new ArrayList[noOfvertex];
        for (int i = 0; i < noOfvertex ; i++) {
            adj[i] = new ArrayList<>();
        }

    }

    void edge(int x, int y){
        adj[x].add(y);
    }

    void dephtFirstSearch(int sourcevertex) {
        boolean[] visited = new boolean[V];
        Stack<Integer> s1 = new Stack<>();

        s1.push(sourcevertex);
        int node;
        while (!s1.isEmpty()) {
            sourcevertex = s1.peek();
            s1.pop();

            for (int i = 0; i < adj[sourcevertex].size(); i++) {
                node = adj[sourcevertex].get(i);
                if (!visited[node]) {
                    s1.push(node);
                }
            }
            if (visited[sourcevertex] == false) {
                System.out.println(sourcevertex);
                visited[sourcevertex] = true;
            }

        }
    }


    public static void main(String[] args) {
        DFS dfs1 = new DFS(6);
        dfs1.edge(0,1);
        dfs1.edge(0,2);
        dfs1.edge(0,5);
        dfs1.edge(1,0);
        dfs1.edge(1,2);
        dfs1.edge(2,0);
        dfs1.edge(2,1);
        dfs1.edge(2,3);
        dfs1.edge(2,4);
        dfs1.edge(3,2);
        dfs1.edge(4,2);
        dfs1.edge(4,5);
        dfs1.edge(5,0);
        dfs1.edge(5,4);


        dfs1.dephtFirstSearch(0);



    }
}