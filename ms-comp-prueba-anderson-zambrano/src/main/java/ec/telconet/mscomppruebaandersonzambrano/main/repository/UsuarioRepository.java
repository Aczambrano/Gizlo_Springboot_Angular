package ec.telconet.mscomppruebaandersonzambrano.main.repository;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    @Query(value = "select * from springbootcamp.usuario where name LIKE %:name%",nativeQuery = true)
    List<UsuarioEntity> findByName(String name);

    @Query(value = "select * from springbootcamp.usuario where mail LIKE %:mail%",nativeQuery = true)
    List<UsuarioEntity> findByMail(String mail);


    @Query(value = "select * from springbootcamp.usuario where username =:user",nativeQuery = true)
    UsuarioEntity findByUserLogin(String user);

    @Query(value = "select * from springbootcamp.usuario where username LIKE %?1%",nativeQuery = true)
    List<UsuarioEntity> findByUsername(String username);


}
