package app.app.coreClasses.genericCrudSuperClasses;

import app.app.coreClasses.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudGenericController <
        Entity extends CrudGenericEntity,
        Service extends CrudGenericService<Entity>
        >{
    @Autowired
    Service service;
    @PostMapping(CrudGenericEndPointName.SAVE)
    private ResponseEntity<ResponseWrapper<String>>
    register(@RequestBody Entity object){

        ResponseWrapper<String> response = new ResponseWrapper<>();

        try {
            response.setMessage(service.save(object));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping(CrudGenericEndPointName.FIND_ALL)
    private ResponseEntity<ResponseWrapper<List<Entity>>>
    listAll(){

        ResponseWrapper<List<Entity>> response = new ResponseWrapper<>();

        try {
            response.setObject(service.listAll());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping(CrudGenericEndPointName.FIND_BY_ID)
    private ResponseEntity<ResponseWrapper<Entity>>
    findById(@RequestBody Entity entity) {

        ResponseWrapper<Entity> response = new ResponseWrapper<>();

        try {
            response.setObject(service.findById(entity.getId()));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping(CrudGenericEndPointName.UPDATE)
    private ResponseEntity<ResponseWrapper<String>>
    update(@RequestBody Entity object) {

        ResponseWrapper<String> response = new ResponseWrapper<>();

        try {
            response.setMessage(service.update(object));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping(CrudGenericEndPointName.DELETE)
    private ResponseEntity<ResponseWrapper<String>>
    delete(@RequestBody Entity object) {

        ResponseWrapper<String> response = new ResponseWrapper<>();

        try {
            response.setMessage(service.delete(object.getId()));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
