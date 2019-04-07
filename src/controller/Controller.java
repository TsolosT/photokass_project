package controller;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.dialog;
import util.initservice;
import util.property;

public class Controller implements Initializable {

	@FXML // bgImage-prodImage ta imageview otan kanei upload, pImg_bgimg ta imageview
			// sthn eikona pou ftiaxneis otan kanei add,middle to idio,logo to logo katw sto
			// footer
	private ImageView bgImage, pImg, prodImage, logo, bgimg, middle;

	@FXML
	private TextArea textArea;
	@FXML
	private Label textImg, header, footer; // textImg labe=main content pou grafeis,header==to label gia to title
											// ,footer=to label gia to mail

	@FXML
	private TextField textFieldHeader, textFieldFooter;

	@FXML
	private Canvas canvas; // to canvas==to middle image
	GraphicsContext gc;
	@FXML
	private AnchorPane picture;// h eikona pou ftiaxneis
	@FXML // ta combobox sta tab gia edit tou font etc ta h=header,m=main,ta f=footer
	private ComboBox comboBox, hfamily, hsize, hstyle, halignment, ffamily, fsize, fstyle, falignment, fpos, mfamily,
			msize, mstyle, malignment, mtextalignment;
	@FXML // ta combobox sto tab gia image edit ,bg=background img,pro=product
			// img,middle=to color tou middle image,outputsize=sto menu
	private ComboBox bgopacity, proopacity, middleopacity, bgblur, problur, middleblur, outputSize, bgNoWhiteblur,
			bgblurShrink;
	@FXML
	private ColorPicker hcolorpicker, fcolorpicker, mcolorpicker, middlecolor;
	@FXML
	private CheckBox hyes, fyes, myes;
	@FXML // to scene pou einai to label k to img tou footer
	private Pane footerlogo;
	@FXML
	private AnchorPane rootpane, aboutpane; // gia ta new page
	private String text;// den 8umame xd

	private static Image img1, img2, img3; // xrhshmopoiounte sto upload image eite bg eite prod
	// ulits compoments
	public initservice iservice;
	public property propertyO;
	public dialog dialogO;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iservice.initHeaderProperties(hfamily, hsize, hstyle, halignment);
		iservice.initFooterProperties(ffamily, fsize, fstyle, falignment, fpos);
		iservice.initMainProperties(mfamily, msize, mstyle, malignment, mtextalignment);
		iservice.initOpa(bgopacity, proopacity, middleopacity);
		iservice.initBlur(bgblur, bgNoWhiteblur, problur, middleblur, bgblurShrink);
		iservice.initSize(outputSize);

		gc = canvas.getGraphicsContext2D();
	}

	// menu actions
	public void InsertBgImage(ActionEvent event) {
		FileChooser fileChooser = iservice.setExtFilter();

		// display open file dialog
		File file = fileChooser.showOpenDialog(null);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			img1 = SwingFXUtils.toFXImage(bufferedImage, null);
			bgImage.setImage(img1);
		} catch (IOException ex) {
			String msg = "we failed to upload background image";
			dialogO.errorMsg(msg);
		}
	}

	public void InsertProdImage(ActionEvent event) {
		FileChooser fileChooser = iservice.setExtFilter();

		// display open file dialog
		File file = fileChooser.showOpenDialog(null);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			img2 = SwingFXUtils.toFXImage(bufferedImage, null);
			prodImage.setImage(img2);
		} catch (IOException ex) {
			String msg = "we failed upload product image";
			dialogO.errorMsg(msg);
		}
	}

	public void Save(ActionEvent event) throws IOException {
		String name;
		int[] imgsize = propertyO.getOutSize(outputSize);
		String path = savetemp();
		Image img = new Image(path, imgsize[0], imgsize[1], false, false);
		FileChooser fileChooser = new FileChooser();
		fileChooser = iservice.setExtFilter();
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);

				dialogO.SaveInfdialog();
			} catch (IOException ex) {
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
				dialogO.unSaveInfdialog();
			}
		}

	}

	public String savetemp() throws IOException {
		WritableImage writableImage = new WritableImage((int) picture.getWidth(), (int) picture.getHeight());
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		picture.snapshot(sp, writableImage);
		File file = new File("\\temp");
		ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
		String path = file.toURI().toString();

		return path;
	}

	// main ui actions
	public void AddBgImage(ActionEvent event) {
		try {
			bgimg.setImage(bgImage.getImage());
		} catch (Exception e) {
			String msg = "we failed  add background image";
			dialogO.errorMsg(msg);
		}
	}

	public void AddProImage(ActionEvent event) {
		try {
			pImg.setImage(prodImage.getImage());
		} catch (Exception e) {
			String msg = "we failed add product image";
			dialogO.errorMsg(msg);
		}

	}

	public void ProdImageRemove(ActionEvent event) {
		Image imgtest = pImg.getImage();
		try {
			if (imgtest == null) {
				// do nothing
			} else {
				pImg.setImage(null);
			}
		} catch (Exception e) {
			String msg = "we failed remove the product image.You can clear all or ...";
			dialogO.errorMsg(msg);
		}

	}

	public void BgImageRemove(ActionEvent event) {
		Image imgtest = bgimg.getImage();
		try {
			if (imgtest == null) {
				// do nothing
			} else {
				bgimg.setImage(null);
			}
		} catch (Exception e) {
			String msg = "we failed remove the background image.You can clear all or...";
			dialogO.errorMsg(msg);
		}

	}

	// tab content actions
	public void AddText(ActionEvent event) {

		try {
			text = textArea.getText();
			textImg.setText(text);
		} catch (Exception e) {
			System.out.println("wtf add text1");
		}
	}

	public void RemoveText(ActionEvent event) {
		if (textImg.getText() == null) {
			// do nothing
		} else {
			textImg.setText("");
		}

	}

	public void AddHeader(ActionEvent event) {

		try {
			String htext = textFieldHeader.getText();
			header.setText(htext);
		} catch (Exception e) {
			System.out.println("wtf add head");
		}
	}

	public void RemoveHeader(ActionEvent event) {
		if (header.getText() == null) {
			// do nothing
		} else {
			header.setText("");
		}

	}

	public void AddFooter(ActionEvent event) {

		try {
			String ftext = textFieldFooter.getText();
			footer.setText(ftext);
		} catch (Exception e) {
			System.out.println("wtf add footer");
		}
	}

	public void RemoveFooter(ActionEvent event) {
		if (footer.getText() == null) {
			// do nothing
		} else {
			footer.setText("");
		}

	}

	public void UploadAddLogo(ActionEvent event) {

		FileChooser fileChooser = iservice.setExtFilter();

		// display open file dialog
		File file = fileChooser.showOpenDialog(null);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			img3 = SwingFXUtils.toFXImage(bufferedImage, null);
			logo.setImage(img3);
		} catch (IOException ex) {
			// todo dialog trya
			System.out.println("problem to upload logo img");
		}
	}

	public void RemoveLogo(ActionEvent event) {
		Image imglogo = logo.getImage();
		if (imglogo == null) {
			// do nothing
		} else {
			logo.setImage(null);
		}
	}

	public void ClearAll(ActionEvent event) {
		boolean check = dialogO.confirm();
		if (check == true) {
			textImg.setText("");
			pImg.setImage(null);
			header.setText("");
			logo.setImage(null);
			footer.setText("");
			bgimg.setImage(null);
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		}

		else {
			// do nothing
		}

	}

	// tab text properties
	// header text properties
	public void setHeadfamily(ActionEvent event) {
		String family = propertyO.GetFamily(hfamily);
		Double size = Double.parseDouble(propertyO.GetSize(hsize));
		String weight = propertyO.GetStyle(hstyle);

		if (weight == null || weight == "normal") {
			header.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.REGULAR, size));
		} else if (weight == "700") {
			header.setFont(Font.font(family, FontWeight.BOLD, FontPosture.REGULAR, size));
		} else if (weight == "italic") {
			header.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.ITALIC, size));
		} else if (weight == "both") {
			header.setFont(Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, size));
		}
	}

	public void setColor(ActionEvent event) {
		Color color;
		if (hcolorpicker.getValue() == null) {
			color = Color.web("#000000");
			header.setTextFill(color);
		} else {
			header.setTextFill(hcolorpicker.getValue());
		}
	}

	public void setAlignment(ActionEvent event) {
		String pos = propertyO.GetAlignment(halignment);

		if (pos == "LEFT") {
			header.setAlignment(Pos.CENTER_LEFT);
		} else if (pos == "RIGHT") {

			header.setAlignment(Pos.CENTER_RIGHT);
			;
		} else {
			header.setAlignment(Pos.CENTER);
		}
	}

	public void setUnderline(ActionEvent event) {

		if (hyes.isSelected()) {
			header.setUnderline(true);
		} else {
			header.setUnderline(false);
		}
	}

	public void setHeadfamilysize(ActionEvent event) {
		String family = propertyO.GetFamily(hfamily);
		Double size = Double.parseDouble(propertyO.GetSize(hsize));
		header.setFont(new Font(family, size));
	}

	// tab footer text properties
	public void setFooterfamily(ActionEvent event) {
		String family = propertyO.GetFamily(ffamily);
		Double size = Double.parseDouble(propertyO.GetSize(fsize));
		String weight = propertyO.GetStyle(fstyle);

		if (weight == null || weight == "normal") {
			footer.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.REGULAR, size));
		} else if (weight == "700") {
			footer.setFont(Font.font(family, FontWeight.BOLD, FontPosture.REGULAR, size));
		} else if (weight == "italic") {
			footer.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.ITALIC, size));
		} else if (weight == "both") {
			footer.setFont(Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, size));
		}
	}

	public void setFooterColor(ActionEvent event) {
		Color color;
		if (fcolorpicker.getValue() == null) {
			color = Color.web("#000000");
			footer.setTextFill(color);
		} else {
			footer.setTextFill(fcolorpicker.getValue());
		}
	}

	public void setfooterfamilysize(ActionEvent event) {
		String family = propertyO.GetFamily(ffamily);
		Double size = Double.parseDouble(propertyO.GetSize(fsize));

		footer.setFont(new Font(family, size));
	}

	public void setFooterAlignment(ActionEvent event) {
		String pos = propertyO.GetAlignment(falignment);

		if (pos == "LEFT") {
			footer.setAlignment(Pos.CENTER_LEFT);
		} else if (pos == "RIGHT") {

			footer.setAlignment(Pos.CENTER_RIGHT);
			;
		} else {
			footer.setAlignment(Pos.CENTER);
		}
	}

	public void setFooterUnderline(ActionEvent event) {

		if (fyes.isSelected()) {
			footer.setUnderline(true);
		} else {
			footer.setUnderline(false);
		}
	}

	public void setFooterPos(ActionEvent event) {
		String pos = propertyO.GetPos(fpos);
		if (pos == "TOP-LEFT") {
			footerlogo.setLayoutX(0);
			footerlogo.setLayoutY(7);
		} else if (pos == "TOP-RIGHT") {
			footerlogo.setLayoutX(585);
			footerlogo.setLayoutY(7);

		} else if (pos == "BOTTOM-LEFT") {
			footerlogo.setLayoutX(0);
			footerlogo.setLayoutY(588);
		} else if (pos == "BOTTOM-RIGHT") {
			footerlogo.setLayoutX(579);
			footerlogo.setLayoutY(588);
		} else if (pos == "BOTTOM-CENTER" || pos == null) {
			footerlogo.setLayoutX(290);
			footerlogo.setLayoutY(588);
		}
	}

	// tab main text properties

	public void setMainfamily(ActionEvent event) {
		String family = propertyO.GetFamily(mfamily);
		Double size = Double.parseDouble(propertyO.GetSize(msize));
		String weight = propertyO.GetStyle(mstyle);

		if (weight == null || weight == "normal") {
			textImg.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.REGULAR, size));
		} else if (weight == "700") {
			textImg.setFont(Font.font(family, FontWeight.BOLD, FontPosture.REGULAR, size));
		} else if (weight == "italic") {
			textImg.setFont(Font.font(family, FontWeight.NORMAL, FontPosture.ITALIC, size));
		} else if (weight == "both") {
			textImg.setFont(Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, size));
		}

	}

	public void setfamilysize(ActionEvent event) {
		String family = propertyO.GetFamily(mfamily);
		Double size = Double.parseDouble(propertyO.GetSize(msize));

		textImg.setFont(new Font(family, size));
	}

	public void setMainColor(ActionEvent event) {
		Color color;
		if (mcolorpicker.getValue() == null) {
			color = Color.web("#000000");
			textImg.setTextFill(color);
		} else {
			textImg.setTextFill(mcolorpicker.getValue());
		}
	}

	public void setMainAlignment(ActionEvent event) {
		String pos = propertyO.GetAlignment(malignment);
		if (pos == "TOP-LEFT") {
			textImg.setAlignment(Pos.TOP_LEFT);
		} else if (pos == "TOP-CENTER") {
			textImg.setAlignment(Pos.TOP_CENTER);
		} else if (pos == "TOP-RIGHT") {
			textImg.setAlignment(Pos.TOP_RIGHT);
		} else if (pos == "CENTER-LEFT") {
			textImg.setAlignment(Pos.CENTER_LEFT);
		} else if (pos == "CENTER-RIGHT") {

			textImg.setAlignment(Pos.CENTER_RIGHT);
			;
		} else if (pos == "CENTER") {
			textImg.setAlignment(Pos.CENTER);
		} else {
			textImg.setAlignment(Pos.TOP_LEFT);
		}
	}

	public void setMainUnderline(ActionEvent event) {

		if (myes.isSelected()) {
			textImg.setUnderline(true);
		} else {
			textImg.setUnderline(false);
		}
	}

	public void setMainTextAlignment(ActionEvent event) {
		if (propertyO.GetPos(mtextalignment) == "CENTER") {
			textImg.setTextAlignment(TextAlignment.CENTER);
		} else if (propertyO.GetPos(mtextalignment) == "JUSTIFY") {
			textImg.setTextAlignment(TextAlignment.JUSTIFY);
		} else if (propertyO.GetPos(mtextalignment) == "LEFT") {
			textImg.setTextAlignment(TextAlignment.LEFT);
		} else if (propertyO.GetPos(mtextalignment) == "RIGHT") {
			textImg.setTextAlignment(TextAlignment.RIGHT);
		} else {
			textImg.setTextAlignment(TextAlignment.JUSTIFY);
		}
	}
	// tab image proccess

	public void setbgOpa(ActionEvent event) {
		Double opa = propertyO.getOpa(bgopacity);
		try {
			bgimg.setOpacity(opa);
		} catch (Exception e) {
			dialogO.errorMsg("with changing the opacity of  the background image");
		}
	}

	public void setProOpa(ActionEvent event) {
		Double opa = propertyO.getOpa(proopacity);
		try {
			pImg.setOpacity(opa);
		} catch (Exception e) {
			dialogO.errorMsg("with changing the opacity of  the product image");
		}
	}

	public void setbgBlur(ActionEvent event) {
		Double num = propertyO.getBlur(bgblur);
		GaussianBlur blur = new GaussianBlur(num);
		try {
			if (num == 0) {
				bgimg.setEffect(blur);
				setOldPosFooter();
			} else {
				bgimg.setEffect(blur);
				setNewPosFooter(1);
			}
		} catch (Exception e) {
			dialogO.errorMsg("with blurring (crop down+right way) the background image");
		}
	}

	public void setbgBlurCenter(ActionEvent Event) {
		try {
			int radius = propertyO.getIntBlur(bgNoWhiteblur);
			if (radius == 0) {
				bgimg.setImage(bgImage.getImage());
				setOldPosHeader();
				setOldPosFooter();
			} else {
				int size = radius * 2 + 1;
				float weight = 1.0f / (size * size);
				float[] data = new float[size * size];

				for (int i = 0; i < data.length; i++) {
					data[i] = weight;
				}
				Kernel kernel = new Kernel(size, size, data);
				ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

				BufferedImage i = op.filter(SwingFXUtils.fromFXImage(bgimg.getImage(), null), null);
				bgimg.setImage(SwingFXUtils.toFXImage(i, null));
				setNewPosHeader(1);
				setNewPosFooter(0);
			}
		} catch (Exception e) {
			dialogO.errorMsg("with blurring(1st way)  the background image");
		}
	}

	public void setbgBlurShrink(ActionEvent Event) {
		try {
			int radius = propertyO.getIntBlur(bgblurShrink);
			if (radius == 0) {
				bgimg.setImage(bgImage.getImage());
				setOldPosFooter();
				setOldPosHeader();
			} else {
				int size = radius * 2 + 1;
				float weight = 1.0f / (size * size);
				float[] data = new float[size * size];

				for (int i = 0; i < data.length; i++) {
					data[i] = weight;
				}
				Kernel kernel = new Kernel(size, size, data);
				ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
				BufferedImage i = op.filter(SwingFXUtils.fromFXImage(bgimg.getImage(), null), null);
				bgimg.setImage(SwingFXUtils.toFXImage(i, null));
				setNewPosFooter(0);
				setNewPosHeader(0);
			}
		} catch (Exception e) {
			dialogO.errorMsg("with blurring(Shrink way)  the background image");
		}
	}

	public void setProBlur(ActionEvent event) {
		Double num = propertyO.getBlur(problur);
		GaussianBlur blur = new GaussianBlur(num);
		try {
			pImg.setEffect(blur);
		} catch (Exception e) {
			dialogO.errorMsg("with blurring the product image");
		}
	}

	// middle img properties
	public void addMiddle(ActionEvent event) {
		Color color;
		try {
			if (middlecolor.getValue() == null) {
				color = Color.BLUE;
			} else {
				color = middlecolor.getValue();
			}
			gc.setFill(color);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		} catch (Exception e) {
			dialogO.errorMsg("with painting  the middle image");
		}

	}

	public void removeMiddle(ActionEvent event) {
		try {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		} catch (Exception e) {
			dialogO.errorMsg("with clearing the middle image");
		}
	}

	public void setMopacity(ActionEvent event) {
		Double opa = propertyO.getOpa(middleopacity);
		try {
			canvas.setOpacity(opa);
		} catch (Exception e) {
			dialogO.errorMsg("with changing the opacity of the middle image");
		}

	}

	public void setMBlur(ActionEvent event) {
		Double num = propertyO.getBlur(middleblur);
		GaussianBlur blur = new GaussianBlur(num);
		try {
			canvas.setEffect(blur);
		} catch (Exception e) {
			dialogO.errorMsg("with blurring the middle image");
		}
	}

	public void setNewPosFooter(int effect) {
		String pos = propertyO.GetPos(fpos);
		if (effect == 1) {
			if (pos == "TOP-RIGHT") {
				footerlogo.setLayoutX(538);
				footerlogo.setLayoutY(34);
			} else if (pos == "BOTTOM-RIGHT") {
				footerlogo.setLayoutX(537);
				footerlogo.setLayoutY(552);
			} else if (pos == "BOTTOM-CENTER") {
				footerlogo.setLayoutX(290);
				footerlogo.setLayoutY(549);
			}
		} else {
			if (pos == "TOP-RIGHT") {
				footerlogo.setLayoutX(556);
				footerlogo.setLayoutY(34);
			} else if (pos == "BOTTOM-RIGHT") {
				footerlogo.setLayoutX(537);
				footerlogo.setLayoutY(572);
			} else if (pos == "BOTTOM-CENTER") {
				footerlogo.setLayoutX(290);
				footerlogo.setLayoutY(569);
			}
		}
	}

	public void setOldPosFooter() {
		String pos = propertyO.GetPos(fpos);
		if (pos == "TOP-RIGHT") {
			footerlogo.setLayoutX(563);
			footerlogo.setLayoutY(16);
		} else if (pos == "BOTTOM-RIGHT") {
			footerlogo.setLayoutX(557);
			footerlogo.setLayoutY(588);
		} else if (pos == "BOTTOM-CENTER") {
			footerlogo.setLayoutX(290);
			footerlogo.setLayoutY(588);
		} else {
			// nothing
		}
	}

	public void setOldPosHeader() {
		int num = propertyO.getIntBlur(bgblurShrink);
		if (num == 0) {
			header.setLayoutX(7);
			header.setLayoutY(7);
		}
	}

	public void setNewPosHeader(int center) {
		int num = propertyO.getIntBlur(bgblurShrink);
		String pos = propertyO.GetAlignment(halignment);
		if (center == 1) {
			if (num != 0) {
				if (pos == "LEFT") {
					header.setLayoutX(30);
					header.setLayoutY(50);
				} else {
					header.setLayoutX(12);
					header.setLayoutY(50);
				}
			}
		} else {
			if (num != 0) {
				if (pos == "LEFT") {
					header.setLayoutX(30);
					header.setLayoutY(30);
				} else {
					header.setLayoutX(12);
					header.setLayoutY(30);
				}
			}
		}
	}

	// next scene methods
	@FXML
	public void displayHelpPage(ActionEvent event) {
		try {
			Parent help = FXMLLoader.load(getClass().getResource("/view/help.fxml"));
			Stage stage = new Stage();
			stage.setTitle("PhotoKass-Help");
			stage.setScene(new Scene(help));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void displayAboutPage(ActionEvent event) throws IOException {
		try {
			Parent about = FXMLLoader.load(getClass().getResource("/view/about.fxml"));
			Stage stage = new Stage();
			stage.setTitle("PhotoKass-About");
			stage.setScene(new Scene(about));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}