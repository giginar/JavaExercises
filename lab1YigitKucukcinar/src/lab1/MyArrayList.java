package lab1;

import java.util.Arrays;

public class MyArrayList<T> {
	
	T[] items = (T[])new Object[10];

	private int sizeAtTheMoment = 0;

	public int getSize() {
		return sizeAtTheMoment;
	}

	public Object get(int itemIndexNo) {
		if (sizeAtTheMoment > itemIndexNo) {
			return items[itemIndexNo];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void add(Object obj) {
		if (items.length > sizeAtTheMoment) {
			items[sizeAtTheMoment++] = (T)obj;
		}else if(items.length - sizeAtTheMoment <= 10) {
			items = Arrays.copyOf(items, items.length + 10);
			System.out.println("Size is increased to " + items.length);
			items[sizeAtTheMoment++] = (T)obj;			
		}else {
			items[sizeAtTheMoment++] = (T)obj;
		}
		
	}
	
	

	public Object remove(int itemIndexNo) {
		if (sizeAtTheMoment > itemIndexNo) {
			Object object = items[itemIndexNo];
			items[itemIndexNo] = null;
			int x = itemIndexNo;
			while (x < sizeAtTheMoment) {
				items[x] = items[x + 1];
				items[x + 1] = null;
				x++;
			}
			sizeAtTheMoment--;
			return object;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	@Override
	public String toString() {
		return Arrays.toString(items);
	}
	
}
