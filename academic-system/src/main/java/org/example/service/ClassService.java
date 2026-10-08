package org.example.service;

import org.example.persistence.ClassRepository;

public class ClassService {

    private ClassRepository repository;

    public ClassService() {
        repository = new ClassRepository();
    }

    public boolean registerClass(String name) {

        if (name == null || name.isBlank()) {
            return false;
        }

        return repository.createClass(name);

    }

    public void listClasses() {

        repository.listClasses();

    }

}