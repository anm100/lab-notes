package ui.utils;

public abstract class Logo {
 private final static String logoUrl="pic.png";
 private final static String logoText = "Clinic Of Doctor Haytham Sheikh Khalil";
 private final static int logoSize = 20;
 private final static String logoFont= "Arial";

 
public static int getLogosize() {
	return logoSize;
}
public static String getLogofont() {
	return logoFont;
}
public static String getLogoUrl() {
	return logoUrl;
}
public static String getLogoText() {
	return logoText;
} 
 
 
 

}
