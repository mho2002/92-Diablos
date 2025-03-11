package com.example.service;


import com.example.repository.MainRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public abstract class MainService<T> {

    private final MainRepository<T> mainRepository;

    public MainService(MainRepository<T> mainRepository) {
        this.mainRepository = mainRepository;
    }

    protected MainRepository<T> getMainRepository() {
        return this.mainRepository;
    }

    
    
}
