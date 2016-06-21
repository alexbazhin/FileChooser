package filepanel;

import javax.swing.*;


public class FileListModel {

    private ImageIcon icon;
    private String value;

    public FileListModel(String value, ImageIcon icon) {
        this.value = value;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public ImageIcon getIcon() {
        return icon;
    }

}
