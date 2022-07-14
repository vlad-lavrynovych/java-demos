package org.java_demos.spring_core.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "1", "2", "2", "2", "3");

        Map<String, Integer> collect = strings.stream().collect(Collectors.toMap(s -> s, s -> 1, (a, b) -> a + b));

        System.out.println(collect);


        Node n4 = new Node("4", null);
        Node n3 = new Node("3", n4);
        Node n2 = new Node("2", n3);
        Node n1 = new Node("1", n2);

        System.out.println(n1);

        Node prev = null;
        Node current = n1;
        while (current.hasNext()) {
            Node next = current.next();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        current.setNext(prev);

        System.out.println(n4);


        String str = "12345678";
        System.out.println(Arrays.stream(str.split("")).mapToInt(s -> Integer.parseInt(s)).sum());

        int i = Integer.parseInt(str);
        int result = 0;
        for (int i1 = 1; i1 <= str.length(); i1++) {
            int nextVal = i % 10;
            result += nextVal;
            i = i / 10;
        }
        System.out.println(result);
    }
}

class Node {
    String name;
    Node next;

    public Node(String name, Node next) {
        this.name = name;
        this.next = next;
    }

    boolean hasNext() {
        return next != null;
    }

    Node next() {
        return next;
    }

    void setNext(Node node) {
        next = node;
    }

    @Override
    public String toString() {
        return name + " " + (next == null ? "" : next.toString());
    }
}
