package com.varkashy.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
   public int data;
   public BinaryTree left;
   public BinaryTree right;

   static int MAX_LEVEL = 0;
    public BinaryTree(int data){
        this.data = data;
    }

    public static boolean areMirror(BinaryTree tree1, BinaryTree tree2) {
        if(tree1 == null && tree2 == null){
            return true;
        }
        if(tree1 == null || tree2 == null){
            return false;
        }
        return (tree1.data == tree2.data)
                && (areMirror(tree1.left,tree2.right))
                && (areMirror(tree1.right,tree2.left));
    }

    public static void rightMostViewUsingLevelOrder(BinaryTree root){
        Queue<BinaryTree> treeQueue = new LinkedList<>();
        if(root == null){
            return;
        }
        treeQueue.add(root);
        while (!treeQueue.isEmpty())
        {
            // number of nodes at current level
            int n = treeQueue.size();

            // Traverse all nodes of current level
            for (int i = 1; i <= n; i++) {
                BinaryTree temp = treeQueue.poll();

                // Print the right most element at
                // the level
                if (i == n)
                    System.out.print(temp.data + " ");

                // Add left node to queue
                if (temp.left != null)
                    treeQueue.add(temp.left);

                // Add right node to queue
                if (temp.right != null)
                    treeQueue.add(temp.right);
            }
        }
    }

    public static void rightViewUsingRecursion(BinaryTree root){
        if(root == null){
            return;
        }
        else{
            MAX_LEVEL = 0;
            rightViewUsingRecursion(root,1);
        }
    }

    private static void rightViewUsingRecursion(BinaryTree root, int level) {
        if(root == null){
            return;
        }
        if(MAX_LEVEL < level){
            System.out.println(root.data);
            MAX_LEVEL = level;
        }
        rightViewUsingRecursion(root.right,level+1);
        rightViewUsingRecursion(root.left,level+1);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
