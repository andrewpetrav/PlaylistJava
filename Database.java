import java.util.*;
public class Database {
	
	
	static ArrayList<Playlist> playlists=new ArrayList<Playlist>();
	static ArrayList<User> users=new ArrayList<User>();
	
	
	static User none=new User("void", "void");
	static User Roland=new User("Roland", "12345");
	static User Skroob=new User("Skroob","12345");
	static User Andrew=new User("Andrew", "password");
	static User JoeExotic=new User("TigerKing", "CaroleBaskinFedHerHusbandToTheTigers");
	
	static Playlist RolandP=new Playlist();
	static Playlist SkroobP=new Playlist();
	static Playlist AndrewP=new Playlist();
	static Playlist JoeExoticP=new Playlist();
	
	public static void initializeArrayList() {
		users.add(Roland);
		users.add(Skroob);
		users.add(Andrew);
		users.add(JoeExotic);
		for(int i=0; i<4; i++) {
			System.out.println(users.get(i).getID());
		}
		
		playlists.add(RolandP);
		playlists.add(SkroobP);
		playlists.add(AndrewP);
		playlists.add(JoeExoticP);
		
		SkroobP.load("CS116.csv");
		RolandP.load("CS116.csv");
		AndrewP.load("top5.csv");

	}


}
