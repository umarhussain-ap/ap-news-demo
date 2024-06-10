package com.systems.demo.apnewsdemo.datastructures.nodebased;

import java.util.Objects;
import java.util.Optional;

public class LRUCache<T> {

    private Queue<T> cacheValQueue;

    public void addToCache(T val) {
        if(cacheValQueue==null){
            cacheValQueue = new Queue<>();
            cacheValQueue.insertFirst(val);
        }
        if(cacheValQueue.getSize()==1) {
           upsertData(val,cacheValQueue.getRootNode());

            }
        }

    private void upsertData(T val,QueueNode<T> queueNode) {
        boolean update = Optional.ofNullable(queueNode)
            .map(QueueNode::getData)
            .map(QueueNode.Data::getValue)
            .stream().anyMatch(data -> Objects.equals(queueNode.getData().getValue(),val));
            if(update) {
                queueNode.getData().updateTime();
            }else{

                queueNode.setData(new QueueNode.Data<>(val));
            }
    }

    }




