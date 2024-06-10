package com.systems.demo.apnewsdemo.datastructures.arraybased;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Value implements Comparable<Value> {

    Value(int value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    private int priority;
    private int value;

    @Override
    public int compareTo(Value o) {

        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        } else if (this.value > o.getValue()) {
            return -1;
        } else if (this.value < o.getValue()) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "value{" +
            "priority=" + priority +
            ", value=" + value +
            '}';
    }
}
