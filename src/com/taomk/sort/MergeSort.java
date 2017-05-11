package com.taomk.sort;

import com.taomk.sort.utils.ArrayUtils;

/**
 * 归并排序
 * 
 * {@link http://www.cnblogs.com/jianboqi/archive/2013/01/15/2860500.html}
 * 
 * @author taomk 2017年5月11日 下午1:56:28
 *
 */
public class MergeSort {

	public static void sort(int[] unsortArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			sort(unsortArray, left, center);
			sort(unsortArray, center + 1, right);
			merge(unsortArray, left, center, right);
		}
	}

	private static void merge(int[] unsortArray, int left, int center, int right) {

		// 临时数组
		int[] tmpArr = new int[unsortArray.length];

		// 右边数组的第一个元素索引
		int mid = center + 1;

		// 临时数组的索引
		int tempArrayIndex = left;

		// 缓存左数组第一个元素的索引
		int tmp = left;

		// 每次从两个数组中取出最小的放入临时数组
		while (left <= center && mid <= right) {
			if (unsortArray[left] <= unsortArray[mid]) {
				tmpArr[tempArrayIndex++] = unsortArray[left++];
			} else {
				tmpArr[tempArrayIndex++] = unsortArray[mid++];
			}
		}
		
		// 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		while (mid <= right) {
			tmpArr[tempArrayIndex++] = unsortArray[mid++];
		}
		while (left <= center) {
			tmpArr[tempArrayIndex++] = unsortArray[left++];
		}
		
		ArrayUtils.displayDetails(tmpArr);
		
		// 将临时数组中的内容拷贝回原数组中
		// （原left-right范围的内容被复制回原数组）
		while (tmp <= right) {
			unsortArray[tmp] = tmpArr[tmp++];
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
		sort(unsortedArrays, 0, unsortedArrays.length-1);
		interval = System.nanoTime() - startTime;

		System.out.print("排序之后：");
		ArrayUtils.displayDetails(unsortedArrays);

		System.out.println("排序耗时：" + interval + "ns");
	}

}
