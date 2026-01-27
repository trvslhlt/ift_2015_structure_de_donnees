package data_structure.demos;

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
	
	public static void main(String[] args) throws InterruptedException {
		String[] parensTests = {
				"",
				"()",
				"(())",
				"()()",
				"((()()))",
				"(",
				")(",
				"((",
				"(()"};
		for (String pt : parensTests) {
			System.out.println(verifyParentheses(pt));
		}
	}
}
