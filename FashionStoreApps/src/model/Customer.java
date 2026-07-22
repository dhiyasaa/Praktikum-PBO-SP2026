package model;

public class Customer {

    private String idCustomer;
    private String nama;
    private String email;
    private String alamat;
    private String noHp;

    public Customer(String idCustomer,
            String nama,
            String email,
            String alamat,
            String noHp) {

        this.idCustomer = idCustomer;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.noHp = noHp;

    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHp() {
        return noHp;
    }

}