package org.example.academic.system.security;
import org.example.academic.system.model.User;
public class Session{private static User u;public static void login(User x){u=x;}public static User getLoggedUser(){return u;}}