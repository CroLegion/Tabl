import java.io.*;
public class User {
   int jobID;
   String jobname;
   int jobtype;
   
   String passhash;

   public User(int userID, int usertype, String username, String firstname, String lastname, String email, String phone, String passhash) {
   		this.userID = userID;
   		this.usertype = usertype;
   		this.username = username;
   		this.firstname = firstname;
   		this.lastname = lastname;
   		this.passhash = passhash;
   }

   public void setJobdesc(String e) {
		email = e;
   }


   public void setparentID(String p) {
		phone = p;
   }
}