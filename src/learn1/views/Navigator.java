package learn1.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

import learn1.views.PropertiesView;
import learn1.editors.EditorInput;
import learn1.editors.FirstEditor;

public class Navigator extends CommonNavigator {
	@Override
	protected CommonViewer createCommonViewer(Composite parent) {
		CommonViewer viewer = super.createCommonViewer(parent);
		// Customize the viewer if necessary
		viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());

		// Add selectionChangeListener
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Object firstElement = selection.getFirstElement();
				if (firstElement instanceof IFile) {
					IFile selectedFile = (IFile) firstElement;
					showProperties(selectedFile);
				} else if (firstElement instanceof IFolder) {
					IFolder selectedFolder = (IFolder) firstElement;
					showProperties(selectedFolder);
				} else if (firstElement instanceof IProject) {
					IProject selectedProject = (IProject) firstElement;
					showProperties(selectedProject);
				}
			}
		});
		// Add double click listener
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Object firstElement = selection.getFirstElement();
				if (firstElement instanceof IFile) {
					IFile selectedFile = (IFile) firstElement;
					openEditor(selectedFile);
//					try {
//						showFileContent(selectedFile);
//					} catch (CoreException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		});
		return viewer;
	}

	@Override
	protected Object getInitialInput() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	private void showProperties(IResource resource) {
		String properties = getProperties(resource);
		// Display properties in a dialog or a specific part of your UI
		// For simplicity, we will just print to the console
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			try {
				PropertiesView propertiesView = (PropertiesView) page.showView(PropertiesView.ID);
				if (propertiesView != null) {
					propertiesView.updateProperties(properties);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(properties);
	}

	private String getProperties(IResource resource) {
		StringBuilder properties = new StringBuilder();
		properties.append("Name: ").append(resource.getName()).append("\n");
		properties.append("Path: ").append(resource.getFullPath()).append("\n");
		properties.append("Type: ").append(getResourceType(resource)).append("\n");

		return properties.toString();
	}

	private String getResourceType(IResource resource) {
		if (resource instanceof IFile) {
			return "File";
		} else if (resource instanceof IFolder) {
			return "Folder";
		} else if (resource instanceof IProject) {
			return "Project";
		}
		return "Unknown";
	}

	private void showFileContent(IFile file) throws CoreException {
		FirstEditor view = (FirstEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView("learn1.editor");
		if (view != null) {
			view.displayFileContent(file);
		}
	}

	private void openEditor(IFile file) {
		try {
			EditorInput editorInput = new EditorInput(file);
			IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
			String editorID = desc == null ? "learn1.editor" : desc.getId();
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, editorID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
