package repositories.interfaces;

import entities.Medicine;

import java.util.List;

public interface IMedicineRepository {
    List<Medicine> getAllMedicine();
    List<Medicine> getByManufacturer(String manufacturer);
    List<Medicine> getExpired();
    boolean createMedicine(Medicine medicine);
    Medicine getByID(int id);
    Medicine getByName(String name);
    boolean removeByID(int id);
    boolean checkOrder(int id);
}