package datastructures;

// First In/First Out
// should use to store reservations

public class Queue {

    class Node<T extends Comparable<T>>{
        private T value;
        private Node<T> next;

        Node(T value){
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public <T> Queue(T value){
        // create a node and set first and last to it
        Node newNode = new Node((Comparable) value);
        first = newNode;
        last = newNode;
        length ++;
    }

    // print the contents of the Queue
    public void printQueue(){
        Node temp = first;
        while(temp != null){
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    // get the value of the first node in the queue
    public <T> Object getFirst(){
        return first.value;
    }

    // get the value of the last node in the queue
    public <T> Object getLast(){
        return last.value;
    }

    // Add Node to the end of the queue
    public <T> void enqueue(T value){
        Node newNode = new Node((Comparable) value); // create new Node;
        if(length == 0){ // empty queue
            first = newNode;
            last = newNode;
        } else{ // point last.next to the newNode and shift last to newNode
            last.next = newNode;
            last = newNode;
        }
        length ++;
    }

    // Remove Node from the front of the queue
    public <T> Node dequeue(){
        if(length == 0) return null;
        Node temp = first;
        if(length == 1){
            first = null;
            last = null;
        } else{
            first = temp.next;
            temp.next = null;
        }

        length --;
        return temp;
    }
}
