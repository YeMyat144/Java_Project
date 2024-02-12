package com.firsttime_pizza;

public class UserAccount {
    private String id;
	private String username;
	private String password;

public UserAccount(String username, String password) {
    this.username = username;
    this.password = password;
}//have to delete


public UserAccount(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
}

public String getID(){
    return id;
}
public String getUsername() {
    return username;
} 

public String getPassword() {
    return password;
}

}

