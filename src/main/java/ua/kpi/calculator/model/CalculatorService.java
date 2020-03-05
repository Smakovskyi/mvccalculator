package ua.kpi.calculator.model;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    //private static CalculatorService instance = new CalculatorService();
    //public static CalculatorService getInstance(){
    //    return instance;
    //}

    public long add(int first, int second){
        return first + second;
    }

    public long sub(int first, int second) {
        return first - second;
    }

    public Optional<Integer> div(int first, int second){
        Optional<Integer> result = Optional.empty();
        if( second != 0){
            result = Optional.of(first / second);
        }
        return result;
    }

    public int divUsual(int first, int second){
        if( second == 0 ){
            throw new IllegalArgumentException("Can't divide by zero");
        }
        return first/second;
    }
}
