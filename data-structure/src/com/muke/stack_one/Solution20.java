package com.muke.stack_one;


/**
 * leetcode中20号问题
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 例如:
 * 输入: "()"       true
 * 输入: "()[]{}"   true
 * 输入: "(]"       false
 * 输入: "([)]"     false
 * 输入: "{[]}"     true
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = new String("()[]{}");
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isValid(s));
    }
}
