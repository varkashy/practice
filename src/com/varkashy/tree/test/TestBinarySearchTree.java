package com.varkashy.tree.test;

import com.varkashy.tree.BinarySearchTree;

public class TestBinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree root = BinarySearchTree.createBinaryTreeFromArray(new int[]{1, 2, 3, 4, 15, 19, 27});
        testLeastCommonAncestors(root);
        BinarySearchTree.findTopKKeys(root,4);
        testNextMaxKey(root,18);
        testHeightOfBinaryTree(root);
        testTraversal(root);
        testSearch(root,new int[]{3,11});
        testInsertIntoBinaryTree(root, new int[]{-1,11});
        testDeleteFromBinaryTree(root, new int[]{4,7,11});
    }

    private static void testLeastCommonAncestors(BinarySearchTree root) {
        BinarySearchTree leastCommonAncestors = BinarySearchTree.findLeastCommonAncestors(3, 19, root);
        System.out.println(leastCommonAncestors.getData());
        leastCommonAncestors = BinarySearchTree.findLeastCommonAncestors(3, 4, root);
        System.out.println(leastCommonAncestors.getData());
        leastCommonAncestors = BinarySearchTree.findLeastCommonAncestors(19, 27, root);
        System.out.println(leastCommonAncestors.getData());
        leastCommonAncestors = BinarySearchTree.findLeastCommonAncestors(1, 3, root);
        System.out.println(leastCommonAncestors.getData());
    }

    private static void testNextMaxKey(BinarySearchTree root, int givenKey) {
        BinarySearchTree maxKeyForGivenValue = BinarySearchTree.firstMaxKeyForGivenValue(root, givenKey);
        System.out.println(maxKeyForGivenValue.getData());
    }

    private static void testHeightOfBinaryTree(BinarySearchTree root) {
        int heightOfBinaryTree = BinarySearchTree.heightOfBinaryTree(root);
        System.out.println(heightOfBinaryTree);
    }

    private static void testDeleteFromBinaryTree(BinarySearchTree root, int[] ints) {
        for(int toDelete:ints){
            root = BinarySearchTree.deleteFromBinaryTree(root,toDelete);
            System.out.println("inOrder after deleting "+toDelete);
            BinarySearchTree.inOrderTraversal(root);
        }
    }

    private static void testInsertIntoBinaryTree(BinarySearchTree root, int[] ints) {
        for(int toInsert: ints){
           root = BinarySearchTree.insertIntoBinaryTree(root,toInsert);
        }
        BinarySearchTree.inOrderTraversal(root);
    }

    private static void testSearch(BinarySearchTree root, int[] ints) {
        for(int toSearch:ints){
            BinarySearchTree resultNode = BinarySearchTree.searchInBinaryTree(root,toSearch);
            if(resultNode == null){
                System.out.println("Not Found");
            }
            else{
                System.out.println("Found");
            }
        }
    }

    private static void testTraversal(BinarySearchTree root) {
        System.out.println("In-Order");
        BinarySearchTree.inOrderTraversal(root);
        System.out.println("Post-Order");
        BinarySearchTree.postOrderTraversal(root);
        System.out.println("Pre-Order");
        BinarySearchTree.preOrderTraversal(root);
        System.out.println("level order traversal");
        BinarySearchTree.levelOrderTraversal(root);
    }
}
