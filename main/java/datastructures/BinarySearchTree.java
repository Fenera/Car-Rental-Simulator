package datastructures;

// stores the cars
// the value should be the VIN b/c every car has one
// can also store staff by their staff ID number


// NOTE: values in a BST must be unique (in this case)
/*
* Source accessed: https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
* Date: 12/2/2025*/

public class BinarySearchTree {

    // create Node class
    // T must extend Number to implement comparison
    class Node<T extends Number> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        // methods

        public Node(T value){
            this.value = value;
        }

        public T getValue(){
            return value;
        }

        public void setValue(T value){
            this.value = value;
        }

        public Node<T> getLeft(){
            return left;
        }

        public void setLeft(Node<T> newLeft){
            left = newLeft;
        }

        public Node<T> getRight(){
            return right;
        }

        public void setRight(Node<T> newRight){
            right = newRight;
        }

        private Node root; // the root is the top-most node of a BST

        // no BST constructor is needed because I can assign a root in insert so default constructor suffices

        public boolean insert(T value){
            Node newNode = new Node(value);

            // assign root if BST is empty
            if(root == null){
                root = newNode;
            }

            try {
                // check if new value is already present in the BST
                Node temp = root; // temporary Node to serve as an iterator
                // NOTE: temp is a pointer to root not reference
                while (true) {
                    if (newNode.value == temp.value) {
                        return false;
                    }

                    if ((int) newNode.value < (int) temp.value) { // newNode is less than temp (to the left of)
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
            }catch(Exception e){
                System.out.println("BST can only contain integer values");
            }
            return false; // to resolve missing return statement error
        }

        // checks if the binary search tree contains the value
        public boolean contains(T value){
            if(root == null) return true; // BST is empty
            Node temp = root; // temporary variable that points to root
            while(temp != null){
                // value is larger than temp's value (to the right of)
                if((int) value > (int) temp.value){ // convert generic to int for statement to work
                    temp = temp.right; // shift temp to right node
                } else if ((int) value < (int) temp.value){ // value is less thhan temp (to the left of)
                    temp = temp.left; // shift temp to the left;
                } else{ // value is not less than/more than temp so it is EQUAL
                    return true;
                }
            }
            return false; // BST did not contain value
        }
    }

}
