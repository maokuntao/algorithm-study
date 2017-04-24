package com.taomk.sort;

import java.util.concurrent.atomic.AtomicInteger;

import com.taomk.sort.utils.ArrayUtils;

/**
 * <pre>
 * 快速排序：
 * 基本思路：通过一次排序，将待排数组分为两个部分，其中一部分的每个元素都比另一部分的小。
 * 然后再按照上述思路，对这两部分继续排序，知道整个序列有序。
 * 复杂度：O(nlog2n)
 * </pre>
 * 
 * @author taomk 2017年4月20日 下午4:25:47
 *
 */
public class QuickSort {

	private static AtomicInteger loopCount = new AtomicInteger(0);
	private static long interval = 0L;
	
	/**
	 * <pre>
	 * 选取一个基准值，每次循环之后在这个基准值左边的元素都比基准值小，右边的都比基准值大。
	 * 此时，基准值所在的位置，即为它在有序队列中应该所在的位置。
	 * 然后再对左边和右边分别递归，让队列有序。
	 * </pre>
	 * @param unsortedArrays
	 * @param start
	 * @param end
	 */
	public static void sort(int[] unsortedArrays, int start, int end) {

		// 循环结束条件
		if (start >= end) {
			return;
		}
		int i = start;
		int j = end;

		// 比较标识,是刚刚进入方法时index=i的那个元素值，每次递归时保持不变
		int pivot = unsortedArrays[i];

		// 按照从前往后的顺序遍历比对
		boolean loopOrderASC = true;

		while (i != j) {
			if (loopOrderASC) {
				if (pivot > unsortedArrays[j]) {
					ArrayUtils.swap(unsortedArrays, i, j);
					loopOrderASC = false;
				} else {
					j--;
				}
			} else {
				if (pivot < unsortedArrays[i]) {
					ArrayUtils.swap(unsortedArrays, i, j);
					loopOrderASC = true;
				} else {
					i++;
				}
			}
			loopCount.getAndIncrement();
			System.out.println("pivot : " + pivot);
		}
		ArrayUtils.displayDetails(unsortedArrays);

		// 将pivot左边的那部分排序
		sort(unsortedArrays, start, j - 1);
		// 将pivot右边的那部分排序
		sort(unsortedArrays, i + 1, end);

	}

	public static void main(String[] args) {
		int[] unsortedArrays = ArrayUtils.genaralInteger(10, 100);
		System.out.print("未排序之前：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("排序过程：");
		Long startTime = System.nanoTime();
		sort(unsortedArrays, 0, unsortedArrays.length-1);
		interval = System.nanoTime() - startTime;
		
		System.out.print("排序之后：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("循环次数：" + loopCount.get());
		System.out.println("排序耗时：" + interval + "ns");
	}
}
