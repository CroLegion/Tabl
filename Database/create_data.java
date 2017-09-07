import java.util.ArrayList;
import java.io.*;
public class create_data {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World");
		ArrayList<User> list = new ArrayList<User>();
		/* Object creation */
     	User user_1 = new User(1,1,"devinj","devin","johnson","devinj@iastate.edu","3202268480","password");
     	user_1.setEmail("Fake@iastate.edu");
     	user_1.setPhone("1134567890");


     	System.out.println("Variable Value :" + user_1.firstname + user_1.lastname);
	}
}