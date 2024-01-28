package ec.telconet.mscomppruebaandersonzambrano.main.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private String correo;
}
