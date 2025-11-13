package DanhSach;

import java.util.ArrayList;
import Phieu.PhieuMuon;

public class DanhSachPhieuMuon {
    private ArrayList<PhieuMuon> ds = new ArrayList<>();

    // ===== Nhap moi danh sach =====
    public void nhapDanhSach() {
        ds.clear();
        System.out.print("Nhap so luong phieu muon: ");
        int n = Integer.parseInt(PhanTu.sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Phieu muon thu " + (i + 1) + " ---");
            PhieuMuon pm = new PhieuMuon();
            pm.nhap();
            ds.add(pm);
        }
    }

    // ===== Xuat danh sach =====
    public void xuatDanhSach() {
        if (ds.isEmpty()) {
            System.out.println("Danh sach phieu muon trong!");
            return;
        }
        System.out.printf("%-10s %-15s %-15s %-15s\n",
                "Ma PM", "Ngay muon", "Ngay tra", "Tinh trang");
        System.out.println("-----------------------------------------------------");
        for (PhieuMuon pm : ds) {
            pm.xuat();
        }
    }

    // ===== Them phieu muon =====
    public void themPhieuMuon() {
        System.out.println("\n=== THEM PHIEU MUON MOI ===");
        PhieuMuon pm = new PhieuMuon();
        pm.nhap();
        ds.add(pm);
        System.out.println("Them phieu muon thanh cong!");
    }

    // ===== Chinh sua thong tin =====
    public void suaPhieuMuon() {
        System.out.print("Nhap ma phieu can sua: ");
        String ma = PhanTu.sc.nextLine();
        PhieuMuon pm = timTheoMa(ma);
        if (pm != null) {
            pm.sua();
        } else {
            System.out.println("Khong tim thay phieu muon co ma: " + ma);
        }
    }

    // ===== Xoa phieu muon =====
    public void xoaPhieuMuon() {
        System.out.print("Nhap ma phieu can xoa: ");
        String ma = PhanTu.sc.nextLine();
        PhieuMuon pm = timTheoMa(ma);
        if (pm != null) {
            ds.remove(pm);
            System.out.println("Da xoa phieu muon: " + ma);
        } else {
            System.out.println("Khong tim thay phieu muon!");
        }
    }

    // ===== Tim phieu muon =====
    public void timPhieuMuon() {
        System.out.print("Nhap ma phieu can tim: ");
        String ma = PhanTu.sc.nextLine();
        PhieuMuon pm = timTheoMa(ma);
        if (pm != null) {
            System.out.printf("%-10s %-15s %-15s %-15s\n",
                    "Ma PM", "Ngay muon", "Ngay tra", "Tinh trang");
            System.out.println("-----------------------------------------------------");
            pm.xuat();
        } else {
            System.out.println("Khong tim thay phieu muon!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        int dangMuon = 0, daTra = 0;
        for (PhieuMuon pm : ds) {
            if (pm.getTinhTrang().equalsIgnoreCase("Dang muon"))
                dangMuon++;
            else if (pm.getTinhTrang().equalsIgnoreCase("Da tra"))
                daTra++;
        }
        System.out.println("Tong so phieu: " + ds.size());
        System.out.println(" - Dang muon: " + dangMuon);
        System.out.println(" - Da tra: " + daTra);
    }

    // ===== Tong so luong =====
    public void tongSoLuong() {
        System.out.println("Tong so luong phieu muon hien co: " + ds.size());
    }

    // ===== Ham tim phieu theo ma =====
    private PhieuMuon timTheoMa(String ma) {
        for (PhieuMuon pm : ds) {
            if (pm.getMaPM().equalsIgnoreCase(ma)) {
                return pm;
            }
        }
        return null;
    }

    // ===== Menu quan ly danh sach phieu muon =====
    public void menu() {
        int chon;
        do {
            System.out.println("\n*** QUAN LY DANH SACH PHIEU MUON ***");
            System.out.println("1. Nhap moi danh sach");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Them phieu muon vao danh sach");
            System.out.println("4. Chinh sua thong tin phieu muon");
            System.out.println("5. Xoa phieu muon");
            System.out.println("6. Tim phieu muon");
            System.out.println("7. Thong ke");
            System.out.println("8. Tong so luong phieu muon");
            System.out.println("0. Quay lai menu quan ly");
            System.out.println("=================================");
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
                case 0 -> System.out.println("Quay lai menu chinh...");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}
