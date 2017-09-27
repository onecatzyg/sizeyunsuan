package test;

import java.util.Stack;
import java.util.Scanner;
import java.util.Random;

public class transf {
    private Stack<String> stack;//中缀表达式转换为后缀表达式所需要的栈
    private String infix;    //生成的中缀表达式
    private String post = "";     //存储得到的后缀表达式
    //初始化
    public transf(Stack<String> stack, String infix) {//转化
        this.stack = stack;
        this.infix = infix;
    }
    private String processInfix(String infix) {
        String result = "";
        for (int i = 0; i < infix.length() - 1; i++) {
            char temp1 = infix.charAt(i);
            char temp2 = infix.charAt(i + 1);
            if (isDigital(temp1) && isDigital(temp2)) {
                result += temp1;
            } else {
                result += temp1 + " ";
            }
        }
        result += infix.charAt(infix.length() - 1); // 将最后一个元素添加进去
        return result;
    }
    private boolean isDigital(char ch) {
        if (ch >= '0' && ch <= '9')//判断字符是否为数字
            return true;
        else
            return false;
    }
    public void process() {//转化过程
        String[] strArr = processInfix(infix).split(" ");
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            switch (str) {//运算符操作
            case "+":
            case "-":
                getOperation(str, 1);
                break;
            case "*":
            case "/":
                getOperation(str, 2);
                break;
            case "(":
                stack.push(str);
                break;
            case ")":
                getParent();
                break;
            default:
                post += " " + str;
                break;
            }
        }
        while (!stack.isEmpty()) {//当栈非空则在原有字符串后加上出栈元素
            post += " " + stack.pop();
        }
    }
    private void getParent() {
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("(")) {
                break;
            } else {
                post += " " + top;
            }
        }
    }
    private void getOperation(String str, int priority) {
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("(")) {
                stack.push(top);
                break;
            } else {
                int priTop = getPriority(top);
                if (priTop < priority) {
                    stack.push(top);
                    break;
                } else {
                    post += " " + top;
                }
            }
        }
        stack.push(str);
    }
    private int getPriority(String str) {
        int pri = 0;
        if (str.equals("+") || str.equals("-")) {
            pri = 1;
        } else {
            pri = 2;
        }
        return pri;
    }

    public String getPost() {
        return post.trim();//去掉字符串首位空格
    }
    public static void main(String[] args) {
    	int x,y;
    	int a,b,c,d;//操作数
    	int e,f,g;//操作符
    	int h = 0;
    	int score;
    	String[] op ={"+","-","*","/"};
        System.out.println("请输入题目个数");
        Scanner xx = new Scanner(System.in);
        x = xx.nextInt();
        for(int n =0;n < x;n++){
        	Random aa = new Random();
        	Random bb = new Random();
        	Random cc = new Random();
        	Random dd = new Random();
        	Random ee = new Random();
        	Random ff = new Random();
        	Random gg = new Random();
        	a = aa.nextInt(100)+1;
        	b = bb.nextInt(100)+1;
        	c = cc.nextInt(100)+1;
        	d = dd.nextInt(100)+1;
        	e = ee.nextInt(4);
        	f = ff.nextInt(4);
        	g = gg.nextInt(4);
        	String a1 = String.valueOf(a);
        	String b1 = String.valueOf(b);
        	String c1 = String.valueOf(c);
        	String d1 = String.valueOf(d);
            Stack<String> stack = new Stack<>();
            String input =a1+op[e]+b1+op[f]+c1+op[g]+d1;
            System.out.println(input+"=");
            transf infix = new transf(stack, input);
            infix.process();

            String post = infix.getPost();
            Stack<Integer> stack_result = new Stack<>();
            houzhuiyunsuan ptr = new houzhuiyunsuan(post, stack_result);
            ptr.operate();
            Scanner yy = new Scanner(System.in);
            y = yy.nextInt();
            int ffff = ptr.getResult();
            if(y==ffff){
            	System.out.println("回答正确！");
            	h++;
            }
            else
            System.out.println("正确答案是"+ffff);
        }
        score = 100*h/x;
        System.out.println("总共"+x+"题，"+"恭喜答对"+h+"题");
        System.out.println("总分"+score+"分");
    }
}

