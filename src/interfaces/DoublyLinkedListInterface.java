package interfaces;

/*
 * Interface DoublyLinkedListInterface represents a contract enrolment of 
 * standard doubly linked list implementation
 * of add, remove, iterate etc methods
 * @param <E> whose types to be implementd from this interface
 * @author Kok Heng
 * @date 17 November 2019 
 */

public interface DoublyLinkedListInterface<E> {

	/*
	 * This method should return the number of items
	 * in the queue
	 */
	public int size();

	/*
	 * This method should return true is there is no elements
	 * in the queue, and false if there is at least one element
	 * in the queue
	 */
	public boolean isEmpty();

	/** 
	 * Inserts the specified item at the beginning of this collection. 
	 * @param E the specified of the item of the collection.
	 */
	public void addFirst(E e);

	/**
	 * Appends the specified item to the end of this list.
	 * @param e the specified of the item of the collection.
	 */
	public void addLast(E e);

	/**
	 * Removes and returns the first element from this list.
	 */
	public E removeFirst();

	/*
	 * remove final item from the list.
	 * @return the item from the last item of the list.
	 */
	public E removeLast();

	/*
	 *  Inserts the specified element at the specified position in this list. 
	 */
	public void add(int i, E e); 

	/*
	 * Get the item from the specified index
	 * @return the element at the specified position in this list. 
	 */
	public E get(int i);

	/*
	 *  Set the item at the specified index.
	 *  @return replaces the item at the specified position in this list with the  specified item. 
	 */
	public E set(int i, E e);

	/**
	 * this method walks forward through the linked list
	 */
	public void iterateForward();

	/*
     * This method should return the object at the top
     * of the queue. If the queue is empty it should return
     * null
     */
    public E front();

    /*
     * This method should return the object at the after
     * of the queue. If the queue is empty it should return
     * null
     */
    public E tail();

	/*
     * This method should add the element to the after of the
     * queue
     */
	/** As same as void addLast(E E); */
	public void enqueue(E item);

    /*
     * This method should return the object at the top
     * of the queue and remove if from it.
     * If the queue is empty it should return
     * null
     */
/** As same as E removeFirst(); */
	public E dequeue();
}
