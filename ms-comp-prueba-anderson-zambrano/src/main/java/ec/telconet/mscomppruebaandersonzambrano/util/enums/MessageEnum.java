package ec.telconet.mscomppruebaandersonzambrano.util.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {

    OK("Respuesta Exitosa.",200),
    CREATE("Creación Exitosa",201),
    UPDATE("Actualización Exitosa",201),
    DELETE("Registro Eliminado Correctamene",201),
    NOT_FOUND("No se encontraron resultados",404),
    INTERNAL_ERROR("Problemas en la Transacción",500),
    CORREO_NO_VALIDO("Ingrese un correo valido", 403),
    UPPER_CASE("Ingrese el nombre en mayúscula",403),
    NOT_STRONG_PASS("Ingrese una contraseña segura",403),
    LOGIN_ERROR("Login Incorrecto",404),
    NOT_ENCRYPT("Hubo un conflicto",409),
    NOT_DESENCRYPT("No se desencriptó la contrasña",409);
    private String mensaje;
    private Integer code;

    private MessageEnum(String mensaje,Integer code){
        this.mensaje = mensaje;
        this.code = code;
    }
}
