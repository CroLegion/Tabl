import java.util.ArrayList;
public class create_data {


	public static ArrayList<User> create_users() {
		ArrayList<User> user_list = new ArrayList<User>();


		/* Object creation */
     	User user_1 = new User(1,1,"devinj","devin","johnson","password");
     	user_1.setEmail("Fake@iastate.edu");
     	user_1.setPhone("1134567890");
     	User user_2 = new User(2,2,"user 2","john","doe","password1234");
     	User user_3 = new User(5,43,"user 3","harry","stiles","password");
     	User user_4 = new User(8,8,"user 4","tom","platz","password1458");
     	
     	user_list.add(user_1);
     	user_list.add(user_2);
     	user_list.add(user_3);
     	user_list.add(user_4);

     	System.out.println("User name: " + user_1.firstname + " " + user_1.lastname);

          return user_list;
	}


     public static ArrayList<Job> create_jobs() {
          ArrayList<Job> job_list = new ArrayList<Job>();

          Job job_1 = new Job(1234, "Monday Construction", 2);
          job_1.setJobdesc("Pouring concrete");
          job_1.setparentID(555);

          Job job_2 = new Job(2589, "Tuesday Framing", 3);

          job_list.add(job_1);
          job_list.add(job_2);

          System.out.println("Job name: "+ job_1.jobname);

          return job_list;
     }
}