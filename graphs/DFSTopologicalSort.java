// // Depth First Search
class DFSTopologicalSort{

    private static void dfs(int vertex, int [] sortedArray, int numOfVertices, int [][] adjacencyMatrix, int [] visited, int [] done){
        if (done[vertex+1]==0){
            if (visited[vertex]==1)
                sortedArray[0]=-1;
            else {
                visited[vertex]=1;
                for (int i=0;i<numOfVertices;i+=1){
                    if (adjacencyMatrix[vertex][i]==1){
                        dfs(i, sortedArray, numOfVertices, adjacencyMatrix, visited, done);
                        if (sortedArray[0]==-1)
                            break;
                    }
                }
                done[vertex+1]=1;
                sortedArray[done[0]-1]=vertex;
                done[0]-=1;
                
            }
        }
    }

    private static int [] topologicalSortDFS(int vertex, int numOfVertices, int [][] adjacencyMatrix, int [] visited, int [] done) {
        int [] sortedArray =new int[numOfVertices];
        for (int i=0; i<numOfVertices; i+=1){
            dfs(i, sortedArray, numOfVertices, adjacencyMatrix, visited, done);
            if (sortedArray[0]==-1)
                break;
        }
        return sortedArray;
	}

    public static void main(String[] args) {
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
        int [] visited1={0,0,0,0,0,0,0,0};
        int [] done1={8,0,0,0,0,0,0,0,0};

        int startVertex=1;
        int[] sortedArray1=topologicalSortDFS(startVertex-1, numOfVertices1, adjacencyMatrix1, visited1, done1);

        if (sortedArray1[0]==-1)
            System.out.println("Loop Detected in graph 1!");
        
        else{
            System.out.println("DFS Topological Sort of Graph 1:");
            for (int i=0;i<numOfVertices1;i+=1)
                System.out.print("\t"+sortedArray1[i]);
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
        int [] visited2={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int [] done2={14,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        // start vertex is m i.e. index 0
        startVertex=0;
        int[] sortedArray2=topologicalSortDFS(startVertex, numOfVertices2, adjacencyMatrix2, visited2, done2);

        if (sortedArray2[0]==-1)
            System.out.println("Loop Detected in graph 2!");
        
        else{
            String graph2str="mnopqrstuvwxyz";
            System.out.println("DFS Topological Sort of Graph 2:");
            for (int i=0;i<numOfVertices2;i+=1)
                System.out.print("\t"+graph2str.charAt(sortedArray2[i]));
        }

        System.out.println("\n");
    }

	
}