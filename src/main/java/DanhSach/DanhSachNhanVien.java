package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.NhanVien;
import SanPham.PhanTu;

public class DanhSachNhanVien implements DanhSachChung {
    private int soLuong;
    private NhanVien[] dsNhanVien;

    public DanhSachNhanVien() {
        this.dsNhanVien = this.getDsNhanVien();
    }

    public DanhSachNhanVien(int var1, NhanVien[] var2) {
        this.soLuong = var1;
        this. dsNhanVien = var2;
    }

    public int getSoluong() {
        return this.soLuong;
    }

    public void setSoLuong(int var1) {
        this.soLuong = var1;
    }

    public NhanVien[] getDsNhanVien() {
        String var1 = FileHander.docFile("dsnv.txt");
        String[] var2 = var1.split("\n");
        if(var2[0].length() == 0) {
            this.setSoLuong(0);
        } else {
            this.setSoLuong(Integer.parseInt(var2[0]));
        }

        this.dsNhanVien = new NhanVien[this.soLuong];
        int var4 = 0;
        
        for(int var7 = 1; var7 < var2.length; ++var7) {
            String[] var6 = var2[var7].split("#");
            int var5 = 0;
            NhanVien var3 = new NhanVien();
            var3.setMaNV(var6[var5++]);
            var3.setHoten(var6[var5++]);
            var3.setChucvu(var6[var5++]);
            var3.setNgaythangnamsinh(var6[var5++]);
            var3.setGioitinh(var6[var5++]);
            var3.setCCCD(var6[var5++]);
            var3.setDiachi(var6[var5++]);
            var3.setSdt(var6[var5++]);
            var3.setEmail(var6[var5++]);
            var3.setNgayvaolam(var6[var5++]);
            var3.setHesoluong(Double.parseDouble(var6[var5++]));
            var3.setSongaynghitrongthang(Integer.parseInt(var6[var5++]));
            this.dsNhanVien(var4++) = var3;
        }

        return this.dsNhanVien;
    }

    public void setDsNhanVien(Phan tu[] var1) {
        String var3 = "dsnv.txt";
        FileHandler.resetFile(var3);
        FileHandler.ghiFile("" + this.soLuong, var3);
        
        for(int var4 = 0; var4 < this.soLuong; ++var4) {
            NhanVien var2 = (NhanVien)var1[var4];
            FileHandler.themNv(var2.getMaNV(), var2.getHoten(), var2.getChucvu(), var2.getNgaythangnamsinh(), var2.getGioitinh(), var2.getCCCD(), var2.getDiachi(), var2.getSdt(), var2.getEmail(), var2.getNgayvaolam(), var2.getHesoluong(), var2.getSongaynghitrongthang());
        }
        this.dsNhanVien = (NhanVien[]) dsNhanVien;
    }

    public void themKPhanTuVaoDanhSach() {
        PhanTu pt;
        pt = new NhanVien();
        pt.nhap();
        themVaoDanhSach(pt);
    }

    public void chinhSuaThongTinPhanTu(String mnv) {
        int viTri = -1;
        for(int i = 0; i < soLuong; i ++) {
            if(dsNhanVien[i].getManhanvien().equals(mnv)) {
                viTri = i;
                break;
            }
        }
        NhanVien[] dsnv = getDsNhanVien();
        //tim thay
        if(viTri != -1) {
            dsnv[viTri].suaThongTin();
            setDsNhanVien(dsnv);
        } else 
             System.out.println("Khong tim thay!");
    }

    public void nhapDanhSach() {
        FileHandler.resetFile("dsnv.txt");
        System.out.println("Nhap so luong nhan vien: ");

        soLuong = KiemTra.checkNumber();
        dsNhanVien = new NhanVien[soLuong];

        int stt, soLuongTemp = 0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i ++) {
            dsNhanVien[i] = new NhanVien();
            stt = i + 1;

            System.out.println("** Nhan vien thu " + stt + "**" );
            dsNhanVien[i].nhap();

            soLuong += ++soLuongTemp;
            setDsNhanVien(dsNhanVien);
        }
    }

    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co nhan vien nao!!");
            return;
        }
        System.out.println("=== Danh sach nhan vien ===");
        for (int i = 0; i < soLuong; i ++) {
            getDsNhanVien()[i].xuat();
        }
        System.out.println();
    }
    public void themVaoDanhSach(PhanTu pt) {
        //tao mang tam
        NhanVien[] dsNhanVienTmp = new NhanVien[soLuong +1];
        for (int i = 0; i < soLuong; i ++)
           dsNhanVienTmp[i] = getDsNhanVien()[i];
        dsNhanVienTmp[soLuong] = (NhanVien) pt;
        soLuong++;
        setDsNhanVien(dsNhanVienTmp);
    }
}
