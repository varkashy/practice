package com.varkashy.tree.test;

import com.varkashy.tree.BinaryTree;

public class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTree root = BinaryTree.createBinaryTreeFromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        testHeightOfBinaryTree(root);
        testTraversal(root);
        testSearch(root,new int[]{3,11});
        testInsertIntoBinaryTree(root, new int[]{-1,11});
        testDeleteFromBinaryTree(root, new int[]{4,7,11});
    }

    private static void testHeightOfBinaryTree(BinaryTree root) {
        int heightOfBinaryTree = BinaryTree.heightOfBinaryTree(root);
        System.out.println(heightOfBinaryTree);
    }

    private static void testDeleteFromBinaryTree(BinaryTree root, int[] ints) {
        for(int toDelete:ints){
            root = BinaryTree.deleteFromBinaryTree(root,toDelete);
            System.out.println("inOrder after deleting "+toDelete);
            BinaryTree.inOrderTraversal(root);
        }
    }

    private static void testInsertIntoBinaryTree(BinaryTree root, int[] ints) {
        for(int toInsert: ints){
           root = BinaryTree.insertIntoBinaryTree(root,toInsert);
        }
        BinaryTree.inOrderTraversal(root);
    }

    private static void testSearch(BinaryTree root, int[] ints) {
        for(int toSearch:ints){
            BinaryTree resultNode = BinaryTree.searchInBinaryTree(root,toSearch);
            if(resultNode == null){
                System.out.println("Not Found");
            }
            else{
                System.out.println("Found");
            }
        }
    }

    private static void testTraversal(BinaryTree root) {
        System.out.println("In-Order");
        BinaryTree.inOrderTraversal(root);
        System.out.println("Post-Order");
        BinaryTree.postOrderTraversal(root);
        System.out.println("Pre-Order");
        BinaryTree.preOrderTraversal(root);
        System.out.println("level order traversal");
        BinaryTree.levelOrderTraversal(root);
    }
}
