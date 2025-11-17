package SanPham;

import DanhSach.DanhSachSach;
import KiemTra.KiemTra;

public class LoaiSach extends PhanTu {
    private String maLoaiSach;
    private String tenLoaiSach;
    private String nhaXuatBan;
    private int soLuong = 0;
    private String[] dsmaSach;

    public LoaiSach() {}

    public LoaiSach(String maLoaiSach, String tenLoaiSach, String nhaXuatBan, int soLuong, String[] dsmaSach) {
        this.maLoaiSach = maLoaiSach;
        this.tenLoaiSach = tenLoaiSach;
        this.nhaXuatBan = nhaXuatBan;
        this.soLuong = soLuong;
        this.dsmaSach = dsmaSach;
    }

    public String getmaLoaiSach() {
        return maLoaiSach;
    }

    public void setmaLoaiSach(String maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public void setmaLoaiSach() {
        System.out.print("Nhap ma loai sach: ");
        DanhSachSach dsSach = new DanhSachSach();
        boolean check;
        do {
            maLoaiSach = sc.nextLine();
            check = dsSach.layPhanTuVoi(maLoaiSach) == null;
            if (!check)
                System.out.println("Ma loai sach đa ton tai! Moi nhap lai: ");
        } while (!check);
    }

    public String gettenLoaiSach() {
        return tenLoaiSach;
    }

    public void settenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public void settenLoaiSach() {
        System.out.print("Nhap ten loai sach: ");
        tenLoaiSach = sc.nextLine();
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setNhaXuatBan() {
        System.out.print("Nhập nhà xuất bản: ");
        nhaXuatBan = sc.nextLine();
    }
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong() {
        DanhSachSach dss = new DanhSachSach();
        Sach[] dsstmp = dss.getDsSach();
        soLuong = 0;
        for (int i = 0; i < dsstmp.length; i++) {
            if (dsstmp[i].gettenSach().equals(tenLoaiSach)) {
                soLuong++;
            }
        }
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String[] getDsMaSach() {
        return dsmaSach;
    }

    public void setDsMaSach(String[] dsmaSach) {
        this.dsmaSach = dsmaSach;
        soLuong = dsmaSach.length;
    }

    public void setDsMaSach() {
        DanhSachSach dss = new DanhSachSach();
        Sach[] dsstmp = dss.getDsSach();
        int count = 0;
      for (int i = 0; i < dsstmp.length; i++)
      if (dsstmp[i].gettenSach().equals(tenLoaiSach)) count++;

      String[] dsmaSpArr = new String[count];
      int m = 0;
      for (int i = 0; i < dsstmp.length; i++) {
    if (dsstmp[i].gettenSach().equals(tenLoaiSach))
        dsmaSpArr[m++] = dsstmp[i].getmaSach();
       }
        setDsMaSach(dsmaSpArr);

    }

    public void nhapDsMaSach() {
        DanhSachSach ttds = new DanhSachSach();
        String[] dsmaSp = new String[soLuong];
        Sach pt;

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Them ma sach thu " + (i + 1) + ":");
            do {
                pt = (Sach) ttds.timPhanTu();
                if (pt == null) {
                    System.out.println("Khong tim thay sach!");
                    System.out.print("Ban co muon them sach moi? (1 - co, 0 - khong): ");
                    int chon = KiemTra.CheckNumber();
                    if (chon == 1) ttds.themKPhanTuVaoDanhSach();
                } else if (!pt.gettenSach().equals(tenLoaiSach)) {
                    System.out.println("Ten sach khong phu hop!");
                    pt = null;
                }
            } while (pt == null);

            dsmaSp[i] = pt.getmaSach();
        }
        setDsMaSach(dsmaSp);
    }

    public void themMaSachVaoDS() {
        String[] dsmaSp = new String[soLuong + 1];
        for (int i = 0; i < soLuong; i++)
            dsmaSp[i] = dsmaSach[i];

        DanhSachSach ttds = new DanhSachSach();
        ttds.themKPhanTuVaoDanhSach();
        dsmaSp[soLuong] = ttds.getDsSach()[ttds.getSoLuong() - 1].getmaSach();
        soLuong++;
        setDsMaSach(dsmaSp);
    }

    public void themKMaSachVaoDS() {
        System.out.print("Nhap so sach can them vao danh sach: ");
        int k = KiemTra.CheckNumber();
        for (int i = 0; i < k; i++)
            themMaSachVaoDS();
    }

    public void xoaMaSachKhoiDS() {
        DanhSachSach dss = new DanhSachSach();
        dss.xoaPhanTu();
        soLuong--;
        setDsMaSach();
    }

    public void xuatLoaiSach() {
        System.out.printf("%-20s %-25s %-20s %-15s %-10s \n", "Ma Loai Sach", "Ten Loai Sach", "Nha Xuat Ban", "So Luong");
        System.out.printf("%-20s %-25s %-20s %-15d %-10d \n", maLoaiSach, tenLoaiSach, nhaXuatBan, soLuong);
        System.out.println("***************************************************");
    }

    @Override
    public void nhap() {
        setmaLoaiSach();
        settenLoaiSach();
        setNhaXuatBan();
        setDsMaSach();
        setSoLuong();
    }

    @Override
    public void xuat() {
        xuatLoaiSach();
        System.out.println("Danh sach sach cung loai:");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n",
                "Ma Sach", "Ten Sach", "Tac Gia", "Nha Xuat Ban", "So Luong", "Gia");

        PhanTu pt;
        DanhSachSach ttds = new DanhSachSach();
        for (int i = 0; i < dsmaSach.length; i++) {
            pt = ttds.layPhanTuVoi(dsmaSach[i]);
            if (pt != null) pt.xuat();
        }
        System.out.println("***************************************************");
    }

    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin Loai Sach === ");
            System.out.println("1. Them ma sach moi vao danh sach");
            System.out.println("2. Xóa ma sach khoi danh sach");
            System.out.println("3. Nhap lai danh sach ma sach");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin loai sach!");
                    break;
                case 1:
                    themKMaSachVaoDS();
                    break;
                case 2:
                    xoaMaSachKhoiDS();
                    break;
                case 3:
                    setDsMaSach();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while (chon != 0);
    }
}
