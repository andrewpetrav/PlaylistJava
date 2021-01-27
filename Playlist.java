import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Playlist{
	Random rand=new Random();
	private String name;
	private int numberOfRecordings = 0;
	private int durationInSeconds = 0;
	//private final int MAX_PLAYLIST_SIZE; // need to be constant
	//private Recording [] recordingList;
	private ArrayList<Recording> recordingList=new ArrayList<Recording>();
	// Non-parametrized constructor
	Playlist(){
		this.name = "Unknown";
		//this.MAX_PLAYLIST_SIZE = 10000;
		this.recordingList = new ArrayList<Recording>(); //[this.MAX_PLAYLIST_SIZE];
	}
	// Parametrized constructor
	Playlist(String name){//, int MAX_PLAYLIST_SIZE){
		// Check if arguments are valid...
		if (name != null) {// && MAX_PLAYLIST_SIZE > 0){
			// ...if yes, use them to set attribute values
			this.name = name;
			//this.MAX_PLAYLIST_SIZE = MAX_PLAYLIST_SIZE;
			this.recordingList = new ArrayList<Recording>(); //[this.MAX_PLAYLIST_SIZE];
		} 
		else {
			// ...if not, fall back on non-parametrized constructor behavior
			this.name = "Unknown";
			//this.MAX_PLAYLIST_SIZE = 10000;
			this.recordingList = new ArrayList<Recording>(); //[this.MAX_PLAYLIST_SIZE];
		}
	}
	 
	// Setters
	public void setName(String name){
		if (name != null){
			this.name = name;
		}
	}
	// Getters
	public ArrayList<Recording> getRecordingList() {
		return this.recordingList;
	}
	
	public Recording getIndexOfSong(String songName, String artistName) {
		for(int i=0; i<recordingList.size();i++) {
			if(songName.equals(recordingList.get(i).getName())&&(artistName.equals(recordingList.get(i).getArtist()))) {
					return recordingList.get(i);
				}	
			}
		return null;
	}
	
	public int getNumberOfRecordings(){
		return this.numberOfRecordings;
	}
	public String getName(){
		return this.name;
	}
	public int getDuration(){
		return this.durationInSeconds;
	}
	// A method that adds a new recording
	public boolean add(Recording newRecording){
		//System.out.println("TTESTTESTEST: "+newRecording);
		// Make sure we can add this recording first (not null and enough space)
		if (newRecording != null && !(recordingList.contains(newRecording))) {// && numberOfRecordings <MAX_PLAYLIST_SIZE){
			// ...if we can, add it...
			this.recordingList.add(newRecording);
			// ...increment the number of recordings...
			this.numberOfRecordings++;
			// ...and add its duration to total playlist duration
			this.durationInSeconds = this.durationInSeconds +newRecording.getDuration();
			// everything worked - return false
			return true;
		} 
		else {
			// ...if we cannot - return false
			return false;
		}
	}
	
	public boolean removeIndex(int index) {
		if(index==0||index>numberOfRecordings) {
			return false;
		}
		else {
			durationInSeconds=-recordingList.get(index).getDuration();
			recordingList.remove(index);
			numberOfRecordings--;
			return true;
		}
	}
	
	public boolean removeName(String name) {
		for(int i=0; i<recordingList.size();i++) {
			if(recordingList.get(i).getName().equals(name)) {
				durationInSeconds=-recordingList.get(i).getDuration();
				recordingList.remove(i);
				numberOfRecordings--;
				return true;
			}
		}
		return false;
	}
	

	// play method
	public void play(){
		// Check if the playlist is empty...
		if (this.numberOfRecordings > 0) {
			// ...if not, iterate over all array objects ...
			for (int index = 0; index < this.numberOfRecordings; index++){
				// ... and invoke their play method
				recordingList.get(index).play();
			}
		System.out.println();
		} 
		else {
			// ...if empty, display this error message
			System.out.println("ERROR: Empty playlist.");
		}
	}
	
	public void shuffle() {
		ArrayList<Recording> shuffleList=(ArrayList<Recording>) recordingList.clone();
		while(shuffleList.size()>0) {
			try {
				int r=rand.nextInt(shuffleList.size());
				shuffleList.get(r).play();
				shuffleList.remove(r);
			}catch(Exception e) {
				continue;
			}
		}
	}
	
	public void load(String file) {
		String n="";
		String t="";
		int d=0;
		double r=0;
		File f=new File(file);
		try {
			Scanner input=new Scanner(f);
			System.out.println("Processing playlist file "+f+":");
			while(input.hasNext()){
				try {
					String line=input.nextLine();
					String[] lineString=line.split(",");
					if(lineString[0].equals("V")) {
						n=lineString[1];
						t=lineString[2];
						d=Integer.parseInt(lineString[3]);
						r=Double.parseDouble(lineString[4]);
						VideoRecording v=new VideoRecording(n,t,d,r);
						if(recordingList.contains(v)) {
							System.out.println("Duplicate entry. Song not added");
						}
						else {
							add(v);
						}
						
					}	
					else if(lineString[0].equals("A")) {
						n=lineString[1];
						t=lineString[2];
						d=Integer.parseInt(lineString[3]);
						r=Double.parseDouble(lineString[4]);
						AudioRecording a=new AudioRecording(n,t,d,r);
						if(recordingList.contains(a)) {
							System.out.println("Duplicate entry. Song not added");
						}
						else {
							add(a);
						}
					}
				
			}catch(NumberFormatException e) {
				System.out.println("ERROR: Number format exception. Recording rejected ("+n+", "+t+", "+d+", "+r+")");
			}
			}
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	

	public void displayStats() {
		int j;
		Recording temp;
		ArrayList<Recording> stats=(ArrayList<Recording>) recordingList.clone();
		for ( int i = 1; i < stats.size(); i++ )
		{
			j = i;
			temp = stats.get(i);
			while ( j != 0 && stats.get(j-1).getNumberOfPlays() < temp.getNumberOfPlays() )
				{
					stats.set(j, stats.get(j-1));
					j--;
				}
			stats.set(j, temp);
		}
		
		for(int i=0;i<=stats.size()-1;i++) {
			System.out.println(stats.get(i).getArtist()+" - "+stats.get(i).getName()+" - "+stats.get(i).getNumberOfPlays());
		}
		
	}
		

	// toString method
	public String toString(){
		String returnString = "Playlist: " + this.name + " [" + ((int)Math.floor(this.durationInSeconds/60)) + "m" + (this.durationInSeconds %60) + "s]:\n";
		if (this.numberOfRecordings > 0) {
			for (int index = 0; index < this.numberOfRecordings; index++){
				returnString = returnString +recordingList.get(index).toString() + "\n";
			}	
		}
	return returnString;
	}
}