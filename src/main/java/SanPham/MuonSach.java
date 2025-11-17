package SanPham;

import KiemTra.KiemTra;

public class MuonSach extends PhanTu {
    private int soPhieuMuon;
    private String ngayLapPhieu;
    private String maNhanVien;
    private String maKhachHang;
    private String ngayTraDuKien;
    private String trangThai;
    private Sach[] dsSachMuon;

    public MuonSach() {
        this.trangThai = "Dang muon";
    }
    
    public MuonSach(int soPhieuMuon, String ngayLapPhieu, String maNhanVien, String maKhachHang,
                    String ngayTraDuKien, String trangThai, Sach[] dsSachMuon) {
        this.soPhieuMuon = soPhieuMuon;
        this.ngayLapPhieu = ngayLapPhieu;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.ngayTraDuKien = ngayTraDuKien;
        this.trangThai = trangThai;
        this.dsSachMuon = dsSachMuon;
    }

    public int getSoPhieuMuon() { return soPhieuMuon; }
    public String getNgayLapPhieu() { return ngayLapPhieu; }
    public String getMaNhanVien() { return maNhanVien; }
    public String getMaKhachHang() { return maKhachHang; }
    public String getNgayTraDuKien() { return ngayTraDuKien; }
    public String getTrangThai() { return trangThai; }
    public Sach[] getDsSachMuon() { return dsSachMuon; }
    
    @Override
    public void nhap() {
        System.out.println("\n--- NHAP THONG TIN PHIEU MUON ---");
        
        System.out.print("Nhap Ma Phieu Muon (so): ");
        this.soPhieuMuon = KiemTra.CheckNumber();

        this.ngayLapPhieu = String.format("%02d/%02d/%d", ngayhientai, thanghientai, namhientai);
        System.out.println("Ngay Lap Phieu (Tu dong): " + this.ngayLapPhieu);

        System.out.print("Nhap Ma Nhan Vien lap phieu: ");
        this.maNhanVien = sc.nextLine();
        
        System.out.print("Nhap Ma Khach Hang muon: ");
        this.maKhachHang = sc.nextLine();
        
        System.out.print("Nhap Ngay Tra Du Kien (dd/MM/yyyy): ");
        this.ngayTraDuKien = sc.nextLine();
        
        System.out.println("Trang Thai Mac Dinh: " + this.trangThai);
        
        System.out.print("Nhap so luong dau sach muon muon: ");
        int soLuongDauSach = KiemTra.CheckNumber();
        
        this.dsSachMuon = new Sach[soLuongDauSach];
        
        for (int i = 0; i < soLuongDauSach; i++) {
            System.out.println("--- Nhap thong tin sach thu " + (i + 1) + " ---");
            System.out.print("Nhap Ma Sach: ");
            String maS = sc.nextLine();
            
            System.out.print("Nhap So Luong: ");
            int sl = KiemTra.CheckNumber();
            this.dsSachMuon[i] = new Sach(maS, "Chua biet", "Chua biet", "Chua biet", sl, 0);
        }
    }

    @Override
    public void xuat() {
        int tongSoLuong = 0;
        if (dsSachMuon != null) {
            for (Sach sach : dsSachMuon) {
                tongSoLuong += sach.getSoLuong();
            }
        }
        
        System.out.println("====================================");
        System.out.printf("%-20s %-20s\n", "PHIEU MUON SACH:", "Ma PM: " + soPhieuMuon);
        System.out.printf("%-20s %-20s\n", "Ngay Lap Phieu:", ngayLapPhieu);
        System.out.printf("%-20s %-20s\n", "Ma NV Lap:", maNhanVien);
        System.out.printf("%-20s %-20s\n", "Ma KH Muon:", maKhachHang);
        System.out.printf("%-20s %-20s\n", "Ngay Tra Du Kien:", ngayTraDuKien);
        System.out.printf("%-20s %-20s\n", "Trang Thai:", trangThai);
        System.out.println("Tong So Luong Sach: " + tongSoLuong);
        
        System.out.println("--- CHI TIET SACH MUON ---");
        if (dsSachMuon != null && dsSachMuon.length > 0) {
            System.out.printf("%-10s %-20s %-10s\n", "STT", "Ma Sach", "So luong");
            for (int i = 0; i < dsSachMuon.length; i++) {
                System.out.printf("%-10s %-20s %-10d\n", (i + 1), dsSachMuon[i].getmaSach(), dsSachMuon[i].getSoLuong());
            }
        } else {
            System.out.println("  (Khong co sach nao trong phieu.)");
        }
        System.out.println("====================================");
    }

    @Override
    public void suaThongTin() {
        int chon = -1;
        do {
            System.out.println("\n--- SUA THONG TIN PHIEU MUON " + soPhieuMuon + " ---");
            System.out.println("1. Cap nhat Trang Thai (Hien tai: " + this.trangThai + ")");
            System.out.println("2. Sua Ngay Tra Du Kien (Hien tai: " + this.ngayTraDuKien + ")");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Chon muc can sua: ");
            
            chon = KiemTra.CheckNumber();
            
            switch(chon) {
                case 1:
                    System.out.print("Nhap Trang Thai moi: ");
                    this.trangThai = sc.nextLine();
                    System.out.println("Cap nhat trang thai thanh cong.");
                    break;
                case 2:
                    System.out.print("Nhap Ngay Tra Du Kien moi (dd/MM/yyyy): ");
                    this.ngayTraDuKien = sc.nextLine();
                    System.out.println("Cap nhat ngay tra du kien thanh cong.");
                    break;
                case 0:
                    System.out.println("Thoat khoi chuc nang sua thong tin.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while(chon != 0);
    }
}
