package learn1.nav;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.OpenFileAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.PartInitException;
import org.eclipse.jface.action.Separator;

public class NavigatorActionProvider extends CommonActionProvider {

    private IWorkbenchWindow window;
    private OpenFileCustomAction openFileAction;



    @Override
    public void init(ICommonActionExtensionSite anExtensionSite) {
        super.init(anExtensionSite);
        if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
        	window = ((ICommonViewerWorkbenchSite) anExtensionSite.getViewSite()).getWorkbenchWindow();
            openFileAction = new OpenFileCustomAction(window, null); // Pass null, we'll set the file later
        }
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
    	ISelection selection = getContext().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();
         // Add the default resource actions
            menu.add(new Separator("group.open"));
            menu.appendToGroup("group.open", ActionFactory.ABOUT.create(window));
            menu.appendToGroup("group.open", ActionFactory.DELETE.create(window));
            menu.appendToGroup("group.open", ActionFactory.RENAME.create(window));
            menu.add(new Separator("group.new"));
            menu.appendToGroup("group.new", ActionFactory.NEW.create(window));
            menu.appendToGroup("group.new", ActionFactory.SAVE.create(window));
            menu.appendToGroup("group.new", ActionFactory.SAVE_AS.create(window));
            menu.appendToGroup("group.new", ActionFactory.SAVE_ALL.create(window));
            menu.add(new Separator("group.edit"));
            menu.appendToGroup("group.edit", ActionFactory.COPY.create(window));
            menu.appendToGroup("group.edit", ActionFactory.PASTE.create(window));
            menu.appendToGroup("group.edit", ActionFactory.CUT.create(window));
            menu.appendToGroup("group.edit", ActionFactory.SELECT_ALL.create(window));
            menu.appendToGroup("group.edit", ActionFactory.FIND.create(window));
            menu.appendToGroup("group.edit", ActionFactory.REFRESH.create(window));
            menu.add(new Separator("group.navigate"));
            menu.appendToGroup("group.navigate", ActionFactory.BACK.create(window));
            menu.appendToGroup("group.navigate", ActionFactory.FORWARD.create(window));
            menu.appendToGroup("group.navigate", ActionFactory.UP.create(window));

            // Add the custom "Open" action
            if (firstElement instanceof IFile) {
                openFileAction.setFile((IFile) firstElement);
                menu.appendToGroup("group.open", openFileAction);
            }
        }
    }

    @Override
    public void setContext(ActionContext context) {
        super.setContext(context);
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        super.fillActionBars(actionBars);
    }

    @Override
    public void updateActionBars() {
        super.updateActionBars();
    }
}