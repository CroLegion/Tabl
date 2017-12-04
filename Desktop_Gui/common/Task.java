package common;

public class Task {
	public String name;
	public String desc;
	public String reason;
	public int parentID;
	public int taskID;
	
	public Task(String name, String desc, String reason, int parentID, int taskID){
		this.name = name;
		this.desc = desc;
		this.reason = reason;
		this.parentID = parentID;
		this.taskID = taskID;
	}
}
