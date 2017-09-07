import java.io.*;
public class Job {
   int jobID;
   String jobname;
   int jobtype;
   String jobdesc;
   int parentID;

   public Job(int jobID, String jobname, int jobtype) {
   		this.jobID = jobID;
         this.jobname = jobname;
         this.jobtype = jobtype;
   }

   public void setJobdesc(String e) {
		jobdesc = e;
   }


   public void setparentID(int p) {
		parentID = p;
   }
}