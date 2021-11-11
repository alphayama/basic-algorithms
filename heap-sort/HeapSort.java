class HeapSort{
    static void swap(int[] arr, int pos1, int pos2){
        arr[pos1]+=arr[pos2];
        arr[pos2]=arr[pos1]-arr[pos2];
        arr[pos1]-=arr[pos2];
    }

    static void compareNodeAndChildren(int[] arr, int pos, int arrLength){
        if(2*pos+2<arrLength){
            if(arr[pos]>arr[2*pos+1]&&arr[2*pos+1]<=arr[2*pos+2]){
                swap(arr, pos, 2*pos+1);
                compareNodeAndChildren(arr, 2*pos+1,arrLength);
            }
            else if (arr[pos]>arr[2*pos+2]&&arr[2*pos+2]<=arr[2*pos+1]){
                swap(arr, pos, 2*pos+2);
                compareNodeAndChildren(arr, 2*pos+2,arrLength);
            }
        }

        else if (2*pos+1<arrLength&&arr[pos]>arr[2*pos+1]){
            swap(arr, pos, 2*pos+1);
        }

    }

    static void heapSort(int[] arr){
        int lastNode=arr.length-1;
        while(lastNode>0){
            swap(arr, 0, lastNode);
            lastNode-=1;
            compareNodeAndChildren(arr, 0, lastNode+1);
        }
    }

    static void floydWarshall(int[] arr){
        int parent=arr[0]/2;
        while(parent>=1){
            compareNodeAndChildren(arr, parent,arr.length);
            parent-=1;
        }
    }

    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i+=1){
            System.out.print("\t"+arr[i]);
        }
    }

    public static void main(String[] args) {
        // Element 0 is size of the array
        int[] arr=new int[]{16,3,7,12,7,1,6,2,5,6,8,9,4,6,10,11};
        arr[0]=arr.length-1;
        System.out.println("Default Array / Not Heapified-Unsorted Array:");
        printArray(arr);
        System.out.println("\n\nHeapified Array (using Floyd-Warshall Algorithm):");
        floydWarshall(arr);
        printArray(arr);
        System.out.println("\n\n------------------------------------------------------");
        System.out.println("\nUnsorted Array:");
        printArray(arr);
        System.out.println("\n\nHeap Sorted Array (Descending Order):");
        heapSort(arr);
        printArray(arr);
        System.out.println("\n\n");
    }
}