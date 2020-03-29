package dataStructures.tree;

class BinaryTree {
	int data;
	BinaryTree left;
	BinaryTree right;

	public BinaryTree(int data){
		this.data = data;
	}
}
public class TraversalTree {

	public static BinaryTree populateTree(int[] treeData, int left, int right){
		//get middle node
		if(left > right){
			return null;
		}
		int mid = (left+right)/2;
		BinaryTree node = new BinaryTree(treeData[mid]);
		node.left = populateTree(treeData,left,mid-1);
		node.right = populateTree(treeData,mid+1,right);
		return node;
	}

	public static BinaryTree search(BinaryTree root,int data){
		if(root ==null || root.data == data){
			return root;
		}
		if(data > root.data){
			return search(root.right,data);
		}
		else
			return search(root.left,data);

	}

	public static BinaryTree delete(BinaryTree root,int dataToDelete){
		if(root == null)
		{
			return root;
		}
		if(dataToDelete>root.data) {
			root.right=delete(root.right,dataToDelete);
		}
		else if(dataToDelete<root.data) {
			root.left=delete(root.left, dataToDelete);
		}
		else{
			/*
			3 cases arise:
			1. The node to be deleted is leaf node
			2. The node to be deleted has one child
			3. The node to be deleted has 2 child
			 */

			if(root.left==null){
				return root.right;
			}
			else if(root.right == null){
				return root.left;
			}

			//find smallest node on right subtree
			root.data = minValue(root.right);
			root.right = delete(root.right,root.data);


		}
		return root;
	}

	private static int minValue( BinaryTree root) {
		int minv = root.data;
		while (root.left != null)
		{
			minv = root.left.data;
			root = root.left;
		}
		return minv;
	}

	public static void main(String[] args) {
		int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7};
		int n = arr.length;
		BinaryTree root = populateTree(arr, 0, n - 1);
//		System.out.println("PreOrder");
//		preOrder(root);
//		System.out.println();
//		System.out.println("InOrder");
//		inOrder(root);
//		System.out.println();
//		System.out.println("PostOrder");
//		postOrder(root);
//		System.out.println();
//		doSearch(root,5);
//		doSearch(root,15);
		delete(root,1);
		System.out.println("InOrder");
		inOrder(root);
		delete(root,2);
		System.out.println("InOrder");
		inOrder(root);
		delete(root,6);
		System.out.println("InOrder");
		inOrder(root);
		delete(root,4);
		System.out.println("InOrder");
		inOrder(root);
	}

	private static void doSearch(final BinaryTree root,int data) {
		BinaryTree searchResult = search(root,data);
		if(searchResult!=null){
			System.out.println("found");
		}
		else{
			System.out.println("Not Found");
		}
	}

	private static void preOrder(final BinaryTree root) {
		if(root == null){
			return;
		}
		System.out.print(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}

	private static void inOrder(final BinaryTree root) {
		if(root == null){
			return;
		}
		inOrder(root.left);
		System.out.print(root.data);
		inOrder(root.right);
	}

	private static void postOrder(final BinaryTree root) {
		if(root == null){
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data);
	}

}
