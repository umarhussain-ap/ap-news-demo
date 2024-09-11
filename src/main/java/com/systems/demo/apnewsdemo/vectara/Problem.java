package com.systems.demo.apnewsdemo.vectara;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;

/*

Write a function that determines whether a string (containing brackets) is in proper form or not.
For a string to be in proper form, following criteria should be fulfilled:
1. All opening brackets must close in the same order.
2. The number of opening brackets and closing brackets must be the same for all type of brackets.

Ignore every character other than brackets, other chars can come at any place, in any order.

function isValid(str: String) {
}

For example:
1. []
valid
2. [{(1})]
Invalid
3. [{1(ab({cd}{()}))}]
valid

*/


public class Problem {

    public static final char SQUARE_OB = '[';
    public static final char CURLY_OB = '{';

    public static final char SMALL_OB = '(';

    public static final char SMALL_CB = ')';

    public static final char CURLY_CB = '}';

    public static final char SQUARE_CB = ']';


    public static boolean validParenthesis(String string) {

        char [] array = string.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean isValid = false;

        for(int i = 0; i < array.length; i++) {
            if(array[i]==SMALL_OB || array[i]==CURLY_OB || array[i]== SQUARE_OB) {
                if(i == array.length-1) {
                    return false;
                }
                stack.push(array[i]);
                continue;
            }

            if(stack.isEmpty()) {
                return false;
            }

            if(array[i]== SMALL_CB) {
                char pop = stack.pop();

                isValid = pop == SMALL_OB;

                if(!isValid) {
                    break;
                }

            } else if (array[i]==CURLY_CB) {

                char pop = stack.pop();

                isValid = pop ==CURLY_OB;

                if(!isValid) {
                   break;
                }

            } else if (array[i]==SQUARE_CB) {

                char pop = stack.pop();

               isValid =  pop == SQUARE_OB;

                if(!isValid) {
                    break;
                }

            }

        }

        return stack.isEmpty() && isValid;
    }

    public String isBalanced(String string) {

        char [] array = string.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean isValid = false;

        for(int i = 0; i < array.length; i++) {
            if(array[i]==SMALL_OB || array[i]==CURLY_OB || array[i]== SQUARE_OB) {
                if(i == array.length-1) {
                    break;
                }
                stack.push(array[i]);
                continue;
            }

            if(stack.isEmpty()) {
                break;
            }

            if(array[i]== SMALL_CB) {
                char pop = stack.pop();

                isValid = pop == SMALL_OB;

                if(!isValid) {
                    break;
                }

            } else if (array[i]==CURLY_CB) {

                char pop = stack.pop();

                isValid = pop ==CURLY_OB;

                if(!isValid) {
                    break;
                }

            } else if (array[i]==SQUARE_CB) {

                char pop = stack.pop();

                isValid =  pop == SQUARE_OB;

                if(!isValid) {
                    break;
                }

            }

        }

        return stack.isEmpty() && isValid ? "YES" : "NO" ;
    }

}

@Slf4j
class Driver {

    public static void main(String[] args) {
        System.out.println("([{asdsa}]) :"+ Problem.validParenthesis("([{asdsa}])"));
        System.out.println("([{}]) :"+ Problem.validParenthesis("([{}])"));
        System.out.println("([asdsa}]) :"+ Problem.validParenthesis("([asdsa}])"));
        System.out.println("([{asdsa}] :"+ Problem.validParenthesis("([{asdsa}]"));
        System.out.println("()() :"+Problem.validParenthesis("()()"));
    }

}
