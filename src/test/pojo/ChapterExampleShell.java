package test.pojo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ChapterExampleShell extends Shell {
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			ChapterExampleShell shell = new ChapterExampleShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChapterExampleShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		ChapterExampleComposite composite = new ChapterExampleComposite(this, SWT.NONE);
		composite.setChapterExample(ChapterExampleFactory.createChapterExamples(composite.getContentComposite()));
		createContents();
	}
	
	protected void createContents() {
		setText("RCP Application UI Example");
		setSize(800, 600);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	@Override
	public void dispose() {
		super.dispose();
		TrayItemComposite.trayItem.dispose();
	}
}
