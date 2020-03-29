package com.varkashy.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    int data;
    BinaryTree leftNode;
    BinaryTree rightNode;

    public BinaryTree(int data){
        this.data = data;
    }

    public static void inOrderTraversal(BinaryTree root){
        if(root == null){
            return;
        }
        inOrderTraversal(root.leftNode);
        System.out.println(root.data);
        inOrderTraversal(root.rightNode);
    }

    public static void preOrderTraversal(BinaryTree root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        preOrderTraversal(root.leftNode);
        preOrderTraversal(root.rightNode);
    }

    public static void postOrderTraversal(BinaryTree root){
        if(root == null){
            return;
        }
        postOrderTraversal(root.leftNode);
        postOrderTraversal(root.rightNode);
        System.out.println(root.data);
    }

    public static BinaryTree createBinaryTreeFromArray(int[] numbers){
        if(numbers.length == 0){
            return null;
        }
        //find midpoint
        return createBinaryTreeFromArray(numbers,0,numbers.length-1);
    }

    private static BinaryTree createBinaryTreeFromArray(int[] numbers, int low, int high) {
        if(low>high){
            return null;
        }
        int mid = (low+high)/2;
        BinaryTree root = new BinaryTree(numbers[mid]);
        root.leftNode = createBinaryTreeFromArray(numbers,low,mid-1);
        root.rightNode = createBinaryTreeFromArray(numbers,mid+1,high);
        return root;
    }

    public static BinaryTree insertIntoBinaryTree(BinaryTree root, int data){
        if(root == null){
            root = new BinaryTree(data);
        }
        else if(data>root.data){
            root.rightNode = insertIntoBinaryTree(root.rightNode,data);
        }
        else if (data < root.data){
            root.leftNode = insertIntoBinaryTree(root.leftNode,data);
        }
        return root;
    }

    public static BinaryTree deleteFromBinaryTree(BinaryTree root,int data){
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
                BinaryTree minimumNode = findMinimumNode(root.rightNode);
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

    public static BinaryTree searchInBinaryTree(BinaryTree root, int data){
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
    private static BinaryTree findMinimumNode(BinaryTree root) {
        while(root.leftNode!=null){
            root = root.leftNode;
        }
        return root;
    }

    public static void levelOrderTraversal(BinaryTree root){
        Queue<BinaryTree> traversalQueue = new LinkedList<>();
        if(root == null){
            return;
        }
        traversalQueue.add(root);
        while(!traversalQueue.isEmpty()){
            BinaryTree currentNode = traversalQueue.poll();
            System.out.println(currentNode.data);
            if(currentNode.leftNode!=null){
                traversalQueue.add(currentNode.leftNode);
            }
            if(currentNode.rightNode!=null){
                traversalQueue.add(currentNode.rightNode);
            }
        }
    }
}
