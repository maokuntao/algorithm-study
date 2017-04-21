package com.taomk.leetcode;

/**
 * 
 * <pre>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8


 *
 * <a>https://leetcode.com/problems/add-two-numbers/?tab=Description</a>
 * </pre>
 * 
 * @author taomk 2017年3月10日 上午9:11:59
 *
 */
public class Chapter2 {

	public static void main(String[] args) {
		SingleListNode sln = new SingleListNode();
//		for (int i = 0; i < 10; i++) {
//			sln.add(i);
//		}
//		sln.print(sln.head);
		
		SingleListNode sln1 = new SingleListNode();
		sln1.add(2).add(4).add(3);
		sln.print(sln1.head);
		
		
		SingleListNode sln2 = new SingleListNode();
		sln2.add(5).add(6).add(4);
		sln.print(sln2.head);
		
		ListNode result = addTwoNumbers(sln1.head , sln2.head);
		sln.print(result);
	}
	

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
		ListNode previousNode = new ListNode(0);
		ListNode head = previousNode;
		int carry = 0;
		while(l1!=null || l2!=null || carry!=0){
			ListNode currentNode = new ListNode(0);

			int sum = ((l1==null)?0:l1.value) + ((l2==null)?0:l2.value) +carry;
			currentNode.value = sum % 10;
			carry = sum / 10;
			
			previousNode = previousNode.next = currentNode;
			
			l1 = (l1==null)?null:l1.next;
			l2 = (l2==null)?null:l2.next;
		}
		return head.next;
	}
}

class SingleListNode{
	
	ListNode head;
	private ListNode current;
	
	SingleListNode add(int value){
		if(head==null){
			current = head = new ListNode(value);
		}else{
			current = current.next = new ListNode(value);
		}
		return this;
	}
	
	void print(ListNode target){
		if(target!=null){
			current = target;
			while (current!=null) {
				System.out.print(current.value + "-->");
				current = current.next;
			}
		}else{
			System.out.println("Empty ListNode.");
		}
		System.out.println();
	}
	
}

/**
 * 链表定义
 * 
 * @author taomk 2017年3月10日 上午11:16:37
 *
 */
class ListNode {

	int value;
	ListNode next;

	ListNode(int val) {
		this.value = val;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
	
}
