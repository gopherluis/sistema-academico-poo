package org.example.academic.system.model;

import java.util.ArrayList;
import java.util.List;

public class AcademicSystem {
    private static AcademicSystem instance;
    private final List<AcademicClass> classes=new ArrayList<>();
    private AcademicSystem(){}
    public static AcademicSystem getInstance(){
        if(instance==null) instance=new AcademicSystem();
        return instance;
    }
    public List<AcademicClass> getClasses(){ return classes; }
}
