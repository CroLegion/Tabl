import java.util.ArrayList;
import java.io.*;
public class database_IO {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World");
		ArrayList<User> list = new ArrayList<User>();
		/* Object creation */
     	User user_1 = new User(1,1,"devinj","devin","johnson","password");
     	user_1.setEmail("Fake@iastate.edu");
     	user_1.setPhone("1134567890");
     	User user_2 = new User(2,2,"user 2","john","doe","password1234");
     	User user_3 = new User(3,3,"user 3","harry","stiles","password");
     	User user_4 = new User(3,3,"user 4","tom","platz","password1458");
     	
     	list.add(user_1);
     	list.add(user_2);
     	list.add(user_3);
     	list.add(user_4);

     	System.out.println("Variable Value :" + user_1.firstname + user_1.lastname);
	}
}