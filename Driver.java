import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Driver {
	
	
	static User currentUser; //who is signed in 
	static Playlist currentPlaylist; //user's playlist
	static boolean exit=false;
	static boolean signedIn=false;
	static boolean logOut=false;
	static Scanner sc=new Scanner(System.in);
	static String input="";
	
	public static void signIn() {
		System.out.println("Enter your username or user ID");
		String username=sc.next(); //can be username or user ID
		System.out.println("Enter your password");
		String password=sc.next();
		if(StreamingSystem.checkUser(username, password).getUsername()!=("void")) {
			signedIn=true;
			currentUser=StreamingSystem.checkUser(username, password);
		}
		else {
			System.out.println("Either your username or password is incorrect");
			}
			
	}
	
	public static void signUp() {
		String username="void";
		while(username=="void") {
			System.out.println("Enter what you want your username to be");
			username=sc.next();
			System.out.println("Enter what you want your password to be");
			String password=sc.next();
			User user=new User(username,password);
			
			if(StreamingSystem.addUser(user)) {
			//if user added, sign them in as that user, sign in by ID
				signedIn=true;
				currentUser=StreamingSystem.checkUser(username, password);
				
			}
		}
	}
	
		
		
	public static void play() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("1. Play song");
			System.out.println("2. Play playlist");
			input=sc.next();
			
			if(input.equals("1")) { //play song
				playSong();
			}
			
			else if(input.equals("2")) { //play playlist
				currentPlaylist.play();
			}
			
			else if(input.equals("0")) {
				exitThis=true;
			}
		}
	}
	
	public static void playSong() {
		boolean exitThis=false;
		while(exitThis==false) {
			int posn=-1;
			input="";
			System.out.println("1. Play song based off of index");
			System.out.println("2. Play song based off of name");
			input=sc.next();		
			

			if(input.equals("1")) { //based off of index
					boolean exitThisSub=false;
					while(exitThisSub==false) {
						input="";
						System.out.println("Enter the index position of the song you would like to play"); 
						
						try {
							posn=sc.nextInt();
						}catch(Exception e){System.out.println("Please enter a valid number"); input="skip"; posn=-1; String holder=sc.next();}
						
						if(posn==0) {
							exitThisSub=true;
						}
						
						else if(input!="skip"){
							try{
								currentPlaylist.getRecordingList().get(posn-1).play();
							}catch(Exception e){System.out.println("ERROR: Index out of bounds");}
						}
					}	
			}
			
			else if(input.equals("2")) { //based off of name
				input="";
				String songName="";
				String artistName=null;
				System.out.println("Enter the name of the song you would like to play"); //needs error checking
				while(songName.equals("")) {
					songName=sc.nextLine();
				}
				
				System.out.println("Enter the name of the artist of the song that you would like to play"); //needs error checking
				artistName=sc.nextLine();
				try {
					Recording thisSong=currentPlaylist.getIndexOfSong(songName, artistName);
					thisSong.play();
				}catch(Exception e) {
					System.out.println("Sorry, cannot play that song");
				}
			}
			else if(input.equals("0")) {
				exitThis=true;	
			}
			
		}
	}
	
	
	public static void addRecording() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("1. Add recording");
			System.out.println("2. Add recordings from file");
			System.out.println("3. Add recordings from playlist");
			input=sc.next();
			
			if(input.equals("1")) { //recording
				addRec();
			}
			
			else if(input.equals("2")) { //file
				addFile();	
			}
			
			else if(input.equals("3")) { //playlist
				addPlaylist();
			}
			
			else if(input.equals("0")) {
				exitThis=true;
			}
		}
		
	}
	
	public static void addRec() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("1. Audio Recording");
			System.out.println("2. Video Recording");
			input=sc.next();
			if(input.equals("0")) {
				exitThis=true;
			}
			else if(input.equals("1")||input.equals("2")){
				try {
					System.out.println("Type in the name of the song that you would like to add");
					String songName=sc.next();
					System.out.println("Type in the name of the artist");
					String artistName=sc.next();
					System.out.println("Type in the recording's length in seconds");
					int songDuration=sc.nextInt();
					if(input.equals("1")) {
						AudioRecording a=new AudioRecording(artistName, songName, songDuration, 0.0);
					}
					else if(input.equals("2")) {
						VideoRecording v=new VideoRecording(artistName, songName, songDuration, 0.0);
					}
				}catch(Exception e) {System.out.println("Not a valid entry"); String holder=sc.next();}
				
			}
		}
	}
	
	
	public static void addFile() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("Type the name of the file you would like to add");
			input=sc.next();
			if(input.equals("0")) {
				exitThis=true;
			}
			else {
				currentPlaylist.load(input);
			}
		}
		
	}
	
	public static void addPlaylist() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("Type the ID of the user whose playlist you would like to add");
			input=sc.next();
			if(input.equals("0")) {
				exitThis=true;
			}
			else {
				if(input.equals(currentUser.getID())){
					System.out.println("You cannot enter your own ID");
				}
				else {
					try {
						ArrayList<Recording> listToAdd=StreamingSystem.getUserPlaylist(input);
						for(int i=0;i<listToAdd.size();i++) {
							currentPlaylist.add(listToAdd.get(i));
						}
					}catch(Exception e) {
						System.out.println("Not a valid ID");
					}	
			}
			}
			
		}
		
	}
	
	public static void remove() {
		boolean exitThis=false;
		while(exitThis==false) {
			input="";
			System.out.println("1. Remove based off of index");
			System.out.println("2. Remove based off of song name");
			input=sc.next();
			
			if(input.equals("0")) {
				exitThis=true;
			}
			
			if(input.equals("1")) {//off of index
				boolean exitThisSub=false;
				while(exitThisSub==false) {
					int removeThis=0;
					System.out.println("Type the index of the song that you would like to remove");
					try {
						removeThis=sc.nextInt();
					}catch(Exception e){System.out.println("Please enter a valid number"); input="skip"; removeThis=-1; String holder=sc.next();}
					
					if(removeThis==0) {
						exitThisSub=true;
					}
					else if(input!="skip") {
						if(!currentPlaylist.removeIndex(removeThis)) {
							System.out.println("Sorry, not an available position");
						}
						else {
							System.out.println("Successfully removed");
						}
					}
					
				}
				
			}
			
			else if(input.equals("2")) {//off of name
				System.out.println("Type the name of the song that you would like to remove");
				String removeThis=sc.next();
				if(!currentPlaylist.removeName(removeThis)) {
					System.out.println("Sorry, not an available song");
				}
				else {
					System.out.println("Successfully removed");
				}
			}	
		}
	}
	
	public static void shuffle() {
		currentPlaylist.shuffle();

	}
	
	public static void save() {
		 Calendar cal = Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM_DD_YYYY_HH_mm_ss");
		 String strDate = sdf.format(cal.getTime());
		 String prefix=(currentUser.getUsername().toUpperCase()+"_PLAYLIST_");
		 String suffix=".csv";
		 String saveName=prefix+strDate+suffix;
		 System.out.println(saveName);	    
	     currentPlaylist.setName(saveName);
	}
	
	public static void display() {
		currentPlaylist.displayStats();
	}
	
	public static void signOut() {
		currentUser=null; //signing out
		currentPlaylist=null; //sign out
		logOut=true;
		System.out.println("You have been logged out");
	}
	
	public static void exit() {
		signOut();
		exit=true;
		System.out.println("Closing program");
	}
		
	public static void main(String[] args){
		Database.initializeArrayList();
		exit=false;
		while(exit==false) {
			signedIn=false;
			while(signedIn==false) {
				String input="";
				System.out.println("1. Sign in");
				System.out.println("2. Sign up");
				input=sc.next();

				if(input.equals("1")) {
					signIn();	
				}
			
				else if(input.equals("2")) {
					signUp();
				}
			}
			
			int pos=StreamingSystem.indexOfUser(currentUser);
			currentPlaylist=Database.playlists.get(pos);
			System.out.println("In the submenus, press '0' to go back");
			logOut=false;
			while(logOut==false) {
				Scanner sc=new Scanner(System.in);
				System.out.println("1. Play"); //individual from other file, or from other playlist
				System.out.println("2. Add recording(s)");
				System.out.println("3. Remove recording"); //individual recording in playlist, based on name, playlist in order
				System.out.println("4. Shuffle"); //play shuffled playlist
				System.out.println("5. Save"); //save playlist
				System.out.println("6. Display"); //display statistics
				System.out.println("7. Sign out");
				System.out.println("8. Exit");
				input=sc.next();
				if(input.equals("1")) {//play
					play();
				}
				else if(input.equals("2")) { //add
					addRecording();
					
				}
				else if(input.equals("3")) { //Remove
					remove();
				}
				
				else if(input.equals("4")) { //shuffle
					shuffle();
				}
				
				else if(input.equals("5")) { //save
					save();
				}
				
				else if(input.equals("6")) { //display
					display();
				}
				else if(input.equals("7")) { //sign out
					signOut();
				}
				else if(input.equals("8")) {
					exit();
				}
	
			}
		}	
	}
}

	

