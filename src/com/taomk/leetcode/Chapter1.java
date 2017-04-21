package com.taomk.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <pre>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
	Given nums = [2, 7, 11, 15], target = 9,

	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
 *
 * <a>https://leetcode.com/problems/two-sum/</a>
 * </pre>
 * 
 * @author taomk 2017年3月10日 上午9:11:59
 *
 */
public class Chapter1 {

	public static void main(String[] args) {

		int[] nums = { 2, 7, 11, 15, 8, 3, 9, 4 };
		int target = 10;

		int[] result = twoSum(nums, target);
		
		System.out.println(result[0] + " , " + result[1]);
	}

	public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
		Map<Integer, Integer> cacheMap = new HashMap<Integer, Integer>();
		for(int i = 0 ; i < nums.length ; i++){
		    if(cacheMap.containsKey(target-nums[i])){
		        result[0] = cacheMap.get(target-nums[i]);
		        result[1] = i;
		        return result;
		    }
		    cacheMap.put(nums[i] , i);
		    showContent(cacheMap);
		}
		
		return result;}
	
	static void showContent(Map<Integer, Integer> map){
		Set<Integer> keySet =  map.keySet();
		StringBuffer sb = new StringBuffer();
		for (Integer key : keySet) {
			sb.append(key + "-" + map.get(key) + " ");
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}
}
