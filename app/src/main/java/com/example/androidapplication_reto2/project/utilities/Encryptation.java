package com.example.androidapplication_reto2.project.utilities;

import android.util.Log;

import com.example.androidapplication_reto2.project.factories.UserFactory;
import com.example.androidapplication_reto2.project.interfaces.RestUser;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Encryptation {

    private static String publicKey ="";
    private final static String CRYPTO_METHOD = "RSA";
    private final static String OPCION_RSA = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
    private static Cipher cipher;


    /**
     * Get the public key from the server
     */
    public static void getKey(){
        RestUser restUser = UserFactory.getClientText();
        Call<String> getKeyCall =  restUser.getPublicKey();
        getKeyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("ENCRYPTATION", "BIEN "+response.body());
                setPublicKey(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("ENCRYPTATION", "MAL "+t.getMessage());
            }
        });
    }

    /**
     * Encrypt one message to encrypted with public key
     * @param messageToEncrypt message to encrypt
     * @return the message encrypted
     * @throws Exception
     */
    public static String encrypt(String messageToEncrypt) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_METHOD);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(hexStringToByteArray(publicKey));
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        cipher = Cipher.getInstance(OPCION_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(messageToEncrypt.getBytes());
        return toHexadecimal(encrypted);
    }

    /**
     * Method that transform byte[] to hexadecimal string
     * @param resume the string that we want transform
     * @return the byte[] with the transformation
     */
    public static String toHexadecimal(byte[] resume) {
        String HEX = "";
        for (int i = 0; i < resume.length; i++) {
            String h = Integer.toHexString(resume[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    /**
     * Method that transform hexadecimal string to byte[]
     * @param s the string that we want transform
     * @return the byte[] with the transformation
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static void setPublicKey(String publicKey) {
        Encryptation.publicKey = publicKey;
    }
}
