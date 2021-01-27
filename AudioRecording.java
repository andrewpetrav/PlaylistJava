
public class AudioRecording extends Recording {
	private final double BITRATE;
	
	public AudioRecording() {
		super();
		this.BITRATE=0.0;
	}
	
	public AudioRecording(String a, String r, int d, double b) { 
		super(a,r,d);
		if(b>0)
			BITRATE=b;
		else
			BITRATE=0;
	}
	
	public double getBITRATE() {
		return BITRATE;
	}
	
	public String toString(){
		
		return super.toString()+" [AUDIO | bitrate: "+BITRATE+" kpbs]";
	}
}
