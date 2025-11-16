package SanPham;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import KiemTra.KiemTra;
import java.util.Scanner;

// MuonSach kế thừa PhanTu để có các phương thức cơ bản
public class MuonSach extends PhanTu {
    private String maMuonSach;
    private String maSach;
    private String tenSach;
    private LocalDate ngayMuon;
    private LocalDate ngayTraDuKien;
    private int soLuong;

    // Định dạng ngày tháng
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Constructor mặc định
    public MuonSach() {
        super();
        this.maMuonSach = "";
        this.maSach = "";
        this.tenSach = "";
        this.ngayMuon = LocalDate.now();
        this.ngayTraDuKien = LocalDate.now().plusDays(7); // Mặc định 7 ngày
        this.soLuong = 0;
    }

    // Constructor đầy đủ
    public MuonSach(String maMuonSach, String maSach, String tenSach, LocalDate ngayMuon, LocalDate ngayTraDuKien, int soLuong) {
        super(maMuonSach); // MaMuonSach làm mã định danh chung
        this.maMuonSach = maMuonSach;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.soLuong = soLuong;
    }
    
    // Override phương thức nhập (từ PhanTu)
    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Nhap Thong Tin Chi Tiet Sach Muon ---");
        
        System.out.print("Ma Chi Tiet Muon: ");
        this.maMuonSach = sc.nextLine();
        super.setMa(this.maMuonSach);

        System.out.print("Ma Sach: ");
        this.maSach = sc.nextLine();

        System.out.print("Ten Sach: ");
        this.tenSach = sc.nextLine();

        System.out.print("So Luong Muon: ");
        this.soLuong = KiemTra.CheckNumber();

        // Nhập ngày tháng có thể phức tạp hơn, ta dùng mặc định
        this.ngayMuon = LocalDate.now();
        this.ngayTraDuKien = LocalDate.now().plusDays(7);
        System.out.println("Ngay Muon (Mac dinh): " + ngayMuon.format(DATE_FORMATTER));
        System.out.println("Ngay Tra Du Kien (Mac dinh +7 ngay): " + ngayTraDuKien.format(DATE_FORMATTER));
    }

    // Override phương thức xuất (từ PhanTu)
    @Override
    public void xuat() {
        System.out.printf("| %-18s | %-10s | %-30s | %-12s | %-18s | %-8s |\n",
                this.maMuonSach, this.maSach, this.tenSach, 
                this.ngayMuon.format(DATE_FORMATTER), 
                this.ngayTraDuKien.format(DATE_FORMATTER), 
                this.soLuong);
    }
    
    // Static header cho việc xuất danh sách
    public static void xuatHeader() {
        System.out.println("=================================================================================================");
        System.out.printf("| %-18s | %-10s | %-30s | %-12s | %-18s | %-8s |\n",
                "Ma Chi Tiet Muon", "Ma Sach", "Ten Sach", "Ngay Muon", "Ngay Tra Du Kien", "SL");
        System.out.println("=================================================================================================");
    }
    
    // Getters and Setters

    public String getMaMuonSach() {
        return maMuonSach;
    }

    public void setMaMuonSach(String maMuonSach) {
        this.maMuonSach = maMuonSach;
        super.setMa(maMuonSach);
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public LocalDate getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(LocalDate ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}