package repository.impl;

import entity.Cloth;
import repository.IClothRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClothRepository implements IClothRepository {
    public static final String SRC_CLOTH = "src/data/cloths.dat";

    public List<Cloth> getAll() {
        List<Cloth> cloths = new ArrayList<>();
        cloths = readFileBinary(cloths);
        return cloths;
    }

    public void save(Cloth s) {
        List<Cloth> cloths = getAll();
        cloths.add(s);
        writeFileBinary(cloths);
    }

    public void writeFileBinary(List<Cloth> cloths) {
        File file = new File(SRC_CLOTH);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(cloths);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Looix khong tim thay file");
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }
    }

    public List<Cloth> readFileBinary(List<Cloth> cloths) {
        File file = new File(SRC_CLOTH);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            cloths = (List<Cloth>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi ko tim thay file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Loi khong tim thay class");
        }
        return cloths;
    }

    public Cloth findById(int id) {
        List<Cloth> cloths = getAll();
        for (Cloth cloth : cloths) {
            if (cloth.getId() == id) {
                return cloth;
            }
        }

        return null;
    }

    public List<Cloth> getClothByName(String name) {
        List<Cloth> cloths = getAll();
        List<Cloth> clothList = new ArrayList<>();
        String searchName = name.toLowerCase();
        for (Cloth cloth : cloths) {
            if (cloth.getName().toLowerCase().contains(searchName)) {
                clothList.add(cloth);
            }
        }

        return clothList;
    }

    public void update(int id, Cloth cloth) {
        List<Cloth> items = getAll();
        Cloth old = findById(id);
        int index = items.indexOf(old);
        items.set(index, cloth);
        writeFileBinary(items);
    }

    public void delete(int id) {
        List<Cloth> items = getAll();
        items.removeIf(cloth -> cloth.getId() == id);
        writeFileBinary(items);
    }

    public Cloth getLast() {
        List<Cloth> items = getAll();
        return items.isEmpty() ? null : items.get(items.size() - 1);
    }
}
