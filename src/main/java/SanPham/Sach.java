package SanPham;

import DanhSach.DanhSachSach;
import DanhSach.DanhSachTheLoai;
import KiemTra.KiemTra;
import java.util.Scanner;
public class Sach extends PhanTu {
    private String maSach;
    private String tenSach;
    private String TacGia;
    private String TheLoai;
    private int SoLuong;
    private int price;
    protected static Scanner sc = new Scanner(System.in);
    public Sach(){
    }

    public Sach(String maSach , String tenSach , String TacGia, String TheLoai, int SoLuong , int price ){
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.TacGia = TacGia;
        this.TheLoai = TheLoai;
        this.SoLuong = SoLuong;
        this.price = price;
    }

    public String getmaSach(){
        return maSach;
    }
    public void setmaSach(){
        DanhSachSach ttds = new DanhSachSach();
        DanhSachTheLoai dstl = new DanhSachTheLoai();
        System.out.print("Ban co muon xuat ra man hinh danh sach the loai khong ?(1 - in, 0 - khong):");
        int chon = KiemTra.CheckNumber();
        if(chon == 1) dstl.xuatDanhSach();
        System.out.print("Nhap ma Sach: ");
        boolean Check = false;
        do
        {
            maSach = sc.nextLine();

            Check = ttds.layPhanTuVoi(maSach) == null;
            if(!Check) System.out.print("Ma Sach da ton tai, moi nhap lai:  ");

            if(Check){
                Check = dstl.CheckmaTheLoai(maSach) == true;
                if(Check) System.out.print("Ma Sach phai trung 1 phan voi ma The Loai!! Moi nhap lại: ");

            }
        } while (!Check);
    }

    public void setmaSachTT(){
        DanhSachSach ttds = new DanhSachSach();
        Sach[] dss = ttds.getDsSach();
        int stt = 1;
        for( int i = 0; i < dss.length; i++){
            if(dss[i].getLosach ===");
            System.out.println("1. Sua ma Sach");
            System.out.println("2. Sua ten Sach");
            System.out.println("3. Sua tac gia");
            System.out.println("4. Sua so luong");
            System.out.println("5. Sua gia");
            System.out.println("0. Quay ve menu truoc");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon){
                case 0:
                    System.out.println("Thoat sua thong tin xe!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai:"+getmaSach());
                    setmaSach();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+gettenSach());
                    settenSach();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai:" +getTacGia());
                    setTacGia();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getSoLuong());
                    setSoLuong();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: "+getPrice());
                    setPrice();
                    break;
            }
        } while(chon!=0);
     }
}
