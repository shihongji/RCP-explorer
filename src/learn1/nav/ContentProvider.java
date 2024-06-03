package learn1.nav;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ContentProvider implements ITreeContentProvider {

    @Override
    public void dispose() {
        // Cleanup if necessary
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Respond to input change if necessary
        System.out.println("ContentProvider.inputChanged()...");
        System.out.println("Old input: " + oldInput);
        System.out.println("New input: " + newInput);
    }

    @Override
    public Object[] getElements(Object inputElement) {
        System.out.println("ContentProvider.getElements()...");
        if (inputElement == null) {
            System.out.println("Input element is null");
            return new Object[0];
        }
        System.out.println("Input element class: " + inputElement.getClass().getName());

        if (inputElement instanceof IProject[]) {
            System.out.println("ContentProvider.getElements()...IProject[]");
            return (IProject[]) inputElement;
        }

        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (IProject project : projects) {
            System.out.println("Found project: " + project.getName());
        }

        return projects;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        System.out.println("ContentProvider.getChildren()...");
        if (parentElement instanceof IProject) {
            try {
                return ((IProject) parentElement).members();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (parentElement instanceof IFolder) {
            try {
                return ((IFolder) parentElement).members();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        System.out.println("ContentProvider.getParent()...");
        if (element instanceof IResource) {
            return ((IResource) element).getParent();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        System.out.println("ContentProvider.hasChildren()...");
        if (element instanceof IProject) {
            return true;
        } else if (element instanceof IFolder) {
            try {
                return ((IFolder) element).members().length > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}