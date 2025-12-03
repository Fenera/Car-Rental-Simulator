package datastructures;

// singly-linked list
// use for rental logs (write to .txt file in utilities package)
// note to self: add (Comparable) to nodes in void methods & constructor
public class LinkedList{

    // generic class for Node
    class Node<T extends Comparable<T>>{
        private T value;
        private Node<T> next;

        Node(T value){
            this.value = value;
        }
    }

    private Node head; // front of LL
    private Node tail; // end of LL
    private int length;

    public <T> LinkedList(T value){
        Node firstNode = new Node((Comparable) value); // create first node
        head = firstNode;
        tail = firstNode;
        length = 1;
    }

    public <T> Object getHead(){
        return head.value;
    }
    public <T> Object getTail(){
        return tail.value;
    }
    public int getLength(){
        return length;
    }

    public void printLinkedList(){
        Node temp = head; // pointer to head
        while (temp != null) { // go to end of LL or tail.next (== null)
            System.out.print(temp.value + " -> "); // print temp value
            temp = temp.next; // shift temp next
        }
        System.out.println(); // new line
    }

    // Append: Adding a node to the end of a linked list:
    public <T> void append(T value){
        Node newNode = new Node((Comparable) value); // create newNode
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
