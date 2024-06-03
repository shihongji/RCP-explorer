package learn1;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		System.out.println("Perspective.createInitialLayout()...");
		layout.setEditorAreaVisible(true);
		String editorArea = layout.getEditorArea();
//		layout.setFixed(true);
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.3f, editorArea);
		left.addView("learn1.views.navigator");
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, editorArea);
        bottom.addView("learn1.views.propertiesView");

	}

}
