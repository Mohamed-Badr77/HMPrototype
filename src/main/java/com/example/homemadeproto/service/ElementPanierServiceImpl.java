package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.ElementPanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementPanierServiceImpl implements ElementPanierService {

    private final ElementPanierRepository elementPanierRepository;

    @Autowired
    public ElementPanierServiceImpl(ElementPanierRepository elementPanierRepository) {
        this.elementPanierRepository = elementPanierRepository;
    }
}
