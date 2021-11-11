class Dijkstra{
    public static void printVerticesEdges(int v, int[][] adjacencyMatrix){
        int i,j;
        System.out.println("List of Vertices: ");
        for (i=0;i<v; i+=1){
            System.out.print("\t"+(i+1));
        }
        System.out.println("\nList of the Edges: ");
        for (i=0;i<v;i+=1){
            for(j=0;j<v;j+=1){
                if(adjacencyMatrix[i][j]>0){
                    System.out.println((i+1)+" to "+(j+1)+"\tWeight="+adjacencyMatrix[i][j]);
                }
            }
        }
    }

    private static int findVertexWithMinimumDistance(int v, int[] nodeDistance, int[] nodeStatus){
        int minDist=Integer.MAX_VALUE;
        int minDistVertex=-1;
        for (int i=0;i<v;i+=1){
            if (nodeDistance[i]<minDist&&nodeStatus[i]==0){
                minDist=nodeDistance[i];
                minDistVertex=i;
            }
        }
        if (minDistVertex!=-1)
            nodeStatus[minDistVertex]=1;
        return minDistVertex;
    }

    private static void findAdjacentNodesAndUpdateDistance(int vertex, int v, int [][] adjacencyMatrix, int[] nodeDistance, int[] nodeParent) {
        for (int i=0;i<v;i+=1){
            if(adjacencyMatrix[vertex][i]>0 && nodeDistance[vertex]+adjacencyMatrix[vertex][i]<nodeDistance[i]){
                nodeDistance[i]=nodeDistance[vertex]+adjacencyMatrix[vertex][i];
                nodeParent[i]=vertex;
            }
        }
    }

    public static void main(String[] args) {
        //No. of vertices
        int v=10;
        int i;
        System.out.println("No. of vertices in the graph = "+v);
        

        // The vertices ae from 1 to 10. Corresponding rows and columns are of index v-1
        // The edge goes from row(i) to column(j) in adjacencyMatrix[i][j]
        int[][] adjacencyMatrix = {
            {0,2,0,3,0,0,6,0,0,0},
            {0,0,6,1,0,3,0,0,0,0},
            {0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,8,0,1,2,3,0},
            {0,0,2,0,0,0,0,0,3,8},
            {0,0,7,0,4,0,0,0,0,3},
            {0,0,0,0,0,0,0,5,0,0},
            {6,0,0,0,0,0,0,0,4,0},
            {0,0,0,0,0,0,0,0,0,6},
            {0,0,0,0,0,0,0,0,0,0}
        };

        System.out.println("VERTICES AND EDGES IN THE GRAPH");
        printVerticesEdges(v,adjacencyMatrix);

        // Supply actual vertex value here for Starting vertex not its index value
        int startVertex=1;
        System.out.println("\nStarting vertex for finding the Shortest Path: "+startVertex);

        // Starting vertex has corresponding index value 0 and rest are initialized as Integer.MAX_VALUE
        int[] nodeDistance={Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        int[] nodeParent={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};

        nodeDistance[startVertex-1]=0;
        // Node status is 0 if min distance hasn't been finalised yet. otherwise 1
        int[] nodeStatus={0,0,0,0,0,0,0,0,0,0};

        // minDistVertex here is the STARTING vertex's index
        int minDistVertex=startVertex-1;
        do{
            findAdjacentNodesAndUpdateDistance(minDistVertex, v, adjacencyMatrix, nodeDistance, nodeParent);
            minDistVertex=findVertexWithMinimumDistance(v, nodeDistance, nodeStatus);
            //System.out.println("minvertex="+(minDistVertex+1));
        }while(minDistVertex!=-1);

        System.out.println("VERTICES AND EDGES IN THE SHORTEST PATH TREE FOUND USING DIJKSTRA'S ALGORITHM");
        System.out.println("List of Vertices: ");
        for (i=0;i<v; i+=1){
            System.out.print("\t"+(i+1));
        }

        System.out.println("\nList of the Edges: ");
        System.out.println("Root Node is: "+startVertex);
        for(i=0;i<v;i+=1){
            if(nodeParent[i]!=-1)
                System.out.println((nodeParent[i]+1)+" to "+(i+1)+"\tWeight="+adjacencyMatrix[nodeParent[i]][i]);
        }
    }

}