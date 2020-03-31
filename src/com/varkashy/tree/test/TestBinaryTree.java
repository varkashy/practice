package com.varkashy.tree.test;

import com.varkashy.tree.BinaryTree;

public class TestBinaryTree {

    public static void main(String[] args) {

        BinaryTree a = new BinaryTree(1);
        BinaryTree b = new BinaryTree(1);
        a.left = new BinaryTree(2);
        a.right = new BinaryTree(3);
        a.left.left = new BinaryTree(4);
        a.left.right = new BinaryTree(5);

        b.left = new BinaryTree(3);
        b.right = new BinaryTree(2);
        b.right.left = new BinaryTree(5);
        b.right.right = new BinaryTree(4);

        if (BinaryTree.areMirror(a, b) == true)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
