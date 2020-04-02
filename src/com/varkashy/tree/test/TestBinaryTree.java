package com.varkashy.tree.test;

import com.varkashy.tree.BinaryTree;

public class TestBinaryTree {

    public static void main(String[] args) {

        testBinaryTreeMirror();

        testRightOrderLevelTraversal();

        testRightOrderRecursion();
    }

    private static void testRightOrderRecursion() {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        root.right.right.right = new BinaryTree(8);
        System.out.println("Right skewed tree");
        BinaryTree.rightViewUsingRecursion(root);
        root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.left.left.left = new BinaryTree(6);
        root.left.left.right = new BinaryTree(7);
        root.left.left.left.left = new BinaryTree(8);
        System.out.println("left skewed tree");
        BinaryTree.rightViewUsingRecursion(root);
    }

    private static void testBinaryTreeMirror() {
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

    private static void testRightOrderLevelTraversal() {
        BinaryTree root = new BinaryTree(10);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(7);
        root.left.right = new BinaryTree(8);
        //root.right.right = new BinaryTree(15);
        root.right.left = new BinaryTree(12);
        root.right.left.right = new BinaryTree(14);

        BinaryTree.rightMostViewUsingLevelOrder(root);
    }
}
