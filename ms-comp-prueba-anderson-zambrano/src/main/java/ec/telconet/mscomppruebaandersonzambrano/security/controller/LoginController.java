package ec.telconet.mscomppruebaandersonzambrano.security.controller;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.service.UsuarioService;
import ec.telconet.mscomppruebaandersonzambrano.security.entity.LoginRequest;
import ec.telconet.mscomppruebaandersonzambrano.security.response.LoginResponse;
import ec.telconet.mscomppruebaandersonzambrano.security.service.LoginService;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<OutputEntity<LoginResponse>> login(@RequestBody LoginRequest data){

        OutputEntity<LoginResponse> out = null;

        try {
            out = this.loginService.login(data);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<LoginResponse>().error();
            return new ResponseEntity<>(out, out.getCode());
        }


    }

    @GetMapping("/username/{username}")
    public ResponseEntity<OutputEntity<List<UsuarioResponse>>> findByUserName(@PathVariable String username){
        OutputEntity <List<UsuarioResponse>> out = null;
        try{
            out = this.usuarioService.findByUserName(username);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<UsuarioResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
