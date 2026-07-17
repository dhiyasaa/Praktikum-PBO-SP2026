public class MainApp {

    public static void main(String[] args) {

        // ================= Mobil =================
        Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Manual");

        mobil.tampilkanInfo();
        mobil.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + mobil.jenisBahanBakar());
        mobil.infoKonsumsi();
        mobil.fiturMobile();

        System.out.println();

        // ================= Bus =================
        Bus bus = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, "Eksekutif");

        bus.tampilkanInfo();
        bus.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + bus.jenisBahanBakar());
        bus.infoKonsumsi();
        System.out.println("Kapasitas Penumpang: " + bus.kapasitasPenumpang() + " penumpang");
        bus.fiturBus();

        Bus.JadwalPerjalanan jadwal =
                bus.new JadwalPerjalanan("Jakarta - Bandung", "08:00");

        jadwal.tampilkanJadwal();

        System.out.println();

        // ================= Pesawat =================
        Pesawat pesawat = new Pesawat(
        "Garuda",
        "Boeing 737",
        100,
        "Garuda Indonesia",
        "Domestik"
);

pesawat.tampilkanInfo();
pesawat.nyalakanMesin();
System.out.println("Jenis Bahan Bakar: " + pesawat.jenisBahanBakar());
    }
}