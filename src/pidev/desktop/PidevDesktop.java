/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.desktop;

import com.sun.javafx.runtime.VersionInfo;

/**
 *
 * @author MSI
 */
public class PidevDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
System.out.println(("JavaFX Version: " + VersionInfo.getVersion()));
        System.out.println(("JavaFX Runtime Version: " + VersionInfo.getRuntimeVersion()));    }
    
}
