package ec.telconet.mscomppruebaandersonzambrano.main.repository;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.ProductoEntity;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    @Query(value = "select * from springbootcamp.producto where name LIKE %:name%",nativeQuery = true)
    List<ProductoEntity> findByName(String name);

}
