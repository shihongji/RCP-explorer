package test.pojo;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ElementFactory {
	public static Element[] getElements(int total) {
		return getElements(total, 1);
	}

	public static Element[] getElements(int total, int second) {
		Element[] elements = new Element[total];
		for (int i = 0; i < total; i++) {
			elements[i] = new Element("Element " + i, "Column 0 " + i, "Column 1 " + i);
			try {
				Thread.sleep(second * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return elements;
	}
	
	public static Element[] getRandomElements(int total) {
		return getRandomElements(total, 1);
	}
	
	// total is the maximum number of elements
	public static Element[] getRandomElements(int total, int second) {
		Random random = new Random();
		Element[] elements = new Element[random.nextInt(total)];
		for (int i = 1; i <= elements.length; i++) {
			elements[i - 1] = new Element("Element " + (int) (Math.random() * 100), "Column 0 " + i, "Column 1 " + i);
			try {
				Thread.sleep(second * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return elements;
	}
	
	public static Image getColumn0Image() {
		return Display.getDefault().getSystemImage(SWT.ICON_INFORMATION);
	}
	
	public static Image getImage() {
		return Display.getDefault().getSystemImage(SWT.ICON_WORKING);
	}

}
