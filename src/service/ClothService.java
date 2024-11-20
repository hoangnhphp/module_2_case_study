package service;

import entity.Cloth;
import repository.impl.ClothRepository;

import java.util.ArrayList;
import java.util.List;

public class ClothService {
    private static ClothRepository clothRepository = new ClothRepository();

    public List<Cloth> getAll() {
        return clothRepository.getAll();
    }

    public List<Cloth> getByGender(char gender) {
        List<Cloth> cloths = getAll();
        List<Cloth> result = new ArrayList<>();
        for (Cloth cloth : cloths) {
            if (cloth.getGenderType() == gender) {
                result.add(cloth);
            }
        }

        return result;
    }

    public void add(Cloth cloth) {
        clothRepository.save(cloth);
    }

    public Cloth getById(int id) {
        return clothRepository.findById(id);
    }

    public List<Cloth> getByName(String name) {
        return clothRepository.getClothByName(name);
    }

    public void update(int id, Cloth cloth) {
        clothRepository.update(id, cloth);
    }

    public void delete(int id) {
        clothRepository.delete(id);
    }

    public Cloth getLast() {
        return clothRepository.getLast();
    }
}
