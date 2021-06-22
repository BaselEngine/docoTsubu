package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistLogic extends LoginLogic{	
	
	public boolean execute(User user) {
		List<List<String>> big_list = new ArrayList<List<String>>();
		
		
		if(!(user.getPass().equals(user.getCheckPass())))return false;
		
		big_list = FileReader(fileName);
		
		if(isExist(big_list, user.getName(), user.getPass())) return false;
		
		Regist(big_list, user.getName(), user.getPass());
		
		return FileWriter(fileName, big_list);
		
	}
	
	
	private static void Regist(List<List<String>> big_list, String name, String pass) {
		big_list.get(0).add(name);
		big_list.get(1).add(pass);
	}
	
	
	protected boolean FileWriter(String fileName, List<List<String>> big_list)	{
		try(FileWriter fw = new FileWriter(fileName,true);){
			for (int i = 0; i < big_list.get(1).size(); i++){
				fw.write(big_list.get(0).get(i) + ":" + big_list.get(1).get(i) + "\n");
			}
			return true;
		}
		catch (IOException e){
			return false;
		}
	}
	
	
}
