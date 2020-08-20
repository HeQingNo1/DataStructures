package stack;

public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "300+2*6-20";
        //创建两个栈 数栈 符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数

        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,(char)oper);
                        //把结果入数栈
                        numStack.push(res);
                        //将当前符号入栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //为空直接入栈
                    operStack.push(ch);
                }
            }else{
                //如果是数 则入数栈
                /**
                 * numStack.push(ch - 48); //字符’1‘ 不是1  (个位数 计算）
                 */

                //1.在处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数。
                //2.在处理数时，需要expression表达式index向后再看一位，如果是数就扫描，是符号才入栈
                //因此需要定义一个字符串变量，用于拼接
                keepNum +=ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个是不是数字(注意是看后一位，不是index++)
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop
        while(true){
            //如果符号栈为空，则计算到最后结果，数栈中只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,(char)oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("%s=%d",expression,res2);
    }
}
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，存放数据
    private int top=-1;//top表示栈顶，初始化为-1
    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大则优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1; //假定目前的表达式只有+,-,*,/
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1,int num2,char oper){
        int res = 0;//用于存放计算结果
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
    //返回当前栈顶的值，但不是真正的pop
    public int peek(){
        return stack[top];
    }
}