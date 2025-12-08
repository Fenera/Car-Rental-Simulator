package Tests;

import edu.augie.finalProgram.taye.DataStructures.LinkedList;

import java.util.Iterator;

public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        System.out.println("=== APPEND ===");
        list.append(10);
        list.append(20);
        list.append(30);
        list.printLinkedList();

        System.out.println("Head: " + list.getHead());
        System.out.println("Tail: " + list.getTail());
        System.out.println("Length: " + list.getLength());

        System.out.println("Prepend");
        list.prepend(5);
        list.printLinkedList();


        list.insert(2, 15);
        list.printLinkedList();

        list.removeFirst();
        list.printLinkedList();

        list.removeLast();
        list.printLinkedList();


        list.set(1, 99);
        list.printLinkedList();

        list.removeByIndex(1);
        list.printLinkedList();

        System.out.println("test iterator");
        for (Iterator<Integer> it = list.items(); it.hasNext(); ) {
            Integer i = it.next();
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\n=== DELETE VALUE 10 ===");
        list.delete(10);
        list.printLinkedList();

        System.out.println("\n=== REVERSE ===");
        list.reverse();
        list.printLinkedList();
    }
}
