package enums;

/*
 * Enumeration Priority represents of applicant's priority status
 * to be seen by staff at counter.
 * It consists of precedence of low, medium and high respectively
 * @author Kok Heng
 * @date 04 November 2019
 */

public enum Priority {

	/** Declare priority status level. */

	high(1), medium(2), low(3);

	private int precedence;

	/** Constructs empty instance of priority. @param precedence */
	Priority(int precedences) {
		precedence = precedences;
	}

	/*
	 * Get the representation of priority status.
	 * @return the representation for the priority status.
	 */
	public int getPrecedence() {
		return precedence;
	}

}
