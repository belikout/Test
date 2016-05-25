package cn.edu.nju.lzx;

import java.util.Comparator;

public class Mycomparator implements Comparator<Curriculum> {
	    public int compare(Curriculum c,Curriculum c0) {
	    	int i,j,x,y;
	    	String[] weekday={"星期一","星期二","星期三","星期四","星期五"};
	    	String[] time={"一，二节","三，四节","五，六节","七，八节","九，十节"};
	    	for( i=0;i<weekday.length;i++){
	    		if(weekday[i].equals(c.date)){
	    			break;
	    		}
	    	}
	    	for( j=0;j<weekday.length;j++){
	    		if(weekday[j].equals(c0.date)){
	    			break;
	    		}
	    	}
	    	for( x=0;x<time.length;x++){
	    		if(time[x].equals(c.time)){
	    			break;
	    		}
	    	}
	    	for( y=0;y<time.length;y++){
	    		if(time[y].equals(c0.time)){
	    			break;
	    		}
	    	}
	  if(i<j||(i==j&&x<y)){
		  return -1;
	  }
	  else{
		  return 1;
	  }
	    }
	}

