public class User {
   int userID;
   int usertype;
   String username;
   String firstname;
   String lastname;
   String email;
   String phone;
   String passhash;

   public User(int userID, int usertype, String username, String firstname, String lastname, String passhash) {
         this.userID = userID;
         this.usertype = usertype;
         this.username = username;
         this.firstname = firstname;
         this.lastname = lastname;
         this.passhash = passhash;
   }

   public void setEmail(String e) {
      email = e;
   }


   public void setPhone(String p) {
      phone = p;
   }
}