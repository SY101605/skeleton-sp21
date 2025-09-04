package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {
    private Node sentinel;      //循环指针
    private int size;          //队列中的元素数量

    private class Node{
        Item item;
        Node prev;
        Node next;

        Node(Item item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addLast(Item item){
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public void addFirst(Item item){
      Node newNode = new Node(item, sentinel, sentinel.next);
      sentinel.next.prev = newNode;
      sentinel.next = newNode;
      size++;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for(Node pointer = sentinel; pointer.next != sentinel; pointer = pointer.next){
            System.out.print(pointer.next.item + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst(){
        if (isEmpty()){
            return null;
        }
        Node oldNode = sentinel.next;
        sentinel.next = oldNode.next;
        oldNode.next.prev = sentinel;
        size--;
        return oldNode.item;
    }

    @Override
    public Item removeLast(){
        if (isEmpty()){
            return null;
        }
        Node oldNode = sentinel.prev;
        oldNode.prev.next = sentinel;
        sentinel.prev = oldNode.prev;
        size--;
        return oldNode.item;
    }

    @Override
    public Item get(int index){
        Node pointer = sentinel.next;
        if (index > size - 1){
            return null;
        }
        for (int i = 0; i <= index; i++){
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public Iterator<Item> iterator(){
        return new LinkingDequeIterator();
    }

    private class LinkingDequeIterator implements Iterator<Item>
    {
        private Node current = sentinel.next;
        private int i = size;
        public boolean hasNext() { return i > 0; }
        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public Item getRecurisive(int index) {
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }
        return getRecurisionHelper(sentinel.next, index);
    }
    private  Item getRecurisionHelper(Node node, int remanining){
        if (remanining == 0){
            return node.item;
        }
        return getRecurisionHelper(node.next, remanining - 1);
    }
}
