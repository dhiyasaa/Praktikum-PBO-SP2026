public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {

    private String maskapai;
    private String jenisPenerbangan;

    public Pesawat(String merk, String model, int tahunProduksi,
                   String maskapai, String jenisPenerbangan) {
        super(merk, model, tahunProduksi);
        this.maskapai = maskapai;
        this.jenisPenerbangan = jenisPenerbangan;
    }

    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Bersiap lepas landas");
    }

    public String jenisBahanBakar() {
        return "Avtur";
    }


    public String namaMaskapai() {
        return maskapai;
    }


    public String jenisPenerbangan() {
        return jenisPenerbangan;
    }

    public void fiturPesawat() {
        System.out.println("Maskapai: " + namaMaskapai());
        System.out.println("Jenis Penerbangan: " + jenisPenerbangan());
    }
}