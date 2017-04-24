package com.taomk.sort;

import java.util.concurrent.atomic.AtomicInteger;

import com.taomk.sort.utils.ArrayUtils;

/**
 * <pre>
 * 选择排序：
 *  在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 *  然后在剩下的数当中再找最小的与第二个位置的数交换，
 *  如此循环到倒数第二个数和最后一个数比较为止。
 * 复杂度：
 *  O(N(N-1)/2)
 * </pre>
 * 
 * @author taomk 2017年4月24日 下午3:06:29
 *
 */
public class SelectSort {

	// 循环次数
	static AtomicInteger loopCount = new AtomicInteger(0);

	/**
	 * 遍历n次，确定index为n-1这个元素的值
	 * 
	 * @param unsortedArrays
	 */
	public static void sort(int[] unsortedArrays) {

		int size = unsortedArrays.length; // 数组长度

		for (int i = 0; i < size; i++) {
			// 待确定的位置
			int k = i;
			// 选择出应该在第i个位置的数
			for (int j = size - 1; j > i; j--) {
				// 从后往前遍历，找到比基准值小的元素，确定为新的基准值
				if (unsortedArrays[j] < unsortedArrays[k]) {
					k = j;
				}
				loopCount.getAndIncrement();
			}
			ArrayUtils.displayDetails(unsortedArrays);
			// 交换两个数
			ArrayUtils.swap(unsortedArrays, i, k);
		}
	}

	public static void main(String[] args) {

		// 时间间隔
		long interval = 0L;

		int[] unsortedArrays = ArrayUtils.genaralInteger(10, 100);
		System.out.print("未排序之前：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("排序过程：");
		Long startTime = System.nanoTime();
		sort(unsortedArrays);
		interval = System.nanoTime() - startTime;

		System.out.print("排序之后：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("循环次数：" + loopCount.get());
		System.out.println("排序耗时：" + interval + "ns");

	}

}
