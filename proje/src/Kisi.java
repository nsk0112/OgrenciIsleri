import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Kisi {
    private String kullaniciTuru;
    private String ad;
    private String soyad;
    private String dogumTarihi;
    private String sifre;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getKullaniciTuru() {return kullaniciTuru;}

    public void setKullaniciTuru() {this.kullaniciTuru = kullaniciTuru;}


    public Kisi() {
    }


    public Kisi(String kullaniciTuru, String ad, String soyad, String dogumTarihi, String sifre) {
        this.kullaniciTuru = kullaniciTuru;
        this.ad = ad;
        this.soyad = soyad;
        this.dogumTarihi = dogumTarihi;
        this.sifre = sifre;
    }


    public abstract void giris() throws IOException;

    public abstract void kisiEkle(int sec) throws IOException;

    public abstract void notGoruntule(String[] x) throws FileNotFoundException;

    @Override
    public String toString() {
        return "Kisi [ad=" + ad + ", dogumTarihi=" + dogumTarihi + ", sifre=" + sifre + ", soyad=" + soyad + "]";
    }

}

    

    

