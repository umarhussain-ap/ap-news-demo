package com.systems.demo.apnewsdemo.datastructures.nodebased;

public class Driver {

    public static void main(String[] args) {
        Queue<Integer>queue = new Queue<>();
        queue.insertFirst(1);
        queue.insertFirst(2);
        queue.insertFirst(3);
        queue.insertLast(4);
        queue.insertLast(5);
        queue.insertLast(6);
        System.out.println("size: "+queue.getSize());
        System.out.println("root: "+queue.getRootNode().getData().getValue());
        System.out.println("tail: "+queue.getTailNode().getData().getValue());

        QueueNode q = queue.getRootNode();
        while (q !=null){
            System.out.print(q.getData().getValue()+" ");
            q = q.next;
        }
    }
}
