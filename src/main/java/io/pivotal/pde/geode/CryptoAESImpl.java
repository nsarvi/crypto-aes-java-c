package io.pivotal.pde.geode;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoAESImpl implements ICryptoService {

    private byte[] iv;

    private int keySize = 16;

    private static final int DEFAULT_KEY_ITERATIONS = 2048;

    private SecretKeySpec secret;

    private static String ALGORITHM="AES/CBC/PKCS5PADDING";
    private static String UTF8="UTF-8";


    public CryptoAESImpl(byte[] iv, byte[] secretKey)  {
        this.iv = iv;
        this.secret = new SecretKeySpec(secretKey, "AES");
    }


    /**
     * Encrypts the plain text and returns the encrypted text
     *
     * @param text
     * @return
     */
    @Override
    public String encrypt(String text)  {
        if (text == null) return null;
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secret, ivspec);
            byte [] encryptedTextBytes = cipher.doFinal(text.getBytes(UTF8));
            return Base64.getEncoder().encodeToString(encryptedTextBytes);

        } catch (InvalidKeyException  invalidKeyException) {
            invalidKeyException.printStackTrace();
            throw new RuntimeException(invalidKeyException);
        } catch ( InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param encryptedText
     * @return
     */
    @Override
    public String decrypt(String encryptedText) {
        if (encryptedText == null) return null;
        byte [] packageBytes = Base64.getDecoder().decode(encryptedText);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
            return new String(cipher.doFinal(packageBytes));
        } catch (InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

}
