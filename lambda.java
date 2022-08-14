package edu;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.Person.Gender;
/* LAMBDA AND STREAMS */
public class lambda {
    public static void main(String[] args) throws IOException {


        List<Person> people=List.of(
            new Person("John Smith",23,Gender.MALE),
            new Person("Angela Mike", 18,Gender.FEMALE),
            new Person("Goarge Lee",44,Gender.MALE),
            new Person("Jessica Brian", 25, Gender.FEMALE));

        //people.forEach(System.out::println);

        List<Person>femeles= people.stream()
            .filter(Person -> Person.getGender().equals(Gender.FEMALE)) //if the gender is famale 
            .collect(Collectors.toList());//collect famales in a list 

        System.out.println("Filtered List of females :");
        femeles.forEach(System.out::println);
        
        List<Person> sortedAge= people.stream()
            .sorted(Comparator.comparing(Person::getAge))
            .collect(Collectors.toList());

 
        System.out.println("\nFiltered List based on age :");
        sortedAge.forEach(System.out::println);


        System.out.println("\nPerson who has the max age :");
        people.stream()
            .max(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);


        System.out.println("\nPerson who has the min age :");
        people.stream()
            .min(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);


        System.out.println("\nList of females and males separately : ");
        Map<Gender, List<Person>> groupingByGender=people.stream()
            .collect(Collectors.groupingBy(Person::getGender));
        //printing map   
        groupingByGender.forEach((key,ListOfvalues) -> {
            System.out.println(key+"\n---------");
            ListOfvalues.forEach(System.out::println);
        });

        System.out.println("\nOldest Female is : ");
        Optional<String> oldestFemale=people.stream()
            .filter(Person ->Person.getGender().equals(Gender.FEMALE))
            .max(Comparator.comparing(Person::getAge))
            .map(Person::getName);
        oldestFemale.ifPresent(System.out::println);

        System.out.println("\nOldest Male is : ");
        Optional<String> oldestMale=people.stream()
            .filter(Person ->Person.getGender().equals(Gender.MALE))
            .max(Comparator.comparing(Person::getAge))
            .map(Person::getName);
        oldestFemale.ifPresent(System.out::println);



        Comparator<String> comparable = new Comparator<String>() {
            @Override
            // override a method from Comparable class
            public int compare(String s1, String s2) {
                // anonymous inner class (one object that overrides a method)
                return s1.compareTo(s2);
            }
        };
        // System.out.println(comparable.compare("hello", "world"));

        // a shortcut to the same previous code using lambda expression with the same
        // result
        Comparator<String> comparable2 = (s1, s2) -> { // s1,s2 is the argument to the compareTo method that is one abstract method(FunctionalInterface)
            return s1.compareTo(s2);
        };
        // System.out.println(comparable2.compare("hello", "world"));



        double tot=Stream.of(1.0,2.0,3.0,4.0)
            .reduce(0.0, (Double a,Double b) -> a+b); //a is the accumulator 
        /* (0+1,2)
           (1+2,3)
           (3+3,4)
           (6+4) */
        System.out.println("Total "+tot);

        List<Integer> number = Arrays.asList(2,3,4,5); 
        int evenSum = number.stream()
            .filter(x -> x % 2 == 0)
            .reduce(0,(ans,i) -> ans+i);
        System.out.println(evenSum); 

        
        System.out.println( reverseStr("lambda"));


        // reading file and manupulate it using lambda and streams
        // Stream<String> test = Files.lines(Path.of("\\Users\\SMART WAY\\Desktop\\smallHamlet2.txt"));
        // test
        //     .sorted()
        //     .filter( word -> word.length() == 2 ) //returns boolean
        //     .forEach(System.out::println); //prints words in file with length 2 
        // test.close();

        // IntStream
		// 	.range(1, 10)
		// 	.skip(2) //starts from 3
		// 	.forEach(x -> System.out.println(x));
        

    }

    private static String reverseStr(String toReverse) {
        String rev=Arrays.stream(toReverse.split(""))
            .reduce("", (char1,char2) -> char2 + char1 );//concatination
        return rev;
    }
}
