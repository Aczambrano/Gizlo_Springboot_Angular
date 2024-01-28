package ec.telconet.mscomppruebaandersonzambrano.main.service;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.ProductoEntity;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.models.UsuarioEntity;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.ProductoRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.request.UsuarioRequest;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.ProductoResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import ec.telconet.mscomppruebaandersonzambrano.main.repository.ProductoRepository;
import ec.telconet.mscomppruebaandersonzambrano.util.Exception.MyException;
import ec.telconet.mscomppruebaandersonzambrano.util.entities.OutputEntity;
import ec.telconet.mscomppruebaandersonzambrano.util.enums.MessageEnum;
import ec.telconet.mscomppruebaandersonzambrano.util.helper.MetodoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    public OutputEntity<List<ProductoResponse>> getlAll(){
        OutputEntity<List<ProductoResponse>> outputEntity = new OutputEntity<>();
        try {
            //Buscamos los datos de la tabla de la bdd
            List<ProductoEntity> productoModelo = this.productoRepository.findAll();
            //Mapeamos los datos con un dto (ProductoResponse)
            List<ProductoResponse> productoResponses = productoModelo.stream().map(
                    p -> new ProductoResponse(p)).collect(Collectors.toList());
            //retornamos la respuesta junto con la cosulta
            return outputEntity.ok(200, "Ok",productoResponses);

        }catch (Exception e){
            return outputEntity.error(500,"Error del sistema", null);
        }
    }

    public OutputEntity<List<ProductoResponse>> findByName(String name) {

        OutputEntity<List<ProductoResponse>> outPut = new OutputEntity<>();
        try {
            List<ProductoEntity> productoEntities = this.productoRepository.findByName(name);

            if(productoEntities.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            List<ProductoResponse> productoResponses = productoEntities.stream().map(u ->
                    new ProductoResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productoResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<ProductoResponse>> getLastuser(Integer pInicial, Integer pFinal){
        OutputEntity<List<ProductoResponse>> outPut = new OutputEntity<>();
        try{
            Pageable pageable = PageRequest.of(pInicial,pFinal, Sort.by("id").descending());
            List<ProductoEntity> productoEntities = this.productoRepository.findAll(pageable).getContent();

            if(productoEntities.isEmpty()){
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<ProductoResponse> productRespones = productoEntities.stream().map(u->
                    new ProductoResponse(u)).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productRespones);
        }catch (MyException e) {
            return outPut.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> create(ProductoRequest data){
        OutputEntity<String> output = new OutputEntity<>();
        try {
            ProductoEntity producto = new ProductoEntity(data);
            this.productoRepository.save(producto);
            return output.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
            //TODO GUARDAMOS
        }catch (Exception e){
            return output.error();
        }
    }

    public OutputEntity<String> update(Long id,ProductoRequest data) throws MyException {
        OutputEntity<String> output = new OutputEntity<>();


        try {
            ProductoEntity producto = this.productoRepository.findById(id).
                    orElseThrow(() -> new MyException(MessageEnum.NOT_FOUND.getCode(),
                            MessageEnum.NOT_FOUND.getMensaje()));

            producto.setNombre(data.getNombreP());
            producto.setCantidad(data.getCantidadP());
            producto.setPrecio(data.getPrecioP());
            producto.setImagen(data.getImagenP());
            producto.setEstado(data.getEstadoP());
            //producto.setUsuario(data.getIdUsuario());

            //UsuarioEntity user = new UsuarioEntity(usuario);
            this.productoRepository.save(producto);
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
            ProductoEntity producto = this.productoRepository.findById(id).
                    orElseThrow(() -> new MyException(MessageEnum.NOT_FOUND.getCode(),
                            MessageEnum.NOT_FOUND.getMensaje()));
            //TODO validar correo valido
            producto.setEstado('e');
            //UsuarioEntity user = new UsuarioEntity(usuario);
            this.productoRepository.save(producto);
            return output.ok(MessageEnum.DELETE.getCode(), MessageEnum.DELETE.getMensaje(), null);
            //TODO GUARDAMOS
        } catch (MyException e) {
            return output.error(e.getCode(),e.getMensaje(),null);
        }catch (Exception e){
            return output.error();
        }
    }

}
