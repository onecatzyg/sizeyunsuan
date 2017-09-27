package test;
import java.util.Stack;
public class houzhuiyunsuan {
	 private String post;   //中缀表达式转换得到的后缀表达式
	    private Stack<Integer> stack;   //用于得到计算结果的栈

	    public houzhuiyunsuan(String post, Stack<Integer> stack) {
	        this.post = post;
	        this.stack = stack;
	    }

	    //由后缀表达式得到四则运算结果的实现过程
	    public void operate(){
	        String[] strArr = post.split(" ");
	        for(int i = 0; i < strArr.length; i++){
	            String temp = strArr[i];
	            if(isDigital(temp)){
	                stack.push(Integer.valueOf(temp));
	            }else{
	                int result = compute(temp);
	                stack.push(result);
	            }
	        }
	    }

	    private int compute(String str){//四则运算操作
	        int re = 0;
	        int m = stack.pop();//出栈第一个元素
	        int n = stack.pop();//出栈第二个元素
	        switch(str){
	        case "+" :
	            re = n + m;
	            break;
	        case "-" :
	            re = n - m;
	            break;
	        case "*" :
	            re = n * m;
	            break;
	        case "/" :
	            re = n / m;
	            break;
	        default :
	            break;
	        }
	        return re;
	    }

	    private boolean isDigital(String str){
	        char[] chArr = str.toCharArray();
	        int len = chArr.length;
	        int count = 0;
	        for(int i = 0; i < len; i++){
	            if(chArr[i] >= '0' && chArr[i] <= '9')
	                count++;
	        }
	        return count == len;
	    }

	    public int getResult() {
	        return stack.pop();
	    }

}
