package com.systems.demo.apnewsdemo.datastructures.nodebased;

import lombok.Getter;


@Getter
public class Queue<T> {
    private QueueNode<T> rootNode = null;

    private QueueNode<T> tailNode = null;

    private int size;

    public void insertLast(T t) {
        if(rootNode==null) {
            rootNode = new QueueNode<>(new QueueNode.Data<>(t), null, tailNode);
        }else if(tailNode == null) {
            tailNode = new QueueNode<>(new QueueNode.Data<>(t), rootNode, null);
        }else {
            QueueNode<T> node = new QueueNode<>(new QueueNode.Data<>(t),tailNode,null);
            tailNode.next = node;
            tailNode = node;
        }
        size++;
    }

    public void insertFirst(T t) {
        if(rootNode==null) {
            rootNode = new QueueNode<>(new QueueNode.Data<>(t), null, tailNode);
        }else if(tailNode == null) {
            QueueNode<T> queueNode = new QueueNode<>(new QueueNode.Data<>(t), null, rootNode);
            tailNode = rootNode;
            tailNode.previous = queueNode;
            tailNode.next = null;
            rootNode = queueNode;
        }else {
            QueueNode<T> queueNode = new QueueNode<>(new QueueNode.Data<>(t), null, rootNode);
            rootNode.previous = queueNode;
            rootNode = queueNode;
        }
        size++;
    }

    public T removeLast() {
        T value = null;

        if(tailNode==null) {
            return value;
        }
        else if(tailNode.previous==rootNode ) {
            value = tailNode.getData().getValue();
            tailNode=null;
            rootNode = null;
            size--;
        } else {
            value = tailNode.getData().getValue();
            tailNode = tailNode.previous;
            size--;
        }
        return value;
    }

    public T removeFirst() {
        T value = null;

        if(rootNode==null) {
            return value;
        }
        else if(rootNode.next==tailNode ) {
            value = rootNode.getData().getValue();
            tailNode=null;
            rootNode = null;
            size--;
        } else {
            value = rootNode.getData().getValue();
            rootNode = tailNode.next;
            size--;
        }
        return value;
    }


}

