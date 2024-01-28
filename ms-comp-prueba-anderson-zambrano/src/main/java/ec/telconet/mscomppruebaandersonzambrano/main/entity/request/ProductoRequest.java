package ec.telconet.mscomppruebaandersonzambrano.main.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {

    private String nombreP;
    private int cantidadP;
    private double precioP;
    private String imagenP;
    private Character estadoP;

    //private Long idUsuario;

}
