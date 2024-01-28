package ec.telconet.mscomppruebaandersonzambrano.main.entity.models;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.ProductoRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import jakarta.persistence.*;
import lombok.*;

/*Utilizamos lombok para no mostrar tanto código como los constructores
 * y los gettes y setters, una manera facil para mantener nuestra clase limpia*/
@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Table(name = "producto")
@Builder // Patrón de diseño que facilita la construcción de objetos complejos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor sin argumentos
@Getter
@Setter

public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String nombre;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "imagen", nullable = false, length = 500)
    private String imagen;
    @Column(name = "status", nullable = false, length = 1)
    private Character estado;

    /*
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
*/
    /*
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

*/
    public ProductoEntity(ProductoRequest data) {
        this.nombre = data.getNombreP();
        this.cantidad= data.getCantidadP();
        this.precio = data.getPrecioP();
        this.imagen = data.getImagenP();
        this.estado = data.getEstadoP();
    }

    public ProductoEntity(ProductoEntity data) {

        this.nombre = data.getNombre();
        this.cantidad= data.getCantidad();
        this.precio = data.getPrecio();
        this.imagen = data.getImagen();
        this.estado = data.getEstado();
        //this.usuario = data.getUsuario();
    }


}
