package model;

public abstract class Product implements Sellable {

    private String idProduct;
    private String namaProduct;
    private String category;
    private String brand;
    private double harga;
    private int stok;

    public Product() {

    }

    public Product(String idProduct, String namaProduct,
            String category, String brand,
            double harga, int stok) {

        this.idProduct = idProduct;
        this.namaProduct = namaProduct;
        this.category = category;
        this.brand = brand;
        this.harga = harga;
        this.stok = stok;

    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

}