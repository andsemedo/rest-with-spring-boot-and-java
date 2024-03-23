package com.andsemedodev.restwithspringbootandjava.controllers;

import com.andsemedodev.restwithspringbootandjava.convertes.NumberConverter;
import com.andsemedodev.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import com.andsemedodev.restwithspringbootandjava.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    AtomicLong counter = new AtomicLong();
    SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return math.sum(num1, num2);
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtration(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return math.subtration(num1, num2);
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return math.multiplication(num1, num2);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return math.division(num1, num2);
    }

    @RequestMapping(value = "/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return math.average(num1, num2);
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable(value = "number") String number) throws Exception {

        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        Double num = NumberConverter.convertToDouble(number);

        return math.squareRoot(num);
    }



}
