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
		// 現在のディレクトリ情報を取得
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
		List<List<String>> big_list = new ArrayList<List<String>>();	//二つのリストを保存する大きなリスト
		List<String> list_1= new ArrayList<String>();	//ユーザー名の部分を格納するリスト
		List<String> list_2 = new ArrayList<String>();	//パスワードの部分を格納するリスト
		String line;
		int k;
		
		
		try( FileReader fileReaderr = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(fileReaderr); ){
			// 1行づつ読み込む
			while ((line = bufferReader.readLine()) != null){
				//「 ： 」で分割する。
				k = line.indexOf(":");
				list_1.add(line.substring(0,k));
				list_2.add(line.substring(k + 1));
			}
			
			//dig_listにlist_1,2を格納する。
			big_list.add(list_1);
			big_list.add(list_2);
			return big_list;

		}
		catch (IOException e){
			Logger(e);
			return null;
		}
	}
	
	
	//ログ出力
		private static void Logger(Exception e)
		{
			String logFileName = "log.txt";
			FileWriter fw = null;
			StackTraceElement[] ste = new Throwable().getStackTrace();
			String path =  System.getProperty("user.dir"); 
			path = new File(path, logFileName).getPath();
			
			//実行するメソッドがスタックに蓄積されていく。その中のデータをみてどこでエラーしたかを特定する。
			//スタック内の最新のデータはこのメソッド（loggerメソッド）なのでその一つ前のデータがエラーしたメソッドになる。
		    System.out.print(ste[1].getClassName() + " / "); // クラス名を取得
		    System.out.print(ste[1].getMethodName() + " / "); // メソッド名を取得
		    System.out.println(ste[1]); // スタックトレースの情報を整形して表示
		    e.printStackTrace();
		    
		    //日付/クラス/メソッド/その他の形で出力する
		    String str = new SimpleDateFormat("yyyy'年'MM'月'dd'日'E'曜日'k'時'mm'分'ss'秒'").format(new Date()) 
		    				+ "/" + ste[1].getClassName() + "/" + ste[1].getMethodName() + "/" + e + "\n";
		    System.out.println(str);
		    
		    try
		    {
		    	//第二引数がtrueだと追加になる。
		    	fw = new FileWriter(path,true);
		    	fw.write(str);
		    
		    }
		    catch(IOException e2)
		    {
		    	System.out.println("ログでエラー発生");
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
		    	//エラーした時点で強制終了
		    	System.exit(0);
		    }
		}
		
}
