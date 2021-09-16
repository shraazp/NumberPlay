package com.linledlist.mavenproject;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NuberPlaylist {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> myNumberList = new ArrayList<>();
		for(int i=0;i<5;i++) 
			myNumberList.add(i);

		//method 1
		Iterator it = myNumberList.iterator();
		while(it.hasNext()) {
			System.out.println("method 1: "+it.next());
		}

		System.out.println("--------------------");

		//method 2
		class MyConsumer implements Consumer<Integer>{
			public void accept(Integer t) {
				System.out.println("mthd 2: "+t);
			}
		}
		MyConsumer action = new MyConsumer();
		myNumberList.forEach(action);

		System.out.println("--------------------");

		//method 3
		myNumberList.forEach(new Consumer() {
			@Override
			public void accept(Object t) {
				System.out.println("mthd 3: "+t);

			}	
		});

		System.out.println("--------------------");

		//method 4
		myNumberList.forEach((ele) -> System.out.println("mthd 4: "+ ele));

		System.out.println("--------------------");

		//method 5
		for(Integer i:myNumberList)
			System.out.println("method 5: "+i);

		System.out.println("--------------------");

		//method 6
		Consumer<Integer> myListAction = ele -> {
			System.out.println("method 6: "+ele);
		};
		myNumberList.forEach(myListAction);

		System.out.println("--------------------");

		//method 7

		List<Double> doubleList = new ArrayList<Double>();
		Function<Integer,Double> toDoubleFunction = Integer::doubleValue;
		Predicate<Integer> isEven = n -> n>0 && n%2==0;
		myNumberList.forEach(n -> {
			doubleList.add(toDoubleFunction.apply(n));
		});
		doubleList.forEach(n -> {
			System.out.println(n+" is even = "+isEven.test(n.intValue()));
		});

		//method 8, processing the streams
		myNumberList.stream().forEach(n ->{
			System.out.println("Stream forEach value: "+n);
		});

		//method 9,apply operation on stream,store the result
		List<Double> streamList = myNumberList.stream()
				.filter(isEven)
				.map(toDoubleFunction)
				.collect(Collectors.toList());
		System.out.println("Printing Even double list: "+streamList);

		//method 10, list the first even number
		Integer first = myNumberList.stream()
				.filter(isEven)
				.peek(n -> System.out.println("peak even number: " +n))
				.findFirst()
				.orElse(null);
		System.out.println("Method 10: First Even: "+first);

		//method 11, Minimum even number
		Integer min = myNumberList.stream()
				.filter(isEven)
				.min((n1,n2) -> n1-n2)
				.orElse(null);
		System.out.println("Method 11: Min Even: "+min);

		//method 12, Maximum even number
		Integer max = myNumberList.stream()
				.filter(isEven)
				.max(Comparator.comparing(Integer::intValue))
				.orElse(null);
		System.out.println("Method 12: Max Even: "+max);

	}
}