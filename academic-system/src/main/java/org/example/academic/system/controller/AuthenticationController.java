package org.example.academic.system.controller;
import org.example.academic.system.security.Session;import org.example.academic.system.service.AuthenticationService;
public class AuthenticationController{
AuthenticationService s=new AuthenticationService();
public void login(String u,String p){Session.login(s.login(u,p));}
}