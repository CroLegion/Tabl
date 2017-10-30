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
   public String[] errors=new String[]{
   "Your password doesn't have a length of at least 6",
   "Your password doesn't have a number in it.",
   "Your password doesn't have a letter in it.",
   "Your password doesn't have a capital letter in it."
   };
   public Boolean[] haveErrors=new Boolean[]{false,false,false,false};
   
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
   public String[] setPass(String p) {   
	   for(int i=0;i<p.length();i++){   
		   if(p.charAt(i)>=0 &&p.charAt(i)<=9){//number
			   haveErrors[1]=true;
		   }
		   if(p.charAt(i)>=97 &&p.charAt(i)<=122){//letter
			   haveErrors[2]=true;
		   }
		   if(p.charAt(i)>=65 &&p.charAt(i)<=90){//capital letter
			   haveErrors[3]=true;
		   }
		   
	   }if(p.length()>=6){//length >6
		   haveErrors[0]=true;
	   }if(haveErrors[0]==true &&haveErrors[1]==true && haveErrors[2]==true&& haveErrors[3]==true)passhash = p;
	   return getErrorMessages(haveErrors);
  }
   
   //given boolean array, checks against error array and returns errors
   private String[] getErrorMessages(Boolean[] bool){
	   String[]string =new String[3];
	   for (int i=0; i<bool.length;i++) {
		   if(bool[i]==false)string[i]=errors[i];
	   }
	   return string;
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
	   phone =p;
	   return true;
//	  StringBuilder str = new StringBuilder(p);
//      if(p.charAt(3)=='-' && p.charAt(7)=='-' &&p.length()==12){//properly formated
//    	  phone = p;
//    	  return true;
//      }else if((p.charAt(3)=='-' &&p.length()==11) || (p.charAt(7)=='-' &&p.length()==11)){//format missing a -
//    	  if(p.charAt(3)=='-'){//left -
//    		  str.insert(3, '-');
//    		  p = new String(str);
//    		  phone = p;
//    		  return true;
//    	  }str.insert(7, '-');//right -
//		  p = new String(str);
//		  phone = p;
//		  return true;
//      }else{//format missing both or wrong
//    	  if(p.length()!=10)return false;
//    	  str.insert(3, '-');
//    	  str.insert(7, '-');
//		  p = new String(str);
//      }return false;
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