package controllers;

import applicants.Applicant;
import doubly_linked_lists.*;
import enums.Priority;
import helpers.Helper;
import views.Menu;
import java.sql.Date;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Class Controller represents the flow of querying applicant appointment
 * It take user input values and display the objects
 * at any times, it allows to launch the menu function
 * @author Kok Heng
 * @date 03 December 2019 
 */
public class Controller {

	/* Initial the linked list object. */
	KWLinkedList<Applicant> linkedList = new KWLinkedList<>();
	Menu menu; // taking menu selection

	/* Constructs an empty controller and launch the menu. */
	public Controller() {
		this.menu = new Menu();
		while (true) {
			showMenu();
		}
	}

	/* Methods to take input selection and display the menu selection. */
	private void showMenu() {
		String selection;
		for (int i = 0; i < menu.getSelection().size(); i=i+1) {
			System.out.println(i + 1 + " - " + menu.getSelection().get(i));
		}
		selection = Helper.readInput("Please choose a selection from the menu", menu.getSelection().size() + "|[1-9]");

		/** Allows switching 1 to another menu selection. */
		switch (selection) {
		/** add a new applicant. */
		case "1":
			addNewApplicant();
			break;

		/** check the next applicant from the queue */
		case "2":
			checkNextApplicantInQueue();
			break;

			/** check the last applicant from the queue */
		case "3":
			checkLastApplicantInQueue();
			break;

		/** searching a specific applicant (supply id) */
		case "4":
			searchForAnApplicant();
			break;

		/** update a specific applicant details (supply id) */
		case "5":
			updateApplicantDetails();
			break;

		/** removing a specific applicant from queue (supply id) */
		case "6":
			removeApplicantByAppointmentId();
			break;

		/** removing the top of the applicant from the queue */
		case "7":
			removeApplicant();
			break;

		/** removing number of applicants when supply total number of applicant to be removed */
		case "8":
			removeLastNRecordsFromQueue();
			break;

		/** checking total number of applicants from the queue */
		case "9":
			checkNumberOfApplicantInQueueStatus();
			break;

		/** quitting the program */
		case "10":
			quitTheProgram();
			break;

		default: break;
		}
	}

	/* Methods to add a new applicant, if success applicant object display */
	private void addNewApplicant() {
		System.out.println("Add a new  applicant into the queue");
		String firstName = Helper.readInput("Enter first name: ");
		String surname = Helper.readInput("Enter surname: ");
		String passportNumber = Helper.readInput("Enter passport number: ");
		Date arrivedDate = new Date(System.currentTimeMillis());
		Priority priorityStatus = Priority.valueOf(Helper.readInput("Enter priority status: ", Priority.low, Priority.medium, Priority.high));
		Applicant applicant = Applicant.createApplicant(firstName, surname, passportNumber, arrivedDate, priorityStatus);
		linkedList.addFirst(applicant);
		System.out.println("The applicant  added successfully");
		System.out.println("A new applicant added: ");
		System.out.println(applicant);
		Helper.backToMenu();
	}

	/* Methods to check the next applicant from the queue. */ 
	private void checkNextApplicantInQueue() {
		System.out.println("Checking next applicant (first applicant) in the queue");
		System.out.println(linkedList.front());
		Helper.backToMenu();
	}

	/* Methods to check the last applicant from queue. */ 
	private void checkLastApplicantInQueue() {
		System.out.println("Checking last  applicant in the queue");
		System.out.println(linkedList.tail());
		Helper.backToMenu();
	}

	/* Methods to search for applicant supply their appointment id. */
	private void searchForAnApplicant() {
		System.out.println("Search applicant by entering the appointment id");
		int index = linkedList.searchApplicant(Helper.readInput("Enter the appointment id of the applicant: "));
		if (index < 0) {
			System.out.println("Applicant not found");
		} else {
			System.out.println("The applicant is at the position " + index + " in the queue");
		}
		Helper.backToMenu();
	}

	/* Methods to update applicant details, if found it should display the new entry fields. */ 
	private void updateApplicantDetails() {
		try{
			System.out.println("Update Applicant Details...");
			Applicant applicant = linkedList.getSpecificApplicant(Helper.readInput("Enter the appointment id of the applicant to change detail: "));
			Applicant changed = Applicant.createApplicant(applicant.getAppointmentId(),
					Helper.readInput("Enter 	new first name of this applicant: ", "[A-Za-z]+"),
					Helper.readInput("Enter new surname of this applicant", "[A-Za-z]+"),
					Helper.readInput("Enter new passport number"),
					applicant.getArrivedDate(),
					Priority.valueOf(Helper.readInput("Applicant's priority status: ", Priority.low, Priority.medium, Priority.high))
					);

			if(! changed.equals(applicant)) {
				System.out.println("The applicant new details has been updated successfuly");
				linkedList.set(applicant.getAppointmentId(), changed);
				if(changed.getPriority() != applicant.getPriority()) {
					System.out.println("The applicant priority status has changed, the applicant queue status also has been updated");
					linkedList.removeApplicant(applicant);
					linkedList.addFirst(changed);
				}
			}
			else System.out.println("The update operation  has not been changed");
		}catch (NoSuchElementException e) {
			System.out.println("Applicant with this appointment id cannot be found in the system");
		}
		Helper.backToMenu();
	}

	/* Methods to remove applicant from queue, if found supply by appointment id. */
	private void removeApplicantByAppointmentId() {
		System.out.println("Remove applicant by entering appointment id");
		try{
			Applicant applicant = linkedList.removeApplicant(Helper.readInput("Enter appointment id of the applicant to remove: "));
			System.out.println("One applicant appointment id match found\nRemoving...");
			System.out.println("Applicant details removed: ");
			System.out.println(applicant);
			Helper.backToMenu();
		}catch (NoSuchElementException e){
			System.out.println("Error! Applicant not found...");
		}
	}

	/* Methods to remove applicant from the top. */
	private void removeApplicant() {
		System.out.println("Remove applicant from the queue");
		System.out.println("Please wait.. removing applicant");
		try{
			//Applicant applicant = linkedList.dequeue();
			Applicant applicant = linkedList.removeFirst();
			System.out.println("The applicant has been removed  successfully");
			System.out.println("Applicant removed:");
			System.out.println(applicant);
			Helper.backToMenu();
		}catch (NoSuchElementException e){
			System.out.println("Error! The queue either empty or applicant cannot be removed this time.");
			Helper.backToMenu();
		}
	}

	/* Methods to remove number of applicants from queue by supply the number. */
	private void removeLastNRecordsFromQueue() {
		System.out.println("Remove last N records from queue");
		try{
			List<Applicant> list = linkedList.removeNumberRecordsFromQueue(Integer.parseInt(Helper.readInput("Enter number of applicants to remove from the tail of the queue: ")));
			System.out.println("The number of applicants of records from queue have been Removed successfully removed applicant: ");
			list.forEach(applicant -> System.out.println(applicant));
			Helper.backToMenu();
		}catch (NoSuchElementException e){
			System.out.println("Cannot remove the number of applicants this time, try less number than number that entered.");
		}catch (IllegalFormatConversionException ex) {
			System.err.println(ex.getMessage());
		}
	}

	/* Methods to see total of number of applicants from queue. */
	private void checkNumberOfApplicantInQueueStatus() {
		System.out.println("Getting the number of applicants from the queue");
		//Node<Applicant> current = linkedList.getHead();
		//int sequence = 1;
		System.out.println("\tID \t\t\tFirst Name \t\tSurname \t\tPassport Number \t\tArrived Date \t\tPriority Status" + "\n");
		//		while (current!= null) {
		//System.out.println(sequence + " : " + current.getItem().toString());
		//current = current.getNext();
		//sequence++;
		linkedList.iterateForward();
		System.out.println();
		//}
		Helper.backToMenu();
	}

	/** Methods to quit the program. */
	private void quitTheProgram() {
		// getting local time
		Date time = new Date(System.currentTimeMillis());
		System.out.printf("System log off at: %tc", time);
		System.exit(0);
	}

}
