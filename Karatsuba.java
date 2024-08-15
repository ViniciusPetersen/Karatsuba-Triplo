import java.util.Scanner;

public class Karatsuba {

    public static void main(String args[]) {
        
        System.out.println(vezes(args[0], args[1]));
    }

    public static String vezes(String a, String b) {
        if (a == "0" || b == "0") {
            return "0";
        }

        int aT = a.length();
        int bT = b.length();
        if (aT == 1 && bT == 1) {
            String result = Integer.parseInt(a) * Integer.parseInt(b) + "";
            return result;
        } else {

            String a1 = "", a2 = "", a3 = "", b1 = "", b2 = "", b3 = "";
            if (aT == 1) {

                a1 = "0";
                a2 = "0";
                a3 = a;
            } else if (aT == 2) {
                a1 = "0";
                a2 = a.substring(0, 1);
                a3 = a.substring(aT - 1, aT);
            } else if (aT % 3 != 0) {
                a1 = a.substring(0, aT / 3 + (aT % 3) / (aT % 3));
                a2 = a.substring(aT / 3 + (aT % 3) / (aT % 3), aT * 2 / 3 + (aT % 3) / (aT % 3));
                a3 = a.substring(aT * 2 / 3 + (aT % 3) / (aT % 3), aT);
            } else if (aT % 3 == 0) {
                a1 = a.substring(0, aT / 3);
                a2 = a.substring(aT / 3, aT * 2 / 3);
                a3 = a.substring(aT * 2 / 3, aT);
            }
            if (bT == 1) {

                b1 = "0";
                b2 = "0";
                b3 = b;
            } else if (bT == 2) {
                b1 = "0";
                b2 = b.substring(0, 1);
                b3 = b.substring(bT - 1, bT);
            } else if (bT % 3 != 0) {
                b1 = b.substring(0, bT / 3 + (bT % 3) / (bT % 3));
                b2 = b.substring(bT / 3 + (bT % 3) / (bT % 3), bT * 2 / 3 + (bT % 3) / (bT % 3));
                b3 = b.substring(bT * 2 / 3 + (bT % 3) / (bT % 3), bT);
            } else if (bT % 3 == 0) {
                b1 = b.substring(0, bT / 3);
                b2 = b.substring(bT / 3, bT * 2 / 3);
                b3 = b.substring(bT * 2 / 3, bT);
            }
            String aShift1 = "";
            String aShift2 = "";
            String bShift1 = "";
            String bShift2 = "";
            if (aT > 2) {
                for (int i = 0; i < a3.length(); i++) {
                    aShift2 = aShift2 + "0";
                }
                for (int i = 0; i < a2.length() + a3.length(); i++) {
                    aShift1 = aShift1 + "0";
                }
            } else if (aT > 1) {
                for (int i = 0; i < a3.length(); i++) {
                    aShift2 = aShift2 + "0";
                }
            }
            if (bT > 2) {
                for (int i = 0; i < b3.length(); i++) {
                    bShift2 = bShift2 + "0";

                }
                for (int i = 0; i < b2.length() + b3.length(); i++) {
                    bShift1 = bShift1 + "0";
                }
            } else if (bT > 1) {
                for (int i = 0; i < b3.length(); i++) {
                    bShift2 = bShift2 + "0";
                }
            }

            String a1xb1 = vezes(a1, b1) + aShift1 + bShift1;
            String a1xb2 = vezes(a1, b2) + aShift1 + bShift2;
            String a1xb3 = vezes(a1, b3) + aShift1;
            String a2xb1 = vezes(a2, b1) + aShift2 + bShift1;
            String a2xb2 = vezes(a2, b2) + aShift2 + bShift2;
            String a2xb3 = vezes(a2, b3) + aShift2;
            String a3xb1 = vezes(a3, b1) + bShift1;
            String a3xb2 = vezes(a3, b2) + bShift2;
            String a3xb3 = vezes(a3, b3);

            String resultado = sumStrings(a1xb1, sumStrings(a1xb2, sumStrings(a1xb3, sumStrings(a2xb1,
            sumStrings(a2xb2, sumStrings(a2xb3, sumStrings(a3xb1, sumStrings(a3xb2, a3xb3))))))));

            while(resultado.charAt(0) == '0' && resultado.length() > 1) {
                resultado = resultado.substring(1);
            }

            return resultado;
        }
    }

    public static String sumStrings(String num1, String num2) {
        StringBuilder totalSum = new StringBuilder();
        int carry = 0;
        int maxLength = Math.max(num1.length(), num2.length());

        for (int i = 0; i < maxLength; i++) {
            int digit1 = (i < num1.length()) ? Character.getNumericValue(num1.charAt(num1.length() - 1 - i)) : 0;
            int digit2 = (i < num2.length()) ? Character.getNumericValue(num2.charAt(num2.length() - 1 - i)) : 0;

            int sum = digit1 + digit2 + carry;

            carry = sum / 10;

            totalSum.insert(0, sum % 10);
        }

        if (carry > 0) {
            totalSum.insert(0, carry);
        }

        return totalSum.toString();
    }
}