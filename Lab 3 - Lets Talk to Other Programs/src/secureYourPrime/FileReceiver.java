package secureYourPrime;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class FileReceiver implements Runnable {
    Socket socket;
    BufferedReader bufferedReader;
    SecretKey secretKey;
    Cipher cipher;

    public FileReceiver(Socket socket, BufferedReader input, SecretKey secretKey, Cipher cipher) {
        this.socket = socket;
        this.bufferedReader = input;
        this.secretKey = secretKey;
        this.cipher = cipher;
    }

    @Override
    public void run() {
        try {
            String path = "/Users/eir/eclipse-workspace/YourPrime/src/secretFileServer.txt";
            readWriteFile(socket, path);

            // decipher file
            String decryptedMessage = decipherFile(path);
            System.out.println(decryptedMessage);

        } catch (IOException | InvalidKeyException | InvalidAlgorithmParameterException |
                 NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // TODO complete the read and write file method below. First, you will read the transmitted bytes
    // from the client side, and save this data into a file on the server side. You should read the
    // transmitted data in bytes (to preserve the encoding of the encrypted data).
    //
    public void readWriteFile(Socket socket, String path) throws IOException {

    }

    // TODO complete the decipher process to reveal the hidden message in the file. You should make use of the
    // cipherOutputStream in this method - the reverse of the encryption process
    //
    public String decipherFile(String path) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        FileInputStream fileIn = new FileInputStream(path);
        byte[] fileIv = new byte[16];
        fileIn.read(fileIv);
        this.cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

        return path;
    }
}
