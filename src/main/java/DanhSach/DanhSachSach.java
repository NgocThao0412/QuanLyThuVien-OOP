package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.PhanTu;
import SanPham.Sach;
import SanPham.SachThieuNhi;
import SanPham.SachTamLy;
import SanPham.SachTrinhTham;

public class DanhSachSach implements DanhSachChung {
    private int soLuong;
    private Sach[] dsSach;

    public DanhSachSach() {
        dsSach = getDsSach();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Sach[] getDsSach() {
        String data = FileHandler.docFile("dss.txt");
        String[] dArr = data.split("\n");

        if (dArr[0].length() == 0) {
            setSoLuong(0);
            dsSach = new Sach[0];
            return dsSach;
        }

        setSoLuong(Integer.parseInt(dArr[0]));
        dsSach = new Sach[soLuong];

        int k = 0;
        for (int i = 1; i < dArr.length; i++) {
            String[] lArr = dArr[i].split("#");
            int m = 0;

            Sach sp;
            switch (lArr[3]) {
                case "Sach Trinh Tham": sp = new SachTrinhTham(); break;
                case "Sach Thieu Nhi": sp = new SachThieuNhi(); break;
                case "Sach Tam Ly": sp = new SachTamLy(); break;
                default: sp = new Sach(); break;
            }

            sp.setmaSach(lArr[m++]);
            sp.settenSach(lArr[m++]);
            sp.setTacGia(lArr[m++]);
            sp.setLoaiSach(lArr[m++]);
            sp.setSoLuong(Integer.parseInt(lArr[m++]));
            sp.setPrice(Integer.parseInt(lArr[m++]));

            // Nếu có NhaXuatBan
            if (sp instanceof SachTrinhTham)
                ((SachTrinhTham) sp).setNhaXuatBan(lArr[m++]);
            else if (sp instanceof SachThieuNhi)
                ((SachThieuNhi) sp).setNhaXuatBan(lArr[m++]);
            else if (sp instanceof SachTamLy)
                ((SachTamLy) sp).setNhaXuatBan(lArr[m++]);

            dsSach[k++] = sp;
        }

        return dsSach;
    }

    public void setDsSach(Sach[] dsSach) {
        String tenFile = "dss.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong + "\n", tenFile);

        for (int i = 0; i < soLuong; i++) {
            Sach sp = dsSach[i];
            String nhaXuatBan = "";
            if (sp instanceof SachTrinhTham)
                nhaXuatBan = ((SachTrinhTham) sp).getNhaXuatBan();
            else if (sp instanceof SachThieuNhi)
                nhaXuatBan = ((SachThieuNhi) sp).getNhaXuatBan();
            else if (sp instanceof SachTamLy)
                nhaXuatBan = ((SachTamLy) sp).getNhaXuatBan();

            FileHandler.themSach(
                sp.getmaSach(), sp.gettenSach(), sp.getTacGia(), sp.getLoaiSach(),nhaXuatBan,
                sp.getSoLuong(), sp.getPrice()
            );
        }

        this.dsSach = dsSach;
    }

    // ======== NHẬP/XUẤT DANH SÁCH =========
    public void nhapDanhSach() {
        FileHandler.resetFile("dss.txt");
        System.out.print("Moi nhap so luong sach: ");
        soLuong = KiemTra.CheckNumber();
        dsSach = new Sach[soLuong];

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Them sach thu " + (i + 1) + ": ");
            String loai = KiemTra.checkTheLoaiSach();
            switch (loai) {
                case "Sach Trinh Tham": dsSach[i] = new SachTrinhTham(); break;
                case "Sach Thieu Nhi": dsSach[i] = new SachThieuNhi(); break;
                case "Sach Tam Ly": dsSach[i] = new SachTamLy(); break;
                default: dsSach[i] = new Sach(); break;
            }
            dsSach[i].nhap();
        }

        setDsSach(dsSach);

        // Reset thể loại
        DanhSachTheLoai dstl = new DanhSachTheLoai();
        dstl.resetDsTheLoai();
    }

    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Chua co Sach nao!!");
            return;
        }
        System.out.println("=== Danh sach Sach ===");
        System.out.printf("%-15s %-25s %-20s %-20s %-10s %-10s\n",
                "Ma Sach", "Ten Sach", "Tac Gia", "Loai Sach", "So luong", "Gia");
        for (int i = 0; i < soLuong; i++) {
            dsSach[i].xuat();
        }
    }

    // ======== THEM/XOA/SUA =========
    public void themVaoDanhSach(PhanTu pt) {
        Sach[] temp = new Sach[soLuong + 1];
        System.arraycopy(dsSach, 0, temp, 0, soLuong);
        temp[soLuong] = (Sach) pt;
        soLuong++;
        setDsSach(temp);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong sach can them vao danh sach: ");
        int sl;
        do {
            sl = KiemTra.CheckNumber();
            if(sl <= 0) System.out.print("Nhap so lon hon 0! Moi nhap lai: ");
        } while(sl <= 0);

        for(int i=0;i<sl;i++) {
            System.out.println("Them sach thu " + (i+1) + ": ");
            String loai = KiemTra.checkTheLoaiSach();
            PhanTu pt;
            switch (loai) {
                case "Sach Trinh Tham": pt = new SachTrinhTham(); break;
                case "Sach Thieu Nhi": pt = new SachThieuNhi(); break;
                case "Sach Tam Ly": pt = new SachTamLy(); break;
                default: pt = new Sach(); break;
            }
            pt.nhap();
            themVaoDanhSach(pt);
        }

        DanhSachTheLoai dstl = new DanhSachTheLoai();
        dstl.resetDsTheLoai();
    }

    public void xoaPhanTu() {
        System.out.println("Tim sach can xoa: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            Sach[] temp = new Sach[soLuong - 1];
            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri) continue;
                temp[k++] = dsSach[i];
            }

            // Xoa khoi danh sach the loai
            DanhSachTheLoai dstl = new DanhSachTheLoai();
            dstl.xoaPhanTuMaSach(dsSach[viTri].getmaSach(), dsSach[viTri].getLoaiSach());

            soLuong--;
            setDsSach(temp);
            System.out.println("Xoa thanh cong!");
        } else System.out.println("Khong tim thay Sach!");
    }

    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim Sach can chinh sua: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            dsSach[viTri].suaThongTin();
            setDsSach(dsSach);
        } else System.out.println("Khong tim thay Sach!");
    }

    // ======== TIM KIEM =========
    public PhanTu timPhanTu() {
        System.out.print("Tim Sach theo ten (1) hay ma (2): ");
        int loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        System.out.print("Nhap gia tri can tim: ");
        String value = sc.nextLine();

        System.out.print("Tim chinh xac (1) hay tuong doi (2): ");
        int chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        for (int i = 0; i < soLuong; i++) {
            Sach s = dsSach[i];
            if (chon == 1) {
                if ((loai == 1 && s.gettenSach().equalsIgnoreCase(value)) ||
                    (loai == 2 && s.getmaSach().equalsIgnoreCase(value)))
                    return s;
            } else {
                if ((loai == 1 && s.gettenSach().contains(value)) ||
                    (loai == 2 && s.getmaSach().contains(value)))
                    return s;
            }
        }
        return null;
    }

    public int timViTriPhanTu() {
        System.out.print("Tim Sach theo ten (1) hay ma (2): ");
        int loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        System.out.print("Nhap gia tri can tim: ");
        String value = sc.nextLine();

        System.out.print("Tim chinh xac (1) hay tuong doi (2): ");
        int chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        for (int i = 0; i < soLuong; i++) {
            Sach s = dsSach[i];
            if (chon == 1) {
                if ((loai == 1 && s.gettenSach().equalsIgnoreCase(value)) ||
                    (loai == 2 && s.getmaSach().equalsIgnoreCase(value)))
                    return i;
            } else {
                if ((loai == 1 && s.gettenSach().contains(value)) ||
                    (loai == 2 && s.getmaSach().contains(value)))
                    return i;
            }
        }
        return -1;
    }

    public int timViTriSach(String maSach) {
        for(int i=0;i<soLuong;i++) {
            if(dsSach[i].getmaSach().equalsIgnoreCase(maSach))
                return i;
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String maSach) {
        for(int i=0;i<soLuong;i++) {
            if(dsSach[i].getmaSach().equalsIgnoreCase(maSach))
                return dsSach[i];
        }
        return null;
    }

    // ======== THONG KE =========
    public void thongKe() {
        dsSach = getDsSach();
        int chon, n;
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In Sach co so luong lon hon n");
            System.out.println("2. In Sach co gia ban lon hon n");
            System.out.println("0. Quay lai menu");
            System.out.print("Moi chon: ");
            chon = KiemTra.CheckNumber();
            switch(chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.CheckNumber();
                    for(Sach s : dsSach) if(s.getSoLuong() > n) s.xuat();
                    break;
                case 2:
                    System.out.print("Nhap gia ban can tim: ");
                    n = KiemTra.CheckNumber();
                    for(Sach s : dsSach) if(s.getPrice() > n) s.xuat();
                    break;
                default: chon = 0; break;
            }
        } while(chon != 0);
    }
}
