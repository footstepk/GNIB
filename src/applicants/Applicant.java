package applicants;

import enums.Priority;
import helpers.Helper;
import java.sql.Date;
import java.util.Objects;

/*
 * Class Applicant represents the notion of an applicant details
 * It consists its attributes of name, passport number, arrival date of appointment and priority status.
 * @author Kok Heng
 * @date 02 November 2019
 */
public class Applicant {

	/** Declare data fields for applicant. */

	private String appointmentId; // the appointment id
	private String firstName; // the first name
	private String surname; // the surname
	private String passportNumber; // the passport number
	private Date arrivedDate; // the date of arrival for the appointment
	private Priority priority; // the priority status i.e. low medium high

	/** Constructs empty applicant object. */
	public Applicant() {}

	/*
	 * Constructs applicant with specified properties 
	 * @param id the appointment id
	 * @param first the first name
	 * @param lastName the surname
	 * @param passportNo the passport number
	 * @param arrival the arrived date
	 * @param priorities the priority
	 */
	public Applicant(String id, String first, String last, String passportNo, Date arrived, Priority priorities) {
		appointmentId = id;
		firstName = first;
		surname = last;
		passportNumber = passportNo;
		arrivedDate = arrived;
		priority = priorities;
	}

	/*
	 * Constructs applicant with specified properties,
	 * without appointment id
	 * @param first the first name
	 * @param lastName the surname
	 * @param passportNo the passport number
	 * @param arrival the arrived date
	 * @param priorities the priority
	 */
	public Applicant(String first, String last, String passportNo, Date arrived, Priority priorities) {
		appointmentId = generatesAppointmentId();
		firstName = first;
		surname = last;
		passportNumber = passportNo;
		arrivedDate = arrived;
		priority = priorities;
	}

	/*
	 * Constructs applicant with specified properties,
	 * @param applicant the applicant object
	 */
	public Applicant(Applicant applicant) {
		appointmentId = applicant.getAppointmentId();
		firstName = applicant.getFirstName();
		surname = applicant.getSurname();
		passportNumber = applicant.getPassportNumber();
		arrivedDate = applicant.getArrivedDate();
		priority = applicant.getPriority();
	}

	/*
	 * Constructs applicant object with specified properties 
	 * @param id the appointment id
	 * @param first the first name
	 * @param lastName the surname
	 * @param passportNo the passport number
	 * @param arrival the arrived date
	 * @param priorities the priority
	 * @return the instance of the applicant with specified properties
	 */
	public static Applicant createApplicant(String id, String first, String lastName, String passportNo, Date arrival, Priority priorities) {
		return new Applicant(id, first, lastName, passportNo, arrival, priorities);
	}

	/*
	 * Constructs applicant object with specified properties,
	 * without appointment id
	 * @param first the first name
	 * @param lastName the surname
	 * @param passportNo the passport number
	 * @param arrival the arrived date
	 * @param priorities the priority
	 * @return the instance of the applicant with specified properties
	 */
	public static Applicant createApplicant(String first, String lastName, String passportNo, Date arrival, Priority priorities) {
		return new Applicant(first, lastName, passportNo, arrival, priorities);
	}

	/*
	 * Constructs applicant object with specified properties,
	 * @param applicant the applicant object
	 * @return the instance of the applicant object
	 */
	public static Applicant createApplicant(Applicant applicant) {
		return new Applicant(applicant);
	}

	/** The getter methods. */
	public String getAppointmentId() {
		return appointmentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public Date getArrivedDate() {
		return arrivedDate;
	}

	public Priority getPriority() {
		return priority;
	}

	/*
	 * Immutable Setters  a new object with the
	 * different state is returned instead.
	 */
	public Applicant setAppointmentId(String appointmentId) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	public Applicant setFirstName(String firstName) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	public Applicant setSurname(String surname) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	public Applicant setPassPortNumber(String passportNumber) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	public Applicant setArrivedDate(Date arrivedDate) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	public Applicant setPriority(Priority priority) {
		return new Applicant(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	/**
	 * Method to compare this object Equals to supply object object 
	 * @param obj the object to be compared
	 * @return boolean values are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Applicant applicant = (Applicant) obj; // casting applicant object
		return Objects.equals(appointmentId, applicant.appointmentId) &&
				Objects.equals(firstName, applicant.firstName) &&
				Objects.equals(surname, applicant.surname) &&
				Objects.equals(passportNumber, applicant.passportNumber) &&
				Objects.equals(arrivedDate, applicant.arrivedDate) &&
				priority == applicant.priority;
	}

	/*
	 * Methods to get a hash code for the object
	 * @returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, firstName, surname, passportNumber, arrivedDate, priority);
	}

	/**
	 * Method to generate an appointment id,
	 * of size 8 in length
	 * @return the string representation of this appointment id
	 */
	public static String generatesAppointmentId() {
		return Helper.generateAppointmentId(8);
	}

	/*
	 * restructure the default display object
	 * @return the string representation of this applicant object with specified properties
	 */
	@Override
	public String toString() {
		String display = "\t" + appointmentId + "\t" + firstName +
				"\t" + surname + "\t" + passportNumber +
				"\t" + arrivedDate + "\t" + priority + "\t";
		return display;
	}

}
