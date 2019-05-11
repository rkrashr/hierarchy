package rk.tech;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SortedList<T> implements List<T> {

	List<T> collection;
	Comparator<T> comparator;
	
	public SortedList(List<T> collection, Comparator<T> comparator) {
		this.collection = collection;
	}

	@Override
	public boolean add(T e) {
		int j = Collections.binarySearch(collection, e, comparator);
		boolean duplicate = false;
		if (j<0)
			j = -(j + 1);
		else
			duplicate = true;
		collection.add(j, e);
		return duplicate;
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return collection.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		collection.clear();
	}

	@Override
	public boolean contains(Object o) {
		return collection.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return collection.containsAll(c);
	}

	@Override
	public T get(int index) {
		return collection.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return collection.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return collection.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return collection.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return collection.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return collection.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return collection.remove(o);
	}

	@Override
	public T remove(int index) {
		return collection.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return collection.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return collection.retainAll(c);
	}

	@Override
	public T set(int index, T element) {
		return collection.set(index, element);
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return collection.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return collection.toArray();
	}

	@Override
	public <V> V[] toArray(V[] a) {
		return collection.toArray(a);
	}
	
}
