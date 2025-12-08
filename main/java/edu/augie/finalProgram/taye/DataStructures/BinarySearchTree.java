package edu.augie.finalProgram.taye.DataStructures;

// stores the cars
// the value should be the VIN b/c every car has one
// can also store edu.augie.finalProgram.taye.Staff by their edu.augie.finalProgram.taye.Staff ID number


// NOTE: values in a BST must be unique (in this case)
/*
* Source accessed: https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
* Date: 12/2
*
* Source accessed: https://stackoverflow.com/questions/47510048/printing-java-binary-search-tree
* Date: 12/4
*
* Source accessed: https://medium.com/swlh/java-how-to-delete-a-node-in-binary-search-tree-aa2d4befe728
* & https://www.digitalocean.com/community/tutorials/binary-search-tree-bst-search-insert-remove
* Date: 12/5
* */

public class BinarySearchTree <T extends Comparable<T>, V extends Comparable> {

    // create Node class
    // T must extend Number to implement comparison
    class Node<T extends Comparable<T>, V> {
        private T key;
        private V value;
        private Node<T, V> left;
        private Node<T, V> right;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        // methods

        public T getKey() {
            // returns key (vin # for sample)
            return key;
        }

        public void setKey(T key) {
            // sets the key
            this.key = key;
        }

        public V getValue() {
            // gets value (i.e Vehicle object)
            return value;
        }

        public void setValue(V value) {
            // sets vehicle object
            this.value = value;
        }

        public Node<T, V> getLeft() {
            // gets node on left side of subtree
            return left;
        }

        public void setLeft(Node<T, V> left) {
            // sets node on left side of subtree
            this.left = left;
        }

        public Node<T, V> getRight() {
            // gets node on right side of subtree
            return right;
        }

        public void setRight(Node<T, V> right) {
            // sets node on right side of subtree
            this.right = right;
        }
    }

        private Node<T, V> root; // the root is the top-most node of a BST

        // no BST constructor is needed because I can assign a root in insert so default constructor suffices

        public <T> boolean insert(T key, V value){
            // create a node with the key and value
            Node newNode = new Node((Comparable) key, value); // Comparable for key so we can do comparisons when inserting

            // assign root if BST is empty
            if(root == null){
                root = newNode; // assign the new node to root
            }
                // check if new key is already present in the BST
                Node temp = root; // temporary Node to serve as an iterator
                // NOTE: temp is a pointer to root
                while (true) {
                    if (newNode.key == temp.key) {
                        return false; // key already in BST
                    }
                    int compare = newNode.key.compareTo(temp.key);
                    if (compare < 0) { // newNode is less than temp (to the left of)
                        if (temp.left == null) {
                            temp.left = newNode; // temp.left is empty so put newNode there
                            return true;
                        }
                        temp = temp.left; // if temp.left is not empty, shift temp to the left node
                    } else{
                        if(temp.right == null){ // newNode is more than temp (to the right of)
                            temp.right = newNode;
                            return true;
                        }
                        temp = temp.right; // if temp.right is not empty, shift temp to the right node
                    }
                }
        }

        // checks if the binary search tree contains the key (i.e. Vin number)
        public <T extends Comparable, V> boolean contains(T key){
            if(root == null) return true; // BST is empty
            Node temp = root; // temporary variable that points to root
            while(temp != null){

                int compare = key.compareTo(temp.key);
                // key is larger than temp's key (to the right of)
                if(compare > 0){ // convert generic to int for statement to work
                    temp = temp.right; // shift temp to right node
                } else if (compare < 0){ // key is less than temp key(to the left of)
                    temp = temp.left; // shift temp to the left;
                } else{ // key is not less than/more than temp so it is EQUAL
                    return true;
                }
            }
            return false; // BST did not contain key
        }

        public V searchByKey(T key){
            if(root == null) return null; // BST is empty
            Node temp = root; // temporary variable that points to root
            while(temp != null){
                int compare = key.compareTo((T) temp.key); // use compareTo because key is not numeric
                // key is larger than temp's key (to the right of)
                if(compare > 0){
                    temp = temp.right; // shift temp to right node
                } else if (compare < 0){ // key is less than temp key(to the left of)
                    temp = temp.left; // shift temp to the left;
                } else{ // key is not less than/more than temp so it is EQUAL
                    return (V) temp.value;
                }
            }
            return null;
        }


        public Node<T, V> getRoot() {
            // returns the root
            return root;
        }

        public void printTree(Node node){
            // prints out the values of the tree recursively

                if(node == null) return; // base case

                printTree(node.left); // print left subtree
                System.out.println(node.value); // print the current nodes
                printTree(node.right); // print right subtree
        }

        public void delete(T key){
            // call removeByKey()
            // the root is updated until it is the node that is to be deleted
            root = removeByKey(root, key);
        }

        public Node<T, V> removeByKey(Node<T, V> root, T key){
            // removes the node from the bst given its value (i.e. remove vehicle from vehicle bst by vin #)
            // recursive method

            if(root == null){
                return null; // bst is empty
            }

            int compare = key.compareTo(root.key); // since key is a generic, use compare to get integer equivalent

            if(compare < 0){ // traverse tree (left side)
                root.left = removeByKey(root.left, key);
            } else if(compare > 0){
                root.right = removeByKey(root.right, key); // traverse tree on right side
            } else{ // this means the keys are equal to each other -> node is found

                // nodes have 1 child
                // deleting root node so the parent node should point to root.right (root.left is nonexistent)
                if(root.left == null){
                    return root.right;
                } else if(root.right == null){ // same as above but point to root.left
                    return root.right;
                }

                // node has two children
                // get the smallest in right subtree
                root.key = (T) findMin(root.right);

                root.right = removeByKey(root.right, root.key);
            }
            return root;
        }

        // finds the node with the smallest value in the subtree (left side for bst)
        public T findMin(Node node){
            T minv = (T) node.key;
            while(node.left != null) {
                minv = (T) node.left.key;
                node = node.left;
            }

            return minv;
        }



}
