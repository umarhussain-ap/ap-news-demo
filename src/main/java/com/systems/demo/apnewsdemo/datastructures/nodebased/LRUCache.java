package com.systems.demo.apnewsdemo.datastructures.nodebased;

import java.util.Objects;
import java.util.Optional;

import lombok.Getter;

@Getter
public class LRUCache<T> {

    private Queue<T> cache;

    private final int cacheSize;

    public LRUCache() {
        this.cacheSize = 10;
    }

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void addToCache(T val) {
        if(cache ==null) {
            cache = new Queue<>();
            cache.insertFirst(val);
        }
        if(cache.getSize()==1) {
           upsertData(val, cache.getRootNode());
          }
        }

    private void upsertData(T val,QueueNode<T> queueNode) {
        boolean update = Optional.of(queueNode)
            .map(QueueNode::getData)
            .map(QueueNode.Data::getValue)
            .stream()
            .anyMatch(data -> Objects.equals(queueNode.getData().getValue(), val));
            if(update) {
                queueNode.getData().updateTime();
            } else {
                queueNode.setData(new QueueNode.Data<>(val));
            }
        }

    }




