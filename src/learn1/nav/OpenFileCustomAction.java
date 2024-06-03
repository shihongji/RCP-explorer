package learn1.nav;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

public class OpenFileCustomAction extends Action {

    private final IWorkbenchWindow window;
    private IFile file;

    public OpenFileCustomAction(IWorkbenchWindow window, IFile file) {
        this.window = window;
        this.file = file;
        setText("Open");
    }
    public OpenFileCustomAction(IWorkbenchWindow window) {
        this.window = window;
		this.file = null;
        setText("Open");
    }

    @Override
    public void run() {
        try {
            IDE.openEditor(window.getActivePage(), new FileEditorInput(file), "learn1.editor");
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    public void setFile(IFile file) {
        this.file = file;
    }
}