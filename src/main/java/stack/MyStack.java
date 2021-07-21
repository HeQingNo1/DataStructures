package stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<E>{

    private Object[] arr;//存放元素的物理结构

    private int stackLength = 4;//数组的默认长度

    private int size; //记录栈容器的元素个数

    private int index = -1; //操作数组下标位置的索引

    private boolean isFull(){

       return this.size == this.stackLength;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){

        return this.size == 0;
    }

    /**
     * 数组初始化或者以1.5倍容量对数组扩容
     * @return
     */
    private void capacity(){
        if(this.arr == null) {
            this.arr = new Object[this.stackLength];
        }
        if(this.isFull()){
            this.stackLength = this.stackLength + (this.stackLength >> 1);
            this.arr = Arrays.copyOf(this.arr,this.stackLength);
            }

    }

    /**
     *
     * @param item
     * @return
     */
    public E push(E item){
        //初始化
        this.capacity();

        this.arr[++index] = item;

        this.size++;

        return item;

    }

    /**
     *
     * @return
     */
    public E pop(){
        if(this.size == 0){
            throw new EmptyStackException();
        }

        this.size--;

        return (E)this.arr[this.index--];
    }

    /**
     *
     * @return
     */
    public E peek(){
        if(this.size == 0){
            throw new EmptyStackException();
        }
        return (E)this.arr[this.index];
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        System.out.println(stack.isEmpty());
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.size);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
