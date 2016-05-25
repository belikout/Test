package cn.edu.nju.lzx;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class CurriculumSchedule {
	static String FileName="/Users/ClaraLee/Desktop/CurriculumSchedule.txt";
	static ArrayList<Curriculum> l=new ArrayList<Curriculum>();  
	
 Curriculum proCuri(String Info) {
	String[] why=Info.split("；");
	 Curriculum c=new Curriculum(why[0],why[1],why[2],why[3]);
	return c;
}
  void allocate(int cmd, Curriculum c) {
	// TODO Auto-generated method stub
	switch(cmd){
	case 0: add(c);
	break;
	case 1: remove(c);
	break;
	case 2: update(c);
	}
}
 String separateInfo(String input) {
	String[] split = input.split(" ");	
	String Info=split[1];
	// TODO Auto-generated method stub
	return Info;
}
 int judgeCmd(String input) {
	// TODO Auto-generated method stub
	String command;
	int cmd = -1;
	String[] split = input.split(" ");	
	command = split[0];
	
	String[] cmds = {"Add","Remove","Update","Find","Show"};
	for(int i=0;i<cmds.length;i++){
		if(command.equals(cmds[i])==true){
			cmd = i; 
			break;
		}
	}
	return cmd;
}
 String inputProcess() {
	// TODO Auto-generated method stub
	String input = null;
	BufferedReader br;
	System.out.println("请输入你想进行的课程操作：");
	try {
		br = new BufferedReader(new InputStreamReader(System.in));
		input=br.readLine();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	return input;

} 
 
  boolean judgeExist(Curriculum c){
	 boolean k=false;
	 String date=c.date;
	 String time=c.time;

	 try{
			BufferedReader br=new BufferedReader(new FileReader(FileName));
			String line;
			while((line=br.readLine())!=null){
				String date0;
				String time0;
				String[] split = line.split("；");
				
				date0 = split[0];
				time0 = split[1];
				if(date.equals(date0)&&time.equals(time0)) {
					k = true;
					break;
				}
			}
			br.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}

	 
	 return k;
 }
  void show() {
	// TODO Auto-generated method stub
	 ArrayList<Curriculum> list=new ArrayList<Curriculum>();
	 try{
			BufferedReader br=new BufferedReader(new FileReader(FileName));
			String line;
			while((line=br.readLine())!=null){
				String[] split = line.split("；");
				Curriculum c=new Curriculum(split[0],split[1],split[2],split[3]);
				list.add(c);
			}
			br.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	 Mycomparator comparator=new Mycomparator();
     Collections.sort(list, comparator);
     for(int i=0;i<list.size();i++){
    	 System.out.println(CurriculumToString(list.get(i)));
     }
}
{
	// TODO Auto-generated method stub
	
}
  void find(String date,String time) {
	// TODO Auto-generated method stub
	 Curriculum c=new Curriculum(date,time,null,null);
	 boolean k=judgeExist(c);
	 if(k){
		 try{
				BufferedReader br=new BufferedReader(new FileReader(FileName));
				String line;
				while((line=br.readLine())!=null){
					String date0;
					String time0;
					String[] split = line.split("；");
					
					date0 = split[0];
					time0 = split[1];
					if(date.equals(date0)&&time.equals(time0)) {
						c.name=split[2];
						c.location=split[3];
						break;
					}
				}
			
				br.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		 System.out.println("成功查到课程！");
		 System.out.println(CurriculumToString(c));
	 }
	 
	 else{
		 System.out.println("该时间无课程！");
	 }
	 }

  void update(Curriculum c0) {
	// TODO Auto-generated method stub
	boolean k=judgeExist(c0);
	String date=c0.date;
	String time=c0.time;
	Curriculum c=new Curriculum(null,null,null,null);
	if(k){
		try{
			BufferedReader br=new BufferedReader(new FileReader(FileName));
			String line;
			while((line=br.readLine())!=null){
				String date0;
				String time0;
				String[] split = line.split("；");
				
				date0 = split[0];
				time0 = split[1];
				if(date.equals(date0)&&time.equals(time0)) {
					c.date=split[0];
					c.time=split[1];
					c.name=split[2];
					c.location=split[3];
					break;
				}
			}
		
			br.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		remove(c);
		add(c0);
	}
	else{
		System.out.println("该时间无课！");
		add(c0);
	}
}
 
  void add(Curriculum c0) {
	// TODO Auto-generated method stub
	boolean k=judgeExist(c0);
	read();
	if(k){
		System.out.println("该时间段有课，不可添加课程！");
	}
	else{
		l.add(c0);
		write();
		System.out.println("成功添加课程！");
	}
}
  void remove(Curriculum c0) {
		// TODO Auto-generated method stub
		 read();
			boolean k=false;
			for(int i=0;i<l.size();i++){
				if(CurriculumToString(c0).equals(CurriculumToString(l.get(i)))){
			l.remove(i);
					k=true;
					break;
				}
			}
			write();
			if(k){
				System.out.println("已从文件中删除该课程");
				}
						
			else{
				System.out.println("文件中不包括所删课程！");
			}
			}
  void read(){
		l.clear();
		try{
			File file=new File(FileName);
			FileReader fileReader=new FileReader(file);
			BufferedReader reader=new BufferedReader(fileReader);
			String str=null;
			while ((str=reader.readLine())!=null){
				if(str!=" "){
				String[] cu=str.split("；");	
				Curriculum c=new Curriculum(cu[0],cu[1],cu[2],cu[3]);
				l.add(c);
				}
			}
			reader.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
void write(){
	 try{
			FileWriter writer=new FileWriter(FileName);
			for(int i=0;i<l.size();i++){
				writer.write(CurriculumToString(l.get(i))+"\r\n");
			}
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();}
	}
String CurriculumToString(Curriculum c){
		return c.date+"；"+c.time+"；"+c.name+"；"+c.location+"；";
	}
	 
}
