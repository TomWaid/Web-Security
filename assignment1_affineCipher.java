import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HW1
{
    @SuppressWarnings("serial")
    static String Prob_2_input;
    static final int alphaIndex = (int) 'A';


    static void Problem1() {
        String cipherText = "Z ENIZNMN GWBG KA. RO ZD B LNLENA RQ B DNHANG RATBOZYBGZRO HBIINK U.W.B.O.G.R.L., PWRDN TRBI ZD GRGBI PRAIK KRLZOBGZRO. GWNZA UIBO ZD GR BHXJZAN B DJUNAPNBURO BOK GR WRIK GWN PRAIK ABODRL. Z QNBA GWBG PN KR ORG WBMN LJHW GZLN JOGZI GWNV DJHHNNK. Z WBMN ZOGNAHNUGNK GPR NOHAVUGNK LNDDBTND (HZUWNA_B.GSG BOK HZUWNA_E.GSG) GWBG PNAN DNOG EV KA. RO GR WZD HRODUZABGRA, LA. EIRPQZNIK. Z LBOBTNK GR KZDHRMNA GWN UIBZOGNSG RQ GWN QZADG LNDDBTN:\n\"LA. EIRPQZNIK, LV BDDRHZBGN PZII KNIZMNA GWN UBVLNOG ONSG QAZKBV BG ORRO. Z NSUNHG VRJ GR KNIZMNA GWN UIBOD QRA GWN DJUNAPNBURO ZO NSHWBOTN. VRJ DWRJIK NOHAVUG GWNL PZGW ENOKNAHZUWNA GR UANMNOG BOVRON QARL DGNBIZOT GWNL. Z PZII DUNHZQV GWN IRHBGZRO QRA GWN NSHWBOTN ZO LV ONSG LNDDBTN. KR ORG KBAN GR QBZI LN.\"\nZ ENIZNMN GWBG GWN DNHROK LNDDBTN PBD NOHAVUGNK JDZOT GWN DBLN FNV, EJG GWN NOHAVUGZRO IRRFD UNAQNHG, BOK Z PBD ORG BEIN GR EANBF ZG.\nUINBDN DNOK ANZOQRAHNLNOGD ZLLNKZBGNIV! Z GAZNK GR BHG HBJGZRJDIV, EJG Z WBMN B QNNIZOT GWBG KA. RO'D WNOHWLNO BAN ROGR LN. Z KRO'G FORP WRP IROT Z WBMN ENQRAN GWNV KZDHRMNA LV ZKNOGZGV BOK LV DNHANG WZKZOT";

        String K_vals = Frequency(cipherText);

        System.out.println(K_vals);

        int k1 = 7 * 19 % 26;
        int k2 = 13 - 4 * k1 % 26;
        for (char cipherChar : cipherText.toCharArray())
            if (Character.isLetter(cipherChar)) { // only letters are encrypted, punctuation marks and whitespace are not
                // following line converts letters to numbers between 0 and 25
                int cipher = (int)cipherChar - alphaIndex;
                int plain = Math.floorMod((cipher - k2) * 9, 26); // decrypt
                // following line coverts numbers between 0 and 25 to letters
                char plainChar = (char)(plain + alphaIndex);
                Prob_2_input += plainChar;
                System.out.print(plainChar);
            }
            else
                System.out.print(cipherChar);
        // END SOLUTION
        System.out.println();
    }
    static void Problem2() throws IOException {
        byte[] cipherTextA = Files.readAllBytes(Paths.get("C:\\Users\\Tommy\\IdeaProjects\\varsityTutorsHW1\\src\\cipher_a (2).txt"));
        byte[] cipherTextB = Files.readAllBytes(Paths.get("C:\\Users\\Tommy\\IdeaProjects\\varsityTutorsHW1\\cipher_b (2).txt"));
        // BEGIN SOLUTION
        String plainTextAstring = "MR. BLOWFIELD, MY ASSOCIATE WILL DELIVER THE PAYMENT NEXT FRIDAY AT NOON. I EXPECT YOU TO DELIVER THE PLANS FOR THE SUPERWEAPON IN EXCHANGE. YOU SHOULD ENCRYPT THEM WITH BENDERCIPHER TO PREVENT ANYONE FROM STEALING THEM. I WILL SPECIFY THE LOCATION FOR THE EXCHANGE IN MY NEXT MESSAGE. DO NOT DARE TO FAIL ME";
        byte[] plainTextA = plainTextAstring.getBytes();
        byte[] key = new byte[plainTextA.length];
        byte[] plainTextB = new byte[cipherTextB.length];
        for (int i = 0; i <plainTextA.length; i++) {

            key[i] = (byte)(cipherTextA[i] ^ plainTextA[i]);
        }
        for (int i = 0; i <cipherTextB.length; i++)
        {
            plainTextB[i] = (byte)(cipherTextB[i] ^ key[i]);
        }


        System.out.println(new String(plainTextB));
    }
    public static byte[] benderDecrypt(int key, byte[] cipherText) {

        byte[] p = new byte[cipherText.length];

        byte r = (byte)key;

        for (int i = 0; i < p.length; i++)
        {
            r = (byte)(r * key);
            p[i] = (byte)(cipherText[i] ^ r);
        }
        return p;
    }



    static void Problem3() throws IOException {
        byte[] cipherText3 = Files.readAllBytes(Paths.get("cipher3.txt"));

        byte[] plainText3 = benderDecrypt(129, cipherText3);

        String plainText3String = new String(plainText3);

        System.out.println(plainText3String);


    }


    public static void main(String[] args) throws IOException {

        Problem1();
        Problem2();
        Problem3();
    }
    public static String Frequency(String str) {


        Map<Integer, Character> hm = new HashMap<Integer, Character>();

        TreeMap<Integer, Character> sorted = new TreeMap<>();

        int i, j, k;
        char c, ch;
        i = str.length();
        for (c = 'A'; c <= 'z'; c++) {
            k = 0;
            for (j = 0; j < i; j++) {
                ch = str.charAt(j);
                if (ch == c) {
                    k++;
                }
            }
            if (k > 0) {

                hm.put(k, c);
            }
        }

        sorted.putAll(hm);



        for (Map.Entry<Integer, Character> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());


        List< Character> newArrayList = new ArrayList(sorted.values());

        String K_Vals = "";

        for (int var_1 = 0; var_1 < newArrayList.size(); var_1++) {

            if (var_1 == newArrayList.size() - 2) {

                K_Vals += newArrayList.get(var_1);

            }
            else  if (var_1 == newArrayList.size() - 1) {

                K_Vals += newArrayList.get(var_1);

            }

        }

        return K_Vals;

    }

}