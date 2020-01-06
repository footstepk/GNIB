package views;

import java.util.ArrayList;
import java.util.List;

/*
 * Menu class represents the main activity for front desk,
 * it displays the selection of menu to choose from number
 * respectively
 *@author Kim Jang Wong 2017300
 *@date 01 December 2019
 */

public class Menu {

	private List<String> selection; // a list of menu selection

	/** Constructs an empty menu with its menu selection methods. */
	public Menu() {
		// setup the menu
		setSelection();
	}

	/*
	 * Get the list of items from the collection list
	 * @return the list of items added from the collection list
	 */
		public List<String> setSelection() {
		selection = new ArrayList<>();
		selection.add("Add New Applicant"); // add a new applicant
		selection.add("Check Next Applicant in Queue"); // check the next applicant from the queue
		selection.add("Check  Last Applicant in Queue"); // check the last applicant from the queue
		selection.add("Search For an Applicant"); // searching a specific applicant (supply id)
		selection.add("Update Applicant Details"); // update a specific applicant details (supply id)
		selection.add("Remove Applicant by Appointment ID"); // removing a specific applicant from queue (supply id)
		selection.add("Remove Applicant From The Queue"); // removing the top of the applicant from the queue
		selection.add("Remove The Last N Number of Records From The Queue"); // removing number of applicants when supply total number of applicant to be removed
		selection.add("Check Number of Applicants in Queue Status"); // checking total number of applicants from the queue
		selection.add("Quit the program"); // quitting the program
		return selection;
	}

	/*
	 * Get the list of items from the collection list
	 * @return the list of items from the collection list
	 */
	public List<String> getSelection() {
		return selection;
	}
}
