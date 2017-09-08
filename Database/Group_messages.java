public class Group_messages {
	int msgID;
	String msgsubject;
	String msgcontent;
	int msgsender;
	int msgdest;
	String timestamp;

	public Group_messages (int msgID, String msgsubject, String msgcontent, int msgsender, int msgdest, String timestamp) {
		this.msgID = msgID;
		this.msgsubject = msgsubject;
		this.msgcontent = msgcontent;
		this.msgdest = msgdest;
		this.timestamp = timestamp;
	}
}