package model;

import java.io.Serializable;

public class User implements Serializable{
		private String name;
		private String pass;
		private String checkPass;
		
		public User() {}
		public User(String name, String pass) {
			this.name = name;
			this.pass = pass;
		}
		public User(String name, String pass, String checkPass) {
			this.name = name;
			this.pass = pass;
			this.checkPass = checkPass;
		}
		
		
		public String getName() {return name;}
		public String getPass() {return pass;}
		public String getCheckPass() {return checkPass;}
	}


