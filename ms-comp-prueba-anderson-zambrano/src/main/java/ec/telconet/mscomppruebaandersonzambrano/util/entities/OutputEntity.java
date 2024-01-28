package ec.telconet.mscomppruebaandersonzambrano.util.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ec.telconet.mscomppruebaandersonzambrano.util.enums.MessageEnum;
import ec.telconet.mscomppruebaandersonzambrano.util.helper.MetodoHelper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Data
public class OutputEntity<T> {


    @JsonIgnore
    private HttpStatus code;

    private ArrayList<String> mensaje = new ArrayList<>();

    private Boolean error = false;

    private T data;

    public  OutputEntity<T> ok(Integer code, String mensaje, T data){
        this.code = MetodoHelper.seleccionarEstado(code);
        this.mensaje.add(mensaje);
        this.data = data;
        return this;
    }

    public  OutputEntity<T> ok(Integer code, ArrayList<String> mensaje, T data){
        this.code = MetodoHelper.seleccionarEstado(code);
        this.mensaje = mensaje;
        this.data = data;
        return this;
    }

    public OutputEntity<T> error(Integer code, String mensaje, T data) {
        this.code = MetodoHelper.seleccionarEstado(code);
        this.mensaje.add(mensaje);
        this.data = data;
        this.error = true;

        return this;
    }

    public OutputEntity<T> error(Integer code, ArrayList<String> mensaje, T data) {
        this.code = MetodoHelper.seleccionarEstado(code);
        this.mensaje = mensaje;
        this.data = data;
        this.error = true;

        return this;
    }

    public OutputEntity<T> error() {
        this.code = MetodoHelper.seleccionarEstado(MessageEnum.INTERNAL_ERROR.getCode());
        this.mensaje.add(MessageEnum.INTERNAL_ERROR.getMensaje());
        this.data = data;
        this.error = true;

        return this;
    }

}
