package com.taomk.sort.utils;

import java.util.Random;

/**
 * 
 * 数组工具类
 * 
 * @author taomk 2017年4月20日 下午1:59:40
 *
 */
public class ArrayUtils {

	/**
	 * 生产一个指定大小，限定整数上限数组
	 * @param count 数组的大小
	 * @param bound 数组元素的上限
	 * @return
	 */
	public static int[] genaralInteger(int count , int bound){
		
		int[] result = new int [count];
		int i = 0;
		while(i < count){
			result[i] = new Random().nextInt(bound);
			i++;
		}
		
		return result;
	}
	
	/**
	 * 展示数组内容
	 * @param result
	 */
	public static void displayDetails(int[] result){
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			if(i == result.length -1){
				content.append(result[i] + "\n");
			}else{
				
				content.append(result[i] + " ");
			}
		}
		System.out.println(content.toString());
	}
	
	/**
	 * 交换数组中index为i,j两个位置的值
	 * 
	 * @param arrays
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arrays , int i , int j){
		int temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
}
