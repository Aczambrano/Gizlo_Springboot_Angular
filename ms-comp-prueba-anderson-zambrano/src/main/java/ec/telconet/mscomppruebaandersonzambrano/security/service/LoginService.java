package ec.telconet.mscomppruebaandersonzambrano.security.service;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.UsuarioEntity;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.repository.UsuarioRepository;
import ec.telconet.mscomppruebaandersonzambrano.security.entity.LoginRequest;
import ec.telconet.mscomppruebaandersonzambrano.security.response.LoginResponse;
import ec.telconet.mscomppruebaandersonzambrano.util.Exception.MyException;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import ec.telconet.mscomppruebaandersonzambrano.util.enums.MessageEnum;
import ec.telconet.mscomppruebaandersonzambrano.util.helper.MetodoHelper;
import ec.telconet.mscomppruebaandersonzambrano.util.helper.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {
    @Value("${secret-key-password}")
    private String secretKeyPassword;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenHelper tokenHelper;

    public OutputEntity<LoginResponse> login(LoginRequest data){
        OutputEntity<LoginResponse> output = new OutputEntity<>();
        try{
            LoginResponse loginResponse = new LoginResponse();

            UsuarioEntity usuario = this.usuarioRepository.findByUserLogin(data.getUsuario());
            if(usuario ==null){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            ///TODO
            String password = MetodoHelper.desencryptPass(usuario.getContrasena(),this.secretKeyPassword);

            UsuarioResponse usuarioResponse = new UsuarioResponse(usuario);

            if(!StringUtils.pathEquals(password,data.getClave())){
                throw new MyException(MessageEnum.LOGIN_ERROR.getCode(),
                        MessageEnum.LOGIN_ERROR.getMensaje());
            }

            loginResponse.setUsuarioResponse(usuarioResponse);
            loginResponse.setToken(this.tokenHelper.generateToken(data.getUsuario(),
                    usuarioResponse,"clave123"));
            return output.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), loginResponse);
        }catch (MyException e){
            return output.error(e.getCode(), e.getMensaje(),null);
        }catch (Exception e){
            return output.error();
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
}
