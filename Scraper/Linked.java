package Scraper;

public class Linked<Item> {
	private Node head;
	private int size;

	public Linked(){
		head = null;
		size = 0;
	}
	
private class Node{
	private Item item;
	private Node next;
}

public void push(Item item){

	Node old = head;
	head = new Node();
	head.item = item;
	head.next = old;
	size++;
}
public void print(){
	Node pointer = head;
	while(pointer!=null){
		System.out.println(pointer.item);
		pointer = pointer.next;
	}
}
public Item pop(){
	if(head==null){
		return null;
	}
	Item item = head.item;
	head = head.next;
	
	size--;
	return item;
}

public int getSize(){
	return size;
}




public static void main(String[] args) {
	Linked<String> str = new Linked<String>();
	str.push("Definition");
	str.push("word");
	str.push("Def");
	str.push("word");
	str.print();
	System.out.println(str.getSize());
	System.out.println(str.pop());
	System.out.println(str.pop());
	System.out.println(str.pop());

	System.out.println(str.pop());
	System.out.println(str.pop());
	System.out.println(str.getSize());


}

}
