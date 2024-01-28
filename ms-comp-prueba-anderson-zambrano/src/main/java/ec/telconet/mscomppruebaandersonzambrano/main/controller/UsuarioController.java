package ec.telconet.mscomppruebaandersonzambrano.main.controller;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.service.UsuarioService;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/name/{name}")
    public ResponseEntity<OutputEntity<List<UsuarioResponse>>> findByName(@PathVariable String name){
        OutputEntity <List<UsuarioResponse>> out = null;
        try{
            out = this.usuarioService.findByName(name);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<UsuarioResponse>>().error();
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





    @GetMapping("/mail/{mail}")
    public ResponseEntity<OutputEntity<List<UsuarioResponse>>> findByMail(@PathVariable String mail){
        OutputEntity <List<UsuarioResponse>> out = null;
        try{
            out = this.usuarioService.findByMail(mail);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<UsuarioResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/paginacion")
    public ResponseEntity<OutputEntity<List<UsuarioResponse>>> findByMail(@RequestParam Integer pInicial,
                                                                          @RequestParam Integer pFinal){
        OutputEntity <List<UsuarioResponse>> out = null;
        try{
            out = this.usuarioService.getLastuser(pInicial,pFinal);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<UsuarioResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @GetMapping()
    public ResponseEntity<OutputEntity<List<UsuarioResponse>>> getAll(){
        OutputEntity <List<UsuarioResponse>> out = null;
        try{
            out = this.usuarioService.getAll();
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<UsuarioResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PostMapping()
    public ResponseEntity<OutputEntity<String>> create(@RequestBody UsuarioRequest data){
        OutputEntity<String> out = null;
        try{
            out = this.usuarioService.create(data);
            return new ResponseEntity<>(out, out.getCode());
        }catch (Exception e){
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> update(@PathVariable Long id,
                                                       @RequestBody UsuarioRequest data){
        OutputEntity<String> out = null;
        try{
            out = this.usuarioService.update(id,data);
            return new ResponseEntity<>(out, out.getCode());
        }catch (Exception e){
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> delete(@PathVariable Long id){
        OutputEntity<String> out = null;
        try{
            out = this.usuarioService.delete(id);
            return new ResponseEntity<>(out, out.getCode());
        }catch (Exception e){
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }




}
