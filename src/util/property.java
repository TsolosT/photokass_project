package util;

import javafx.scene.control.ComboBox;

public class property {

	public static String GetFamily(ComboBox x) {
		String choice;
		if(x.getValue()==null)
		{
			choice="Arial";
		}
		else {
			choice=(String)x.getValue();
		}
			
		return choice;		
	}
	public static String GetSize(ComboBox x) {
		String choice;
	
		if(x.getValue()==null) {
			choice="14";
		}
		else {
			choice=(String)x.getValue();
		}
				
		return choice;		
	}
	public static String GetAlignment(ComboBox x) {
		String choice;
		
		choice=(String)x.getValue();
		return choice;		
		
	}
	public static String GetPos(ComboBox x) {
		String choice;
		
		choice=(String)x.getValue();
		return choice;		
		
	}
	public static String GetStyle(ComboBox x) {
		String choice=null;
		
		choice=(String)x.getValue();
		
		if(choice.contentEquals("bold"))
		{
			choice="700";
		}
		else if(choice.contentEquals("italic")){
			choice="italic";
		}
		else if(choice.contentEquals("both")){
			choice="both";
		}
		else if(choice=="normal"||choice==null)
		{				
			choice="normal";
		}
	
		return choice;		
	}
	
	public static Double getOpa(ComboBox x) {
		Double opa;
		
		opa=Double.parseDouble((String)x.getValue());
		
		return opa;
	}
	public static Double getBlur(ComboBox x)
	{
		Double opa;
		
		opa=Double.parseDouble((String)x.getValue());
		
		return opa;
	}
	public static int getIntBlur(ComboBox x) {
		int blur;
		blur=Integer.parseInt((String)x.getValue());
		return blur;
	}
	public static int[] getOutSize(ComboBox x) {
		int[] size=new int[2];
			
		if(x.getValue()=="1439x1328") {
			size[0]=1439;
			size[1]=1328;
		}
		else if(x.getValue()=="1080x1080")
		{
			size[0]=1080;
			size[1]=1080;
		}
		else if(x.getValue()=="1080x1350")
		{
			size[0]=1080;
			size[1]=1350;
		}else if(x.getValue()=="1080x566")
		{
			size[0]=1080;
			size[1]=566;
		}
		else if(x.getValue()=="1200x627")
		{
			size[0]=1200;
			size[1]=627;
		}
		else if(x.getValue()=="1200x1800")
		{
			size[0]=1200;
			size[1]=1800;
		}else if(x.getValue()=="968x545")
		{
			size[0]=968;
			size[1]=545;
		}
		else {
			size[0]=1439;
			size[1]=1328;
		}
	
		
		return  size;
	}
	
}
