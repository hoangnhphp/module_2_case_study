package controller;

import entity.Cloth;
import service.ClothService;

import java.util.List;

public class ClothController {
    private static ClothService clothService = new ClothService();

    public List<Cloth> getAll() {
        return clothService.getAll();
    }

    public List<Cloth> getClothsByGender(char gender) {
        return clothService.getByGender(gender);
    }

    public void add (Cloth cloth) {
        clothService.add(cloth);
    }

    public Cloth getClothById(int id) {
        return clothService.getById(id);
    }

    public List<Cloth> getClothsByName(String name) {
        return clothService.getByName(name);
    }

    public void update(int id, Cloth cloth) {
        clothService.update(id, cloth);
    }

    public void delete(int id) {
        clothService.delete(id);
    }

    public Cloth getLast() {
        return clothService.getLast();
    }
}
