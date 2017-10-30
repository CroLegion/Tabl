package common;

public class Task {
	String name;
	String desc;
	int parentID;
	int taskID;
	
	public Task(String name, String desc, int parentID, int taskID){
		this.name = name;
		this.desc = desc;
		this.parentID = parentID;
		this.taskID = taskID;
	}
}
