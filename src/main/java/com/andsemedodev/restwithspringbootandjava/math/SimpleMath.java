package com.andsemedodev.restwithspringbootandjava.math;

public class SimpleMath {
    public Double sum(Double numberOne, Double numberTwo
    ) {
        return numberOne + numberTwo;
    }

    public Double subtration(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {

        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {

        return numberOne / numberTwo;
    }

    public Double average(Double numberOne,Double numberTwo) {

        return ( numberOne + numberTwo ) / 2;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}