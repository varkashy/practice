package com.varkashy.tree;

public class BinaryTree {
   public int data;
   public BinaryTree left;
   public BinaryTree right;

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
