
public class VideoRecording extends Recording{
	private final double FRAMERATE;
	
	public VideoRecording() {
		super();
		this.FRAMERATE=0.0;
	}
	
	public VideoRecording(String a, String r, int d, double f) { 
		super(a,r,d);
		if(f>0) 
			FRAMERATE=f;
		else
			FRAMERATE=0;
	}
	
	public double getFRAMERATE() {
		return FRAMERATE;
	}
	
	public String toString() {
		return super.toString()+" [VIDEO | framerate: "+FRAMERATE+" fps]";
	}

}

