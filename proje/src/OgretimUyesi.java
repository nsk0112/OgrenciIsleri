import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OgretimUyesi extends Kisi{
    private String unvan;
    private String tc;


    public OgretimUyesi(String kullaniciTuru, String ad, String soyad, String dogumTarihi, String sifre, String unvan, String tc) {
        super(kullaniciTuru, ad, soyad, dogumTarihi, sifre);
        this.unvan = unvan;
        this.tc = tc;
    }

    public OgretimUyesi() {
    }

    public String getUnvan() {
        return unvan;
    }

    public String getTc() {
        return tc;
    }

    @Override
    public void giris() throws FileNotFoundException {
        Scanner s1 = new Scanner(System.in);
        System.out.print("Ad-Soyad: ");
        String[] adsoyad = s1.nextLine().split(" ");
        String as = adsoyad[0].substring(0,1).toUpperCase()+adsoyad[0].substring(1)+" "+adsoyad[1].substring(0,1).toUpperCase()+adsoyad[1].substring(1);

        File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
        Scanner br = new Scanner(dosya);
        try{
        while(br.hasNextLine()){
            String satir = br.nextLine();
            String okunan[] = satir.split(" ");
            String kontrol = okunan[1]+" "+okunan[2];
            if(kontrol.equals(as)) {
                String[] kelimeler = okunan;
                System.out.print("Şifreniz: ");
                String sifre = s1.nextLine();
                if(okunan[5].equals(sifre)){
                    System.out.println("\nGiriş Başarılı");
                    OgretimUyesi o = new OgretimUyesi("OgretimUyesi", kelimeler[1], kelimeler[2],kelimeler[4],kelimeler[5], kelimeler[0],kelimeler[3] );
                    System.out.println("Hoşgeldiniz, " + o.getUnvan()+" "+o.getAd()+" "+o.getSoyad());
                    secenek(o);
                }
                else{
                    System.out.println("Bilgiler hatalı!");
                    giris();
                }
                break;
            }
        }
    }
        catch(Exception e){System.out.println(e);}
    }

    public void secenek(OgretimUyesi o) throws IOException {
        String[] a = {o.getAd(), o.getSoyad()};

        Scanner s = new Scanner(System.in);
        System.out.println("\nNot Ekle -> 1");
        System.out.println("Dersi Alan Öğrencileri Görüntüle -> 2");
        System.out.println("Derslerimi Görüntüle -> 3");
        System.out.println("Dersin Notlarını Görüntüle -> 4");
        System.out.println("Çıkış -> 0");
        System.out.print("-> ");
        int sec = s.nextInt();
        switch (sec){
            case 1:
                notGirisi(o);
                break;

            case 2:
                dersGoruntule(o);
                break;

            case 3:
                derslerim(o);
                break;

            case 4:
                notGoruntule(a);

            default:
                System.exit(0);
        }

    }

    @Override
    public void kisiEkle(int sec) throws IOException {
        System.out.println("Bu işlem için yetkiniz bulunmamaktadır.");
    }


    @Override
    public void notGoruntule(String[] a) throws FileNotFoundException {
        System.out.print("Notlarını görüntülemek istediğiniz dersin adı: ");
        Scanner s = new Scanner(System.in);
        String ders = s.nextLine();
        String[] derskelimeler = ders.split(" ");
        String[] u = null;
        String adsoyad = a[0]+" "+a[1];
        StringBuilder dersbirlestir = new StringBuilder();
        for (int j = 0; j < derskelimeler.length; j++){
            derskelimeler[j] = derskelimeler[j].substring(0,1).toUpperCase() + derskelimeler[j].substring(1);
            dersbirlestir.append(derskelimeler[j]);
        }
            String dersadi = System.getProperty("user.dir")+"\\src\\Dersler\\"+dersbirlestir+".txt";
            File dosya = new File(dersadi);
            Scanner s1 = new Scanner(dosya);
            String[] ilksatir = s1.nextLine().split("\\+");

            if(ilksatir[1]!=null && ilksatir[1].equals(adsoyad)){
                ArrayList<String> notlar = new ArrayList<>();
                float vizetoplam = 0;
                float finaltoplam = 0;
                int z = 0;
                while(s1.hasNextLine()){
                    if(z==0){
                        System.out.println(ilksatir[0]);
                        if(ilksatir.length==2){
                            System.out.println("Öğretim Görevlisi: "+ilksatir[1]);
                        }
                        else{
                        }
                        z++;
                    }
                    else{
                        String satir = s1.nextLine();
                        notlar.add(satir);
                        String[] x = satir.split(" ");
                        float vizepuan = Float.parseFloat(x[1]);
                        vizetoplam += vizepuan;
                        if(x.length==3){
                            float finalpuan = Float.parseFloat(x[2]);
                            finaltoplam += finalpuan;
                        }
                    }
                }
                System.out.println("      Vize   Final");
                for (String value : notlar) {
                    String[] get = value.split(" ");
                    System.out.print(get[0]+ "   ");
                    if(get.length==2){
                        System.out.println(get[1]+"      ");
                    }
                    else if(get.length>2){
                        System.out.print(get[1]+"      ");
                        System.out.println(get[2]+"    ");

                    }
                }
                float vizeortalama = vizetoplam/notlar.size();
                float finalortalama = finaltoplam/notlar.size();
                System.out.println("\nSınıfın vize not ortalaması : " + vizeortalama);
                System.out.println("Sınıfın final not ortalaması : " + finalortalama);
            }

            else{
                System.out.println("\nBu işlem için yetkiniz bulunmamaktadır.");
            }
    }

    public void notGirisi(Kisi o) throws IOException {
        System.out.print("Not girişi yapmak istediğiniz dersin adı: ");
        Scanner s = new Scanner(System.in);
        String ders = s.nextLine();
        String[] derskelimeler = ders.split(" ");
        StringBuilder dersbirlestir = new StringBuilder();

        for (int i = 0; i < derskelimeler.length; i++) {
            derskelimeler[i] = derskelimeler[i].substring(0, 1).toUpperCase() + derskelimeler[i].substring(1);
            dersbirlestir.append(derskelimeler[i]);
        }

        String dersadi = System.getProperty("user.dir")+"\\src\\Dersler\\" + dersbirlestir + ".txt";

        File dosya = new File(dersadi);
        ArrayList<String> hazir = new ArrayList<>();
        Scanner s1 = new Scanner(dosya);
        String ogr1 = s1.nextLine();
        hazir.add(ogr1);
        String[] ogr = ogr1.split("\\+");

        if (o.getKullaniciTuru().equals("Admin") || ogr[1].equals(o.getAd() + " " + o.getSoyad())) {

            while (s1.hasNextLine()) {
                hazir.add(s1.nextLine());
            }
            Scanner s2 = new Scanner(System.in);
            System.out.println("Klavyeden 'x' harfi girerek çıkış yapabilirsiniz\n");
            String a = "a";

            try {
                while (true) {
                    System.out.print("Öğrenci Numarası: ");
                    a = s2.nextLine();
                    if (a.equals("x")) {
                        break;
                    }
                    else {
                        if(hazir.size()==1){
                            System.out.print("Not: ");
                            String e = s2.nextLine();
                            hazir.add(a + " " + e);
                        }
                        else{
                            for (int j = 1; j < hazir.size(); j++) {
                                if (hazir.get(j).contains(a)) {
                                    System.out.print("Not: ");
                                    String b = s2.nextLine();
                                    String c = hazir.get(j);
                                    hazir.set(j, c + " " + b);
                                    break;
                                } else if (j == hazir.size() - 1) {
                                    System.out.print("Not: ");
                                    String d = s2.nextLine();
                                    hazir.add(a + " " + d);
                                    break;
                                } else{

                                }
                            }
                        }
                    }
                    notyaz(dersadi, hazir);

                }
                System.out.println(dersadi);
                if(o.getKullaniciTuru().equals("Admin")){
                    OgrenciIsleriCalisan v = new OgrenciIsleriCalisan();
                    v.secenek();
                }
                else if(ogr[1].equals(o.getAd() + " " + o.getSoyad())) ((OgretimUyesi)o).secenek((OgretimUyesi) o);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Bu işlem için yetkiniz bulunmamaktadır.");
            secenek((OgretimUyesi) o);
        }

    }

    public void notyaz(String dersadi, ArrayList<String> hazir) throws IOException {
        FileWriter fw = new FileWriter(dersadi, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for(int k=0; k<hazir.size(); k++){
            bw.write(hazir.get(k));
            bw.write("\n");
            bw.flush();
        }
        bw.close();
    }

    public void dersGoruntule(OgretimUyesi u) throws FileNotFoundException {
        System.out.print("Görüntülemek istediğiniz dersin adı: ");
        Scanner s = new Scanner(System.in);
        String ders = s.nextLine();
        String birles = "";
        String[] ayir = ders.split(" ");
        for(int i=0;i<ayir.length;i++){
            birles += ayir[i].substring(0,1).toUpperCase()+ayir[i].substring(1);
        }

        String path = System.getProperty("user.dir")+"\\src\\Dersler\\"+birles+".txt";

        File dosya = new File(path);
        Scanner s1 = new Scanner(dosya);
        ArrayList<String> no = new ArrayList<>();
        int ilk = 0;
        while (s1.hasNextLine()){
            String satir = s1.nextLine();
            if(ilk==0){
                ilk++;
                continue;
            }
            else{
                no.add(satir.substring(0,4));
            }
        }

        ArrayList<Ogrenci> o = new ArrayList<Ogrenci>();

        for(int j=0;j<no.size();j++){
            String path2 = System.getProperty("user.dir")+"\\src\\OgrenciListe.txt";
            File dosya2 = new File(path2);
            Scanner s2 = new Scanner(dosya2);
            while(s2.hasNextLine()){
                String satir = s2.nextLine();
                if(satir.contains(no.get(j))){
                    String[] x = satir.split(" ");
                    String b = "";
                    for(int k=7;k<x.length;k++) b+=x[k]+" ";
                    Ogrenci p = new Ogrenci("Ogrenci", x[0], x[1], x[2], x[3], x[4], x[5], x[6], b);
                    o.add(p);
                    break;
                }
            }

        }
        for(int i=0;i<o.size();i++){
            System.out.println(o.get(i).getOgrenciNo()+" "+o.get(i).getAd()+" "+o.get(i).getSoyad()+
                    " | "+o.get(i).getFakulte()+" | "+o.get(i).getBolum());
        }
        try {
            secenek(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void derslerim(OgretimUyesi o) throws FileNotFoundException {
        File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
        Scanner s = new Scanner(dosya);
        while (s.hasNextLine()){
            String tum = s.nextLine();
            String[] satir = tum.split(" ");
            if(satir[0].equals(o.getUnvan()) && satir[1].equals(o.getAd())){
                String[] dersler = null;
                String k = o.getSifre();
                String sifre = satir[5];
                if(sifre.equals(k)){
                    dersler = tum.split("\\+");
                }
                System.out.println();
                if(dersler==null) System.out.println("Dersiniz bulunmamaktadır.");
                else{
                    for(int i=1;i< dersler.length;i++){
                        System.out.println(dersler[i]);
                    }
                }
                break;
            }
            else{continue;}

        }
        try {
            secenek(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








