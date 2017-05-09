package com.taomk.link;

/**
 * 链表节点
 * 
 * @author taomk 2017年5月8日 上午10:54:51
 *
 */
public class Node {
	
	private String data;// 数据域
	private Node next;// 指针域，指向下一节点

	public Node(String Data) {
		// super();
		this.data = Data;
	}

	public static void show(Node head){
		Node next = head;
		while(next!=null){
			System.out.print(next.getData() + " -> ");
			next = next.getNext();
		}
		System.out.println();
	}
	
	public String getData() {
		return data;
	}

	public void setData(String Data) {
		this.data = Data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node Next) {
		this.next = Next;
	}
}
