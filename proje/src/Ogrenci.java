import org.apache.poi.xwpf.usermodel.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ogrenci extends Kisi{

    private String ogrenciNo;
    private String tc;
    private String fakulte;
    private String bolum;

    public Ogrenci() {
    }

    public Ogrenci(String kullaniciTuru, String ogrenciNo, String ad, String soyad, String dogumTarihi, String tc, String sifre, String fakulte, String bolum) {
        super(kullaniciTuru, ad, soyad, dogumTarihi, sifre);
        this.ogrenciNo = ogrenciNo;
        this.tc = tc;
        this.fakulte = fakulte;
        this.bolum = bolum;
    }

    public Ogrenci(String kullaniciTuru, String ogrenciNo, String ad, String soyad, String dogumTarihi, String sifre) {
        super("Ogrenci", ad, soyad, dogumTarihi, sifre);
        this.ogrenciNo = ogrenciNo;
    }

    public String getOgrenciNo() {
        return ogrenciNo;
    }
    public String getTc() {return tc;}
    public String getFakulte() {return fakulte;}
    public String getBolum() {return bolum;}

    @Override
    public void giris() {
        Scanner s1 = new Scanner(System.in);
        System.out.print("\nÖğrenci numaranız:  ");
        ogrenciNo = s1.nextLine();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\OgrenciListe.txt"));
            try{String satir = br.readLine();
            while(satir!=null){
                String okunan = br.readLine();
                if(okunan.contains(ogrenciNo)) {
                    System.out.print("Şifreniz:  ");
                    String sifre = s1.nextLine();
                    if(okunan.contains(sifre)){
                        System.out.println("\n\nGiriş başarılı");
                        String[] kelimeler = okunan.split(" ");
                        System.out.println("Hoşgeldiniz, " + kelimeler[1] + " " + kelimeler[2]);
                        secenek(kelimeler);
                    }
                    else{
                        System.out.println("Bilgiler hatalı!");
                        giris();
                    }
                    break;
                }
            }
        }
            catch(IOException e){
                System.out.println("Öğrenci numarası bulunamadı.");
                giris();
            } catch (InterruptedException e) {e.printStackTrace();}}
        catch (FileNotFoundException e) {e.printStackTrace();}}


    public void secenek(String[] kelimeler) throws IOException, InterruptedException {
        System.out.println("\nBilgilerimi Görüntüle -> 1");
        System.out.println("Şifremi Değiştir -> 2");
        System.out.println("Notlarımı Görüntüle -> 3");
        System.out.println("Öğrenci Belgesi Oluştur -> 4");
        System.out.println("Transkript Oluştur -> 5");
        System.out.println("Çıkış -> 0");
        System.out.print("-> ");
        Scanner s2 = new Scanner(System.in);
        String sec = s2.nextLine();
        try{
            int secInt = Integer.parseInt(sec);
            switch(secInt){
                case 1:
                    System.out.println("\n\nÖğrenci No: " + kelimeler[0]);
                    System.out.println("Ad: " + kelimeler[1]);
                    System.out.println("Soyad: " + kelimeler[2]);
                    System.out.println("Doğum Tarihi: " + kelimeler[3]);
                    System.out.println("T.C Kimlik Numarası: "+kelimeler[4]);
                    System.out.println("Fakülte: "+kelimeler[6]+" Fakültesi");
                    System.out.print("Bölüm: ");
                    for(int i=7;i< kelimeler.length;i++) System.out.print(kelimeler[i]+" ");
                    System.out.println("\n");
                    secenek(kelimeler);
                    break;

                case 2:
                    sifreDegistir(kelimeler);
                    break;

                case 3:
                    notGoruntule(kelimeler);
                    break;

                case 4:
                    ogrenciBelgesi(kelimeler);
                    secenek(kelimeler);
                    break;

                case 5:
                    transkript(kelimeler);
                    secenek(kelimeler);
                    break;

                default:
                    System.exit(0);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Lütfen geçerli bir rakam giriniz.");
            secenek(kelimeler);
        }
    }

    @Override
    public void kisiEkle(int i) {
        System.out.println("Bu işlem icin yetkiniz bulunmamaktadir.\n\n");
    }

    @Override
    public void notGoruntule(String[] kelimeler) throws FileNotFoundException {
        String path = System.getProperty("user.dir")+"\\src\\Dersler";
        File dosya = new File(path);

        if(dosya.isDirectory()){
            File[] tum = dosya.listFiles();
            String no = kelimeler[0];
            for(int i = 0; i < tum.length; i++){
                Scanner s = new Scanner(tum[i]);
                int a = 0;
                String dersadi = "";
                String egitmen = "";
                while(s.hasNextLine()){
                    String str = s.nextLine();
                    if(a==0){
                        dersadi = str;
                        String[] ayir = dersadi.split("\\+");
                        dersadi = ayir[0];
                        if(ayir.length>1){
                            egitmen = ayir[1];
                            a++;
                        }
                        else a++;

                    }
                    else{
                        if(str.contains(no)){
                            System.out.println(dersadi);
                            if(egitmen!=null)System.out.println("Öğretim Görevlisi:  " + egitmen);
                            String[] ayir = str.split(" ");
                            System.out.print("Vize: " + ayir[1]);
                            if(ayir.length>2){
                                System.out.println("    Final: " + ayir[2]);
                            }
                        }
                    }
                }
                System.out.println();
                }
            }
        try {
            secenek(kelimeler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sifreDegistir(String[] kelimeler) throws IOException {
        System.out.print("\n6 Haneli Yeni Şifreniz: ");
        Scanner sifreoku = new Scanner(System.in);
        String yeniSifre = sifreoku.nextLine();
        File dosya = new File(System.getProperty("user.dir")+"\\src\\OgrenciListe.txt");
        Scanner giris;
        try {
            giris = new Scanner(dosya);
            ArrayList<Ogrenci> satir = new ArrayList<>();
            while(giris.hasNextLine()){
                String[] oku = giris.nextLine().split(" ");
                String b = "";
                for(int i=7;i< oku.length;i++) b+=oku[i]+" ";
                Ogrenci ogrenci = new Ogrenci("Öğrenci",oku[0],oku[1],oku[2],oku[3],oku[4],oku[5],oku[6],b);
                if(ogrenci.getSifre().equals(kelimeler[5])){
                    ogrenci.setSifre(yeniSifre);
                }
                satir.add(ogrenci);
            }
            PrintWriter yaz = new PrintWriter(dosya);
            for (Ogrenci s : satir) {
                yaz.write(s.getOgrenciNo());
                yaz.write(" ");
                yaz.write(s.getAd());
                yaz.write(" ");
                yaz.write(s.getSoyad());
                yaz.write(" ");
                yaz.write(s.getDogumTarihi());
                yaz.write(" ");
                yaz.write(s.getTc());
                yaz.write(" ");
                yaz.write(s.getSifre());
                yaz.write(" ");
                yaz.write(s.getFakulte());
                yaz.write(" ");
                yaz.write(s.getBolum());
                yaz.write("\n");
                yaz.flush();
            }
            yaz.close();
            System.out.println("Şifre başarıyla değiştirildi.\n\n");
            secenek(kelimeler);
        }
        catch (Exception e) {e.printStackTrace();}
        try {
            secenek(kelimeler);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ogrenciBelgesi(String[] ogr) throws IOException{
        String dosyaadi = ogr[0]+ogr[1]+ogr[2]+".docx";
        File dosya = new File(dosyaadi);
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(dosya);
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date tarih = new Date();

        XWPFParagraph p0 = document.createParagraph();
        p0.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun r0 = p0.createRun();
        r0.setFontSize(11);
        r0.setText(sdf.format(tarih));
        r0.addBreak();

        XWPFParagraph p1 = document.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = p1.createRun();
        r1.setBold(true);
        r1.setFontSize(20);
        r1.setText("MARMARA ÜNİVERSİTESİ REKTÖRLÜĞÜ");

        XWPFParagraph p2 = document.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setFontSize(18);
        r2.setBold(true);
        r2.setText("ÖĞRENCİ BELGESİ");

        XWPFParagraph px = document.createParagraph();
        XWPFRun rx = px.createRun();
        rx.setText("");

        String[] x = {"Adı:                    ", "Soyadı:              ", "Doğum Tarihi:  ", "T.C. Kimlik No: "};
        for(int i=1;i<5;i++){
            XWPFParagraph p = document.createParagraph();
            XWPFRun r = p.createRun();
            r.setFontSize(12);
            r.setText(x[i-1] + "     "+ ogr[i]);
        }


        XWPFParagraph p3 = document.createParagraph();
        XWPFRun r3 = p3.createRun();
        r3.setFontSize(12);
        r3.setText("Fakülte:                  " + ogr[6] + " Fakültesi");

        String bolum = "";
        for(int i=7; i<ogr.length;i++) bolum+=ogr[i] + " ";

        XWPFParagraph p4 = document.createParagraph();
        XWPFRun r4 = p4.createRun();
        r4.setFontSize(12);
        r4.setText("Bölüm:                   " + bolum);

        for(int i=0;i<7;i++){
            XWPFParagraph px1 = document.createParagraph();
            XWPFRun rx1 = px1.createRun();
            rx1.setText("");
        }

        XWPFParagraph p5 = document.createParagraph();
        p5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r5 = p5.createRun();
        r5.setBold(true);
        r5.setFontSize(14);
        r5.setText("İLİGİLİ MAKAMA");

        XWPFParagraph p6 = document.createParagraph();
        XWPFRun r6 = p6.createRun();
        r6.setFontSize(12);
        r6.setText("Yukarıda kimlik bilgileri yer alan " + ogr[1] + " " + ogr[2] +
                " isimli kişinin Marmara Üniversitesi tarafından yukarıda belirtilen\n" +
                "programın kayıtlı öğrencisi olduğu bildirilmiştir.");

        document.write(out);
        out.close();

        System.out.println("Dosya açılıyor...");
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(dosya.getAbsolutePath()));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void transkript(String[] ogr) throws IOException {
        System.out.println("\nNot döküm belgesi oluşturuluyor...");
        String dosyaadi = ogr[0]+ogr[1]+ogr[2]+"transkript.docx";
        File dosya = new File(dosyaadi);
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(dosya);

        XWPFParagraph p0 = document.createParagraph();
        p0.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun r0 = p0.createRun();
        r0.setFontSize(11);
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date tarih = new Date();
        r0.setText("Belge Tarihi: " + sdf.format(tarih));
        r0.addBreak();


        XWPFParagraph p1 = document.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = p1.createRun();
        r1.setFontSize(14);
        r1.setBold(true);
        r1.setText("MARMARA ÜNİVERSİTESİ");

        XWPFParagraph p2 = document.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setFontSize(14);
        r2.setBold(true);
        r2.setText("NOT DÖKÜM BELGESİ");


        String data = "Öğrenci No:          "+ogr[0]+"\nT.C. Kimlik No :    "+ogr[4]+
                "\nAd:                         "+ogr[1]+"\nSoyad:                   "+ogr[2]+"\nDoğum Tarihi:      "+ogr[3];

        for(int i=0;i<2;i++){
            XWPFParagraph px1 = document.createParagraph();
            XWPFRun rx1 = px1.createRun();
            rx1.setText("");
        }

        XWPFParagraph p3 = document.createParagraph();
        XWPFRun r3 = p3.createRun();
        if(data.contains("\n")){
            String[] lines = data.split("\n");
            r3.setText(lines[0], 0);
            for(int i=1; i< lines.length; i++){
                r3.addBreak();
                r3.setFontSize(12);
                r3.setText(lines[i]);
            }
        }

        else{
            r3.setText(data, 0);
        }

        r3.addBreak();
        r3.addBreak();


        XWPFTable tablo = document.createTable();
        tablo.setCellMargins(55,55,55,55);
        XWPFTableRow row1 = tablo.getRow(0);
        row1.getCell(0).setText("Ders");
        row1.addNewTableCell().setText("Vize");
        row1.addNewTableCell().setText("Final");
        row1.addNewTableCell().setText("Durum");

        String path = System.getProperty("user.dir")+"\\src\\Dersler";
        File oku = new File(path);
        ArrayList<String> hepsi = new ArrayList<>();
        ArrayList<String> dersadi = new ArrayList<>();
        String ders = "";

        if(oku.isDirectory()){
            File[] tum = oku.listFiles();
            String no = ogr[0];
            for(int i = 0; i < tum.length; i++){
                Scanner s = new Scanner(tum[i]);
                int a = 0;
                while(s.hasNextLine()){
                    String str = s.nextLine();
                    if(a==0){
                        ders = str;
                        a++;
                    }
                    else{
                        if(str.contains(no)){
                            hepsi.add(str);
                            String[] d = ders.split("\\+");
                            dersadi.add(d[0]);
                        }
                    }
                }
            }
        }

        row1.setHeight(25);

        for(int i=0;i<hepsi.size();i++){
            XWPFTableRow row2 = tablo.createRow();
            row2.getCell(0).setText(dersadi.get(i));
            String [] ayir = hepsi.get(i).split(" ");
            row2.getCell(1).setText(ayir[1]);
            if(ayir.length>2) {
                row2.getCell(2).setText(ayir[2]);
                if(Integer.parseInt(ayir[2])>35 && (Integer.parseInt(ayir[1])+Integer.parseInt(ayir[2]))/2>35){
                    row2.getCell(3).setText("Geçti");
                }
                else if(Integer.parseInt(ayir[2])<35 || (Integer.parseInt(ayir[1])+Integer.parseInt(ayir[2]))/2<35){
                    row2.getCell(3).setText("Kaldı");
                }
                else {
                    row2.getCell(3).setText("");
                }
            }
            else row2.getCell(2).setText("");

        }

        document.write(out);
        out.close();

        try {if (Desktop.isDesktopSupported()) {Desktop.getDesktop().open(new File(dosya.getAbsolutePath()));}}
        catch (IOException ioe) {ioe.printStackTrace();}
    }
}
