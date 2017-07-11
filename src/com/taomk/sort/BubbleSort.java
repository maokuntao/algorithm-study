package com.taomk.sort;

import java.util.concurrent.atomic.AtomicInteger;

import com.taomk.sort.utils.ArrayUtils;

/**
 * <pre>
 * 冒泡排序：
 * 基本思想：针对数列中每一个元素，依次比较大小，如果顺序错误的话，就将这两个元素的值交换位置。
 * 复杂度：O(N^2)
 * </pre>
 * 
 * @author taomk 2017年4月20日 下午1:40:21
 *
 */
public class BubbleSort {

	private static AtomicInteger loopCount = new AtomicInteger(0);
	private static long interval = 0L;

	/**
	 * <pre>
	 * 两层循环，每层循环N次。
	 * 每N次外层循环结束，确保前N个元素是有序的。
	 * 两层循环全部结束，则全部有序。
	 * </pre>
	 * 
	 * @param unsortedArrays
	 */
	public static void sort1(int[] unsortedArrays) {

		int length = unsortedArrays.length;

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				loopCount.getAndIncrement();
				if (unsortedArrays[i] < unsortedArrays[j]) {
					ArrayUtils.swap(unsortedArrays, i, j);
				}
			}
			System.out.print("Loop" + loopCount.get() + " : ");
			ArrayUtils.displayDetails(unsortedArrays);
		}

	}

	/**
	 * <pre>
	 * sort1 的改进型：
	 * 比较的是相邻两个元素，如果顺序错误，则交换位置。
	 * 外层一次循环之后，最大的那个元素就在最后（如果按照从小到大排列的话），
	 * 下次循环时，就只需要在在剩下的那些元素中按照这个规则继续进行即可。
	 * 
	 * </pre>
	 * @param unsortedArrays
	 */
	public static void sort2(int[] unsortedArrays) {
		int size = unsortedArrays.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (unsortedArrays[j] > unsortedArrays[j + 1]) {
					loopCount.getAndIncrement();
					ArrayUtils.swap(unsortedArrays, j, j+1);
				}
			}
			System.out.print("Loop" + loopCount.get() + " : ");
			ArrayUtils.displayDetails(unsortedArrays);
		}
		Long startTime = System.nanoTime();
		interval = System.nanoTime() - startTime;
	}

	public static void main(String[] args) {
		int[] unsortedArrays = ArrayUtils.generateInteger(10, 100);
		System.out.print("未排序之前：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("排序过程：");
		Long startTime = System.nanoTime();
		sort1(unsortedArrays);
//		sort2(unsortedArrays);
		interval = System.nanoTime() - startTime;
		
		System.out.print("排序之后：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("循环次数：" + loopCount.get());
		System.out.println("排序耗时：" + interval + "ns");
	}

}
