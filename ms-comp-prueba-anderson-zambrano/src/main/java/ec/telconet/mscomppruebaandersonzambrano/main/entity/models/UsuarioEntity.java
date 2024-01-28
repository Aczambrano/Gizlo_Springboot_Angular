package ec.telconet.mscomppruebaandersonzambrano.main.entity.models;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*Utilizamos lombok para no mostrar tanto código como los constructores
 * y los gettes y setters, una manera facil para mantener nuestra clase limpia*/
@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Table(name = "usuario")
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor sin argumentos
@Getter
@Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", updatable = false, nullable = false,unique = true)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String nombre;

    @Column(name = "lastname", nullable = false, length = 50)
    private String apellido;

    @Column(name = "username", nullable = false, length = 50)
    private String usuario;
    @Column(name = "password", nullable = false, length = 500)
    private String contrasena;
    @Column(name = "mail", nullable = false, length = 50)

    private String email;
    @Column(name = "status", nullable = false, length = 1)
    private Character estado;
    @Column(name = "administrator", nullable = false)
    private Boolean admin;
/*private Date fachaCreacion;

	private Date fechaActualizaion;

	private Integer idUsuarioCreacion;

	private Integer idUsuarioActualizacion;
	*/
/*
    @OneToMany(mappedBy = "usuario")
    private List<ProductoEntity> productos;*/

    public UsuarioEntity(UsuarioRequest data) {

        this.nombre = data.getNombre();
        this.apellido = data.getApellido();
        this.usuario = data.getUsuario();
        this.contrasena = data.getContrasena();
        this.email = data.getCorreo();
        this.estado = 'A';
        this.admin = false;
    }

    public UsuarioEntity(UsuarioEntity data) {

        this.nombre = data.getNombre();
        this.apellido = data.getApellido();
        this.usuario = data.getUsuario();
        this.contrasena = data.getContrasena();
        this.email = data.getEmail();
        this.estado = 'A';
        this.admin = false;
    }
}
