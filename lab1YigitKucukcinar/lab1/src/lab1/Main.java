package lab1;

public class Main {

	public static void main(String[] args) {

		MyArrayList<String> myArray = new MyArrayList<>();

		myArray.add("y");
		myArray.add("k");
		myArray.add("3");
		myArray.add("4");
		myArray.add("l");
		myArray.add("q");
		System.out.println("Array: " + myArray);
		System.out.println("Size: " + myArray.getSize());

		System.out.println("------------------------");

		myArray.remove(3);
		myArray.remove(0);

		System.out.println("Array: " + myArray);
		System.out.println("Size: " + myArray.getSize());

		System.out.println("------------------------");
		
		myArray.add("a");
		myArray.add("n");
		myArray.add("6");
		myArray.add("8");
		myArray.add("f");
		myArray.add("12");
		myArray.add("o");
		myArray.add("p");
		System.out.println("Array: " + myArray);
		System.out.println("Size: " + myArray.getSize());

		System.out.println("------------------------");

		System.out.println("Second element : " + myArray.get(2));
		System.out.println("Fifth element : " + myArray.get(5));
		System.out.println("Tenth element : " + myArray.get(9));
		
		System.out.println("------------------------");
		
		myArray.add("a");
		myArray.add("n");
		myArray.add("6");
		myArray.add("8");
		myArray.add("f");
		myArray.add("12");
		myArray.add("o");
		myArray.add("p");
		myArray.add("a");
		myArray.add("n");
		myArray.add("6");
		myArray.add("8");
		myArray.add("f");
		myArray.add("12");
		myArray.add("o");
		myArray.add("p");
		System.out.println("Array: " + myArray);
		System.out.println("Size: " + myArray.getSize());

	}

}
