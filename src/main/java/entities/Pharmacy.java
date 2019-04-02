package entities;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    Integer id;
    String pharmacyName;
    String adress;
    String phone;
    Double total;
    List<Medicine> medicines;
    public Pharmacy(Integer id, String pharmacyName, String adress, String phone, Double total) {
        this.id = id;
        this.pharmacyName = pharmacyName;
        this.adress = adress;
        this.phone = phone;
        this.total = total;
        medicines = new ArrayList<>();
    }
    public void addMedicine(Medicine md){
        medicines.add(md);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotal() {
        return total;
    }
    public List<Medicine> getListOfMedicines(){
        return medicines;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}
