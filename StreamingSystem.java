import java.util.*;
public class StreamingSystem {
	
	//collection of playlists to access
	ArrayList<Playlist> playlists=new ArrayList<Playlist>();

	
	public static boolean addUser(User us) {
		//error checking that returns false if error
		try {
			Database.users.add(us);
			return true;
		}catch(Exception e) {
			System.out.println("Not able to add user");
			return false;
		}
		
	}
	
	public static int indexOfUser(User us) {
		return Database.users.indexOf(us);
	}
	
	public static ArrayList<Recording> getUserPlaylist(String ID) {
		int whichUser=-1;
		for(int i=0;i<4; i++) {
			if(Database.users.get(i).getID().equals(ID)) {
				whichUser=i;
				break;
			}
		}
		Playlist playlistUser=Database.playlists.get(whichUser);
		return playlistUser.getRecordingList();
	}
	
	
	public static User checkUser(String name, String pw) {
		ArrayList<User> potential=new ArrayList<User>();
		for(int i=0;i<Database.users.size();i++) {
			if(Database.users.get(i).getUsername().toString().equals(name)) {
				potential.add(Database.users.get(i));
			}
			else if(Database.users.get(i).getID().toString().equals(name)) {
				potential.add(Database.users.get(i));
				
			}
		}
		for(int j=0;j<potential.size();j++) {
			if(potential.get(j).getPassword().toString().equals(pw)) {
				//sign in
				return potential.get(j);
			}
			
		}
		return Database.none;
	}

}
