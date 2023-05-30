package com.example.customlist;

import java.util.Arrays;

public class CustomListImpl implements CustomList {
    private Integer[] array;

    public CustomListImpl() {
        this.array = new Integer[10];
    }

    private int size = 0;

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5 + 1));
        }
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5 + 1));
        }
        System.arraycopy(array, index, array, index + 1, array.length - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = array[index];

        if (index != size) {
            System.arraycopy(array, index + 1, array, index, array.length - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] copy = toArray();
        insertionSort(copy);
        return binarySearch(copy, item);
    }

    @Override
    public int indexOf(Integer item) {
        return Arrays.binarySearch(array, item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(CustomList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }


    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new CustomIndexException();
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new CustomNullException();
        }
    }

    private void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (arr[mid] == element) {
                return true;
            }
            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
