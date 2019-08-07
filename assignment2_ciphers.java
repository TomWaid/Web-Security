import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;
// BEGIN SOLUTION
// please import only standard libraries and make sure that your code compiles and runs without unhandled exceptions 
// END SOLUTION

public class HW2 {
  static void P1() throws Exception {


    byte[] cipherBMP = Files.readAllBytes(Paths.get("cipher1.bmp"));


    Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");


    // BEGIN SOLUTION
    byte[] key = new byte[] { 7, 7, 7, 7,
            7, 7, 7, 7,
            7, 7, 7, 7,
            7, 7, 7, 7 };

    byte[] IV = new byte[] { 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0 };

    SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
    IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);

    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,
            ivParameterSpec);


    byte[] plainBMP = cipher.doFinal(cipherBMP);
    // END SOLUTION

    Files.write(Paths.get("plain1.bmp"), plainBMP);
  }

  static void P2() throws Exception {
    // BEGIN SOLUTION
  int counter =0;
   MessageDigest messageDigest = MessageDigest.getInstance("MD5");

    Scanner scanner = new Scanner(new File("messages (1).txt"));
    while (scanner.hasNextLine()) {
      String message = scanner.nextLine();

      byte[]  bitArray = message.getBytes();
      messageDigest.update( bitArray);

      byte[] md =  messageDigest.digest();


      if (md[0] == 70 && md[1] == 124 && md[2] == 72){

        System.out.println(message);

      }

    }
    scanner.close();
    // END SOLUTION
  }

  static void P3() throws Exception {
    byte[] plainBMP = Files.readAllBytes(Paths.get("plain1.bmp"));

    byte[] cipherBMP = Files.readAllBytes(Paths.get("cipher3.bmp"));

    byte[] modifiedBMP = cipherBMP;

    for( int i= 0; i < plainBMP.length ; i++  ){

       if(i <2000){

         modifiedBMP[i] = plainBMP[i];
       }


      }



    // END SOLUTION

    Files.write(Paths.get("cipher3_modified.bmp"), modifiedBMP);
  }

  static void P4() throws Exception {
    byte[] cipherPNG = Files.readAllBytes(Paths.get("cipher4.png"));
// BEGIN SOLUTION
    byte[] key = new byte[] { 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0 };

    byte[] iv = new byte[]
            { 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0 };
    byte[] plainPNG = new byte[]{};
    byte x, m ,z;
    boolean flag=true;
    for( x = 0 ; x < 100 && flag ; x++){
      key[0] = x;
      for( m = 1 ; m <=12 && flag; m++){
        key[1] = m;
        for( z = 1 ; z <= 31 && flag ; z++){
          key[2] = z;
          SecretKeySpec secKey = new SecretKeySpec(key,"AES");
          IvParameterSpec ivSpec = new IvParameterSpec(iv);

          Cipher ci = Cipher.getInstance("AES/CBC/Nopadding");
          ci.init(Cipher.DECRYPT_MODE,secKey,ivSpec);
          plainPNG=ci.doFinal(cipherPNG);

          if(plainPNG[1] == 80 && plainPNG[2] == 78 && plainPNG[3] == 71){
            flag = false;
          }
        }
      }
    }
// END SOLUTION
    Files.write(Paths.get("plain4.png"), plainPNG);
  }

  public static void main(String [] args) {
    try {
      P1();
     P2();
       P3();
     P4();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
