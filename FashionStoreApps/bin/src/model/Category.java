package model;

public class Category {

    private String idCategory;
    private String namaCategory;

    public Category() {

    }

    public Category(String idCategory,
            String namaCategory) {

        this.idCategory = idCategory;
        this.namaCategory = namaCategory;

    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNamaCategory() {
        return namaCategory;
    }

    public void setNamaCategory(String namaCategory) {
        this.namaCategory = namaCategory;
    }

}