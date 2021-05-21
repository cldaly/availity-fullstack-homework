package com.availity.cldaly;

import java.util.Scanner;

public class Parentheses {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean keepRunning;
        do {
            System.out.println("Please enter Lisp code for parentheses validation:");
            String str = sc.nextLine();
            boolean isValid = validateParentheses(str);
            System.out.println("Parentheses are correctly nested and closed: " + isValid);
            System.out.println("Check another? (yes/no): ");
            String res = sc.nextLine();
            keepRunning = res.equals("yes");
        } while (keepRunning);
    }

    private static boolean validateParentheses(String s) {
        if (s == null || s.trim().length() == 0) return false;
        long check = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') check++;
            if (c == ')') check--;
            if (check < 0) return false;
        }
        return check == 0;
    }
}
