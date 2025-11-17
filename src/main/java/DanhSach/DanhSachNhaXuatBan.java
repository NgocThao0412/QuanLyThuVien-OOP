package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.NhaXuatBan;
import SanPham.PhanTu;

import java.util.Scanner;

public class DanhSachNhaXuatBan implements DanhSachChung {
    private int SoLuong;
    private NhaXuatBan[] dsNXB;
    private static Scanner sc = new Scanner(System.in);

    public DanhSachNhaXuatBan() {
        dsNXB = getdsNXB();
        if (dsNXB == null) {
            dsNXB = new NhaXuatBan[0];
            SoLuong = 0;
        } else {
            SoLuong = dsNXB.length;
        }
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }


    public NhaXuatBan[] getdsNXB() {
        String data = FileHandler.docFile("dsnxb.txt");
        if (data == null || data.trim().isEmpty()) {
            SoLuong = 0;
            dsNXB = new NhaXuatBan[0];
            return dsNXB;
        }

        String[] dArr = data.split("\n");
        if (dArr.length == 0 || dArr[0].trim().isEmpty()) {
            SoLuong = 0;
            dsNXB = new NhaXuatBan[0];
            return dsNXB;
        }

        int count;
        try {
            count = Integer.parseInt(dArr[0].trim());
        } catch (Exception e) {
            count = Math.max(0, dArr.length - 1);
        }

        // đảm bảo không vượt quá số dòng thực tế
        int possible = Math.max(0, dArr.length - 1);
        SoLuong = Math.min(count, possible);

        dsNXB = new NhaXuatBan[SoLuong];
        int k = 0;

        for (int i = 1; i < dArr.length && k < SoLuong; i++) {
            String line = dArr[i].trim();
            if (line.isEmpty()) continue;

            String[] lArr = line.split("#");
            // cần tối thiểu 4 phần: ma, ten, dia chi, email
            if (lArr.length < 4) continue;

            int m = 0;
            NhaXuatBan nxb = new NhaXuatBan();

            // sửa lỗi gọi sai cú pháp: nxb.setmaNXB();(lArr[m++]);
            nxb.setmaNXB(lArr[m++].trim());
            nxb.settenNXB(lArr[m++].trim());
            nxb.setDiaChi(lArr[m++].trim());
            nxb.setsdt(lArr[m++].trim());

            dsNXB[k++] = nxb;
        }

        // nếu actual loaded < SoLuong, rút mảng
        if (k != SoLuong) {
            NhaXuatBan[] tmp = new NhaXuatBan[k];
            System.arraycopy(dsNXB, 0, tmp, 0, k);
            dsNXB = tmp;
            SoLuong = k;
        }

        return dsNXB;
    }

    public void setdsNXB(NhaXuatBan[] dsNXB) { // ghi file
        if (dsNXB == null) dsNXB = new NhaXuatBan[0];

        String tenFile = "dsnxb.txt";
        FileHandler.resetFile(tenFile);
        // ghi số lượng + newline
        FileHandler.ghiFile(dsNXB.length + "\n", tenFile);

        for (int i = 0; i < dsNXB.length; i++) {
            NhaXuatBan nxb = dsNXB[i];
            if (nxb == null) continue;
            // dùng phương thức FileHandler.themNXB(...) - giả định đã có
            FileHandler.themNXB(nxb.getmaNXB(), nxb.gettenNXB(), nxb.getDiaChi(), nxb.getsdt());
        }
        this.dsNXB = dsNXB;
        this.SoLuong = dsNXB.length;
    }

    public void nhapDanhSach() {
        FileHandler.resetFile("dsnxb.txt");
        System.out.print("Moi nhap so luong nha xuat ban: ");

        SoLuong = KiemTra.CheckNumber();
        while (SoLuong <= 0) {
            System.out.print("Nhap so lon hon 0! Moi nhap lai: ");
            SoLuong = KiemTra.CheckNumber();
        }

        dsNXB = new NhaXuatBan[SoLuong];

        for (int i = 0; i < SoLuong; i++) {
            System.out.println("Nha xuat ban " + (i + 1) + ": ");
            dsNXB[i] = new NhaXuatBan();
            dsNXB[i].nhap();
        }

        setdsNXB(dsNXB);
    }

    
    public void xuatDanhSach() {
        if (SoLuong == 0 || dsNXB == null || dsNXB.length == 0) {
            System.out.println("Chua co Nha Xuat Ban nao!!");
            return;
        }
        System.out.println("=== Danh sach Nha Xuat Ban ===");
        System.out.printf("%-20s %-30s %-45s %-20s \n", "Ma nha xuat ban", "Ten nha xuat ban", "Dia chi", "Email");
        for (int i = 0; i < SoLuong; i++) {
            if (dsNXB[i] != null) dsNXB[i].xuat();
        }
        System.out.println();
    }

    
    public void themVaoDanhSach(PhanTu pt) {
        if (pt == null) return;
        NhaXuatBan[] current = getdsNXB();
        int cur = (current == null) ? 0 : current.length;
        NhaXuatBan[] dsNXBTemp = new NhaXuatBan[cur + 1];
        for (int i = 0; i < cur; i++) dsNXBTemp[i] = current[i];
        dsNXBTemp[cur] = (NhaXuatBan) pt;
        setdsNXB(dsNXBTemp);
    }

    
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong nha xuat ban can them vao danh sach: ");
        int sl;
        do {
            sl = KiemTra.CheckNumber();
            if (sl <= 0) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while (sl <= 0);

        PhanTu pt;
        for (int i = 0; i < sl; i++) {
            pt = new NhaXuatBan();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim nha xuat ban can chinh sua: ");
        int viTri = timViTriPhanTu();

        NhaXuatBan[] dssp = getdsNXB();

        if (viTri != -1 && viTri < dssp.length) {
            dssp[viTri].suaThongTin();
            setdsNXB(dssp);
        } else System.out.println("Khong tim thay nha xuat ban!");
    }

    
    public void xoaPhanTu() {
        System.out.println("Tim nha xuat ban can xoa !");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            NhaXuatBan[] current = getdsNXB();
            if (current == null || current.length == 0) {
                System.out.println("Danh sach rong!");
                return;
            }
            NhaXuatBan[] dsNXBTemp = new NhaXuatBan[current.length - 1];
            for (int i = 0, k = 0; i < current.length; i++) {
                if (i == viTri) continue;
                dsNXBTemp[k++] = current[i];
            }
            setdsNXB(dsNXBTemp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay nha xuat ban!");
    }

  
    public PhanTu timPhanTu() {
        int loai;
        System.out.print("Tim NXB theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten NXB can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma NXB can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Tim chinh xac (1)/ tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        NhaXuatBan[] dsSanPhamTmp = getdsNXB();
        if (dsSanPhamTmp == null) return null;

        for (int i = 0; i < dsSanPhamTmp.length; i++) {
            NhaXuatBan cur = dsSanPhamTmp[i];
            if (cur == null) continue;
            if (chon == 1) { // tìm chính xác
                if (loai == 1 && cur.gettenNXB().equalsIgnoreCase(giaTriCanTim)) return cur;
                if (loai == 2 && cur.getmaNXB().equalsIgnoreCase(giaTriCanTim)) return cur;
            } else {
                if (loai == 1 && cur.gettenNXB().contains(giaTriCanTim)) return cur;
                if (loai == 2 && cur.getmaNXB().contains(giaTriCanTim)) return cur;
            }
        }
        return null;
    }

    // ========== TIM (tra vi tri) ==========
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim NXB theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1) System.out.print("Nhap ten NXB can tim: ");
        if (loai == 2) System.out.print("Nhap ma NXB can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        NhaXuatBan[] dsSanPhamTmp = getdsNXB();
        if (dsSanPhamTmp == null) return -1;

        for (int i = 0; i < dsSanPhamTmp.length; i++) {
            NhaXuatBan cur = dsSanPhamTmp[i];
            if (cur == null) continue;
            if (chon == 1) {
                if (loai == 1 && cur.gettenNXB().equalsIgnoreCase(giaTriCanTim)) return i;
                if (loai == 2 && cur.getmaNXB().equalsIgnoreCase(giaTriCanTim)) return i;
            } else {
                if (loai == 1 && cur.gettenNXB().contains(giaTriCanTim)) return i;
                if (loai == 2 && cur.getmaNXB().contains(giaTriCanTim)) return i;
            }
        }
        return -1;
    }

    
    public int timViTriSanPham(String maSanPham) {
        NhaXuatBan[] dsSanPhamTmp = getdsNXB();
        if (dsSanPhamTmp == null) return -1;
        for (int i = 0; i < dsSanPhamTmp.length; i++) {
            if (dsSanPhamTmp[i] != null && dsSanPhamTmp[i].gettenNXB().equalsIgnoreCase(maSanPham))
                return i;
        }
        return -1;
    }

    
    public PhanTu layPhanTuVoi(String thamSo) {
        NhaXuatBan[] dssp = getdsNXB();
        if (dssp == null) return null;
        for (int i = 0; i < dssp.length; i++) {
            if (dssp[i] != null && dssp[i].getmaNXB().equalsIgnoreCase(thamSo))
                return dssp[i];
        }
        return null;
    }

    
    public void thongKe() {
        dsNXB = getdsNXB();
        if (dsNXB == null || dsNXB.length == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        int chon;
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In Nha xuat ban theo ma nha xuat ban ");
            System.out.println("2. In Nha xuat ban theo email");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.CheckNumber();
            String giatricanloc;
            boolean check;

            switch (chon) {
                case 1:
                    System.out.print("Nhap ma nha xuat ban can loc: ");
                    giatricanloc = sc.nextLine().trim();
                    check = false;
                    for (NhaXuatBan n : dsNXB) if (n != null && n.getmaNXB().equalsIgnoreCase(giatricanloc)) { check = true; break; }
                    if (!check) {
                        System.out.println("Ma nha xuat ban ko ton tai.");
                        break;
                    } else {
                        for (NhaXuatBan n : dsNXB) if (n != null && n.getmaNXB().equalsIgnoreCase(giatricanloc)) n.xuat();
                    }
                    break;
                case 2:
                    System.out.print("Nhap so dien thoai can loc: ");
                    giatricanloc = sc.nextLine().trim();
                    check = false;
                    for (NhaXuatBan n : dsNXB) if (n != null && n.getsdt().equalsIgnoreCase(giatricanloc)) { check = true; break; }
                    if (!check) {
                        System.out.println("Khong ton tai nha xuat ban co so dien thoai nay.");
                        break;
                    } else {
                        for (NhaXuatBan n : dsNXB) if (n != null && n.getsdt().equalsIgnoreCase(giatricanloc)) n.xuat();
                    }
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
