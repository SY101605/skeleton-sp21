package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<Item> implements Deque<Item>, Iterable<Item>  {
    private Item[] list;
    private int size;
    private int first;
    //private int last;

    public ArrayDeque(){
        list = (Item []) new Object[8];
        size = 0;
        first = (list.length - size) / 2;  //the index of first element
    }


    @Override
    public void addFirst(Item item){
        if (first == 0){
            resize(size * 2);
        }
        list[--first] = item;
        size++;
    }

    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];
        int newFirst = (newCapacity - size) / 2; // 重新计算 first 的位置
        System.arraycopy(list, first, newArray, newFirst, size);
        list = newArray;
        first = newFirst;
    }
    @Override
    public void addLast(Item item){
        if (size + first >= list.length){
            resize(size * 2);
        }
        list[size + first] = item;
        size++;
    }
    

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for(int i = first; i <= size + first; i++){
            System.out.print(list[i]);
        }
        System.out.println();
    }

    private void checkShrink() {
        if (size > 0 && size * 4 < list.length && list.length > 8) {
            resize(list.length / 2);
        }
    }
    @Override
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item temp = list[first];
        list[first] = null;
        first++;
        size--;
        checkShrink();
        return temp;
    }

    @Override
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item temp = list[first + size - 1];
        list[first + size - 1] = null;
        size--;
        checkShrink();
        return temp;
    }

    @Override
    public Item get(int index){
        return list[first + index];
    }

    public Iterator<Item> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<Item>
    {
        private int i = first;
        public boolean hasNext() {return i < first + size; }
        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = list[i];
            i++;
            return item;
        }
    }
}
