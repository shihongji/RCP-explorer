package learn1.nav;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.jface.resource.ImageDescriptor;

import java.util.HashMap;
import java.util.Map;

public class NavLabelProvider extends LabelProvider {
    private static final String FILE_ICON_PATH = "icons/file-c.png";
    private static final String FOLDER_ICON_PATH = "icons/folder-c.png";
    private static final String PROJECT_ICON_PATH = "icons/project-c.png";

    private Map<Image, Image> resizedImages = new HashMap<>();
    private ImageDescriptor fileDescriptor;
    private ImageDescriptor folderDescriptor;
    private ImageDescriptor projectDescriptor;

    public NavLabelProvider() {
        fileDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("learn1", FILE_ICON_PATH);
        folderDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("learn1", FOLDER_ICON_PATH);
        projectDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("learn1", PROJECT_ICON_PATH);
    }

    @Override
    public String getText(Object element) {
        if (element instanceof IProject) {
            return ((IProject) element).getName();
        } else if (element instanceof IFolder) {
            return ((IFolder) element).getName();
        } else if (element instanceof IFile) {
            return ((IFile) element).getName();
        }
        return super.getText(element);
    }

    @Override
    public Image getImage(Object element) {
        ImageDescriptor descriptor = null;
        if (element instanceof IFolder) {
            descriptor = folderDescriptor;
        } else if (element instanceof IFile) {
            descriptor = fileDescriptor;
        } else if (element instanceof IProject) {
            descriptor = projectDescriptor;
        }
        if (descriptor != null) {
            Image originalImage = descriptor.createImage();
            Image resizedImage = resizeImage(originalImage, 16, 16); // Resize to 16x16 or any other size
            resizedImages.put(resizedImage, originalImage);
            return resizedImage;
        }
        return null;
    }

    private Image resizeImage(Image image, int width, int height) {
        ImageData imageData = image.getImageData().scaledTo(width, height);
        return new Image(Display.getCurrent(), imageData);
    }

    @Override
    public void dispose() {
        for (Map.Entry<Image, Image> entry : resizedImages.entrySet()) {
            Image resizedImage = entry.getKey();
            Image originalImage = entry.getValue();
            if (resizedImage != null && !resizedImage.isDisposed()) {
                resizedImage.dispose();
            }
            if (originalImage != null && !originalImage.isDisposed()) {
                originalImage.dispose();
            }
        }
        resizedImages.clear();
        super.dispose();
    }
}