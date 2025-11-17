package DanhSach;

import Phieu.PhieuMuon;
import Phieu.PhanTu;

public class DanhSachPhieuMuon {

    private PhieuMuon[] ds = new PhieuMuon[200]; 
    private int size = 0; 

    // ===== Đọc file khi khởi tạo =====
    public DanhSachPhieuMuon() {
        ds = PhieuMuon.docFile();
        size = ds.length;
    }

    // ===== Nhập mới danh sách =====
    public void nhapDanhSach() {
        System.out.print("Nhap so luong phieu muon: ");
        int n = Integer.parseInt(PhanTu.sc.nextLine());
        size = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Phieu muon thu " + (i + 1) + " ---");
            PhieuMuon pm = new PhieuMuon();
            pm.nhap();
            ds[size++] = pm;
        }

        PhieuMuon.ghiFile(ds);
        System.out.println("Da luu danh sach vao file!");
    }

    // ===== Xuất danh sách từ file =====
    public void xuatDanhSach() {
    PhieuMuon[] dsFile = PhieuMuon.docFile(); 

    if (dsFile.length == 0) {
        System.out.println("File rong, khong co du lieu!");
        return;
    }

    System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s\n",
            "MaPM", "MaDG", "MaTL", "Ngay muon", "Ngay tra", "Tinh trang");
    System.out.println("-----------------------------------------------------------");

    for (PhieuMuon pm : dsFile) {
    System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s\n",
        pm.getMaPM(),
        pm.getMaDG(),
        pm.getMaTL(),
        pm.getNgayMuonString(),
        pm.getNgayTraString(),
        pm.getTinhTrang()
    );
}
}

    // ===== Thêm =====
    public void themPhieuMuon() {
        PhieuMuon pm = new PhieuMuon();
        pm.nhap();
        ds[size++] = pm;
        PhieuMuon.ghiFile(ds);
        System.out.println("Them thanh cong!");
    }

    // ===== Sửa =====
    public void suaPhieuMuon() {
        System.out.print("Nhap ma phieu muon can sua: ");
        String ma = PhanTu.sc.nextLine();
        int index = timTheoMaIndex(ma);

        if (index == -1) {
            System.out.println("Khong tim thay!");
            return;
        }

        ds[index].sua();
        PhieuMuon.ghiFile(ds);
        System.out.println("Da cap nhat file!");
    }

    // ===== Xóa =====
    public void xoaPhieuMuon() {
        System.out.print("Nhap ma phieu muon can xoa: ");
        String ma = PhanTu.sc.nextLine();
        int index = timTheoMaIndex(ma);

        if (index == -1) {
            System.out.println("Khong tim thay ma phieu!");
            return;
        }

 
        for (int i = index; i < size - 1; i++)
            ds[i] = ds[i + 1];

        size--;
        ds[size] = null;

        PhieuMuon.ghiFile(ds);

        System.out.println("Da xoa thanh cong!");
    }

    // ===== Tìm =====
    public void timPhieuMuon() {
        System.out.print("Nhap ma phieu muon can tim: ");
        String ma = PhanTu.sc.nextLine();
        int index = timTheoMaIndex(ma);

        if (index == -1) {
            System.out.println("Khong tim thay!");
            return;
        }

        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s\n",
                "MaPM", "MaDG", "MaTL", "Ngay muon", "Ngay tra", "Tinh trang");
        System.out.println("-----------------------------------------------------------");

        ds[index].xuat();
    }

    // ===== Thống kê =====
    public void thongKe() {
        int dangMuon = 0, daTra = 0;

        for (int i = 0; i < size; i++) {
            String t = ds[i].getTinhTrang().toLowerCase();
            if (t.equals("dang muon")) dangMuon++;
            if (t.equals("da tra")) daTra++;
        }

        System.out.println("Tong phieu: " + size);
        System.out.println("Dang muon: " + dangMuon);
        System.out.println("Da tra: " + daTra);
    }

    // ===== Tổng số lượng =====
    public void tongSoLuong() {
        System.out.println("Tong so phieu muon: " + size);
    }

    // ===== Tìm bằng mã =====
    private int timTheoMaIndex(String ma) {
        for (int i = 0; i < size; i++) {
            if (ds[i].getMaPM().equalsIgnoreCase(ma))
                return i;
        }
        return -1;
    }

    // ===== Menu =====
    public void menu() {
        int chon;

        do {
            System.out.println("\n*** QUAN LY PHIEU MUON ***");
            System.out.println("1. Nhap danh sach moi");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Them phieu muon");
            System.out.println("4. Sua phieu muon");
            System.out.println("5. Xoa phieu muon");
            System.out.println("6. Tim phieu muon");
            System.out.println("7. Thong ke");
            System.out.println("8. Tong so luong");
            System.out.println("0. Thoat");
            System.out.print("Moi chon: ");

            chon = Integer.parseInt(PhanTu.sc.nextLine());

            switch (chon) {
                case 1 -> nhapDanhSach();
                case 2 -> xuatDanhSach();
                case 3 -> themPhieuMuon();
                case 4 -> suaPhieuMuon();
                case 5 -> xoaPhieuMuon();
                case 6 -> timPhieuMuon();
                case 7 -> thongKe();
                case 8 -> tongSoLuong();
                case 0 -> System.out.println("Thoat!");
                default -> System.out.println("Lua chon sai!");
            }

        } while (chon != 0);
    }
}
