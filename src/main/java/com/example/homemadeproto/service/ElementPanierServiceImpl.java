package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.ElementPanierRepository;
import com.example.homemadeproto.entity.ElementPanier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Element;

@Service
public class ElementPanierServiceImpl implements ElementPanierService {

    private final ElementPanierRepository elementPanierRepository;

    @Autowired
    public ElementPanierServiceImpl(ElementPanierRepository elementPanierRepository) {
        this.elementPanierRepository = elementPanierRepository;
    }

    @Override
    public ElementPanier saveElementPanier(ElementPanier elementPanier) {
        return elementPanierRepository.save(elementPanier);
    }

    public void deleteElementPanier(ElementPanier elementPanier) {
        elementPanierRepository.delete(elementPanier);
    }
}
