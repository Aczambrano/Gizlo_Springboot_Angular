package ec.telconet.mscomppruebaandersonzambrano.security.response;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import lombok.Data;

@Data
public class LoginResponse {
    public UsuarioResponse usuarioResponse;

    //TODO Entidad de las opciones del sistema
    // private List<OpcionesResponse> opciones = new ArrayList<>();
    public String token;

    public void mapData(UsuarioResponse usuarioResponse, String token){
        this.usuarioResponse = usuarioResponse;
        this.token = token;
    }
}
