package ec.telconet.mscomppruebaandersonzambrano.security.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String usuario;
    private String clave;
}
