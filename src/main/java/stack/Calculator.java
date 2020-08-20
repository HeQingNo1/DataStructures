package stack;

public class Calculator {
    private ArrayStack numStack = new ArrayStack(10);
    private ArrayStack operStack = new ArrayStack(10);

    private boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int priority(char ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    private int cal(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
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

    public int calculate(String expression) {
        String keepNum = "";//用于拼接多位数
        int num1 = 0;
        int num2 = 0;
        int index = 0;
        char ch = ' ';
        int oper = 0;
        int res = 0;

        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(isOper(ch)){
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果
                    if(priority(ch) <= priority((char)operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = cal(num1,num2,(char)oper);
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
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个是不是数字(注意是看后一位，不是index++)
                    if (isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop
        while (true) {
            //如果符号栈为空，则计算到最后结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = cal(num1, num2, (char) oper);
            numStack.push(res);
        }
        int result = numStack.pop();
        return result;
    }
}
