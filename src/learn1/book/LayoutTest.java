package learn1.book;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

public class LayoutTest extends Composite {

	public LayoutTest(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(2, false));
        
        Composite composite = new Composite(this, SWT.NONE);
        
        Label lblNewLabel = new Label(composite, SWT.NONE);
        lblNewLabel.setBounds(19, 61, 42, 17);
        lblNewLabel.setText("New Label");
        
        Composite composite_1 = new Composite(this, SWT.NONE);
        
        Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
        lblNewLabel_1.setBounds(19, 61, 42, 17);
        lblNewLabel_1.setText("New Label");
        
        Composite composite_2 = new Composite(this, SWT.NONE);
        
        Composite composite_3 = new Composite(this, SWT.NONE);
    }
}
