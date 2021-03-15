package org.example;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Poly {
    private final HashMap<BigInteger, BigInteger> map;

    public Poly() {
        map = new HashMap<>();
    }

    public void addNewTerm(BigInteger exp, BigInteger ratio) {
        if (map.containsKey(exp)) {
            BigInteger oldRatio = map.get(exp);
            map.replace(exp, oldRatio.add(ratio));
        } else {
            map.put(exp, ratio);
        }
    }

    public StringBuilder calcDerivative() {
        if (map.isEmpty()) {
            return new StringBuilder("0");
        }

        StringBuilder der = new StringBuilder();
        for (Map.Entry<BigInteger,BigInteger> entry : map.entrySet()) {
            BigInteger exp = entry.getKey();
            BigInteger ratio = entry.getValue();
            ratio = ratio.multiply(exp);
            exp = exp.add(new BigInteger("-1"));
            if (ratio.equals(BigInteger.ZERO)) {
                continue;
            }
            if (exp.equals(BigInteger.ZERO)) {
                if (ratio.compareTo(BigInteger.ZERO) > 0) {
                    StringBuilder temp = new StringBuilder()
                            .append("+").append(ratio.toString());
                    der.insert(0, temp);
                } else {
                    der.append(ratio.toString());
                }
            } else if (exp.equals(BigInteger.ONE)) {
                if (ratio.compareTo(BigInteger.ZERO) > 0) {
                    StringBuilder temp = new StringBuilder()
                            .append("+").append(ratio.toString())
                            .append("*x");
                    der.insert(0, temp);
                } else {
                    der.append(ratio.toString()).append("*x");
                }
            } else if (ratio.equals(BigInteger.ONE)) {
                der.append("+x**").append(exp.toString());
            } else if (ratio.equals(new BigInteger("-1"))) {
                der.append("-x**").append(exp.toString());
            } else if (ratio.compareTo(BigInteger.ZERO) > 0) {
                StringBuilder temp = new StringBuilder()
                        .append("+").append(ratio.toString())
                        .append("*x**").append(exp.toString());
                der.insert(0, temp);
            } else {
                der.append(ratio.toString()).append("*x**").append(exp.toString());
            }
        }
        return der;
    }
}
