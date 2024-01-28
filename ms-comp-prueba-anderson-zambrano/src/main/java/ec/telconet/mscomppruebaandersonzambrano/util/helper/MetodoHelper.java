package ec.telconet.mscomppruebaandersonzambrano.util.helper;

import ec.telconet.mscomppruebaandersonzambrano.util.Exception.MyException;
import ec.telconet.mscomppruebaandersonzambrano.util.enums.MessageEnum;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodoHelper {

    public static HttpStatus seleccionarEstado(Integer code) {
        HttpStatus status = null;
        switch (code) {
            case 200:
                status = HttpStatus.OK;
                break;
            case 201:
                status = HttpStatus.CREATED;
                break;
            case 400:
                status = HttpStatus.BAD_REQUEST;
                break;
            case 404:
                status = HttpStatus.NOT_FOUND;
                break;
            case 406:
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            case 204:
                status = HttpStatus.NO_CONTENT;
                break;
            case 401:
                status = HttpStatus.UNAUTHORIZED;
                break;
            case 409:
                status = HttpStatus.CONFLICT;
                break;
            case 403:
                status = HttpStatus.FORBIDDEN;
                break;
            case 405:
                status = HttpStatus.METHOD_NOT_ALLOWED;
                break;
            case 408:
                status = HttpStatus.REQUEST_TIMEOUT;
                break;
            case 500:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        return status;
    }


    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void isUpperCase(String cadena) throws MyException {
        if(!cadena.matches("^[A-Z]+$"))
            throw new MyException(MessageEnum.UPPER_CASE.getCode(),MessageEnum.UPPER_CASE.getMensaje());

    }

    public static String encryptPassword(String pass, String secretKey) throws MyException {
        try {
            AesKey key = new AesKey(secretKey.getBytes());
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128GCMKW);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
            jwe.setPayload(pass);
            jwe.setKey(key);
            return jwe.getCompactSerialization();
        } catch ( JoseException e ) {
            throw new MyException(MessageEnum.NOT_ENCRYPT.getCode(), MessageEnum.NOT_ENCRYPT.getMensaje());
        } catch ( Exception e ) {
            throw new MyException(MessageEnum.INTERNAL_ERROR.getCode(), MessageEnum.INTERNAL_ERROR.getMensaje());
        }
    }


    /*
     * Método: desencripta la contraseña del usuario con una secret key
     */
    public static String desencryptPass(String encryptPass, String secretKey) throws MyException {
        try {
            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setAlgorithmConstraints(
                    new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.A128GCMKW));
            jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
                    ContentEncryptionAlgorithmIdentifiers.AES_128_GCM));
            jwe.setKey(new AesKey(secretKey.getBytes()));
            jwe.setCompactSerialization(encryptPass);
            // System.out.println("Payload: ");
            return jwe.getPayload();
        } catch ( JoseException e ) {
            throw new MyException(MessageEnum.NOT_DESENCRYPT.getCode(),
                    MessageEnum.NOT_DESENCRYPT.getMensaje());
        } catch ( Exception e ) {
            throw new MyException(MessageEnum.INTERNAL_ERROR.getCode(),
                    MessageEnum.INTERNAL_ERROR.getMensaje());
        }
    }


    /**
     * Metodo: valida que la contraseña del usuario sea fuete (?=.*[0-9]) un dígito
     * debe aparecer al menos una vez (?=.*[a-z]) una letra minúscula debe aparecer
     * al menos una vez (?=.*[A-Z]) una letra mayúscula debe aparecer al menos una
     * vez (?=.*[@#$%^&+=]) un caracter especial debe aparecer al menos una vez
     * (?=\\S+$) no se permiten espacios en blanco en toda la cadena .{8,} Al menos
     * 8 carácteres
     *
     * @param password
     * @return
     * @throws MyException
     */
    public static void isStrong(String password) throws MyException {

        if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$-_#.@$!%*?&])(?=\\S+$).{8,}"))
            throw new MyException(MessageEnum.NOT_STRONG_PASS.getCode(),
                    MessageEnum.NOT_STRONG_PASS.getMensaje());

    }

    /*

*/
}
