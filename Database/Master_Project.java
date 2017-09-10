import java.io.*;
import java.util.ArrayList;

//The idea for this is that this is the object that would be created when making a new project
public class Master_Project
{
	String projName; 		//Name of the project
	ArrayList<Job> jobs;		//A list of the subjobs, may contain sub jobs themselves
	int expectedTime;		
	String deadline;
	ArrayList<User> workers;	//List to hold all workers on the project done seperate from mangers
	ArrayList<User> managers;	//List of managers since there are times where you will want one and no the other
	String projDesc;
	int hoursSpent;
	int estimatedCost;
	int totalCost;

	public Master_Project(String projName, int expectedTime, String deadline, String projDesc, int estimatedCost)
	{
		this.projName=projName;
		this.expectedTime=expectedTime;
		this.deadline=deadline;
		this.projDesc=projDesc;
		this.estimatedCost=estimatedCost;
		ArrayList<Job> jobs=new ArrayList<Job>();
		ArrayList<User> wokers=new ArrayList<User>();
		ArrayList<User> managers=new ArrayList<User>();
	}
	
	public String getName(){return projName;}
	public ArrayList<Job> getJobs(){return jobs;}
	public int getExpectedTime(){return expectedTime;}
	public String getDeadline(){return deadline;}
	public ArrayList<User> getWorkers(){return workers;}
	public ArrayList<User> getManagers(){return managers;}
	public String getDesc(){return projDesc;}
	public int getHoursSpent(){return hoursSpent;}
	public int getEstimatedCost(){return estimatedCost;}
	public int getTotalCost(){return totalCost;}

	public void convertToDatabase()
	{
		//code for transforming into entry in database
		//
	}

}
