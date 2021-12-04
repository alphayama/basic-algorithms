import java.util.Scanner;

class linked_list_node_swap_sel_sort{

    static class Node{
        int value;
        Node next;
    }

    //This function performs selection sort on a linked list and returns the head node
    public static Node selSort(Node node) {
        Node nodeI,nodeJ,minNodePrev,minNode;;
        nodeI=node;
        Node minNodeNext;
        Node head=node;

        int pass=1;

        //Pass 1:
        nodeJ=nodeI;
        minNodePrev=nodeI;
        while(nodeJ.next!=null){
            if(nodeJ.next.value<minNodePrev.next.value){
                minNodePrev=nodeJ;
            }
            nodeJ=nodeJ.next;
        }
        //Swapping the nodes if both of them are consecutive and first one is head
        if(nodeI.next==minNodePrev.next){
            minNode=minNodePrev.next;
            nodeI.next=minNode.next;
            minNode.next=nodeI;
            head=minNode;
        }
        //Swapping the nodes if nodeI is head and there are nodes between it and node
        //with minimum value
        else if (nodeI.value!=minNodePrev.value){
            minNodeNext=minNodePrev.next.next;
            minNodePrev.next.next=nodeI.next;
            nodeI.next=minNodeNext;
            head=minNodePrev.next;
            minNodePrev.next=nodeI;
        }
        nodeI=head;

        //Print the list after the first pass
        System.out.println("PASS-"+pass);
        System.out.println(head.value);
        Node temp;
        temp=head.next;
        while(temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }

        //Other Passes:
        while (nodeI.next.next!=null){
            pass+=1;
            nodeJ=nodeI;
            minNodePrev=nodeI;
            while(nodeJ.next!=null){
                if(nodeJ.next.value<minNodePrev.next.value){
                    
                    minNodePrev=nodeJ;
                    
                }
                nodeJ=nodeJ.next;
            }
            //Swapping the nodes if they are consecutive
            if(nodeI.next.next==minNodePrev.next){
                minNode=minNodePrev.next;
                minNodePrev.next=minNode.next;
                nodeI.next=minNode;
                minNode.next=minNodePrev;
            }
            //Swapping the nodes if there are one or modes between them
            else if (nodeI.next.value!=minNodePrev.value){
                minNode=minNodePrev.next;
                minNodeNext=minNode.next;
                minNode.next=nodeI.next.next;
                minNodePrev.next=nodeI.next;
                minNodePrev.next.next=minNodeNext;
                nodeI.next=minNode;
            }

            //Printing the list after the current pass
            System.out.println("\nPASS-"+pass);
            System.out.println(head.value);
            temp=head.next;
            while(temp!=null){
                System.out.println(temp.value);
                temp=temp.next;
            }

            nodeI=nodeI.next;
        }
        return head;
    }
    public static void main(String[] args) {
        Node head=new Node(); //Head node
        Node temp=new Node(); //Temporary node
        int size; //Size of the node
        Scanner scan=new Scanner(System.in); //Object of scanner class
        int i; //iterator for for loop

        // Creating a linked list
        System.out.println("Enter size of the node: ");
        size=scan.nextInt();


        //Entering values for the linked list
        temp=head;
        for(i=0;i<size;i+=1){
            System.out.println("Enter value for node "+(i+1)+": ");
            temp.value=scan.nextInt();
            if (i!=size-1){
                temp.next=new Node();
                temp=temp.next;
            }
            else
                temp.next=null;
            
        }
        
        //Traversing and printing the Linked List (First traversal)
        System.out.println("Unsorted Linked List:");
        System.out.println(head.value);
        temp=head.next;
        while(temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }

        // Call the sorting function. It returns the head node
        head=selSort(head);
        
        //Traversing and Printing the Sorted linked list (Second traversal)
        System.out.println("----SORTED LIST------");
        System.out.println(head.value);
        temp=head.next;
        while(temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }
    }
}
