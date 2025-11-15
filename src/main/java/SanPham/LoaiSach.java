package SanPham;

import DanhSach.DanhSachSach;
import KiemTra.KiemTra;

public class LoaiSach extends PhanTu {
    private String maLoaiSach;
    private String tenSach;
    private String nhaXuatBan;
    private int namXuatBan;
    private int soLuong = 0;
    private String[] dsmaSach;

    public LoaiSach() {}

    public LoaiSach(String maLoaiSach, String tenSach, String nhaXuatBan, int namXuatBan, int soLuong, String[] dsmaSach) {
        this.maLoaiSach = maLoaiSach;
        this.tenSach = tenSach;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
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
        System.out.print("Nhập mã loại sách: ");
        DanhSachSach dsSach = new DanhSachSach();
        boolean check;
        do {
            maLoaiSach = sc.nextLine();
            check = dsSach.layPhanTuVoi(maLoaiSach) == null;
            if (!check)
                System.out.println("Mã loại sách đã tồn tại! Mời nhập lại: ");
        } while (!check);
    }

    public String gettenSach() {
        return tenSach;
    }

    public void settenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void settenSach() {
        System.out.print("Nhập tên sách: ");
        tenSach = sc.nextLine();
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

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setNamXuatBan() {
        System.out.print("Nhập năm xuất bản: ");
        namXuatBan = KiemTra.CheckNumber();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong() {
        DanhSachSach dss = new DanhSachSach();
        Sach[] dsstmp = dss.getDsSach();
        soLuong = 0;
        for (int i = 0; i < dsstmp.length; i++) {
            if (dsstmp[i].gettenSach().equals(tenSach)) {
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
      if (dsstmp[i].gettenSach().equals(tenSach)) count++;

      String[] dsmaSpArr = new String[count];
      int m = 0;
      for (int i = 0; i < dsstmp.length; i++) {
    if (dsstmp[i].gettenSach().equals(tenSach))
        dsmaSpArr[m++] = dsstmp[i].getmaSach();
       }
        setDsMaSach(dsmaSpArr);

    }

    public void nhapDsMaSach() {
        DanhSachSach ttds = new DanhSachSach();
        String[] dsmaSp = new String[soLuong];
        Sach pt;

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Thêm mã sách thứ " + (i + 1) + ":");
            do {
                pt = (Sach) ttds.timPhanTu();
                if (pt == null) {
                    System.out.println("Không tìm thấy sách!");
                    System.out.print("Bạn có muốn thêm sách mới? (1 - có, 0 - không): ");
                    int chon = KiemTra.CheckNumber();
                    if (chon == 1) ttds.themKPhanTuVaoDanhSach();
                } else if (!pt.gettenSach().equals(tenSach)) {
                    System.out.println("Tên sách không phù hợp!");
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
        System.out.print("Nhập số sách cần thêm vào danh sách: ");
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
        System.out.printf("%-20s %-25s %-20s %-15s %-10s \n", "Mã Loại Sách", "Tên Sách", "Nhà Xuất Bản", "Năm XB", "Số Lượng");
        System.out.printf("%-20s %-25s %-20s %-15d %-10d \n", maLoaiSach, tenSach, nhaXuatBan, namXuatBan, soLuong);
        System.out.println("***************************************************");
    }

    @Override
    public void nhap() {
        setmaLoaiSach();
        settenSach();
        setNhaXuatBan();
        setNamXuatBan();
        setDsMaSach();
        setSoLuong();
    }

    @Override
    public void xuat() {
        xuatLoaiSach();
        System.out.println("Danh sách sách cùng loại:");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n",
                "Mã Sách", "Tên Sách", "Tác Giả", "Nhà Xuất Bản", "Số Lượng", "Giá");

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
            System.out.println("=== Sửa thông tin Loại Sách === ");
            System.out.println("1. Thêm mã sách mới vào danh sách");
            System.out.println("2. Xóa mã sách khỏi danh sách");
            System.out.println("3. Nhập lại danh sách mã sách");
            System.out.println("0. Thoát");
            System.out.println("===============================");
            System.out.print("Nhập lựa chọn: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoát sửa thông tin loại sách!");
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
                    System.out.println("Hãy chọn lại!");
                    break;
            }
        } while (chon != 0);
    }
}
