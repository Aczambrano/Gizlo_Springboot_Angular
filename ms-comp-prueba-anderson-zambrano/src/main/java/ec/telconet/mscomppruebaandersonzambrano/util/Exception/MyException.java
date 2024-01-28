package ec.telconet.mscomppruebaandersonzambrano.util.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
public class MyException extends Exception{

    private static final Long serialVersionUID = 1L;

    private ArrayList<String> mensaje = new ArrayList<>();
    private Integer code;
    private HttpStatus codeHttp;



    public MyException(Integer code, String mensaje) {
        super();
        this.code = code;
        this.mensaje.add(mensaje);

    }
}
