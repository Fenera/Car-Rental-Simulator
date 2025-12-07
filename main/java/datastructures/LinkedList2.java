package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

// same as LinkedList but it accepts two type parameters
// V extends iterable to iterate trough values
public class LinkedList2 <T extends Comparable<T>, V extends Iterable> {

    // generic class for Node
    class Node<T extends Comparable<T>, V> {
        private T key;
        private V value;
        private Node<T, V> next;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head; // front of LL
    private Node tail; // end of LL
    private int length;

    public <T, V> LinkedList2() {
        // T value - parameter
        //Node firstNode = new Node((Object) value); // create first node
        //head = firstNode;
        //tail = firstNode;
        length = 1;
    }

    public <T, V> Object getHead() {
        return head.value;
    }

    public <T, V> Object getTail() {
        return tail.value;
    }

    public int getLength() {
        return length;
    }


    // Remove by object
    public V removeByValue(T keyRemove) {
        // LL is empty
        if (head == null) {
            return null;
        }
        // head is the node to remove
        if (head != null && head.key == keyRemove) {
            head = head.next;
            return (V) head.value;
        }
        LinkedList2.Node currentNode = head;
        LinkedList2.Node previousNode = null;
        LinkedList2.Node temp = null;

        // go through list and search for value
        while (currentNode != null && currentNode.key != keyRemove) {
            previousNode = currentNode;
            currentNode = currentNode.next;
            temp = currentNode;
        }

        // if loop exited bc currentNode == null, skip if statement & return false
        // if loop exited bc currentNode.value == valueRemove, enter if statement & return true
        if (currentNode != null) {
            previousNode.next = currentNode.next;
            return (V) temp.value;
        }
        return null;
    }

    // Append: Adding a node to the end of a linked list:
    public <T, V> void append(T key, V value) {
        LinkedList2.Node newNode = new LinkedList2.Node((Comparable) key, value); // create newNode
        if (head == null) { // LL is empty
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // set the next value of tail to
            tail = newNode;
        }

        length++;
    }

    // define iterator method to iterate values
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null; // checks if the current node is not null
            }

            @Override
            public V next() {
                if (current == null) throw new NoSuchElementException(); // current value is null
                V val = (V) current.value; // get value
                current = current.next; // shift current forward
                return val; // return the value (pass to it.next())
            }
        };
    }
}
