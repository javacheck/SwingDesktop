package com.tool;

import com.Library;

import java.net.URL;
import javax.swing.ImageIcon;

public class CreatecdIcon {
    public static ImageIcon add(String ImageName){
        URL IconUrl = Library.class.getResource("/res/"+ImageName);
        ImageIcon icon=new ImageIcon(IconUrl);
        return icon;
    }
}