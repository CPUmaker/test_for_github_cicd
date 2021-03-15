package org.example;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyProcessor {
    private final Poly poly;
    private final String regex;

    public PolyProcessor() {
        poly = new Poly();
        String digit = "([+\\-]?\\d+)";
        String exp = String.format("([*]{2}\\s*%s)", digit);
        String power = String.format("(x(\\s*%s)?)", exp);
        String termX = String.format("(((%s\\s*[*]\\s*)|([+\\-]\\s*))?%s)", digit, power);
        String term = String.format("(%s|%s)", termX, digit);
        regex = String.format("\\s*([+\\-]\\s*)?%s\\s*", term);
    }

    public void processExpress(String line) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()) {
            if (m.group().contains("x")) {
                BigInteger ratio = ratioProcess(m.group(6), m.group(7));
                BigInteger exp = expProcess(m.group(11));
                String termSign = m.group(1);
                if (termSign != null && termSign.length() >= 1 && termSign.charAt(0) == '-') {
                    ratio = ratio.negate();
                }
                poly.addNewTerm(exp, ratio);
            }
        }
    }

    public void printDerivative() {
        StringBuilder der = poly.calcDerivative();
        if (der.length() == 0) {
            der.append("0");
        }
        if (der.charAt(0) == '+') {
            der.deleteCharAt(0);
        }
        System.out.println(der.toString());
    }

    private BigInteger ratioProcess(String data1, String data2) {
        BigInteger ratio;
        if (data1 != null) {
            if (data1.length() == 0) {
                ratio = new BigInteger("1");
            } else {
                ratio = new BigInteger(data1);
            }
        } else if (data2 != null) {
            if (data2.contains("-")) {
                ratio = new BigInteger("-1");
            } else {
                ratio = new BigInteger("1");
            }
        } else {
            ratio = new BigInteger("1");
        }

        return ratio;
    }

    private BigInteger expProcess(String data) {
        BigInteger exp;
        if (data == null || data.length() == 0) {
            exp = new BigInteger("1");
        } else {
            exp = new BigInteger(data);
        }
        return exp;
    }
}
