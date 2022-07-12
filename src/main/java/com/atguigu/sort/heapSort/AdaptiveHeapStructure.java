package com.atguigu.sort.heapSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public class AdaptiveHeapStructure<T> {
    private ArrayList<T> heap ;
    private HashMap<T , Integer> indexMap;
    private int heapSize ;
    private Comparator<? super T> comparator ;

    public AdaptiveHeapStructure(Comparator<T> comparator){
        heap = new ArrayList<>();
        indexMap = new HashMap<>() ;
        heapSize = 0 ;
        this.comparator = comparator ;
    }

    public boolean isEmpty(){
        return heap.isEmpty() ;
    }
    public int getHeapSize(){
        return heapSize ;
    }
    public void push(T t){
        heap.add(t);
        indexMap.put(t, heapSize);
        heapInsert(heapSize++);
    }
    public T pop(){
        T ans = heap.get(0);
        heapSize = heapSize -1 ;
        swap(0 , heapSize ); //heapSize 是下标，此时正是指向最后一个
        indexMap.remove(ans);
        heap.remove(heapSize);
        heapIfy(0);
        return ans ;
    }

    public void resign(T t){
        heapInsert(indexMap.get(t));
        heapIfy(indexMap.get(t));
    }
    private void heapInsert(int index){
        while (comparator.compare( heap.get(index) , heap.get((index-1)/2)) < 0 ){
            swap(index , (index-1)/2 );
            index = (index -1) / 2  ;
        }
    }

    private void heapIfy(int index){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && comparator.compare(heap.get(left+1), heap.get(left) ) < 0 ? left+1 : left ;
            largest = comparator.compare(heap.get(largest) , heap.get(index)) < 0 ? largest : index;
            if (largest == index){
                break;
            }
            swap(index , largest);
            index = largest ;
            left = index * 2 + 1;
        }
    }

    private  void swap(int i , int j){
        T a = heap.get(i);
        T b=  heap.get(j);
        //千万不能用add
        // set 为替换指定索引位置的值， 而 .add 为在指定索引位置插入值
        heap.set(i,b);
        heap.set(j,a);
        indexMap.put(a,j);
        indexMap.put(b,i);
    }

    public static void main(String[] args) {
        AdaptiveHeapStructure<Student> heap = new AdaptiveHeapStructure<>((o1,o2)-> -(o1.age-o2.age));
        Student student1 = new Student(1, 5);
        heap.push(student1);
        heap.push(new Student(3,6));
        heap.push(new Student(2,8));
        heap.push(new Student(7,7));
        heap.push(new Student(4,1));
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println("==================");
        heap.push(student1);
        heap.push(new Student(3,6));
        heap.push(new Student(2,8));
        heap.push(new Student(7,7));
        heap.push(new Student(4,1));
        student1.age = 10 ;
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println("==============");
        heap.push(student1);
        heap.push(new Student(3,6));
        heap.push(new Student(2,8));
        heap.push(new Student(7,7));
        heap.push(new Student(4,1));
        student1.age = 10 ;
        heap.resign(student1);
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
    }
}
class Student{
    int age ;
    int id ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age && id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id);
    }

    @Override
    public String toString() {
        return "student{" +
                "age=" + age +
                ", id=" + id +
                '}';
    }

    public Student(int age, int id) {
        this.age = age;
        this.id = id;
    }
}