package bText8;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class CustomItem extends JMenuItem{
	int val;
	CustomItem(String text, int x){
		super(text);
		val = x;
	}
	CustomItem(String text){
		super(text);
		val = -1;
	}
}
