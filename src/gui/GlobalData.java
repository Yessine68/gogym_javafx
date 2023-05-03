/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;

/**
 *
 * @author don7a
 */
public class GlobalData {
    static User currentUser;
    
    
    public static void setCurrentUser(User user) {
    currentUser = user;
}

public static User getCurrentUser() {
    return currentUser;
}
}

