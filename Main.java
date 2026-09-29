
public class Main {
    private static int nomorTransaksi = 0;

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addCustomer("Rendy", "Wahyu");
        bank.addCustomer("Ayu", "Aprilia");

        Customer rendy = bank.getCustomer(0);
        Customer ayu = bank.getCustomer(1);

        rendy.setAccount(new Account(500000));
        rendy.setAccount(new Account(300000));
        ayu.setAccount(new Account(1000000));

        System.out.println("===============================");
        System.out.println("Keadaan awal");
        tampilkanRekening(rendy);
        tampilkanRekening(ayu);

        System.out.println("===============================");
        System.out.println("Transaksi");
        setor(rendy, 0, 250000);
        tarik(rendy, 1, 100000);
        tarik(ayu, 0, 2000000);              // gagal
        transfer(rendy, 1, ayu, 0, 200000);

        System.out.println("===============================");
        System.out.println("Keadaan akhir");
        tampilkanRekening(rendy);
        tampilkanRekening(ayu);

        System.out.println("Total nasabah: " + bank.getNumOfCustomers());
    }


    // metode tambahan untuk mempermudah menampilkan informasi rekening dan transaksi
    private static String nama(Customer c, int idx) {
        return c.getFirstName() + " " + c.getLastName() + " (Akun #" + idx + ")";
    }

    private static void tampilkanRekening(Customer c) {
        System.out.println(c.getFirstName() + " " + c.getLastName()
                + " - " + c.getNumOfAccounts() + " akun");
        for (int i = 0; i < c.getNumOfAccounts(); i++) {
            System.out.println("   Akun #" + i + " : Rp "
                    + (long) c.getAccount(i).getBalance());
        }
        System.out.println();
    }

    private static void setor(Customer c, int idx, double jumlah) {
        Account a = c.getAccount(idx);
        double sebelum = a.getBalance();
        boolean ok = a.deposit(jumlah);

        System.out.println("[" + (++nomorTransaksi) + "] SETOR Rp " + (long) jumlah);
        System.out.println("    Ke     : " + nama(c, idx));
        System.out.println("    Status : " + (ok ? "BERHASIL" : "GAGAL (jumlah tidak valid)"));
        System.out.println("    Saldo  : Rp " + (long) sebelum + " -> Rp " + (long) a.getBalance());
        System.out.println();
    }

    private static void tarik(Customer c, int idx, double jumlah) {
        Account a = c.getAccount(idx);
        double sebelum = a.getBalance();
        boolean ok = a.withdraw(jumlah);

        System.out.println("[" + (++nomorTransaksi) + "] TARIK Rp " + (long) jumlah);
        System.out.println("    Dari   : " + nama(c, idx));
        System.out.println("    Status : " + (ok ? "BERHASIL" : "GAGAL (saldo tidak cukup)"));
        System.out.println("    Saldo  : Rp " + (long) sebelum + " -> Rp " + (long) a.getBalance());
        System.out.println();
    }

    private static void transfer(Customer dari, int idxDari,
                                 Customer ke, int idxKe, double jumlah) {
        Account asal = dari.getAccount(idxDari);
        Account tujuan = ke.getAccount(idxKe);
        double asalSebelum = asal.getBalance();
        double tujuanSebelum = tujuan.getBalance();

        boolean ok = asal.withdraw(jumlah);
        if (ok) {
            tujuan.deposit(jumlah);
        }

        System.out.println("[" + (++nomorTransaksi) + "] TRANSFER Rp " + (long) jumlah);
        System.out.println("    Dari     : " + nama(dari, idxDari));
        System.out.println("    Ke       : " + nama(ke, idxKe));
        System.out.println("    Status   : " + (ok ? "BERHASIL" : "GAGAL (saldo tidak cukup)"));
        System.out.println("    Pengirim : Rp " + (long) asalSebelum + " -> Rp " + (long) asal.getBalance());
        System.out.println("    Penerima : Rp " + (long) tujuanSebelum + " -> Rp " + (long) tujuan.getBalance());
        System.out.println();
    }
}