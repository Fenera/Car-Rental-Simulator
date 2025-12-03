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

    // removeLast: removes the last node in the linked list
    public <T> Node removeLast(){
        if(head == null){
            return null;
        }
        if(head.next == null){ // LL only has 1 node
            Node temp = head;
            head = null;
            length --;
            return temp;
        }
        Node secondToLast = head;
        while(secondToLast.next.next != null){ // iterate to second to last node
            secondToLast = secondToLast.next;
        }
        // remove the last node
        secondToLast.next = null;
        length --;
        return head; // return updated LL
    }

    // prepend: add a node to the front of the linked list
    public void prepend(int value){
        Node newNode = new Node(value);

        // empty LL
        if(head == null){
            head = newNode;
            tail = newNode;
        } else{
            newNode.next = head; // place in front of head
            head = newNode; // shift head to new node
        }
        length ++;
    }

    // remove the first node in the linked list
    public <T> Node removeFirst(){
        if(head == null){
            return null;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        length --;
        if(length == 0){ // if the LL only had 1 node which head and tail pointed to, remove tail's pointer as well as head's
            tail = null;
        }

        return temp;
    }

    // get: get a node in a linked list given it's index
    public <T> Node get(int index){
        if(index < 0 || index >= length) return null; // entered an invalid index

        Node temp = head;
        for(int i = 0; i < index; i ++){ // iterate up to index
            temp = temp.next; // move temp to the next node in the chain
        }
        return temp;
    }

    // set a value at a particular index
    public boolean set(int index, int value){
        if(index < 0 || index >= length) return false; // invalid index

        Node temp = head;

        for(int i = 0; i < index; i ++){
            temp = temp.next; // move to next node
        }
        temp.value = value; // change value to argument value
        return true;
    }

    // insert a node at a particular index
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false; // invalid index

        if (index == 0) { // insert at beginning of linked list
            prepend(value);
            return true;
        }
        if (index == length) { // insert at the end of linked list
            append(value);
            return true;
        }

        // create new node
        Node newNode = new Node(value);

        Node temp = get(index - 1); // get node to the left of where you want to insert
        newNode.next = temp.next; // point to node after temp
        temp.next = newNode; // redirect temp's next pointer to the newNode
        length++;
        return true;
    }

    // remove a node at a particular index

    public <T> Node remove(int index){
        // node is at the start or end of LL
        if(index == 0) return removeFirst();
        if(index == length - 1) return removeLast();

        Node prev = get(index - 1);
        if(prev != null){
            Node temp = prev.next;
            prev.next = temp.next; // shift pointer to point at node after the one we want to remove (O(1))
            temp.next = null;
            return temp;
        }else {
            return null;
        }
    }

    public void reverse(){
        // reverse head and tail
        Node temp = head;
        head = tail;
        tail = temp;

        Node after = temp.next;
        Node before = null;

        for(int i = 0; i < length; i ++){
            after = temp.next; // save the next node in after
            temp.next = before; // reverse the pointer
            before = temp; // move before forward
            temp = after; // move after forward
        }

    }
}
