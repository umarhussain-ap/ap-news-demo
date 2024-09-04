package com.systems.demo.apnewsdemo.problem.solving;

import lombok.extern.slf4j.Slf4j;

public class NumberReversal {
    static int reverseNumber(int number) {
        int reverse = 0;
        while (number != 0) {
            int remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number /= 10;
        }
        return reverse;
    }
}
@Slf4j
class Driver {

    public static void main(String[] args) {
            Integer a = 1234567890;
            Integer b = 1214569870;
            log.info("a {}",a);
            log.info("b {}",b);

            Integer reverseA = NumberReversal.reverseNumber(a);
            Integer reverseB = NumberReversal.reverseNumber(b);

            log.info("a reverse {}",reverseA);
            log.info("b reverse {}",reverseB);

            log.info("sum of reverse {}", reverseA+reverseB);

    }
}
