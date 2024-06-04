package learn1.book;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.custom.CBanner;

/**
 * This class shows the examples of composites in SWT.
 */
public class TestCompositeView extends ViewPart {
	public TestCompositeView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("lb1");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("lb2");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(0, 0, 59, 14);
		lblNewLabel_2.setText("New Label");
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(0, 0, 59, 14);
		lblNewLabel_3.setText("New Label");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		GridData gd_viewForm = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_viewForm.widthHint = 189;
		gd_viewForm.heightHint = 166;
		viewForm.setLayoutData(gd_viewForm);
		
		Label lblTopleft = new Label(viewForm, SWT.NONE);
		viewForm.setTopLeft(lblTopleft);
		lblTopleft.setText("topLeft");
		
		Label lblTopcenter = new Label(viewForm, SWT.NONE);
		viewForm.setTopCenter(lblTopcenter);
		lblTopcenter.setText("topCenter");
		
		Label lblImTheContent = new Label(viewForm, SWT.NONE);
		viewForm.setContent(lblImTheContent);
		lblImTheContent.setText("I'm the content");
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_tabFolder.widthHint = 376;
		tabFolder.setLayoutData(gd_tabFolder);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("New Item");
		
		CBanner banner = new CBanner(parent, SWT.NONE);
		
		CTabFolder tabFolder_1 = new CTabFolder(parent, SWT.BORDER);
		GridData gd_tabFolder_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_tabFolder_1.widthHint = 279;
		tabFolder_1.setLayoutData(gd_tabFolder_1);
		tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmNewItem_3 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_3.setText("New Item");
		
		CTabItem tbtmNewItem_4 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_4.setText("New Item");
		new Label(parent, SWT.NONE);
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
