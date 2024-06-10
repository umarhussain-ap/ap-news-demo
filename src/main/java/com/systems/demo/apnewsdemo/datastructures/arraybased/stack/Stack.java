package com.systems.demo.apnewsdemo.datastructures.arraybased.stack;

import java.util.function.Function;

public class Stack {
    int[]stack;

    int top;

    public Stack(int size) {
        top = -1;
        stack = new int[size];
    }

    public void push(int value){
        stack[++top] = value;
    }

    public int pop() {
        return top >= 0 ? stack[top--] : -1;
    }

    public void printStack(){
        for (int j : stack) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack stack = new Stack(4);
        stack.push(10);
        stack.push(5);
        stack.push(4);
        stack.push(3);

        stack.printStack();

        System.out.println(stack.pop());
        stack.printStack();

    }


}
