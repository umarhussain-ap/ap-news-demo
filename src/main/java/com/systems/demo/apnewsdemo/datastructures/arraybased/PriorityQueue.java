package com.systems.demo.apnewsdemo.datastructures.arraybased;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
@Slf4j
public class PriorityQueue {


    private final Value[] queue;

    public PriorityQueue() {
        this.size = 10;
        this.queue = new Value[size];
    }
    public PriorityQueue(int size) {
        this.size = size;
        this.queue = new Value[size];
    }

    private final int size;

    int index = -1;

    public void enqueue(Value value) {
       index++;
       if(index ==0) {
           queue[index] = value;
       } else {
           if(queue[index -1].compareTo(value) >= 0) {
               queue[index] = value;
           } else {
               int insertIndex = peak(value);
              log.info("insertIndex value: {}", insertIndex);
               // once the peak value is found where to insert the value in array we shift the array values to the right
               // insert the value at peak and move rest of the value forward
               // first move them forward
               for (int i = index; i > insertIndex; i--) {
                   queue[i] = queue[i - 1];
               }
               queue[insertIndex]=value;
           }
       }

   }

   public Value dequeue() {
        if(index >0) {
            Value value = queue[index];
            queue[index] = null;
            index--;
            return value;
        }
        return null;
   }

    private int peak(Value value) {
        int peak = index -1;
        while(peak>=0) {
            if(queue[peak].compareTo(value)>=0) {
                peak++;
                return peak;
            }
            peak--;
        }
        return 0;
    }
}
@Slf4j
class Main {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enqueue(new Value(10,1));
        priorityQueue.enqueue(new Value(10,10));
        priorityQueue.enqueue(new Value(30,0));
        priorityQueue.enqueue(new Value(30,0));
        priorityQueue.enqueue(new Value(30,8));
        priorityQueue.enqueue(new Value(30,5));
        priorityQueue.enqueue(new Value(35,5));

        while(priorityQueue.index >0) {
            log.info(priorityQueue.dequeue().toString());
        }
    }
}
