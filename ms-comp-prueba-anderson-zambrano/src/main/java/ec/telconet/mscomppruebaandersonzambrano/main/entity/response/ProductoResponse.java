package ec.telconet.mscomppruebaandersonzambrano.main.entity.response;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.ProductoEntity;
import lombok.Data;

@Data
public class ProductoResponse {

    private Long identificador;
    private String nombre;
    private int cantidad;
    private double precio;
    private String imagen;
    private String estado;
   // private Long idUsuario;

    public ProductoResponse(ProductoEntity p){
        this.identificador = p.getId();
        this.nombre = p.getNombre();
        this.cantidad = p.getCantidad();
        this.precio = p.getPrecio();
        this.imagen = p.getImagen();
        switch ( p.getEstado().toString().toLowerCase()){
            case "a":
                this.estado = "Activo";
                break;
            case "e":
                this.estado = "Eliminado";
                break;
            case "i":
                this.estado = "Inactivo";
                break;
        }
    }
}
