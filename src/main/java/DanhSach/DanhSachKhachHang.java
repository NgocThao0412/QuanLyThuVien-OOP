package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.KhachHang;
import Sach.PhanTu;

public class DanhSachKhachHang implements DanhSachChung {
    private int soLuong;
    private KhachHang[] dsKhachHang;

    public DanhSachKhachHang(){
        dsKhachHang = getDsKhachHang();
    }

    public DanhSachKhachHang(int soLuong, KhachHang[] dsKhachHang){
        this.soLuong = soLuong;
        this.dsKhachHang = dsKhachHang;
    }

    public int getSoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public KhachHang[] getDsKhachHang(){
        String data = FileHandler.docFile("dskh.txt");
        String[] dArr = data.split("\n");

        if (dArr.length == 0 || dArr[0].length() == 0) setSoLuong(0);
        else setSoLuong(Integer.parseInt(dArr[0]));

        dsKhachHang = new KhachHang[soLuong];
        KhachHang kh;

        String[] lArr;
        int k = 0, m;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;

            kh = new KhachHang();
            kh.setMaKhachHang(lArr[m++]);
            kh.setHoten(lArr[m++]);
            kh.setNgaythangnamsinh(lArr[m++]);
            kh.setGioitinh(lArr[m++]);
            kh.setCCCD(lArr[m++]);
            kh.setDiachi(lArr[m++]);
            kh.setSdt(lArr[m++]);
            kh.setEmail(lArr[m++]);

            int n = Integer.parseInt(lArr[m++]);
            String[] tmp = new String[n];
            for(int j = 0; j < n; j++) tmp[j] = lArr[m++];
            kh.setDsmspDamua(tmp);
            kh.setSoDonHangDaThanhToan(Integer.parseInt(lArr[m++]));
            kh.setTongTienDaThanhToan(Integer.parseInt(lArr[m++]));

            dsKhachHang[k++] = kh;
        }
        return dsKhachHang;
    }

    public void setDsKhachHang(PhanTu[] dsKhachhang){
        KhachHang kh;
        String tenFile = "dskh.txt";

        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i = 0; i < soLuong; i++) {
            kh = (KhachHang) dsKhachhang[i];

            FileHandler.themKH(
                kh.getMaKhachHang(),
                kh.getHoten(),
                kh.getNgaythangnamsinh(),
                kh.getGioitinh(),
                kh.getCCCD(),
                kh.getDiachi(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDsmspDamua(),
                kh.getSoDonHangDaThanhToan(),
                kh.getTongTienDaThanhToan(),
                null
            );
        }
        this.dsKhachHang = (KhachHang[]) dsKhachhang;
    }

    public void nhapDanhSach(){
        System.out.print("Nhap so luong doc gia: ");

        soLuong = KiemTra.CheckNumber();
        dsKhachHang = new KhachHang[soLuong];

        int stt, soLuongTemp = 0, soLuongCurrent = soLuong;

        for (int i = 0; i < soLuongCurrent; i++){
            dsKhachHang[i] = new KhachHang();
            stt = i+1;
            System.out.println("** Doc gia thu "+stt+" **");

            dsKhachHang[i].nhap();
            soLuong = ++soLuongTemp;
            setDsKhachHang(dsKhachHang);
        }
    }

    public void xuatDanhSach(){
        if(soLuong == 0) {
            System.out.println("Chua co doc gia nao!!");
            return;
        }
        System.out.println("=== Danh sach doc gia ===");
        for (int i = 0; i < soLuong; i++){
            getDsKhachHang()[i].xuat();
        }
        System.out.println();
    }

    public void themVaoDanhSach(PhanTu pt){
        KhachHang[] dsKhachHangTmp = new KhachHang[soLuong+1];

        for(int i = 0; i < soLuong; i++)
            dsKhachHangTmp[i] = getDsKhachHang()[i];

        dsKhachHangTmp[soLuong] = (KhachHang) pt;

        soLuong++;
        setDsKhachHang(dsKhachHangTmp);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong doc gia can them vao danh sach: ");
        int sl;
        boolean check;
        do {
            sl = KiemTra.CheckNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);

        PhanTu pt;
        for(int i = 0; i < sl; i++)
        {
            pt = new KhachHang();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void chinhSuaThongTinPhanTu(){
        System.out.println("Tim doc gia can chinh sua: ");
        int viTri = timViTriPhanTu();
        KhachHang[] dskh = getDsKhachHang();
        if (viTri != -1) {
            dskh[viTri].suaThongTin();
            setDsKhachHang(dskh);
        }
        else System.out.println("Khong tim thay!");
    }

    public void xoaPhanTu(){
        System.out.println("Tim doc gia can xoa: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            KhachHang[] dsKhachHangTmp = new KhachHang[soLuong-1];

            for(int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri) continue;
                dsKhachHangTmp[k++] = getDsKhachHang()[i];
            }

            soLuong--;
            setDsKhachHang(dsKhachHangTmp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay doc gia!");
    }

    public PhanTu timPhanTu(){
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int loai;
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        KhachHang[] dsKhachHangTmp = getDsKhachHang();

        for(int i = 0; i < soLuong; i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return dsKhachHangTmp[i];
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().contains(giaTriCanTim))
                        return dsKhachHangTmp[i];
            }
        }
        return null;
    }

    public int timViTriPhanTu() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int loai;
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        KhachHang[] dsKhachHangTmp = getDsKhachHang();

        for(int i = 0; i < soLuong; i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriKhachHang(String maKhachHang) {
        KhachHang[] dskh = getDsKhachHang();
        for(int i = 0; i < soLuong; i++) {
            if (dskh[i].getMaKhachHang().equals(maKhachHang))
                return i;
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamSo) {
        KhachHang[] dskh = getDsKhachHang();
        for(int i = 0; i < soLuong; i++) {
            if (dskh[i].getMaKhachHang().equals(thamSo))
                return dskh[i];
        }
        return null;
    }

    public void thongKe() {
        int chon, n;
        dsKhachHang = getDsKhachHang();
        do {
            System.out.println("=== Thong ke Doc Gia ===");
            System.out.println("1. Loc doc gia voi so luong sach da tung muon >= n");
            System.out.println("2. Loc doc gia co so sach dang muon >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.CheckNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong sach da muon can tim: ");
                    n = KiemTra.CheckNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang.getSoDonHangDaThanhToan() >= n) khachHang.xuat();
                    }
                    break;
                case 2:
                    System.out.print("Nhap so luong sach dang muon can tim: ");
                    n = KiemTra.CheckNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang.getTongTienDaThanhToan() >= n) khachHang.xuat();
                    }
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
