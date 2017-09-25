package common;
public class User {
   int userID;
   int usertype;
   String username;
   String firstname;
   String lastname;
   String email;
   String phone;
   String passhash;

   public User(int userID, int usertype, String username, String firstname, String lastname) {
         this.userID = userID;
         this.usertype = usertype;
         this.username = username;
         this.firstname = firstname;
         this.lastname = lastname;
   }

   public void setPass(String p) {
	  passhash = p;
   }
   
   public void setEmail(String e) {
      email = e;
   }


   public void setPhone(String p) {
      phone = p;
   }
   
   public String get_firstname() {
	   return firstname;
   }
   
   public String get_lastname() {
	   return lastname;
   }
   
   public String get_username() {
	   return username;
   }
   
   public String get_email() {
	   return email;
   }
   
   public String get_phone() {
	   return phone;
   }
}