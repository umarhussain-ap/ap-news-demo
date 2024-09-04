package com.systems.demo.apnewsdemo.problem.solving;

import java.util.ArrayList;
import java.util.List;

public class DecimalStringValueCheck {

    public static final String number = "2.5";

    private static List<Double> range(String groupingValue) {
        double value = Double.parseDouble(groupingValue);
        if (value < 1d && value > 0d) {
            return new ArrayList<>(List.of(0d, 1d));
        } else {
            return new ArrayList<>(List.of(Math.floor(value), Math.ceil(value)));
        }

    }

    public static void main(String[] args) {
        System.out.println(Double.parseDouble(number));
       double test  = Double.parseDouble(number);
       System.out.println(Math.ceil(test) == test);

        System.out.println("checking range for 0.5");


        List<Double> range = range("0.5");

        System.out.println("range :"+range);

        System.out.println("range[1] to string  :"+ range.get(1));


        boolean checkString = String.valueOf(range.get(1)).equals(String.valueOf(1));

        System.out.println("checkString:" +checkString);

        String valueAfterModification = String.valueOf(range.get(1)).replaceAll("\\.\\d+$", "");

        boolean checkString2 = valueAfterModification.equals(String.valueOf(1));

        System.out.println("checkString2:" +checkString2);

        boolean testingValue = Math.round(range.get((int) 1.5)) == 1.5;

        System.out.println("value equvalincy :"+ testingValue);


    }

}
