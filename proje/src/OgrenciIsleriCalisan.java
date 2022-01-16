import java.io.*;
import java.util.*;

public class OgrenciIsleriCalisan extends Kisi{

    public OgrenciIsleriCalisan() {
    }

    public OgrenciIsleriCalisan(String kullaniciTuru, String ad, String soyad, String dogumTarihi, String sifre) {
        super("OgrenciIsleri", ad, soyad, dogumTarihi, sifre);
    }

    @Override
    public void giris(){

        Scanner s2 = new Scanner(System.in);
        String kullanici = "admin";
        String sifre = "admin";
        System.out.print("\nKullanıcı adı: ");
        String kullanici1 = s2.nextLine();
        System.out.print("Şifre: ");
        String sifre1 = s2.nextLine();

        if (kullanici1.equals(kullanici)){
            if(sifre1.equals(sifre)){
                System.out.println("\n\nHOŞGELDİNİZ...");
                secenek();
            }
            else{
                System.out.println("Şifre yanlış...");
                giris();
            }
        }
        else{
            System.out.println("Kullanıcı adı yanlış...");
            giris();
        }
        
    }


    public void secenek(){
        System.out.println("\nYapmak istediğiniz işlemi seçiniz:");
        System.out.println("Öğrenci İşlemleri -> 1");
        System.out.println("Not ve Ders İşlemleri -> 2");
        System.out.println("Öğretim Elemanı İşlemleri -> 3");
        System.out.println("Çıkış -> 0");
        System.out.print("-> ");

        Scanner s1 = new Scanner(System.in);
        int sec = s1.nextInt();
        String[] a = null;

        try{
            switch(sec){
                case 1:
                    System.out.println("\nÖğrenci Not Görüntüle -> 1");
                    System.out.println("Öğrenci Ekle -> 2");
                    System.out.println("Öğrenci Sil -> 3");
                    System.out.println("Bölümdeki Öğrencileri Görüntüle -> 4");
                    System.out.println("Çıkış -> 0");
                    System.out.print("-> ");
                    int sec1 = s1.nextInt();

                    if (sec1==1){notGoruntule(a);}
                    else if(sec1 == 2){kisiEkle(1);}
                    else if(sec1 == 3){kisiSil(1);}
                    else if(sec1 == 4){bolumOgr();}
                    else if(sec1 == 0){secenek();}
                    break;

                case 2:
                    System.out.println("\nNot Ekleme -> 1");
                    System.out.println("Not Görüntüleme -> 2");
                    System.out.println("Yeni Ders Oluştur -> 3");
                    System.out.println("Çıkış -> 0");
                    System.out.print("-> ");
                    int sec2 = s1.nextInt();
                    if(sec2==1){
                        OgretimUyesi o = new OgretimUyesi();
                        o.notGirisi(new OgretimUyesi("Admin", "Admin", "Admin", "Admin", "Admin", "Admin", "Admin"));
                        secenek();
                    }
                    else if(sec2 == 2){dersNotGoruntule();}
                    else if(sec2 == 3){dersolustur();}
                    else if(sec2 == 0){secenek();}

                    break;

                case 3:
                    System.out.println("\nÖğretim Elemanı Ekle -> 1");
                    System.out.println("Öğretim Elemanı Sil -> 2");
                    System.out.println("Öğretim Elemanı Ders Ata -> 3");
                    System.out.println("Bölümün Öğretim Üyelerini Görüntüle -> 4");
                    System.out.println("Çıkış -> 0");
                    System.out.print("-> ");
                    int sec3 = s1.nextInt();

                    if(sec3==1){kisiEkle(2);}
                    else if(sec3 == 2){kisiSil(2);}
                    else if(sec3 == 3){dersata();}
                    else if(sec3 == 4){ogrEGoruntule();}
                    else if(sec3 == 0){secenek();}
                    break;

                default:
                    System.exit(0);
            }
        }
        catch(Exception e){
            System.out.println(e);
    }}


    @Override
    public void kisiEkle(int sec) throws IOException {

        if(sec == 1){
            Scanner s2 = new Scanner(System.in);
            System.out.println("\nFakülte");
            System.out.println("Teknoloji -> 1");
            System.out.println("Mühendislik -> 2");
            System.out.println("Fen-Edebiyat -> 3");
            System.out.print("-> ");
            String bolum = System.getProperty("user.dir")+"\\src\\Bolumler\\";
            int f = Integer.parseInt(s2.nextLine());
            String fakulte = "";
            switch (f){
                case 1:
                    bolum += "Tek";
                    fakulte = "Teknoloji";
                    break;

                case 2:
                    bolum += "Muh";
                    fakulte = "Muhendislik";
                    break;

                case 3:
                    bolum += "Fen";
                    fakulte += "Fen-Edebiyat";
            }
            BufferedWriter bw;

            if(f==1){
                System.out.println("\nBilgisayar Mühendisliği -> 1");
                System.out.println("Elektrik-Elektronik Mühendisliği -> 2");
                System.out.print("-> ");
                String bolum2 = "";
                int b = Integer.parseInt(s2.nextLine());

                switch (b){
                    case 1:
                        bolum += "BilMuh.txt";
                        bolum2 = "Bilgisayar Muhendisligi";
                        break;
                    case 2:
                        bolum += "EleMuh.txt";
                        bolum2 = "Elektrik-Elektronik Muhendisligi";
                        break;
                }

                FileWriter fw = new FileWriter(bolum, true);
                bw = new BufferedWriter(fw);
                ogrenciYaz(bw, s2, fakulte, bolum2);

            }

            else if(f==2){
                System.out.println("\nBilgisayar Mühendisliği -> 1");
                System.out.println("Elektrik-Elektronik Mühendisliği -> 2");
                System.out.print("-> ");
                String bolum2 = "";
                int b = Integer.parseInt(s2.nextLine());

                switch (b){
                    case 1:
                        bolum += "BilMuh.txt";
                        bolum2 = "Bilgisayar Muhendisligi";
                        break;

                    case 2:
                        bolum += "EleMuh.txt";
                        bolum2 = "Elektrik-Elektronik Muhendisligi";
                        break;
                }
                FileWriter fw = new FileWriter(bolum, true);
                bw = new BufferedWriter(fw);
                ogrenciYaz(bw, s2, fakulte, bolum2);
            }

            else if(f==3){
                System.out.println("\nPsikoloji -> 1");
                System.out.println("İstatistik -> 2");
                System.out.print("-> ");
                String bolum2 = "";
                int b = Integer.parseInt(s2.nextLine());

                switch (b){
                    case 1:
                        bolum += "Psi.txt";
                        bolum2 = "Psikoloji";
                        break;

                    case 2:
                        bolum += "Ist.txt";
                        bolum2 = "Istatistik";
                        break;
                }
                FileWriter fw = new FileWriter(bolum, true);
                bw = new BufferedWriter(fw);
                ogrenciYaz(bw, s2, fakulte, bolum2);
            }
            }

            else{
                Scanner s3 = new Scanner(System.in);
                FileWriter fw = new FileWriter(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                

                try{
                    System.out.print("Unvanı: ");
                    String a = s3.nextLine();
                    String b= a.substring(0,1).toUpperCase()+a.substring(1);
                    bw.write(b);
                    bw.write(" ");
                    System.out.print("Adı: ");
                    a = s3.nextLine();
                    b= a.substring(0,1).toUpperCase()+a.substring(1);
                    bw.write(b);
                    bw.write(" ");
                    System.out.print("Soyadı: ");
                    a = s3.nextLine();
                    b= a.substring(0,1).toUpperCase()+a.substring(1);
                    bw.write(b);
                    bw.write(" ");
                    System.out.print("T.C. Kimlik No: ");
                    bw.write(s3.nextLine());
                    bw.write(" ");
                    System.out.print("Doğum Tarihi: ");
                    bw.write(s3.nextLine());
                    bw.write(" ");
                    String sif = sifreUret();
                    System.out.println("Şifreniz: " + sif);
                    bw.write(sif);
                    bw.write(" ");
                    System.out.print("Fakülte: ");
                    a = s3.nextLine();
                    b= a.substring(0,1).toUpperCase()+a.substring(1);
                    bw.write(b);
                    bw.write(" ");
                    System.out.print("Bölüm: ");
                    a = s3.nextLine();
                    b= a.substring(0,1).toUpperCase()+a.substring(1);
                    bw.write(b);
                    bw.write("+");
                    bw.write("\n");
                    bw.close();


                }catch (IOException e) {e.printStackTrace();}
            }
            secenek();
        }


    public void ogrenciYaz(BufferedWriter bw, Scanner s2, String fakulte, String bolum) throws IOException{
        FileWriter fw2 = new FileWriter(System.getProperty("user.dir")+"\\src\\OgrenciListe.txt", true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        File dosya = new File(System.getProperty("user.dir")+"\\src\\OgrenciListe.txt");
        Scanner s = new Scanner(dosya);
        int no = 0;
        while(s.hasNextLine()){
            no=Integer.parseInt(s.nextLine().split(" ")[0]);
        }
        no=no+1;
        String numara = String.valueOf(no);
        String a;

        try {
            bw.write("\n");
            bw2.write("\n");
            System.out.println("Öğrenci Numarası: " + numara);
            bw.write(numara);
            bw.write(" ");
            bw2.write(numara);
            bw2.write(" ");
            System.out.print("Adı: ");
            a=s2.nextLine();
            String x = a.substring(0,1).toUpperCase()+a.substring(1);
            bw.write(x);
            bw.write(" ");
            bw2.write(x);
            bw2.write(" ");
            System.out.print("Soyadı: ");
            a=s2.nextLine();
            x = a.substring(0,1).toUpperCase()+a.substring(1);
            bw.write(x);
            bw.write(" ");
            bw2.write(x);
            bw2.write(" ");
            System.out.print("Doğum Tarihi: ");
            a=s2.nextLine();
            bw2.write(a);
            bw2.write(" ");
            System.out.print("T.C. Kimlik Numarası: ");
            a=s2.nextLine();
            bw2.write(a);
            bw2.write(" ");
            System.out.print("Şifre: ");
            a=sifreUret();
            System.out.println(a);
            bw2.write(a);
            bw2.write(" ");
            bw2.write(fakulte);
            bw2.write(" ");
            bw2.write(bolum);
            bw.close();
            bw2.close();
            System.out.println("Lütfen öğrenciden şifresini en kısa sürede değiştirmesini isteyiniz.");

        } catch (IOException e) {e.printStackTrace();}
    }


    @Override
    public void notGoruntule(String[] a) throws FileNotFoundException {
        System.out.print("Notlarını görüntülemek istediğiniz öğrencinin öğrenci numarası: ");
        Scanner scanner = new Scanner(System.in);
        String no = scanner.nextLine();
        String path = System.getProperty("user.dir")+"\\src\\Dersler";
        File dosya = new File(path);

        if(dosya.isDirectory()){
            File[] tum = dosya.listFiles();
            for(int i = 0; i < tum.length; i++){
                Scanner s = new Scanner(tum[i]);
                int x = 0;
                String dersadi = "";
                String egitmen = "";
                while(s.hasNextLine()){
                    String str = s.nextLine();
                    if(x==0){
                        dersadi = str;
                        String[] ayir = dersadi.split("\\+");
                        dersadi = ayir[0];
                        if(ayir.length>1){
                            egitmen = ayir[1];
                            x++;
                        }
                        else x++;

                    }
                    else{
                        if(str.contains(no)){
                            System.out.println(dersadi);
                            if(egitmen!=null)System.out.println("Öğretim Görevlisi: " + egitmen);
                            String[] ayir = str.split(" ");
                            System.out.print("Vize: " + ayir[1]);
                            if(ayir.length>2){
                                System.out.println("    Final: " + ayir[2]);
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
        try {
            secenek();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dersNotGoruntule() throws FileNotFoundException {
        System.out.print("Notlarını görüntülemek istediğiniz dersin adı: ");
        Scanner s = new Scanner(System.in);
        String ders = s.nextLine();
        String[] derskelimeler = ders.split(" ");
        String[] u = null;
        StringBuilder dersbirlestir = new StringBuilder();
        for (int j = 0; j < derskelimeler.length; j++){
            derskelimeler[j] = derskelimeler[j].substring(0,1).toUpperCase() + derskelimeler[j].substring(1);
            dersbirlestir.append(derskelimeler[j]);
        }
        String dersadi = System.getProperty("user.dir")+"\\src\\Dersler\\"+dersbirlestir+".txt";
        File dosya = new File(dersadi);
        Scanner s1 = new Scanner(dosya);
        String[] ilksatir = s1.nextLine().split("\\+");

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
            secenek();

    }


    public String sifreUret(){
        String[] karakterler = "abcdefghijklmnoprstuvyz0123456789".split("");
        String sifre = "";
        Random r = new Random();
        for(int i=0; i<6;i++) sifre+=karakterler[r.nextInt(32)];
        return sifre;
    }


    public void kisiSil(int sil){
        if(sil==1){
            System.out.print("Silmek istediğiniz öğrencinin öğrenci numarası: ");
            Scanner s1 = new Scanner(System.in);
            String no = s1.nextLine();
            File dosya = new File(System.getProperty("user.dir")+"\\src\\OgrenciListe.txt");
            Scanner giris;
            try{
                giris = new Scanner(dosya);
                ArrayList<String> satir = new ArrayList<>();
                
                while(giris.hasNextLine()){
                    String oku = giris.nextLine();
                    if(oku.startsWith(no)){
                        continue;
                    }
                    else{
                        satir.add(oku);
                    }
                }
                PrintWriter yaz = new PrintWriter(dosya);
                for (String value : satir) {
                    yaz.write(value);
                    yaz.write("\n");
                    yaz.flush();
                }
                yaz.close();

                String path = System.getProperty("user.dir")+"\\src\\Dersler";
                File ders = new File(path);

                if(ders.isDirectory()){
                    File[] tum = ders.listFiles();
                    if (tum != null) {
                        for(int j = 0; j < tum.length; j++){
                            Scanner s = new Scanner(tum[j]);
                            ArrayList<String> sinif = new ArrayList<>();
                            while(s.hasNextLine()){
                                String str = s.nextLine();
                                if(str.startsWith(no)){
                                    continue;
                                }
                                else{
                                    sinif.add(str);
                                }
                            }
                            PrintWriter sinifyaz = new PrintWriter(tum[j]);
                            for(String value : sinif){
                                sinifyaz.write(value);
                                sinifyaz.write("\n");
                                sinifyaz.flush();
                            }
                            sinifyaz.close();

                        }
                    }
                }

                File bolumler = new File(System.getProperty("user.dir")+"\\src\\Bolumler");
                if(bolumler.isDirectory()){
                    File[] tum2 = bolumler.listFiles();
                    for(int k=0;k< tum2.length;k++){
                        Scanner s = new Scanner(tum2[k]);
                        ArrayList<String> bolum = new ArrayList<>();
                        while(s.hasNextLine()){
                            String str = s.nextLine();
                            if (str.startsWith(no)){
                                continue;
                            }
                            else{
                                bolum.add(str);
                            }
                            PrintWriter bolumyaz = new PrintWriter(tum2[k]);
                            for(String b : bolum){
                                bolumyaz.write(b);
                                bolumyaz.write("\n");
                                bolumyaz.flush();
                            }
                            bolumyaz.close();
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {e.printStackTrace();}
            finally {
                secenek();
            }

        }

        else{
            Scanner s1 = new Scanner(System.in);
            System.out.print("Silmek istediğiniz kişinin adı ve soyadı: ");
            String[] ayir = s1.nextLine().split(" ");
            String adsoyad = "";
            adsoyad+=ayir[0].substring(0,1).toUpperCase()+ayir[0].substring(1)+" ";
            adsoyad+=ayir[1].substring(0,1).toUpperCase()+ayir[1].substring(1);


            File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
            Scanner giris;
            String kisi ="";

            try{
                giris = new Scanner(dosya);
                ArrayList<String> satir = new ArrayList<>();

                while(giris.hasNextLine()){
                    String oku = giris.nextLine();
                    if(oku.contains(adsoyad)){
                        kisi = oku;
                    }
                    else{
                        satir.add(oku);
                    }
                }
                PrintWriter yaz = new PrintWriter(dosya);
                for (String s : satir) {
                    yaz.write(s);
                    yaz.write("\n");
                    yaz.flush();
                }
                yaz.close();


                String[] dersler = kisi.split("\\+");

                for(int i=1;i< dersler.length;i++){
                    String path = System.getProperty("user.dir")+"\\src\\Dersler\\";
                    String ders = "";
                    String[] x = dersler[i].split(" ");
                    for(int j=0;j<x.length;j++){
                        ders+=x[j];
                    }
                    path+=ders+".txt";
                    File dosya2 = new File(path);
                    Scanner s = new Scanner(dosya2);
                    ArrayList<String> tut = new ArrayList<>();
                    String silinmis = s.nextLine().split("\\+")[0];
                    tut.add(silinmis);
                    while(s.hasNextLine()){
                        String q = s.nextLine();
                        tut.add(q);
                    }
                    PrintWriter pw = new PrintWriter(dosya2);

                    for(String t : tut){
                        pw.write(t);
                        pw.write("\n");
                        pw.flush();
                    }
                    pw.close();
                }
            }
            catch (FileNotFoundException e) {e.printStackTrace();}
            finally {
                secenek();
            }
        }
    }


    public void dersolustur() throws IOException {
        System.out.print("\nOluşturmak istediğiniz dersin adı: ");
        Scanner s = new Scanner(System.in);
        String ders = s.nextLine();
        String[] dersayri = ders.split(" ");
        StringBuilder dersbirlestir = new StringBuilder();
        String dersb = "";
        for (int j = 0; j < dersayri.length; j++){
            dersayri[j] = dersayri[j].substring(0,1).toUpperCase() + dersayri[j].substring(1);
            dersbirlestir.append(dersayri[j]);
            dersb+=dersayri[j].substring(0,1).toUpperCase() + dersayri[j].substring(1) + " ";
        }
        String yol = System.getProperty("user.dir")+"\\src\\Dersler\\" + dersbirlestir + ".txt";

        String yeniders = "";
        for(int i=0;i<dersayri.length;i++) {
            if(i==dersayri.length-1) yeniders+=dersayri[i].substring(0,1).toUpperCase()+dersayri[i].substring(1);
            else yeniders+=dersayri[i].substring(0,1).toUpperCase()+dersayri[i].substring(1)+" ";
        }

        File yeni = new File(yol);
        if(yeni.createNewFile()) {
            System.out.print("Öğretim Görevlisinin Adı: ");
            String[] ad = s.nextLine().split(" ");
            String yeniad = ad[0].substring(0,1).toUpperCase()+ad[0].substring(1)+" "+ad[1].substring(0,1).toUpperCase()+ad[1].substring(1);
            System.out.println("Yeni ders başarıyla oluşturuldu.");
            FileWriter fw = new FileWriter(yol);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write( yeniders + "+" + yeniad);
            bw.close();

            File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
            Scanner s2 = new Scanner(dosya);
            ArrayList<String> liste = new ArrayList<>();
            while(s2.hasNextLine()){
                String x = s2.nextLine();
                if(x.contains(yeniad)){
                    x= x + "+" +yeniders+"+";
                }
                liste.add(x);
            }
            FileWriter fw2 = new FileWriter(dosya);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            for(int k=0;k<liste.size();k++){
                bw2.write(liste.get(k));
                bw2.write("\n");
                bw2.flush();
            }
            bw2.close();

        }
        else System.out.println("Ders zaten mevcut.\n\n");
        secenek();

    }


    public void dersata(){
        try{
            System.out.print("Ders atamak istediğiniz öğretim üyesinin adı ve soyadı: ");
            Scanner s = new Scanner(System.in);
            String[] adsoyad = s.nextLine().split(" ");
            String b = "";
            for(int i=0;i<2;i++){
                if(i==0)b+=adsoyad[i].substring(0,1).toUpperCase()+adsoyad[i].substring(1) + " ";
                else b+=adsoyad[i].substring(0,1).toUpperCase()+adsoyad[i].substring(1);
            }

            System.out.print("Atanacak ders: ");
            String[] ders = s.nextLine().split(" ");
            String c = "";
            String d = "";
            for(int i=0;i<ders.length;i++){
                c+=ders[i].substring(0,1).toUpperCase()+ders[i].substring(1)+ " ";
                d+=ders[i].substring(0,1).toUpperCase()+ders[i].substring(1);
            }
            File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
            Scanner s2 = new Scanner(dosya);
            ArrayList<String> uyeler = new ArrayList<>();
            while(s2.hasNextLine()){
                String satir = s2.nextLine();
                if(satir.contains(b)){
                    satir+=c+"+";
                }
                uyeler.add(satir);
            }

            String dersdosya = System.getProperty("user.dir")+"\\src\\Dersler\\"+d+".txt";
            File dosya2 = new File(dersdosya);
            Scanner s3 = new Scanner(dosya2);
            String y = s3.nextLine();
            if(y.split("\\+").length>1){
                System.out.println("Dersin öğretim elemanı zaten bulunmakta.");

                dersata();
            }
            else{
                String dersdosya2 = System.getProperty("user.dir")+"\\src\\Dersler\\"+d+".txt";
                File dosya3 = new File(dersdosya2);
                Scanner s4 = new Scanner(dosya3);
                ArrayList<String> x = new ArrayList<>();
                while (s4.hasNextLine()) {
                    x.add(s4.nextLine());
                }

                String w = x.get(0)+"+"+b;
                x.set(0,w);

                FileWriter fw2 = new FileWriter(dosya3);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                for(int i=0;i<x.size();i++){
                    bw2.write(x.get(i));
                    bw2.write("\n");
                    bw2.flush();
                }

                FileWriter fw = new FileWriter(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i=0; i<uyeler.size();i++){
                    bw.write(uyeler.get(i));
                    bw.write("\n");
                    bw.flush();
                }
                bw.close();
                bw2.close();
                secenek();
            }
        }
        catch (Exception e){
            System.out.println("Hata oluştu. Lütfen yazımda hata olmadığından emin olun.");
            secenek();
        }
    }


    public void bolumOgr(){
        System.out.print("Görüntülemek istediğiniz bölümün bulunduğu fakülte: ");
        Scanner s = new Scanner(System.in);
        String fakulte = s.nextLine();
        System.out.print("Görüntülemek istediğiniz bölüm: ");
        String bolum = s.nextLine();
        String yol = System.getProperty("user.dir")+"\\src\\Bolumler\\"+fakulte.substring(0,1).toUpperCase()+fakulte.substring(1,3);
        String[] b = bolum.split(" ");
        for(int i=0;i<b.length;i++){
            yol+=b[i].substring(0,1).toUpperCase()+b[i].substring(1,3);
        }
        yol+=".txt";
        System.out.println();
        File dosya = new File(yol);
        try {
            Scanner s1 = new Scanner(dosya);
            while (s1.hasNextLine()){
                System.out.println(s1.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Aradığınız bölüm bulunamadı, lütfen tekrar deneyiniz.");
        }
        finally {
            secenek();
        }
    }


    public void ogrEGoruntule(){
        Scanner s = new Scanner(System.in);
        System.out.print("\nÖğretim elemanlarını görüntülemek istediğiniz fakülte: ");
        String fakulte = s.nextLine();
        System.out.print("Öğretim elemanlarını görüntülemek istediğiniz bölüm: ");
        String[] bolum = s.nextLine().split(" ");
        fakulte=fakulte.substring(0,1).toUpperCase()+fakulte.substring(1);
        for(int i=0;i<bolum.length;i++){
            if(i== bolum.length-1)bolum[i]=bolum[i].substring(0,1).toUpperCase()+bolum[i].substring(1);
            else if(bolum.length==1) bolum[0]=bolum[0].substring(0,1).toUpperCase()+bolum[0].substring(1)+" ";
            else bolum[i]=bolum[i].substring(0,1).toUpperCase()+bolum[i].substring(1)+" ";

        }

        File dosya = new File(System.getProperty("user.dir")+"\\src\\OgretimUyesi.txt");
        try {
            Scanner s2 = new Scanner(dosya);
            while(s2.hasNextLine()){
                String[] kisi = s2.nextLine().split(" ");
                String x = kisi[7]+" ";
                if(kisi[6].equals(fakulte) && x.equals(bolum[0])){
                        System.out.println(kisi[0]+" "+kisi[1]+" "+kisi[2]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        secenek();
    }
}




    

    
    

