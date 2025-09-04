package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque{

    private Comparator<T> DefaultComparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        DefaultComparator = c;
    }
    public T max(){
        return max(DefaultComparator);
    }
    public T max(Comparator<T> c){
        if (this.isEmpty()){
            return null;
        }
        T MaxItem = null;
        for (int i = 0; i < size(); i++){
            T CurrentItem = (T) get(i);
            if (MaxItem == null){
                MaxItem = CurrentItem;
            }else if (c.compare(CurrentItem, MaxItem) > 0){
                MaxItem = CurrentItem;
            }
        }
        return MaxItem;
    }

    public static void main(String[] args){
        Comparator<Integer> DefaultComparator = (s1, s2) -> s1 - s2;
        Comparator<String> AnotherComparator = (a1 , a2) -> a1.length() - a2.length();
        MaxArrayDeque<Integer> array1 = new MaxArrayDeque<>(DefaultComparator);
        MaxArrayDeque<String> array2 = new MaxArrayDeque<>(AnotherComparator);

        array2.addLast("aa");
        array2.addLast("aaaa");
        array2.addLast("addlast");
        array2.addLast("comparator");

        array1.addLast(1);
        array1.addLast(24);
        array1.addLast(15);
        array1.addLast(10);

        int MaxArray1 = array1.max();
        String MaxArray2 = array2.max();
        System.out.println(MaxArray1);
        System.out.println(MaxArray2);
    }

}
