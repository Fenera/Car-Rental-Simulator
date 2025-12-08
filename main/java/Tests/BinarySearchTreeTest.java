package Tests;

import edu.augie.finalProgram.taye.DataStructures.BinarySearchTree;

public class BinarySearchTreeTest {

    public static void main(String[] args) {

        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        System.out.println("=== INSERT TEST ===");
        bst.insert(50, "Car A");
        bst.insert(25, "Car B");
        bst.insert(75, "Car C");
        bst.insert(10, "Car D");
        bst.insert(30, "Car E");

        System.out.println("Inserted 5 nodes.\n");

        System.out.println("=== PRINT TREE (IN ORDER) ===");
        bst.printTree(bst.getRoot());
        System.out.println();

        System.out.println("=== CONTAINS TEST ===");
        System.out.println("Contains 25? " + bst.contains(25));
        System.out.println("Contains 99? " + bst.contains(99));
        System.out.println();

        System.out.println("=== SEARCH TEST ===");
        System.out.println("Search key 75 → " + bst.searchByKey(75));
        System.out.println("Search key 10 → " + bst.searchByKey(10));
        System.out.println("Search key 99 → " + bst.searchByKey(99));
        System.out.println();

        System.out.println("=== DELETE TEST ===");
        System.out.println("Deleting 25...");
        bst.delete(25);

        System.out.println("Tree after deletion:");
        bst.printTree(bst.getRoot());
    }
}
