package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double SIZE_MULTIPLIER = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        increaseSize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        increaseSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);
        T deletedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equal(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        if (array.length == size) {
            T[] temp = array;
            array = (T[]) new Object[(int)(size * SIZE_MULTIPLIER)];
            System.arraycopy(temp, 0, array, 0, size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " is out of bounds for size " + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index value: "
                + index + ", list size: " + size);
        }
    }

    private boolean equal(T a, T b) {
        return a == b || a != null && a.equals(b);
    }
}
