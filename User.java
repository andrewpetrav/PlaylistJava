import java.util.*;

public class User implements Playable {
	//variables
	private String username;
	private String password;
	private String uniqueID;
	private ArrayList<Recording> playlist;
	
	//constructor
	public User(String name, String pw) {
		username=name;
		password=pw;
		boolean nameAccepted=true;
		int i=0;
		do {
			if(name.length()>=5) {
				uniqueID=name.substring(0,4)+i;
			}
			else {
				uniqueID=name+i;
			}
			
			for(int j=0;j<Database.users.size(); j++) {
				if(Database.users.get(j).getID()==uniqueID) {
					nameAccepted=false;
					break;
				}
				
			}
			i++;
				
		}while(nameAccepted==false);
		
		playlist=new ArrayList<Recording>();
		
	}
	//getters
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getID() {
		return uniqueID;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
