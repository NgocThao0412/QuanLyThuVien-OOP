package Phieu;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import KiemTra.CheckLoi;

public class PhieuMuon extends PhanTu {
    private String maPM;
    private String maDG;
    private String maTL;
    private Date ngayMuon;
    private Date ngayTra;
    private String tinhTrang;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_NAME = "dspm.txt";

    public PhieuMuon() {}

    public PhieuMuon(String maPM, String maDG, String maTL, Date ngayMuon, Date ngayTra, String tinhTrang) {
        this.maPM = maPM;
        this.maDG = maDG;
        this.maTL = maTL;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
    }

    // ===== Getter - Setter =====
    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }

    public String getMaDG() { return maDG; }
    public void setMaDG(String maDG) { this.maDG = maDG; }

    public String getMaTL() { return maTL; }
    public void setMaTL(String maTL) { this.maTL = maTL; }

    public Date getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(Date ngayMuon) { this.ngayMuon = ngayMuon; }

    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }

    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    // ===== Nhập =====
    @Override
    public void nhap() {
        System.out.println("\n=== NHAP THONG TIN PHIEU MUON ===");

        do {
            System.out.print("Nhap ma phieu muon: ");
            maPM = sc.nextLine();
        } while (!CheckLoi.KiemTraMaPhieu(maPM));

        do {
            System.out.print("Nhap ma doc gia: ");
            maDG = sc.nextLine();
        } while (!CheckLoi.KiemTraMaDG(maDG));

        do {
            System.out.print("Nhap ma tai lieu: ");
            maTL = sc.nextLine();
        } while (!CheckLoi.KiemTraMaTL(maTL));

        String strNgayMuon, strNgayTra;

        do {
            System.out.print("Nhap ngay muon (dd/MM/yyyy): ");
            strNgayMuon = sc.nextLine();
        } while (!CheckLoi.KiemTraNgay(strNgayMuon));

        do {
            System.out.print("Nhap ngay tra (dd/MM/yyyy): ");
            strNgayTra = sc.nextLine();
        } while (!CheckLoi.KiemTraNgay(strNgayTra)
                || !CheckLoi.KiemTraNgayMuonTra(strNgayMuon, strNgayTra));

        try {
            ngayMuon = sdf.parse(strNgayMuon);
            ngayTra = sdf.parse(strNgayTra);
        } catch (Exception e) {
            System.out.println("Loi dinh dang ngay!");
        }

        do {
            System.out.print("Nhap tinh trang (Dang muon / Da tra): ");
            tinhTrang = sc.nextLine();
        } while (!CheckLoi.KiemTraTinhTrang(tinhTrang));
    }

    // ===== Xuất =====
    @Override
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s\n",
                maPM, maDG, maTL,
                sdf.format(ngayMuon),
                sdf.format(ngayTra),
                tinhTrang);
    }

    // ===== Sửa =====
    @Override
    public void sua() {
        int chon;

        do {
            System.out.println("\n--- CHINH SUA PHIEU MUON ---");
            System.out.println("1. Sua ma doc gia");
            System.out.println("2. Sua ma tai lieu");
            System.out.println("3. Sua ngay muon");
            System.out.println("4. Sua ngay tra");
            System.out.println("5. Sua tinh trang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhap so khong hop le!");
                chon = -1;
                continue;
            }

            try {
                switch (chon) {
                    case 1:
                        System.out.print("Nhap ma doc gia moi: ");
                        maDG = sc.nextLine();
                        break;

                    case 2:
                        System.out.print("Nhap ma tai lieu moi: ");
                        maTL = sc.nextLine();
                        break;

                    case 3:
                        System.out.print("Nhap ngay muon moi: ");
                        ngayMuon = sdf.parse(sc.nextLine());
                        break;

                    case 4:
                        System.out.print("Nhap ngay tra moi: ");
                        ngayTra = sdf.parse(sc.nextLine());
                        break;

                    case 5:
                        System.out.print("Nhap tinh trang moi: ");
                        tinhTrang = sc.nextLine();
                        break;

                    case 0:
                        System.out.println("Thoat sua!");
                        break;

                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (Exception e) {
                System.out.println("Loi nhap lieu: " + e.getMessage());
            }

        } while (chon != 0);
    }

    // ===== Đọc file =====
    public static PhieuMuon[] docFile() {
        PhieuMuon[] ds = new PhieuMuon[200];
        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("#");

                if (p.length == 6) {
                    Date ngayMuon = sdf.parse(p[3]);
                    Date ngayTra = sdf.parse(p[4]);

                    ds[n++] = new PhieuMuon(p[0], p[1], p[2], ngayMuon, ngayTra, p[5]);
                }
            }

        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }

        return Arrays.copyOf(ds, n);
    }

    // ===== Ghi file =====
    public static void ghiFile(PhieuMuon[] ds) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (PhieuMuon pm : ds) {
                if (pm == null) continue;

                bw.write(pm.maPM + "#" + pm.maDG + "#" + pm.maTL + "#" +
                        sdf.format(pm.ngayMuon) + "#" +
                        sdf.format(pm.ngayTra) + "#" +
                        pm.tinhTrang);
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}
