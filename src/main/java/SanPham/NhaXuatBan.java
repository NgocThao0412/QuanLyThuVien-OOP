package SanPham;

import DanhSach.DanhSachNhaXuatBan;
import KiemTra.KiemTra;
public class NhaXuatBan extends PhanTu {
    private String maNXB;
    private String tenNXB;
    private String diachi;
    private String sdt;

    public NhaXuatBan(){
    }

     public NhaXuatBan(String maNXB , String tenNXB , String diachi , String sdt){
        this.maNXB = maNXB ;
        this.tenNXB = tenNXB;
        this.diachi = diachi ;
        this.sdt = sdt;
    }

    public void setmaNXB(String a){
        maNXB = a;
    }

    public void setmaNXB() {
    DanhSachNhaXuatBan ttds = new DanhSachNhaXuatBan();
    NhaXuatBan[] ds = ttds.getdsNXB();
    int stt = 1;

    for (int i = 0; i < ds.length; i++) {
        String ma = ds[i].getmaNXB();     // ví dụ: NXB05
        int so = Integer.parseInt(ma.substring(3));  // tách số 05 → 5
        if (so >= stt) stt = so + 1;
    }

    // tạo mã
    if (stt > 9) maNXB = "NXB" + stt;
    else maNXB = "NXB0" + stt;

    System.out.println("Ma Nha Xuat Ban: " + maNXB);
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

    public void setsdt(String sdt) {
        this.sdt = sdt;
    }
    public void setsdt() {
        System.out.print("Nhap so dien thoai nha xuat ban: ");
        boolean check = false;
        do {
            sdt = sc.nextLine();
            check = KiemTra.KiemTraSDT(sdt);
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

     public String getsdt() {
        return sdt;
    }

    
    @Override
    public void nhap(){
        setmaNXB();
        settenNXB();
        setDiaChi();
        setsdt();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-30s %-45s %-20s   \n",getmaNXB(),gettenNXB(),getDiaChi(),getsdt());

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
                    System.out.println("Thong tin hien tai: "+getsdt());
                    setsdt();
                    break;
                
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }

}

