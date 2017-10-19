package common;

public class Ticket {
	public int ticketID;
	public String title;
	public String message;
	public int submittedBy;
	public boolean isDone;
	public String submittedDate;
	
	public Ticket() {
		
	}
	
	public Ticket(int ticketID,	String title, String message, int submittedBy,	boolean isDone,	String submittedDate) {
		this.ticketID = ticketID;
		this.title = title;
		this.message = message;
		this.submittedBy = submittedBy;
		this.isDone = isDone;
		this.submittedDate = submittedDate;
	}
}
