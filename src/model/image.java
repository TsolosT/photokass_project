package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;

public class image {

private BufferedImage buffImg;
private   Image img;


public image(BufferedImage buffImg, Image img1) {
	this.buffImg = buffImg;
	this.img = img1;
}
public BufferedImage getBuffImg() {
	return buffImg;
}
public void setBuffImg(BufferedImage buffImg) {
	this.buffImg = buffImg;
}
public void setBuffImg(File file) throws IOException {
	this.buffImg = ImageIO.read(file);
}
public Image getImg() {
	return img;
}
public void setImg(Image img1) {
	this.img = img1;
}
public void setAll(BufferedImage buffImg,Image img)
{
	this.setImg(img);
	this.setBuffImg(buffImg);
}


}


