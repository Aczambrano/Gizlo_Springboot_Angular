package ec.telconet.mscomppruebaandersonzambrano.main.controller;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.ProductoRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.ProductoResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.service.ProductoService;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<OutputEntity<List<ProductoResponse>>> getAll(){
        //Declaramos la respuesta que vamos a devolver
        OutputEntity<List<ProductoResponse>> out = null;
        try{
            // Buscamos la respuesta del service
            out = this.productoService.getlAll();
            //retornamos la data y el código de respuesta del sistema
            return new ResponseEntity<>(out,out.getCode());

        }catch (Exception e){
            //inicializamos el out con un contructor propio de esa clase para enionar error
            out = new OutputEntity<List<ProductoResponse>>().error(500,"Error",null);
            //devolvemos el error
            return new ResponseEntity<>(out,out.getCode());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<OutputEntity<List<ProductoResponse>>> findByName(@PathVariable String name){
        OutputEntity <List<ProductoResponse>> out = null;
        try{
            out = this.productoService.findByName(name);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<ProductoResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/paginacion")
    public ResponseEntity<OutputEntity<List<ProductoResponse>>> findByMail(@RequestParam Integer pInicial,
                                                                          @RequestParam Integer pFinal){
        OutputEntity <List<ProductoResponse>> out = null;
        try{
            out = this.productoService.getLastuser(pInicial,pFinal);
            return new ResponseEntity<>(out,out.getCode());
        }catch (Exception e){
            out = new OutputEntity<List<ProductoResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PostMapping()
    public ResponseEntity<OutputEntity<String>> create(@RequestBody ProductoRequest data){
        OutputEntity<String> out = null;
        try{
            out = this.productoService.create(data);
            return new ResponseEntity<>(out, out.getCode());
        }catch (Exception e){
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> update(@PathVariable Long id,
                                                       @RequestBody ProductoRequest data){
        OutputEntity<String> out = null;
        try{
            out = this.productoService.update(id,data);
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
            out = this.productoService.delete(id);
            return new ResponseEntity<>(out, out.getCode());
        }catch (Exception e){
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }




}
