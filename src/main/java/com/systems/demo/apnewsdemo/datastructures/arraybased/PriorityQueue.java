package com.systems.demo.apnewsdemo.datastructures.arraybased;

import java.util.Arrays;

public class PriorityQueue {
    Value[] queue = new Value[10];

    int size = -1;

    public void enqueue(Value value) {
       size++;
       if(size==0) {
           queue[size] = value;
       } else {
           if(queue[size-1].compareTo(value) >= 0) {
               queue[size] = value;
           } else {
               int insertIndex = peak(value);
               System.out.println("insertIndex value:"+insertIndex);
               // once the peak value is found where to insert the value in array we shift the array values to the right
               // insert the value at peak and move rest of the value forward
               // first move them forward
               for (int i = size; i > insertIndex; i--) {
                   queue[i] = queue[i - 1];
               }
               queue[insertIndex]=value;
           }
       }

   }

   public Value dequeue() {
        if(size>0) {
            Value value = queue[size];
            queue[size] = null;
            size--;
            return value;
        }
        return null;
   }

    private int peak(Value value) {
        int peak = size-1;
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

class main{

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enqueue(new Value(10,1));
        priorityQueue.enqueue(new Value(10,10));
        priorityQueue.enqueue(new Value(30,0));
        priorityQueue.enqueue(new Value(30,0));
        priorityQueue.enqueue(new Value(30,8));
        priorityQueue.enqueue(new Value(30,5));
        priorityQueue.enqueue(new Value(35,5));

        while(priorityQueue.size>0) {
            System.out.println(priorityQueue.dequeue());
        }
    }
}
