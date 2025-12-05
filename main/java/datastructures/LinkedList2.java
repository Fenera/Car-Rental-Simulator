package datastructures;

// same as LinkedList but it accepts two type parameters
// singly-linked list
// use for rental logs (write to .txt file in utilities package)
// note to self: add (Object) to nodes in void methods & constructor
public class LinkedList2 <T extends Comparable<T>, V>{

    // generic class for Node
    class Node<T extends Comparable<T>, V>{
        private T key;
        private V value;
        private Node<T, V> next;

        Node(T key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private Node head; // front of LL
    private Node tail; // end of LL
    private int length;

    public <T, V> LinkedList2(){
        // T value - parameter
        //Node firstNode = new Node((Object) value); // create first node
        //head = firstNode;
        //tail = firstNode;
        length = 1;
    }

    public <T, V> Object getHead(){
        return head.value;
    }
    public <T, V> Object getTail(){
        return tail.value;
    }
    public int getLength(){
        return length;
    }


    // Remove by object
    public V removeByValue(T keyRemove){
        // LL is empty
        if(head == null){
            return null;
        }
        // head is the node to remove
        if(head != null && head.key == keyRemove){
            head = head.next;
            return (V) head.value;
        }
        LinkedList2.Node currentNode = head;
        LinkedList2.Node previousNode = null;
        LinkedList2.Node temp = null;

        // go through list and search for value
        while(currentNode != null && currentNode.key != keyRemove){
            previousNode = currentNode;
            currentNode = currentNode.next;
            temp = currentNode;
        }

        // if loop exited bc currentNode == null, skip if statement & return false
        // if loop exited bc currentNode.value == valueRemove, enter if statement & return true
        if(currentNode != null){
            previousNode.next = currentNode.next;
            return (V) temp.value;
        }
        return null;
    }

    // Append: Adding a node to the end of a linked list:
    public <T, V> void append(T key, V value){
        LinkedList2.Node newNode = new LinkedList2.Node((Comparable) key, value); // create newNode
        if(head == null){ // LL is empty
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode; // set the next value of tail to
            tail = newNode;
        }

        length ++;
    }

    }
