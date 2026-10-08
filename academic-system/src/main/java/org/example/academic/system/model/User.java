package org.example.academic.system.model;
import org.example.academic.system.security.Role;
public class User{private String username,password;private Role role;
public User(String u,String p,Role r){username=u;password=p;role=r;}
public String getUsername(){return username;} public Role getRole(){return role;}}