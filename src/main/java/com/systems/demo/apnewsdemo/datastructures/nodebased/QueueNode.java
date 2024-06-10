package com.systems.demo.apnewsdemo.datastructures.nodebased;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class QueueNode<T> {

    QueueNode<T> previous;
    QueueNode<T> next;

    @Setter
    @Getter
    private Data<T> data;

    public QueueNode(Data<T> data,QueueNode<T> previous,QueueNode<T> next) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    @Setter
    @Getter
    public static class Data<T> {
        private Integer priority;
        private T value;
        private LocalDateTime localDateTime = LocalDateTime.now();
        Data(T value) {
            this.value = value;
        }

        public void updateTime(){
            localDateTime = LocalDateTime.now();
        }
    }
}
