package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.PlatRepository;
import com.example.homemadeproto.entity.Plat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatServiceImpl implements PlatService {

    private final PlatRepository platRepository;

    public PlatServiceImpl(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    @Override
    public List<Plat> getAllDishes() {
        return platRepository.findAll();
    }

    @Override
    public Optional<Plat> getDishById(Long id) {
        return platRepository.findById(id);
    }

    @Override
    public Plat saveDish(Plat plat) {
        return platRepository.save(plat);
    }

    @Override
    public void deleteDish(Long id) {
        platRepository.deleteById(id);
    }
}
