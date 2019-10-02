package com.javainuse.service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.javainuse.model.Employee;

@Service
public class EmployeeService {

	public Employee createEmployee(String name, Integer empId) {

		Employee emp = new Employee();
		emp.setName(name);
		emp.setEmpId(empId);
		return emp;
	}

	public String deleteEmployee(String empId)
	{
		List<String> words = Arrays.asList("GFG","Amrit","Saurabh"); 

		Optional<String> longestString =  words.stream() 
        .reduce((word1, word2) 
        		-> word1.length() > word2.length() 
                ? word1 : word2); 

		// Displaying the longest String 
		longestString.ifPresent(System.out::println); 
		Integer startValue=0;
		 List<Integer> numbers = Arrays.asList(2,12,45,11);
		 Integer sum=numbers.stream().reduce(startValue,Integer::sum);
		 
		 Predicate<Integer> lessthanfourty=  s -> s < 40;
		 boolean allLessThanfourty=numbers.stream().allMatch(lessthanfourty);
		 boolean anyLessThanfourty=numbers.stream().anyMatch(lessthanfourty);
		 Optional<Integer> streamfindAny=numbers.stream().findAny();
		 Optional<Integer> streamFindFirst=numbers.stream().findFirst();
		 Integer max=numbers.stream().max(Comparator.comparing(Integer::valueOf)).get();
		 Integer min=numbers.stream().min(Comparator.comparing(Integer::valueOf)).get();
		 
		 run((Stream<Integer>)convertListToStream(numbers.stream().collect(Collectors.toList())));
		 run((Stream<Integer>)convertListToParallelStream(numbers.parallelStream().collect(Collectors.toList()))); 
		 
		 numbers.stream().mapToInt(num -> num.intValue()).limit(3).forEach(System.out::println);
		 
		 numbers.stream().flatMapToInt(num -> IntStream.of(num)).forEach(System.out::println);
		 
		 numbers.stream().sorted(Comparator.reverseOrder()).forEach( n ->System.out.println("reverse order:"+n));
		
		 numbers.stream().sorted(Comparator.naturalOrder()).forEach( n ->System.out.println("Natural Order:"+n));
		 
		 return "Words List::"+words+" Longest word::"+longestString.get()+" Numbers::"+numbers+" Sum of Numbers:"+ sum+" All numebers are Less than Fourty::"+allLessThanfourty +
				" Any Number less than fourty::"+anyLessThanfourty +" find any random:"+streamfindAny+" Find First::"+streamFindFirst+ " Sub List"+words.subList(0, 2) +
				"Max number in stream list: "+max+" Minimum number in stream::"+ min + 
				" ToMap:"+" stream.count "
				 + numbers.stream().count()+ "Filtering list where values greater than 5"+numbers.stream().filter(n ->n>5).collect(Collectors.toList())+
		 " "+ numbers.stream().filter(n ->n>5).collect(Collectors.summarizingInt(n ->n))
		 +" Joining ::"+ words.stream().collect(Collectors.joining())+
		 " partitioningBy where number  > 3 "+numbers.stream().collect(Collectors.partitioningBy(n -> n>3))+
		 " collectingAndThen creating unmodifiable "+words.stream().collect(Collectors.
			collectingAndThen(Collectors.toList(),Collections::<String> unmodifiableList))
		 +" Collectors.mapping doing substring from 0 -3 ::"+ words.stream().collect(Collectors.mapping(s1 -> s1.substring(0, 3), Collectors.toList()));
	}
	public void run (Stream<Integer> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                                " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
	}
	
	private static <T> Stream<T> convertListToStream(List<T> list) 
    { 
        return list.stream(); 
    } 
	private static <T> Stream<T> convertListToParallelStream(List<T> list) 
    { 
        return list.parallelStream(); 
    } 
	

}