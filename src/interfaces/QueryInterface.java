package interfaces;

import applicants.Applicant;
import java.util.List;

/*
 * Interface QueryInterface represents a contract enrolment of 
 * querying for applicant appointment implementation
 * of search, remove, iterate etc methods
 * @param <E> whose types to be implement from this interface
 * @author Kok Heng
 * @date 19 November 2019 
 */

public interface QueryInterface<E extends Applicant> {

	/*
	 * Searching applicant by its appointment id supply
	 * keep in mind that the appointment  id does not necessarily correspond to the position of the applicant in the queue.
	 * @param id the appointment id
	 * @return the applicant if exists else -1 unsuccessful
	 */
	public int searchApplicant(String id);

	public int searchApplicant(E applicant);

	public Applicant getSpecificApplicant(String id);

	/*
	 * Method  to remove an applicant,
	 *  from the system by entering
	 * in their unique appointment id number.
	 * @param applicant the applicant to be removed
	 * @return the applicant has been removed
	 */
	public Applicant removeApplicant(E applicant);

	public Applicant removeApplicant(String id);

	/**
	 *  Method to cut off the after N number of records,
	 * from the queue.
	 * @param n the number of applicants to be removed from queue
	 * @return the list of applicant have been removed from the queue
	 */
	public List<Applicant> removeNumberRecordsFromQueue(int n);

	/**
	 * Remove by a given position index
	 * @param position the index of position to be removed
	 */
	public Applicant removeByPosition(int index);

	public void add(E applicant);

}
