public class Recording {
	// Class attributes / fields
	// Final because we can only set it once
	private final String ARTIST;
	private final String RECORDING_NAME;
	private final int DURATION_IN_SECONDS;
	private int numberOfPlays=0;
	// Non-parametrized constructor
	// Essentially here to prevent Java from setting Strings to default null
	Recording(){
		this.ARTIST = "Unknown";
		this.RECORDING_NAME = "Unknown";
		this.DURATION_IN_SECONDS = 0;
	}
	// Parametrized constructor
	Recording(String recordingName, String artist, int durationInSeconds){
		// Check if inputs are legit...
		if (artist != null && recordingName != null && durationInSeconds >0){
			// ...if yes, use them to set instance variables / attributes/ fields
			this.ARTIST = artist;
			this.RECORDING_NAME = recordingName;
			this.DURATION_IN_SECONDS = durationInSeconds;
		} 
		else {
			// ...if no, do what the non-parametrized constructor does
			this.ARTIST = "Unknown";
			this.RECORDING_NAME = "Unknown";
			this.DURATION_IN_SECONDS = 0;
		}
	}
	public String getArtist(){
		return this.ARTIST;
	}
	public String getName(){
		return this.RECORDING_NAME;
	}
	public int getDuration(){
		return this.DURATION_IN_SECONDS;
	}
	public int getNumberOfPlays() {
		return this.numberOfPlays;
	}
	// play() method
	// Display error message if duration is not positive
	// Otherwise display Now playing + toString() output
	public void play(){
		if (this.DURATION_IN_SECONDS > 0){
			System.out.println("Now playing: " + this.toString());
			numberOfPlays++;
		} 
		else {
			System.out.println("ERROR: Cannot play this recording.");
		}
	}
	
	
		// toString() method
		// Returns: author - recording name [XXmYYs]
		// where XX - full minutes and YY - remaining seconds (no leading zeronecessary)
	public String toString(){
		return this.ARTIST + " - " + this.RECORDING_NAME + " [" + ((int)Math.floor(this.DURATION_IN_SECONDS/60)) + "m" +(this.DURATION_IN_SECONDS % 60) + "s]";
		}
}
