package model;

import model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.swing.*;

public class LoginLogic {
	
	final String fileName = makeFiles("Regist.txt");
	
	public static String makeFiles(String fileName)
	{
		//FileWriter fw = null;
		// ���݂̃f�B���N�g�������擾
		String path =  System.getProperty("user.dir"); 
		path = new File(path, fileName).getPath();
		
		
		try(FileWriter fw = new FileWriter(fileName, true);)
		{
			fw.close();
		}
		catch(IOException e)
		{
			
		Logger(e);
		}		
		return path;
	}
	
	public boolean execute(User user) {
		List<List<String>> big_list = new ArrayList<List<String>>();
		//JOptionPane.showMessageDialog(null,System.getProperty("user.dir"));
		big_list = FileReader(fileName);
		if(isExist(big_list, user.getName(), user.getPass())) return true;
		
		return false;
	}
	
	
	protected static boolean isExist(List<List<String>> big_list, String name, String pass) {
		for (int i = 0; i < big_list.get(1).size(); i++) {
			if(name.equals(big_list.get(0).get(i)) && pass.equals(big_list.get(1).get(i))) return true;
		}
		return false;
	}
	
	
	protected static List<List<String>> FileReader(String fileName){
		List<List<String>> big_list = new ArrayList<List<String>>();	//��̃��X�g��ۑ�����傫�ȃ��X�g
		List<String> list_1= new ArrayList<String>();	//���[�U�[���̕������i�[���郊�X�g
		List<String> list_2 = new ArrayList<String>();	//�p�X���[�h�̕������i�[���郊�X�g
		String line;
		int k;
		
		
		try( FileReader fileReaderr = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(fileReaderr); ){
			// 1�s�Âǂݍ���
			while ((line = bufferReader.readLine()) != null){
				//�u �F �v�ŕ�������B
				k = line.indexOf(":");
				list_1.add(line.substring(0,k));
				list_2.add(line.substring(k + 1));
			}
			
			//dig_list��list_1,2���i�[����B
			big_list.add(list_1);
			big_list.add(list_2);
			return big_list;

		}
		catch (IOException e){
			Logger(e);
			return null;
		}
	}
	
	
	//���O�o��
		private static void Logger(Exception e)
		{
			String logFileName = "log.txt";
			FileWriter fw = null;
			StackTraceElement[] ste = new Throwable().getStackTrace();
			String path =  System.getProperty("user.dir"); 
			path = new File(path, logFileName).getPath();
			
			//���s���郁�\�b�h���X�^�b�N�ɒ~�ς���Ă����B���̒��̃f�[�^���݂Ăǂ��ŃG���[����������肷��B
			//�X�^�b�N���̍ŐV�̃f�[�^�͂��̃��\�b�h�ilogger���\�b�h�j�Ȃ̂ł��̈�O�̃f�[�^���G���[�������\�b�h�ɂȂ�B
		    System.out.print(ste[1].getClassName() + " / "); // �N���X�����擾
		    System.out.print(ste[1].getMethodName() + " / "); // ���\�b�h�����擾
		    System.out.println(ste[1]); // �X�^�b�N�g���[�X�̏��𐮌`���ĕ\��
		    e.printStackTrace();
		    
		    //���t/�N���X/���\�b�h/���̑��̌`�ŏo�͂���
		    String str = new SimpleDateFormat("yyyy'�N'MM'��'dd'��'E'�j��'k'��'mm'��'ss'�b'").format(new Date()) 
		    				+ "/" + ste[1].getClassName() + "/" + ste[1].getMethodName() + "/" + e + "\n";
		    System.out.println(str);
		    
		    try
		    {
		    	//��������true���ƒǉ��ɂȂ�B
		    	fw = new FileWriter(path,true);
		    	fw.write(str);
		    
		    }
		    catch(IOException e2)
		    {
		    	System.out.println("���O�ŃG���[����");
		    	Logger(e2);	    	
		    }
		    finally
		    {
		    	try
		    	{
		    		if(fw != null)
		    		{
		    			fw.close();
		    		}
		    	}
		    	catch(IOException e3) 
		    	{
		    		Logger(e3);
		    	}
		    	//�G���[�������_�ŋ����I��
		    	System.exit(0);
		    }
		}
		
}
