package stack;

public class ArrayStack {
    private int maxSize = 0;
    private int top = -1;
    private int[] stack;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public void push(int val){
        if(isFull()){
            throw new RuntimeException("栈已满");
        }
        top++;
        stack[top] = val;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int res = stack[top];
        top--;
        return res;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == (maxSize - 1);
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        int index = top;
        for(int i = index;i>= 0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    public int peek(){
        int index = top;
        return stack[index];
    }
}
