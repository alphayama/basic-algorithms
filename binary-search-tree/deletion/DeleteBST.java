import java.util.Scanner;

class DeleteBST{
	static class Node{
		Node lchild;
		Node rchild;
		int value;

		Node(int value){
			this.value=value;
		}
	}

	public static void insertBST(Node node, int nodeVal){
		if (nodeVal<=node.value){
			if (node.lchild==null)
				node.lchild=new Node(nodeVal);
			else
				insertBST(node.lchild,nodeVal);
		}
		else{
			if (node.rchild==null)
				node.rchild=new Node(nodeVal);
			else
				insertBST(node.rchild,nodeVal);
		}
	}

	public static void inOrderTraversal(Node node){
		if (node.lchild!=null)
			inOrderTraversal(node.lchild);
		System.out.print("\t"+node.value);
		if (node.rchild!=null)	
			inOrderTraversal(node.rchild);
		
	}

	// Using successor in this program
	public static Node findParentOfSuccessor(Node node){
		if (node.lchild!=null&&node.lchild.lchild==null)
			return (node);
		else
			return findParentOfSuccessor(node.lchild);
	}

	public static Node findNode(Node node,int value){
		if (node.value==value)
			return node;
		else if (value<node.value)
			return findNode(node.lchild,value);
		else if (value>node.value)
			return findNode(node.rchild,value);
		else 
			return null;
	}

	public static void deleteNodeBST(Node root,int nodeVal){
		Node delNode=findNode(root, nodeVal);
		if (delNode.rchild!=null){
			if (delNode.rchild.lchild==null){
				delNode.value=delNode.rchild.value;
				delNode.rchild=delNode.rchild.rchild;
			}
			else{
				Node parentOfSuccessor=findParentOfSuccessor(delNode.rchild);
				delNode.value=parentOfSuccessor.lchild.value;
				parentOfSuccessor.lchild=parentOfSuccessor.lchild.rchild;
			}
			
		}
		else if (delNode.rchild==null){
			if (delNode.lchild==null){
				Node tmp=root;
				int flag=0;
				// Loop to find leaf node's parent and change the link
				while (flag==0){
					if (tmp.lchild!=null&&tmp.lchild.value==delNode.value){
						tmp.lchild=null;
						flag=1;
					}
					else if(tmp.rchild!=null&&tmp.rchild.value==delNode.value){
						tmp.rchild=null;
						flag=1;
					}
					else if (delNode.value>tmp.value)
						tmp=tmp.rchild;
					else
						tmp=tmp.lchild;
				}
			}
				
			else{
				delNode.value=delNode.lchild.value;
				delNode.rchild=delNode.lchild.rchild;
				delNode.lchild=delNode.lchild.lchild;
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int numOfNodes, i;
		System.out.print("Enter number of nodes in the BST: ");
		numOfNodes=scan.nextInt();
		System.out.print("\nEnter value of Node 1 (root node):");
		Node root=new Node(scan.nextInt());
		for (i = 2; i <= numOfNodes; i+=1) {
			System.out.print("Enter value of Node "+i+":");
			insertBST(root, scan.nextInt());
		}
		System.out.print("\nINORDER TRAVERSAL: ");
		inOrderTraversal(root);
		System.out.println("\n");

		System.out.print("\nEnter number of nodes that you want to delete: ");
		numOfNodes=scan.nextInt();
		System.out.println("");
		for (i = 0; i < numOfNodes; i+=1) {
			System.out.print("Enter value of Node #"+(i+1)+" that you want to delete:");
			deleteNodeBST(root, scan.nextInt());
			System.out.print("\nINORDER TRAVERSAL AFTER DELETION: ");
			inOrderTraversal(root);
			System.out.println("\n");
		}

	}
}
