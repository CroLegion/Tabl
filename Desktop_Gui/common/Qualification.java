package common;

public class Qualification {
	private int qualID;
	private String qualName;
	private String qualDesc;
	
	
	public Qualification(int qualID, String qualName, String qualDesc){
		this.qualID = qualID;
		this.qualName = qualName;
		this.qualDesc = qualDesc;
	}
	
	public void setQualID(int i) {
		qualID = i;
	}
	
	public void setQualName(String s){
		qualName = s;
	}
	
	public void setQualDesc(String s){
		qualDesc = s;
	}
	
	public int getQualID() {
		return qualID;
	}
	
	public String getQualName() {
		return qualName;
	}
	
	public String getQualDesc() {
		return qualDesc;
	}
}
