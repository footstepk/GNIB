package doubly_linked_lists;

import applicants.Applicant;
import enums.Priority;
import interfaces.DoublyLinkedListInterface;
import interfaces.QueryInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.sql.Date;

/**
 * This class represents the implementation of Doubly LinkedList<E> class
 * whose super interface implementing the Queue's interface.
 * @param <E> is the type of elements help in this collection. 
 * @author Kok Heng | 20-November-2019
 *
 */

public class KWLinkedList<E> implements DoublyLinkedListInterface<E>, QueryInterface<Applicant> {

	/*
	 * A private nested class that represents the link nodes, in the doubly LinkedList implementation
	 * @param <E> is the type of elements help in this node.
	 */
	protected static class Node<E> {

		/** Declare data fields. */
		protected E item; // the item in the node.
		protected Node<E> next = null; // the success node (reference to the next node)
		protected Node<E> pred = null; // predecessor

		public Node(E items) {
			item = items;
		}

		public Node(E items, Node<E> nexts) {
			item = items;
			next = nexts;
		}

		public Node(E items, Node<E> nexts, Node<E> preds) {
			item = items;
			pred = preds;
			next = nexts;
		}

		public Node(E items, Node<E> preds, Node<E> nexts) {
			item = items;
			pred = preds;
			next = nexts;
		}

		public Node(Node<E> preds, E items, Node<E> nexts) {
			pred = preds;
			item = items;
			next = nexts;
		}

		/** The getter methods. */

		public E getItem() {
			return item;
		}

		public Node<E> getNext() {
			return next;
		}

		public Node<E> getPred() {
			return pred;
		}

		/** The setter methods. */

		public void setItem(E items) {
			item  = items;
		}

		public void setNext(Node<E> nexts) {
			next = nexts;
		}

		public void setPred(Node<E> preds) {
			pred = preds;
		}

		public static <E> void swapItem(Node<E> node1, Node<E> node2) {
			E tempItem = node1.getItem();
			node1.setItem(node2.getItem());
			node2.setItem(tempItem);
		}

	} /** End of static inner class Node<E> */

	/** Declare data fields. */ 
	protected Node<E> head = null; // first node (null if list empty)
	protected Node<E> tail = null; // final node (null if list empty)
	protected int numItems = 0;	  // number of items

	/** Constructs an empty of list for this collection. */
	public KWLinkedList() { numItems = 0;}

	/** The getter methods. */

	public Node<E> getHead() {
		return head;
	}

	public Node<E> getTail() {
		return tail;
	}

	public int getSize() {
		return numItems;
	}

	/** The setter methods. */

		public void setHead(Node<E> heads) {
		head = heads;
	}

	public void setTail(Node<E> tails) {
		tail = tails;
	}

	/** Implements the doubly linked list interface. */

	/*
	 *  Get the number of items in this collection.
	 *  @return the size of the number of the items in this collection.
	 */
	@Override
	public int size() {
		return numItems;
	}

	/*
	 * Get the empty list of this collection
	 * @return the head of the collection is empty or null.
	 */
	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	/** 
	 * Inserts the specified element at the beginning of this collection. 
	 * @param e the specified of the element of the collection.
	 */
	@Override
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e, head, null);
		// check if head != null, then create new node.
		if(head != null) {
			head.pred = newNode;
		}
		head = newNode; // newNode point to the head of the list.
		// you may also check the tail point to null
		if(tail == null) {
			tail = newNode; // now head and tail both create a new node to hold data.
		}
		// Increments count of elements by 1
		numItems++;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * @param e the specified of the element of the collection.
	 */
	@Override
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e, null, tail);
		// check if tail != null, then create new node.
		if(tail != null) {
			tail.next = newNode; 		// newNode point to the tail of the list.
		}
		tail = newNode;
		// you may also check the head point to null
		if(head == null) {
			head = newNode; // now head and tail both create a new node to hold data.
		}
		// Increments count of elements by 1
		numItems++;
	} 

	/**
	 * Removes and returns the first element from this list.
	 */
	@Override
	public E removeFirst() {
		if(numItems == 0) { // you can't remove with empty or no items in the list.
			throw new NoSuchElementException();
		}
		E e = head.item;
		head = head.next;
		if (head != null) head.next = null;
		else tail = null;
		numItems--;
		return e;

		/**
		KWNode<T> removeNode = head; // remove from head of the list
		head = head.next; // prepare to remove and point to the next node
		head.pred = null; // now set head be null
		numItems--; // decrement the node after removing the node
        return removeNode.item;
		 */
	}

	/*
	 * remove final item from the list.
	 * @return the item from the last item of the list.
	 */
	@Override
	public E removeLast() {
		if (numItems==0) { // you can't remove with empty or no items in the list.
			throw new NoSuchElementException();
		}
		E e = tail.item;
		tail = tail.pred;
		if (tail!=null) tail.next = null;
		else head = null;
		numItems--;
		return e;
	}

	/*
	 *  Inserts the specified element at the specified position in this list. 
	 */
	@Override
	public void add(int i, E e) { 
		if (i<0 || i>numItems) throw new IndexOutOfBoundsException();
		if (i == 0) { // insert at front
			head = new Node<E>(e, null, head);
			if (tail==null) tail = head;
			else head.next.pred = head; // fix predecessor in (old) 1st node
		}
		else { // not at front
			Node<E> p = head;
			int pIndex = 0; // Node p is at position pIndex
			while (pIndex!=i-1) {
				p = p.next;
				pIndex++;
			} // node p at index i-1
			p.next = new Node<E>(e, p, p.next); // insert t following p
			if (tail==p) tail = p.next;
			else p.next.next.pred = p.next;//fix predecessor in (old) node i
		}
		numItems++;
	}

	/*
	 * Get the item from the specified index
	 * @return the element at the specified position in this list. 
	 */
	@Override
	public E get(int i) {
		if (i<0||i>=numItems) throw new IndexOutOfBoundsException();
		Node<E> p = head;
		int pIndex = 0; // Node p at index pIndex
		while (pIndex!=i) {
			p = p.next;
			pIndex++;
		}
		return p.item;
	}

	/*
	 *  Set the item at the specified index.
	 *  @return replaces the element at the specified position in this list with the  specified element. 
	 */
	@Override
	public E set(int i, E e) {
		if (i<0||i>=numItems) throw new IndexOutOfBoundsException();
		Node<E> p = head;
		int pIndex = 0; // Node p at index pIndex
		while (pIndex!=i) {
			p = p.next;
			pIndex++;
		}
		E temp = p.item;
		p.item = e;
		return temp;
	}

	/**
	 * this method walks forward through the linked list
	 */
	@Override
	public void iterateForward(){
		Node<E> tmpt = head;
		while(tmpt != null){
			System.out.println(tmpt.item);
			tmpt = tmpt.next;
		}
	}

	/*
	 * This method should return the object at the top
	 * of the queue. If the queue is empty it should return
	 * null
	 */
	@Override
	public E front() {
	return head.getItem();
	}

	/*
	 * This method should return the object at the after
	 * of the queue. If the queue is empty it should return
	 * null
	 */
	@Override
	public E tail() {
		return tail.getItem();
	}

	/*
     * This method should add the element to the after of the
     * queue
     */
	/** As same as void addLast(E E); */
		@Override
	public void enqueue(E item) {
		if(isEmpty()) head = tail =  new Node<>(item);
		else {
			Node<E> newTail = new Node<>(item);
			newTail.setPred(tail);
			tail.setNext(newTail);
			tail = newTail;
		}
		numItems++;
	}

	/*
     * This method should return the object at the top
     * of the queue and remove if from it.
     * If the queue is empty it should return
     * null
     */
	/** As same as E removeFirst(); */

	@Override
	public E dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		E theItem = head.getItem();
		head = head.getNext();
		if(numItems > 1) head.setPred(null);
		else tail = head;
		numItems--;
		return theItem;
	}

	/** Implements the query interface methods. */

	/*
	 * Searching applicant by its appointment id supply
	 * keep in mind that the appointment  id does not necessarily correspond to the position of the applicant in the queue.
	 * @param id the appointment id
	 * @return the applicant if exists else -1 unsuccessful
	 */
	@Override
	public int searchApplicant(String id) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		int index = 0;
		while (tempNode != null) {
			if(id.equals(tempNode.getItem().getAppointmentId())) return index;
			index++;
			tempNode = tempNode.getNext();
		}
		return -1; // no such applicant in the queue
	}

	/*
	 * Searching applicant object if exists
	 *@param applicant the applicant to be searched
	 * @return the applicant if exists else -1 unsuccessful
	 */ 
	@Override
	public int searchApplicant(Applicant applicant) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		int index = 0; // searching at this position
		while (tempNode != null) {
			if(applicant.getAppointmentId().equals(tempNode.getItem().getAppointmentId())) return index;
			index++;
			tempNode = tempNode.getNext();
		}
		return -1; // no such applicant exists
	}

	/*
	 * Searching applicant by supply appointment id
	 *@param id the appointment id
	 * @return the applicant if exists else throw no such element exception.
	 */
	@Override
	public Applicant getSpecificApplicant(String id) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		while (tempNode != null) {
			if(id.equals(tempNode.getItem().getAppointmentId()))
				return tempNode.getItem();
			tempNode = tempNode.getNext();
		}
		throw new NoSuchElementException();
	}

	/*
	 * Method  to remove an applicant,
	 *  from the system by entering
	 * in their unique appointment id number.
	 * @param applicant the applicant to be removed
	 * @return the applicant has been removed
	 */
	@Override
	public Applicant removeApplicant(Applicant applicant) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		while (tempNode.getNext() != null) {
			if(applicant.getAppointmentId().equals(tempNode.getItem().getAppointmentId())) {
				numItems--;
				removeNode(tempNode);
				return tempNode.getItem();
			}
			tempNode = tempNode.getNext();
		}
		throw new NoSuchElementException();
	}

	/*
	 * Method to remove the node a reposition of node
	 * been removed after.
	 */
	private void removeNode(Node<Applicant> tempNode) {
		try {
			tempNode.getPred().setNext(tempNode.getNext());
			tempNode.getNext().setPred(tempNode.getPred());
		} catch (NullPointerException e) {
			if(tempNode.getPred() == null) {
				//head
				head = head.getNext();
			} else {
				//tail
				tail = tail.getPred();
			}
		}
	}

	/*
	 * Removing applicant by supply appoint id
	 * @param id the appoint id
	 *@return applicant the applicant object if found else throw no such element exception.
	 */
	@Override
	public Applicant removeApplicant(String id) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		while (tempNode.getNext() != null) {
			if(id.equals(tempNode.getItem().getAppointmentId())) {
				numItems--;
				removeNode(tempNode);
				return tempNode.getItem();
			}
			tempNode = tempNode.getNext();
		}
		throw new NoSuchElementException();
	}

	/**
	 *  Method to cut off the after N number of records,
	 * from the queue.
	 * @param n the number of applicants to be removed from queue
	 * @return the list of applicant have been removed from the queue
	 */
	@Override
	public List<Applicant> removeNumberRecordsFromQueue(int n) {
		List<Applicant> numberOfApplicants = new ArrayList<>();
		int counter = 0;
		if(n > numItems) { // you can't search numbers greater than in item
			throw new IndexOutOfBoundsException();
		} else if(n <= numItems) {
			while (counter < n) {
				Applicant a = (Applicant) tail.getItem();
				numberOfApplicants.add(a);
				
//				numberOfApplicants.add(tail.getItem());
				tail = tail.getPred();
				tail.setNext(null);
				counter++;
				if(! isEmpty())
					numItems--;
			}
			return numberOfApplicants;
		} else {
			return null;
		}
	}

	/**
	 * Remove by a given position index
	 * @param position the index of position to be removed
	 */
	@Override
	public Applicant removeByPosition(int index) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		int counter = 0;
		while (tempNode.getNext() != null) {
			counter ++;
			if(counter == index) {
				numItems--;
				removeNode(tempNode);
				return tempNode.getItem();
			}
			tempNode = tempNode.getNext();
		}
		throw new NoSuchElementException();
	}

	/** 
	 * Inserts the specified applicant at the beginning of this collection. 
	 * @param E the specified of the item of the collection.
	 */
	@Override
	public void add(Applicant applicant) {
		//enqueue(applicant);

		Node<Applicant> tail= null;
		Node<Applicant> tempNode = tail;
		while (tempNode.getPred() != null) {
			if(tempNode.getPred().getItem().getPriority().getPrecedence()
					> applicant.getPriority().getPrecedence()) {
				Node.swapItem(tempNode.getPred(), tempNode);
			}
			tempNode = tempNode.getPred();
		}
	}

	/** Methods to set applicant's object and its supply @id, @applicant. */
	public void set(String id, Applicant applicant) {
		Node<Applicant> head = null;
		Node<Applicant> tempNode = head;
		while (tempNode != null) {
			if(id.equals(tempNode.getItem().getAppointmentId())) {
				tempNode.setItem(applicant);
				return;
			}
			tempNode = tempNode.getNext();
		}
		throw new NoSuchElementException();
	}

}
