package ua.kpi.calculator;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalSample {

    class Student{
        String name;
        int course;

        public Student(String name, int course) {
            this.name = name;
            this.course = course;
        }
    }


    Optional<String> getString(int val){
        if( val > 0){
            return Optional.of(Integer.toString(val));
        }
        return Optional.empty();
    }

    @Test
    public void testOptionalProcessing(){
        Optional<String> result = getString(5);
        String finalString = result.map( str -> "Final result" + str  )
                .map( str -> "[[ " + str + "]]"  )
                .map( String::toUpperCase )
                .orElse(" Value was less or equals zero");
        System.out.println( finalString );
    }
    @Test
    public void testIntegerProcessing(){
        Optional<Integer> result = Optional.of(5);
        String finalString = result.map( value -> "Final result" + value  )
                .map( str -> "[[ " + str + "]]"  )
                .map( String::toUpperCase )
                .orElse(" Value was less or equals zero");
        System.out.println( finalString );
    }


    @Test
    public void testWithOptionalDouble(){
        Optional<Double> val = Optional.of(100500.);
        String res = val
                .map(num -> Math.sin(num))
                .map(num -> Math.cos(num))
                .map(num -> num.toString())
                .map(str -> "Final result = " + str)
                .map(String::toUpperCase)
                .orElse("Incorrect input");
        System.out.println(res);

//        val = Math.sin (val);
//        val = Math.cos( val);
//        String str = val.toString();
//        str = "result = " + str;
//        str = str.toUpperCase();
//        System.out.println(str);
    }

    @Test
    public void sampleOptionalWithObject(){
        Optional<Student> student = Optional.of(new Student( "aaabbb" , 6));
        Map<String,String> map =
        student.map( old -> new Student( old.name.toUpperCase() , old.course +1 ))
               .map( stud -> (Map<String,String>)new HashMap<String,String>() {
                            { put("name" , stud.name);
                              put("course" , "" + stud.course );}})
               .orElse( Collections.EMPTY_MAP );
        System.out.println(map);
    }
}
