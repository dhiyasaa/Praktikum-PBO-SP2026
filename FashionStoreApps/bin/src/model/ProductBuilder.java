package model;

public class ProductBuilder {

    private String idProduct;
    private String namaProduct;
    private String category;
    private String brand;
    private double harga;
    private int stok;
    private String ukuran;
    private String warna;

    public ProductBuilder setIdProduct(String idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public ProductBuilder setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
        return this;
    }

    public ProductBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder setHarga(double harga) {
        this.harga = harga;
        return this;
    }

    public ProductBuilder setStok(int stok) {
        this.stok = stok;
        return this;
    }

    public ProductBuilder setUkuran(String ukuran) {
        this.ukuran = ukuran;
        return this;
    }

    public ProductBuilder setWarna(String warna) {
        this.warna = warna;
        return this;
    }

    public Clothing build() {

        return new Clothing(
                idProduct,
                namaProduct,
                category,
                brand,
                harga,
                stok,
                ukuran,
                warna
        );

    }

}