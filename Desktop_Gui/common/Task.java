package common;

public class Task {
	String name;
	String desc;
	String reason;
	int parentID;
	int taskID;
	
	public Task(String name, String desc, String reason, int parentID, int taskID){
		this.name = name;
		this.desc = desc;
		this.reason = reason;
		this.parentID = parentID;
		this.taskID = taskID;
	}
}
