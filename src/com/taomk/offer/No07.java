package com.taomk.offer;

import java.util.Stack;

/**
 * 
 * http://codercareer.blogspot.sg/2011/09/no-07-reverse-words-in-sentence.html
 * 
 * @author taomk 2017年9月7日 上午10:18:41
 *
 */
public class No07 {

	public static void main(String[] args) {
		
		String str = "I am a programmer   .";

//		System.out.println(test1(str));
		
		System.out.println(test2(str));
		
	}

	private static String test2(String originalStr) {
		
		if (originalStr == null || originalStr.length() <= 1 || !originalStr.contains(" ")) {
			return originalStr;
		}
		
		String[] splitWords = originalStr.split("\\s+");
		Stack<String> words = new Stack<String>();
		
		for (String word : splitWords) {
			words.push(word);
		}
		
		String result = "";
		for (String word : words) {
			result += word + " ";
		}
		
		return result;
	}
	
	private static String test1(String str) {
		// 第一步，将整个字符串全部翻转
		String reversedStr = reverseStr(str, 0, str.length() - 1);

		// 再将每个单词翻转
		String[] reversedWords = reversedStr.split("\\s+");

		String result = "";

		for (String reversedWord : reversedWords) {
			result += reverseStr(reversedWord, 0, reversedWord.length() - 1) + " ";
		}

		return result;
	}

	/**
	 * 将原始字符串，从开始位置到结束为止的字符翻转
	 * 
	 * @param originalStr
	 *            原始字符串
	 * @param pBegin
	 *            开始位置
	 * @param pEnd
	 *            结束位置
	 */
	private static String reverseStr(String originalStr, int pBegin, int pEnd) {
		if (originalStr == null || originalStr.length() <= 1) {
			return originalStr;
		}

		if (pBegin > pEnd || pBegin > originalStr.length()) {
			throw new IllegalArgumentException();
		}

		if (pBegin < 0) {
			pBegin = 0;
		}

		if (pEnd >= originalStr.length()) {
			pEnd = originalStr.length() - 1;
		}

		char[] originalChars = originalStr.toCharArray();
		while (pBegin < pEnd) {
			char temp = originalChars[pBegin];
			originalChars[pBegin] = originalChars[pEnd];
			originalChars[pEnd] = temp;
			pBegin++;
			pEnd--;
		}

		return new String(originalChars);
	}

}
