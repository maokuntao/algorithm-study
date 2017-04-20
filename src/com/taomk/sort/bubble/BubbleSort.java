package com.taomk.sort.bubble;

import java.util.concurrent.atomic.AtomicInteger;

import com.taomk.sort.common.ArrayUtils;

/**
 * <pre>
 * 冒泡排序：
 * 
 * 	基本思想：针对数列中每一个元素，依次比较大小，如果顺序错误的话，就将这两个元素的值交换位置。
 * 	复杂度：O(N^2)
 * </pre>
 * @author taomk 2017年4月20日 下午1:40:21
 *
 */
public class BubbleSort {

	private static AtomicInteger loopCount = new AtomicInteger(0);
	private static long interval = 0L;
	
	public static void sort(int[] unsortedArrays){
		
		Long startTime = System.nanoTime();
		
		int temp;
		int length = unsortedArrays.length ;
		
		for (int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++){
				loopCount.getAndIncrement();
				if(unsortedArrays[i] < unsortedArrays[j]){
					temp = unsortedArrays[i];
					unsortedArrays[i] = unsortedArrays[j];
					unsortedArrays[j] = temp;
				}
			}
			System.out.print("Loop" + loopCount.get() + " : " );
			ArrayUtils.displayDetails(unsortedArrays);
		}
		
		interval = System.nanoTime() - startTime;
	}
	
	public static void main(String[] args) {
		int[] unsortedArrays = ArrayUtils.genaralInteger(10, 100);
		System.out.println("未排序之前：");
		ArrayUtils.displayDetails(unsortedArrays);
		
		System.out.println("排序之后：");
		sort(unsortedArrays);
		ArrayUtils.displayDetails(unsortedArrays);
		
		System.out.println("循环次数：" + loopCount.get());
		System.out.println("排序耗时：" + interval + "ns");
	}
	
}
