package cn.edu.nju.lzx;

public class StartUp {
	public static void main(String[] args) {
		String input=null;
		String Info=null;
		int cmd=-1;
		CurriculumSchedule cu=new CurriculumSchedule();
		while(true){
		input=cu.inputProcess();
		if(input.equals("Show")){
			cu.show();
		}
		else{
		cmd=cu.judgeCmd(input);
		Info=cu.separateInfo(input);
		if(cmd==3){
			String[] info=Info.split("；");
			cu.find(info[0],info[1]);
		}
		else{
		 Curriculum c=cu.proCuri(Info);
		cu.allocate(cmd,c);
		}
		}
	}

	}
}
