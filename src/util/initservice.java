package util;

import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

public class initservice {

	public static void initHeaderProperties(ComboBox fontFamily,ComboBox fontSize,ComboBox fontStyle,ComboBox textAlignemnt) {
		fontFamily.getItems().addAll("Abel","Aclonica","Arial","Georgia","Helvetica"
				,"sans-serif","Courier New","Verdana","Times New Roman","Tahoma","Impact");
		fontSize.getItems().addAll("9","10","11","12","13","14","15","16","17","18","20","22","24"
				,"28","32","34","36","40","44","48");
		fontStyle.getItems().addAll("normal","bold","italic","both");
		textAlignemnt.getItems().addAll("LEFT","CENTER","RIGHT");
	}
	public static void initFooterProperties(ComboBox fontFamily,ComboBox fontSize,ComboBox fontStyle,ComboBox textAlignemnt,ComboBox textPosition) {
		fontFamily.getItems().addAll("Abel","Aclonica","Arial","Georgia","Helvetica"
				,"sans-serif","Courier New","Verdana","Times New Roman","Tahoma","Impact");
		fontSize.getItems().addAll("9","10","11","12","13","14","15","16","17","18","20","22","24"
				,"28","32","34","36","40","44","48");
		fontStyle.getItems().addAll("normal","bold","italic","both");
		textAlignemnt.getItems().addAll("LEFT","CENTER","RIGHT");
		textPosition.getItems().addAll("BOTTOM-LEFT","BOTTOM-RIGHT","BOTTOM-CENTER","TOP-LEFT","TOP-RIGHT");
	}
	public static void initMainProperties(ComboBox fontFamily,ComboBox fontSize,ComboBox fontStyle,ComboBox textAlignemnt,ComboBox mtextAlig) {
		fontFamily.getItems().addAll("Abel","Aclonica","Arial","Georgia","Helvetica"
				,"sans-serif","Courier New","Verdana","Times New Roman","Tahoma","Impact");
		fontSize.getItems().addAll("9","10","11","12","13","14","15","16","17","18","20","22","24"
				,"28","32","34","36","40","44","48");
		fontStyle.getItems().addAll("normal","bold","italic","both");
		textAlignemnt.getItems().addAll("TOP-LEFT","TOP-CENTER","TOP-RIGHT","CENTER-LEFT","CENTER","CENTER-RIGHT");
		mtextAlig.getItems().addAll("CENTER","JUSTIFY","LEFT","RIGHT");
	}

	public static void initOpa(ComboBox bgOpa,ComboBox prodOpa,ComboBox midOpa) {
		bgOpa.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
		prodOpa.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
		midOpa.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
	
	}
	public static void initBlur(ComboBox bgBlur,ComboBox prodBlur,ComboBox midBlur,ComboBox bgNoWBlur,ComboBox bgSBlur) {
		bgBlur.getItems().addAll("0","5","10","15","20","25","30");
		bgNoWBlur.getItems().addAll("0","5","10","15","20","25","30");
		prodBlur.getItems().addAll("0","5","10","15","20","25","30");
		midBlur.getItems().addAll("0","5","10","15","20","25","30");
		bgSBlur.getItems().addAll("0","5","10","15","20","25","30");
	
	}
	public static void initSize(ComboBox imgSize) {
		imgSize.getItems().addAll("1439x1328","1080x1080","1080x1350","1080x566","1200x627","1200x1800","968x545");
	}
	
	public static FileChooser setExtFilter() 
	{	FileChooser fchooser=new FileChooser();	
	//set extension filter
			FileChooser.ExtensionFilter extFilterJPG=new FileChooser.ExtensionFilter("JPG files(*.JPG)","*.JPG");
			FileChooser.ExtensionFilter extFilterjpg=new FileChooser.ExtensionFilter("jpg files(*.jpg)","*.jpg");
			FileChooser.ExtensionFilter extFilterPNG=new FileChooser.ExtensionFilter("PNG files(*.PNG)","*.PNG");
			FileChooser.ExtensionFilter extFilterpng=new FileChooser.ExtensionFilter("png files(*.png)","*.png");
			fchooser.getExtensionFilters().addAll(extFilterJPG,extFilterjpg,extFilterPNG,extFilterpng);	
		return fchooser;
	}
}
