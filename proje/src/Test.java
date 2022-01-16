import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) throws IOException {
        Scanner s3 = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date tarih = new Date();
        System.out.println();
        System.out.format("%52s", sdf.format(tarih));
        System.out.println("\n\n---------- HOŞGELDİNİZ ----------");
        System.out.println("Öğrenci İşleri Elemanı Girişi -> 1");
        System.out.println("Öğrenci Girişi -> 2");
        System.out.println("Öğretim Üyesi Girişi -> 3");
        System.out.print("-> ");
        int sec = s3.nextInt();

        switch(sec){
            case 1:
                OgrenciIsleriCalisan o1 = new OgrenciIsleriCalisan();
                o1.giris();
                break; 

            case 2:
                Ogrenci og1 = new Ogrenci();
                og1.giris();
                break;

            case 3:
                OgretimUyesi ou1 = new OgretimUyesi();
                ou1.giris();
                break;

            default:
                System.out.println("Lütfen geçerli bir rakam giriniz.");

        }
    }
}
