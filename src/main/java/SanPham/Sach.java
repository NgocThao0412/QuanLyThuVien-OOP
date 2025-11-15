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
                Check = dstl.checkMaTheLoai(maSach) == true;
                if(Check) System.out.print("Ma Sach phai trung 1 phan voi ma The Loai!! Moi nhap lại: ");

            }
        } while (!Check);
    }

    public void setmaSachTT(){
        DanhSachSach ttds = new DanhSachSach();
        Sach[] dss = ttds.getDsSach();
        int stt = 1;
        for( int i = 0; i < dss.length; i++){
            if(dss[i].getLoaiSach().equals("Sach Trinh Tham")){
                stt = Integer.parseInt(dss[i].getmaSach().substring(5))+1;
            }
        }
        if(stt>9) maSach = "DETECTIVE"+ stt;
        else maSach = "DETECTIVE0"+ stt;
        System.out.println("Ma Sach:" + maSach);
    }

    public void setmaSachTN(){
        DanhSachSach ttds = new DanhSachSach();
        Sach[] dss = ttds.getDsSach();
        int stt = 1;
        for( int i = 0; i < dss.length; i++){
            if(dss[i].getLoaiSach().equals("Sach Thieu Nhi")){
                stt = Integer.parseInt(dss[i].getmaSach().substring(8))+1;
            }
        }
        if(stt>9) maSach = "KID"+ stt;
        else maSach = "KID"+ stt;
        System.out.println("Ma Sach:" + maSach);
    }

      public void setmaSachTL(){
        DanhSachSach ttds = new DanhSachSach();
        Sach[] dss = ttds.getDsSach();
        int stt = 1;
        for( int i = 0; i < dss.length; i++){
            if(dss[i].getLoaiSach().equals("Sach Tam Ly")){
                stt = Integer.parseInt(dss[i].getmaSach().substring(7))+1;
            }
        }
        if(stt>9) maSach = "PSYCHO"+ stt;
        else maSach = "PSYCHO"+ stt;
        System.out.println("Ma Sach:" + maSach);
    }

    public void setmaSach(String maSach){
        this.maSach = maSach;
    }

    public String gettenSach(){
        return tenSach;
    }

    public void settenSach(){
        System.out.print("nhap ten Sach:");
        tenSach = sc.nextLine();
    }

    public void settenSach(String tenSach){
        this.tenSach = tenSach;
    }

    public void setTacGia(String TacGia){
        this.TacGia = TacGia;
    }

    public void setTacGia(){
        System.out.print("Nhap ten Tac Gia: ");
        TacGia = sc.nextLine();
    }
    
     public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    public void setSoLuong() {
        System.out.print("Nhap so luong: ");
        boolean check = false;
        do {
            SoLuong = KiemTra.CheckNumber();
            check = SoLuong > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPrice() {
        System.out.print("Vui long nhap gia: ");
        price = KiemTra.CheckNumber();
    }

    public String getTacGia(){
        return TacGia;
    }

    public int getSoLuong(){
        return SoLuong;
    }

    public int getPrice(){
        return price;
    }

    public String getLoaiSach(){
        return TheLoai;
    }

    public void setLoaiSach(String TheLoai){
        this.TheLoai = TheLoai;
    }

    public void setLoaiSach(){
        DanhSachTheLoai dstl = new DanhSachTheLoai();
        TheLoai = dstl.getTheLoai(maSach);
    }
     @Override
     public void nhap(){
        setmaSach();
        settenSach();
        setTacGia();
        setLoaiSach();
        setSoLuong();
        setPrice();
     }

     public void nhapSTT(){
        setmaSachTT();
        settenSach();
        setTacGia();
        setLoaiSach();
        setSoLuong();
        setPrice();
     }

     public void nhapSTN(){
        setmaSachTN();
        settenSach();
        setTacGia();
        setLoaiSach();
        setSoLuong();
        setPrice();
     }

     public void nhapSTL(){
        setmaSachTL();
        settenSach();
        setTacGia();
        setLoaiSach();
        setSoLuong();
        setPrice();
     }

     public String in(){
        String data = "Ma Sach: " + getmaSach() + "\t\tTen Sach: " + gettenSach()
        + "\t\tTac Gia: " + getTacGia() + "\t\tLoai Sach:" + getLoaiSach()
        + "\nSo luong:" + getSoLuong() + "\t\tGia: " + getPrice();
        return data;
     }
     @Override
     public void xuat() {
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n",maSach,tenSach,TacGia,TheLoai,SoLuong,price);
     }
     @Override
     public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin xe ===");
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
