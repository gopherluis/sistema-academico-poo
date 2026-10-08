package org.example.academic.system.service;
import org.example.academic.system.model.User;import org.example.academic.system.security.Role;
public class AuthenticationService{
 public User login(String u,String p){
 if(u.equals("admin")&&p.equals("admin123")) return new User("admin","",Role.ADMIN);
 if(u.equals("prof")&&p.equals("prof123")) return new User("prof","",Role.PROFESSOR);
 throw new RuntimeException("Usuário ou senha inválidos");
 }}