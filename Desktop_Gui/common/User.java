package common;
public class User {
   private int userID;
   private int usertype;
   private String username;
   private String firstname;
   private String lastname;
   private String email;
   private String phone;
   private String passhash;
   public String lengthError="The length of your password is %d characters long, it should be at least 6 characters long.";
   public String numberError="Your password doesn't have a number in it.";
   public String letterError="Your password doesn't have a letter in it.";
   public String capitalError="Your password doesn't have a capital letter in it.";
   private Boolean charLength=false;
   private Boolean hasNumber=false;
   private Boolean hasLetter=false;
   private Boolean hasCapital=false;
   
   public User(int userID, int usertype, String username, String firstname, String lastname) {
         this.userID = userID;
         this.usertype = usertype;
         this.username = username;
         this.firstname = firstname;
         this.lastname = lastname;
   }
   
   
   /*Password must follow the following format
    * 1. Greater than 6 chars
    * 2. Have at least 1 number 
    * 3. Have at least 1 letter
    * 4. Have at least 1 Capital letter
    */
   public void setPass(String p) {
	
	   
	   for(int i=0;i<p.length();i++){   
		   if(p.charAt(i)>=0 &&p.charAt(i)<=9){
			   hasNumber=true;
		   }
		   if(p.charAt(i)>=65 &&p.charAt(i)<=90){
			   hasCapital=true;
		   }
		   if(p.charAt(i)>=97 &&p.charAt(i)<=122){
			   hasLetter=true;
		   }
	   }if(p.length()<6){
		  //return error
	   }else charLength = true;
	   
	   if(hasLetter ==false){
		  //return error
	   }if(hasCapital ==false){
		  //return error
	   }if(hasNumber ==false){
		  //return error
	   }if(charLength==true &&hasLetter==true && hasCapital==true&& hasNumber==true)passhash = p;
  }
   
   public void setEmail(String e) {
      email = e;
   }
   /*
    * set phone number given input of different formats 
    * XXXXXXXXXX,
    * XXX-XXXXXXX,
    * XXXXXX-XXXX,
    * XXX-XXX-XXXX
    * to be XXX-XXX-XXXX format
    */
   public boolean setPhone(String p) {
	  StringBuilder str = new StringBuilder(p);
      if(p.charAt(3)=='-' && p.charAt(7)=='-'){
    	  phone = p;
    	  return true;
      }else if(p.charAt(3)=='-' || p.charAt(7)=='-'){
    	  if(p.charAt(3)=='-'){
    		  str.insert(3, '-');
    		  p = new String(str);
    		  phone = p;
    		  return true;
    		  //set char 7 to be -
    	  }
    	  str.insert(7, '-');
		  p = new String(str);
		  phone = p;
		  return true;
      }else{
    	  str.insert(3, '-');
    	  str.insert(7, '-');
		  p = new String(str);
		  if(p.length()==12){
			  phone = p;
			  return true;
		  }
		  // set 3 and 7 to -
      }return false;
   }
   
   public int get_userID() {
	   return userID;
   }
   
   public int get_usertype() {
	   return usertype;
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