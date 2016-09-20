/**
 * 
 */
package com.ydcun.java.baidu;

/**
 * @author ydcun-psjs
 *
 */
public class main {
	public static void main(String[] args) {

		
		
		
	}
}



int longestValidParentheses(string s) {
		if (s.size() <= 0)return 0;
		stack<char> paren;
		stack<int> pos;
		pos.push(-1);
		int max = 0;
		for (size_t i = 0; i < s.size(); i++)
		{
			if (s[i] == '(')
			{
				paren.push(s[i]);
				pos.push(i);
			}
			else if (!paren.empty()&&paren.top()=='(')
			{
				paren.pop();
				pos.pop();
				max = max < i - pos.top() ? i - pos.top() : max;
			}
			else
			{
				paren.push(s[i]);
				pos.push(i);
			}
		}
		if(max==s.length())return max/2;
		return -1;
	}

