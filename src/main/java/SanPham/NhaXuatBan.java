package SanPham;

import DanhSach.DanhSachNhaXuatBan;
import KiemTra.KiemTra;
public class NhaXuatBan extends PhanTu {
    private String maNXB;
    private String tenNXB;
    private String diachi;
    private String email;

    public NhaXuatBan(){
    }

     public NhaXuatBan(String maNXB , String tenNXB , String diachi , String email){
        this.maNXB = maNXB ;
        this.tenNXB = tenNXB;
        this.diachi = diachi ;
        this.email = email;
    }

    public void setmaNXB(String a){
        maNXB = a;
    }

    public void setmaNXB(){
        System.out.print("nhap ma nha xuat ban: ");
        DanhSachNhaXuatBan ttds = new DanhSachNhaXuatBan();
        boolean check = false;
        do
        {
            maNXB = sc.nextLine();
            check = ttds.layPhanTuVoi(maNXB) == null;
            if(!check) System.out.print("Ma nha xuat ban da ton tai, moi nhap lai: ");
        } while(!check);
    }
    public void settenNXB(){
        System.out.print("Nhap ten nha xuat ban: ");
        tenNXB = sc.nextLine();
    }
    public void settenNXB(String tenNXB){
        this.tenNXB = tenNXB;
    }
    public void setDiaChi(String diachi){
        this.diachi = diachi;
    }
    public void setDiaChi(){
        System.out.print("Nhap dia chi nha xuat ban: ");
        diachi = sc.nextLine();
    }

    public void setemail(String email) {
        this.email = email;
    }
    public void setemail() {
        System.out.print("Nhap email nha xuat ban: ");
        boolean check = false;
        do {
            email = sc.nextLine();
            check = KiemTra.KiemTraEmail(email);
        } while (!check);
    }
    
    public String getmaNXB(){
        return maNXB;
    }

    public String gettenNXB(){
        return tenNXB;
    }

    public String getDiaChi() {
        return diachi;
    }

     public String getemail() {
        return email;
    }

    
    @Override
    public void nhap(){
        setmaNXB();
        settenNXB();
        setDiaChi();
        setemail();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-45s %-20s  \n",getmaNXB(),gettenNXB(),getDiaChi(),getemail());

    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin nha xuat ban ===");
            System.out.println("1. Sua ma nha xuat ban");
            System.out.println("2. Sua ten nha xuat ban");
            System.out.println("3. Sua dia chi nha xuat ban");
            System.out.println("4. Sua so dien thoai nha xuat ban");
            System.out.println("0. Quay ve menu quan ly thu vien");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin nha cung cap!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getmaNXB());
                    setmaNXB();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+gettenNXB());
                    settenNXB();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getDiaChi());
                    setDiaChi();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getemail());
                    setemail();
                    break;
                
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }

}

