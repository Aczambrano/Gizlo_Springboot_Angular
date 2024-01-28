package ec.telconet.mscomppruebaandersonzambrano.main.service;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.UsuarioEntity;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.repository.UsuarioRepository;
import ec.telconet.mscomppruebaandersonzambrano.util.Exception.MyException;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import ec.telconet.mscomppruebaandersonzambrano.util.enums.MessageEnum;
import ec.telconet.mscomppruebaandersonzambrano.util.helper.MetodoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Value("${secret-key-password}")
    private String secretKeyPassword;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public OutputEntity<List<UsuarioResponse>> getAll() {
        OutputEntity<List<UsuarioResponse>> outPut = new OutputEntity<>();
        try {
            List<UsuarioEntity> usuarioModelo = this.usuarioRepository.findAll();

            if(usuarioModelo.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            List<UsuarioResponse> usuarioResponses = usuarioModelo.stream().map(u ->
                    new UsuarioResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }


    public OutputEntity<List<UsuarioResponse>> findByName(String name) {

        OutputEntity<List<UsuarioResponse>> outPut = new OutputEntity<>();
        try {
            List<UsuarioEntity> usuarioModelo = this.usuarioRepository.findByName(name);

            if(usuarioModelo.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            List<UsuarioResponse> usuarioResponses = usuarioModelo.stream().map(u ->
                    new UsuarioResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<UsuarioResponse>> findByMail(String mail) {

        OutputEntity<List<UsuarioResponse>> outPut = new OutputEntity<>();
        try {
            List<UsuarioEntity> usuarioModelo = this.usuarioRepository.findByMail(mail);

            if(usuarioModelo.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            List<UsuarioResponse> usuarioResponses = usuarioModelo.stream().map(u ->
                    new UsuarioResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }


    public OutputEntity<List<UsuarioResponse>> findByUserName(String user) {

        OutputEntity<List<UsuarioResponse>> outPut = new OutputEntity<>();
        try {
            List<UsuarioEntity> usuarioModelo = this.usuarioRepository.findByUsername(user);

            if(usuarioModelo.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            List<UsuarioResponse> usuarioResponses = usuarioModelo.stream().map(u ->
                    new UsuarioResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }


    public OutputEntity<List<UsuarioResponse>> getLastuser(Integer pInicial,Integer pFinal){
        OutputEntity<List<UsuarioResponse>> outPut = new OutputEntity<>();
        try{
            Pageable pageable = PageRequest.of(pInicial,pFinal, Sort.by("id").descending());
            List<UsuarioEntity> usuarioEntities = this.usuarioRepository.findAll(pageable).getContent();

            if(usuarioEntities.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<UsuarioResponse> usuarioResponses = usuarioEntities.stream().map(u->
                    new UsuarioResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        }catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> create(UsuarioRequest data){
        OutputEntity<String> output = new OutputEntity<>();
        try {
            //TODO validar correo valido

            if(!MetodoHelper.isValidEmail(data.getCorreo()))
                throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(),
                        MessageEnum.CORREO_NO_VALIDO.getMensaje());
            //TODO NOMBRE SEA EN MAYÚSCULA
            MetodoHelper.isUpperCase(data.getNombre());
            //TODO CLAVE SEA SEGURA
            MetodoHelper.isStrong(data.getContrasena());

            //TODO ENCRIPTAR CLAVE
            data.setContrasena(MetodoHelper.encryptPassword(data.getContrasena(),this.secretKeyPassword));

            UsuarioEntity user = new UsuarioEntity(data);
            this.usuarioRepository.save(user);
            return output.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
            //TODO GUARDAMOS
        } catch (MyException e) {
            return output.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e){
            return output.error();
        }
    }

    public OutputEntity<String> update(Long id,UsuarioRequest data) throws MyException {
        OutputEntity<String> output = new OutputEntity<>();

        UsuarioEntity usuario = this.usuarioRepository.findById(id).
                orElseThrow(() -> new MyException(MessageEnum.NOT_FOUND.getCode(),
                        MessageEnum.NOT_FOUND.getMensaje()));

        try {
            //TODO validar correo valido

            if(!MetodoHelper.isValidEmail(data.getCorreo()))
                throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(),
                        MessageEnum.CORREO_NO_VALIDO.getMensaje());
            //TODO NOMBRE SEA EN MAYÚSCULA
            MetodoHelper.isUpperCase(data.getNombre());
            //TODO CLAVE SEA SEGURA
            MetodoHelper.isStrong(data.getContrasena());

            //TODO ENCRIPTAR CLAVE
            data.setContrasena(MetodoHelper.encryptPassword(data.getContrasena(),this.secretKeyPassword));

            usuario.setNombre(data.getNombre());
            usuario.setApellido(data.getApellido());
            usuario.setUsuario(data.getUsuario());
            usuario.setContrasena(data.getContrasena());
            usuario.setEmail(data.getCorreo());

            //UsuarioEntity user = new UsuarioEntity(usuario);
            this.usuarioRepository.save(usuario);
            return output.ok(MessageEnum.UPDATE.getCode(), MessageEnum.UPDATE.getMensaje(), null);
            //TODO GUARDAMOS
        } catch (MyException e) {
            return output.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e){
            return output.error();
        }
    }

    public OutputEntity<String> delete(Long id) throws MyException {
        OutputEntity<String> output = new OutputEntity<>();

        try {
            UsuarioEntity usuario = this.usuarioRepository.findById(id).
                    orElseThrow(() -> new MyException(MessageEnum.NOT_FOUND.getCode(),
                            MessageEnum.NOT_FOUND.getMensaje()));
            //TODO validar correo valido
            usuario.setEstado('e');
            //UsuarioEntity user = new UsuarioEntity(usuario);
            this.usuarioRepository.save(usuario);
            return output.ok(MessageEnum.DELETE.getCode(), MessageEnum.DELETE.getMensaje(), null);
            //TODO GUARDAMOS
        } catch (MyException e) {
            return output.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e){
            return output.error();
        }
    }

}
