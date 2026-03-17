package data_structures.demos;

import java.util.ArrayDeque;
import java.util.Deque;

public class Demo3 {
	
	public static boolean verifyParentheses(String ps) {
		int openCount = 0;
		for (int i = 0; i < ps.length(); i++) {
			char c = ps.charAt(i);
			if (c == '(') {
				openCount++;
			} else if (c == ')') {
				openCount--;
			} else {
				return false;
			}
			
			if (openCount < 0) {
				return false;
			}
		}
		return openCount == 0;
	}
	
	public static int evaluatePostfix(String s) throws IllegalArgumentException {
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				continue;
			} else if (Character.isDigit(c)) {
				stack.push(Integer.parseInt(String.valueOf(c)));
			} else if ("+-/*".indexOf(c) != -1) {
				if (stack.size() < 2) {
					throw new IllegalArgumentException("not enough operands");
				}
				Integer op2 = stack.pop();
				Integer op1 = stack.pop();
				Integer res;
				if (c == '+') {
					res = op1 + op2;
				} else if (c == '-') {
					res = op1 - op2;
				} else if (c == '*') {
					res = op1 * op2;
				} else {
					res = op1 / op2;
				}
				stack.push(res);
			} else {
				throw new IllegalArgumentException("invalid character");
			}
		}
		
		if (stack.size() != 1) {
			throw new IllegalArgumentException("something went wrong");
		}
		return stack.pop();
	}
	
	public static void main(String[] args) throws InterruptedException {
//		String[] parensTests = {
//				"",
//				"()",
//				"(())",
//				"()()",
//				"((()()))",
//				"(",
//				")(",
//				"((",
//				"(()"};
//		for (String pt : parensTests) {
//			System.out.println(verifyParentheses(pt));
//		}
		
		String[] prefixTests = {
				"1 1 +",
				"3 4 + 5 *"
		};
		for (String pt : prefixTests) {
			System.out.println(evaluatePostfix(pt));
		}
	}
}
