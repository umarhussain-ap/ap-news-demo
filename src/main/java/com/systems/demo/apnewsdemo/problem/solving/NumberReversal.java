package com.systems.demo.apnewsdemo.problem.solving;

public class NumberReversal {
 public static Integer reverseNumber(Integer number) {
     StringBuilder stringBuilder = new StringBuilder();
     stringBuilder.append(number);
     stringBuilder.reverse();

     return Integer.getInteger(stringBuilder.toString());

 }
}

class Driver {

    public static void main(String[] args) {

    }
}
