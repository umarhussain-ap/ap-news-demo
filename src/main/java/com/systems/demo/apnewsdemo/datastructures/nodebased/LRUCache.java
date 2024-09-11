package com.systems.demo.apnewsdemo.datastructures.nodebased;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;

@Getter
public class LRUCache<T> {

    private Queue<T> queue;

    private Map<T, QueueNode<T>> valueNodeMap;

    private final int cacheSize;

    public LRUCache() {
        super();
        this.cacheSize = 10;
    }

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize <= 0 ? 10 : cacheSize;
    }

    public void addToCache(T val) {
        if (queue == null || queue.getSize() == 0) {
            newInsertion(val);
        }
        if (queue.getSize() >= 1) {
            upsertData(val);
        }
    }

    private void newInsertion(T val) {
        queue = new Queue<>();
        this.valueNodeMap = new HashMap<>();
        queue.insertFirst(val);
        valueNodeMap.put(val, queue.getRootNode());
    }

    private void upsertData(T val) {
        //first check root and last then check the map
        QueueNode<T> queueNode = valueNodeMap.get(val);
        if (Objects.nonNull(queueNode)) {
            queueNode.getData().updateTime();
            //queue.
        } else  {
            if(queue.getSize() <= cacheSize) {
                newInsertion(val);
            } else {
                queue.removeLast();
                newInsertion(val);
            }

        }
    }

}




