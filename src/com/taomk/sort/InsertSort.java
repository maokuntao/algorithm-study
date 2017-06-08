package com.taomk.sort;

import java.util.concurrent.atomic.AtomicInteger;

import com.taomk.sort.utils.ArrayUtils;

/**
 * 每轮循环完毕，保证前N个元素是有序的
 * @author taomk 2017年4月24日 下午4:32:31
 *
 */
public class InsertSort {

	// 循环次数
	static AtomicInteger loopCount = new AtomicInteger(0);

	public static void sort(int[] unsortedArrays) {

		int size = unsortedArrays.length;
		int temp = 0;
		int j = 0;

		for (int i = 0; i < size; i++) {
			temp = unsortedArrays[i];
			// 假如temp比前面的值小，则将前面的值后移
			for (j = i; j > 0 && temp < unsortedArrays[j - 1]; j--) {
				unsortedArrays[j] = unsortedArrays[j - 1];
				loopCount.getAndIncrement();
			}
			unsortedArrays[j] = temp;
			ArrayUtils.displayDetails(unsortedArrays);
		}

	}

	public static void main(String[] args) {

		// 时间间隔
		long interval = 0L;

		int[] unsortedArrays = ArrayUtils.generateInteger(10, 100);
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
