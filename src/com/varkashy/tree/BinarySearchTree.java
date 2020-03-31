package com.varkashy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    int data;
    BinarySearchTree leftNode;
    BinarySearchTree rightNode;

    public BinarySearchTree(int data){
        this.data = data;
    }

    public static void inOrderTraversal(BinarySearchTree root){
        if(root == null){
            return;
        }
        inOrderTraversal(root.leftNode);
        System.out.println(root.data);
        inOrderTraversal(root.rightNode);
    }

    public static void reverseInOrderTraversal(BinarySearchTree root){
        if(root == null){
            return;
        }
        reverseInOrderTraversal(root.rightNode);
        System.out.println(root.data);
        reverseInOrderTraversal(root.leftNode);
    }

    public static void findTopKKeys(BinarySearchTree root, int valueOfK){
        List<BinarySearchTree> kMaxNodes = new ArrayList<>();
        reverseInOrderTraversalWithLimit(root,kMaxNodes,valueOfK);
    }

    private static void reverseInOrderTraversalWithLimit(BinarySearchTree root, List<BinarySearchTree> kMaxNodes, int valueOfK) {

        if(root == null||kMaxNodes.size()>=valueOfK){
            return;
        }

        reverseInOrderTraversalWithLimit(root.rightNode,kMaxNodes,valueOfK);
        if(kMaxNodes.size() < valueOfK) {
            System.out.println(root.data);
            kMaxNodes.add(root);
            reverseInOrderTraversalWithLimit(root.leftNode,kMaxNodes,valueOfK);
        }
    }

    public static void preOrderTraversal(BinarySearchTree root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        preOrderTraversal(root.leftNode);
        preOrderTraversal(root.rightNode);
    }

    public static void postOrderTraversal(BinarySearchTree root){
        if(root == null){
            return;
        }
        postOrderTraversal(root.leftNode);
        postOrderTraversal(root.rightNode);
        System.out.println(root.data);
    }

    public static BinarySearchTree createBinaryTreeFromArray(int[] numbers){
        if(numbers.length == 0){
            return null;
        }
        //find midpoint
        return createBinaryTreeFromArray(numbers,0,numbers.length-1);
    }

    private static BinarySearchTree createBinaryTreeFromArray(int[] numbers, int low, int high) {
        if(low>high){
            return null;
        }
        int mid = (low+high)/2;
        BinarySearchTree root = new BinarySearchTree(numbers[mid]);
        root.leftNode = createBinaryTreeFromArray(numbers,low,mid-1);
        root.rightNode = createBinaryTreeFromArray(numbers,mid+1,high);
        return root;
    }

    public static BinarySearchTree insertIntoBinaryTree(BinarySearchTree root, int data){
        if(root == null){
            root = new BinarySearchTree(data);
        }
        else if(data>root.data){
            root.rightNode = insertIntoBinaryTree(root.rightNode,data);
        }
        else if (data < root.data){
            root.leftNode = insertIntoBinaryTree(root.leftNode,data);
        }
        return root;
    }

    public static BinarySearchTree deleteFromBinaryTree(BinarySearchTree root, int data){
        /*
        Three cases are possible
        Node to be deleted has no children -> mark node as null
        Node to be deleted has only one child -> left or right -> replace node with left or right child
        Node to be deleted has both child -> go to the right subtree -> find the left most node ->
        replace root with left most node of the right subtree -> delete left most node of the right subtree
         */
        if(root == null){
            return root;
        }
        if(root.data == data){
            if(root.leftNode == null && root.rightNode == null){
                return null;
            }
            else if(root.leftNode == null || root.rightNode == null ){
                if(root.leftNode == null){
                    root = root.rightNode;
                    return root;
                }
                if(root.rightNode == null){
                    root = root.leftNode;
                    return root;
                }
            }
            else {
                BinarySearchTree minimumNode = findMinimumNode(root.rightNode);
                root.data = minimumNode.data;
                deleteFromBinaryTree(root.rightNode,minimumNode.data);
                return root;
            }
        }
        if(root.data < data){
            root.rightNode = deleteFromBinaryTree(root.rightNode,data);
        }
        if(root.data > data){
            root.leftNode = deleteFromBinaryTree(root.leftNode,data);
        }
        return root;
    }

    public static BinarySearchTree searchInBinaryTree(BinarySearchTree root, int data){
        if(root == null){
            return null;
        }
        if(data == root.data){
            return root;
        }
        if(data>root.data){
            return  searchInBinaryTree(root.rightNode,data);
        }
        else{
            return  searchInBinaryTree(root.leftNode,data);
        }
    }
    private static BinarySearchTree findMinimumNode(BinarySearchTree root) {
        while(root.leftNode!=null){
            root = root.leftNode;
        }
        return root;
    }

    public static void levelOrderTraversal(BinarySearchTree root){
        Queue<BinarySearchTree> traversalQueue = new LinkedList<>();
        if(root == null){
            return;
        }
        traversalQueue.add(root);
        while(!traversalQueue.isEmpty()){
            BinarySearchTree currentNode = traversalQueue.poll();
            System.out.println(currentNode.data);
            if(currentNode.leftNode!=null){
                traversalQueue.add(currentNode.leftNode);
            }
            if(currentNode.rightNode!=null){
                traversalQueue.add(currentNode.rightNode);
            }
        }
    }

    public static int heightOfBinaryTree(BinarySearchTree root){
        int heightOfBinaryTree = 0;
        if(root == null){
            return heightOfBinaryTree;
        }
        else{
            heightOfBinaryTree = Math.max(heightOfBinaryTree(root.leftNode),heightOfBinaryTree(root.rightNode))+1;
        }
        return heightOfBinaryTree;
    }

    public static BinarySearchTree firstMaxKeyForGivenValue(BinarySearchTree root, int givenKey){
        if(root == null){
            return root;
        }
        if(givenKey <= root.data){
            BinarySearchTree nextCandidate = firstMaxKeyForGivenValue(root.leftNode,givenKey);
            if(nextCandidate != null && root.data < nextCandidate.data){
                return nextCandidate;
            }
            else{
                return root;
            }
        }
        else{
            return firstMaxKeyForGivenValue(root.rightNode,givenKey);
        }
    }

    public int getData() {
        return this.data;
    }

    public static BinarySearchTree findLeastCommonAncestors(int key1, int key2, BinarySearchTree root){
        /*
        Search for key 1 and key 2 in the binary tree, if not found , return null as no ancestor found
         */
        BinarySearchTree key1Node = searchInBinaryTree(root,key1);
        BinarySearchTree key2Node = searchInBinaryTree(root,key2);
        if(key1Node == null || key2Node == null){
            return null;
        }
        return findLeastCommonAncestorsForTree(root,key1,key2);

    }

    private static BinarySearchTree findLeastCommonAncestorsForTree(BinarySearchTree root, int key1, int key2) {
        if(root.data == key1 || root.data == key2 || (root.data > key1 && root.data < key2)||
                (root.data < key1 && root.data > key2)){
            return root;
        }
        else{
            if(root.data < key1 && root.data < key2){
               return findLeastCommonAncestorsForTree(root.rightNode,key1,key2);
            }
            else{
                return findLeastCommonAncestorsForTree(root.leftNode,key1,key2);
            }
        }
    }
}
