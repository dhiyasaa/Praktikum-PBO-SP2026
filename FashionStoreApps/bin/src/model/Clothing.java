package model;

public class Clothing extends Product {

    private String ukuran;
    private String warna;

    public Clothing() {

    }

    public Clothing(String idProduct,
            String namaProduct,
            String category,
            String brand,
            double harga,
            int stok,
            String ukuran,
            String warna) {

        super(idProduct,
                namaProduct,
                category,
                brand,
                harga,
                stok);

        this.ukuran = ukuran;
        this.warna = warna;

    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    @Override
    public String getInfo() {

        return getNamaProduct()
                + " | "
                + ukuran
                + " | "
                + warna;

    }

}