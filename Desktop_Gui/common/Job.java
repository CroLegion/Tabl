package common;
public class Job {
   public int jobID;
   public String jobname;
   public int jobtype;
   public String jobdesc;
   public int parentID;

   public Job(int jobID, String jobname, int jobtype, String jobdesc, int parentID) {
   		this.jobID = jobID;
         this.jobname = jobname;
         this.jobtype = jobtype;
         this.jobdesc= jobdesc;
   }
   
   public Job(int jobID, String jobname, int jobtype, String jobdesc) {
  		this.jobID = jobID;
        this.jobname = jobname;
        this.jobtype = jobtype;
        this.jobdesc= jobdesc;
  }
   public void setJobdesc(String e) {
		jobdesc = e;;
   }



   public void setparentID(int i) {
		parentID = i;
   }
}