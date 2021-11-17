// Breadth First Search
class BFSTopologicalSort{
    private static void addNodesToSorted(int numOfVertices, int [] indegree, int [] sortedArray, int [] visited) {
        for (int i=0;i<numOfVertices;i+=1){
            if(indegree[i]==0&&visited[i]==0){
                sortedArray[sortedArray[0]]=i;
                sortedArray[0]+=1;
                visited[i]=1;
            }
        }
    }

    private static int bfs(int done, int[] visited, int numOfVertices, int [] sortedArray, int [] indegree, int [][] adjacencyMatrix) {
        
        for (int i=0; i<numOfVertices;i+=1){
            if (adjacencyMatrix[sortedArray[done+1]][i]==1){
                indegree[i]-=1;
                // visited[i]=1;
            }
        } 
        done+=1;
        return done;

    }

    private static int topologicalSortBFS(int numOfVertices, int[][] adjacencyMatrix, int [] indegree, int [] sortedArray, int done, int [] visited) {
        //int oldDone=done;
        while (done<numOfVertices){
            addNodesToSorted(numOfVertices, indegree, sortedArray, visited);
            done=bfs(done, visited, numOfVertices, sortedArray, indegree, adjacencyMatrix);
            if(done==sortedArray[0]&&done<numOfVertices-1)
                return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int flag,i;
        // image 1 graph
        int numOfVertices1=8;
        int [][] adjacencyMatrix1={
            {0,1,0,0,1,1,0,0},
            {0,0,1,0,1,0,1,0},
            {0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,1,1},
            {0,0,0,0,1,0,0,1},
            {0,0,0,1,0,0,0,1},
            {0,0,0,0,0,0,0,0}
        };
        int [] indegree1={0,1,1,2,4,1,2,3};
        int [] sortedArray1={1,0,0,0,0,0,0,0,0};
        int done1=0;
        int [] visited1={0,0,0,0,0,0,0,0};

        flag=topologicalSortBFS(numOfVertices1, adjacencyMatrix1, indegree1, sortedArray1, done1, visited1);

        System.out.println("BFS Topological Sort of Graph 1:");
        if (flag==-1)
            System.out.println("Loop detected in Graph 1!");
        else{
            for (i=1;i<=numOfVertices1; i+=1) {
                System.out.print("\t"+sortedArray1[i]);
            }
        }

        // image 2 graph
        int numOfVertices2=14;
        int [][] adjacencyMatrix2={
           //m,n,o,p,q,r,s,t,u,v,w,x,y,z
            {0,0,0,0,1,1,0,0,0,0,0,1,0,0}, //m
            {0,0,1,0,1,0,0,0,1,0,0,0,0,0}, //n
            {0,0,0,0,0,1,1,0,0,1,0,0,0,0}, //o
            {0,0,1,0,0,0,1,0,0,0,0,0,0,1}, //p
            {0,0,0,0,0,0,0,1,0,0,0,0,0,0}, //q
            {0,0,0,0,0,0,0,0,1,0,0,0,1,0}, //r
            {0,0,0,0,0,1,0,0,0,0,0,0,0,0}, //s
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //t
            {0,0,0,0,0,0,0,1,0,0,0,0,0,0}, //u
            {0,0,0,0,0,0,0,0,0,0,1,1,0,0}, //v
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1}, //w
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //x
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0}, //y
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}  //z
        };
        //                m,n,o,p,q,r,s,t,u,v,w,x,y,z
        int [] indegree2={0,0,2,0,2,3,2,2,2,2,1,2,1,2};

        int [] sortedArray2={1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int done2=0;
        int [] visited2={0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        flag=topologicalSortBFS(numOfVertices2, adjacencyMatrix2, indegree2, sortedArray2, done2, visited2);

        System.out.println("\n\nBFS Topological Sort of Graph 2:");
        if (flag==-1)
            System.out.println("Loop detected in Graph 2!");
        else{
            String graph2str="mnopqrstuvwxyz";
            for (i=1;i<=numOfVertices2; i+=1) {
                System.out.print("\t"+graph2str.charAt(sortedArray2[i]));
            }
        }
        System.out.println("\n");
    }

    
}