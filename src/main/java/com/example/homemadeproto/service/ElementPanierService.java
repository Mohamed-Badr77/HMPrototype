package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.ElementPanier;

public interface ElementPanierService {
    ElementPanier saveElementPanier(ElementPanier Ep);

    void deleteElementPanier(ElementPanier toDecrement);
}
