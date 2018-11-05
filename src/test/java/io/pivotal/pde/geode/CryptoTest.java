package io.pivotal.pde.geode;

import io.pivotal.pde.geode.CipherType;
import io.pivotal.pde.geode.CryptoUtil;
import io.pivotal.pde.geode.ICryptoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class CryptoTest {


    @Test
    public void testCryptoName() {

        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());
        String value = "The quick brown fox jumps over the lazy dog";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        System.out.println("encrypted :"+encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testCryptoAddress() {
        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "222, Main Street, Apt 202";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testCryptoCreditCard() {


        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "4567 1234 5678 1234";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }


}
