package learn1.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class FirstEditor extends EditorPart {
	private Text text;
	private IFile file;
	private static final String ID = "learn1.editor";
	private SourceViewer viewer;

	public FirstEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
		setInput(input);
		// Retrieve the file from the editor input
        if (input instanceof FileEditorInput) {
            this.file = ((FileEditorInput) input).getFile();
        } else if (input instanceof EditorInput) {
            this.file = ((EditorInput) input).getFile();
        }
        setPartName(file.getName());

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		viewer = new SourceViewer(parent, null, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        try {
			viewer.setDocument(new Document(loadFileContent(file)));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
    public void displayFileContent(IFile file) throws CoreException {
        viewer.getDocument().set(loadFileContent(file));
    }
    
	private String loadFileContent(IFile file) throws CoreException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getContents()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
