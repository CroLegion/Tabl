package common;
public class Job {
   int jobID;
   String jobname;
   int jobtype;
   String jobdesc;
   String parentID=null;

   public Job(int jobID, String jobname, int jobtype) {
   		this.jobID = jobID;
         this.jobname = jobname;
         this.jobtype = jobtype;
   }

   public void setJobdesc(String e) {
		jobdesc = e;;
   }



   public void setparentID(String s) {
		parentID = s;
   }
}