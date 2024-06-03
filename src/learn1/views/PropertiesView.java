package learn1.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class PropertiesView extends ViewPart {
	public static final String ID = "learn1.views.propertiesView";
	private Label propertiesLabel;

	public PropertiesView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		propertiesLabel = new Label(parent, SWT.NONE);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		propertiesLabel.setFocus();

	}
	
    public void updateProperties(String properties) {
        propertiesLabel.setText(properties);
    }

}
