package entities;

public class Medicine {
    Integer medicinId;
    String shortName;
    String description;
    Double price;

    public Medicine(Integer medicinId, String shortName, String description, Double price) {
        this.medicinId = medicinId;
        this.shortName = shortName;
        this.description = description;
        this.price = price;
    }

    public Integer getMedicinId() {
        return medicinId;
    }

    public void setMedicinId(Integer medicinId) {
        this.medicinId = medicinId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
