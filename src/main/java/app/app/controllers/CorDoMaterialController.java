package app.app.controllers;

import com.sismanut.sismanut.coreClasses.ResponseWrapper;
import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericController;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.CorDoMaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.CorDoMaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.caracteristicas.CorDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.caracteristicas.CorDoMaterialRepository;
import com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial.CorDoMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("amboni/estoque/insumo/cor")
public class CorDoMaterialController extends CrudGenericController<
        CorDoMaterialEntity,
        CorDoMaterialService,
        CorDoMaterialRepository,
        CorDoMaterialDTOIn,
        CorDoMaterialDTOOut> {
    @GetMapping(value = "/modelo")
    public ResponseEntity<ResponseWrapper<CorDoMaterialEntity>> modeloEntidade(){
        ResponseWrapper<CorDoMaterialEntity> resposta = new ResponseWrapper<>();
        resposta.setObject(new CorDoMaterialEntity());
        return ResponseEntity.ok(resposta);
    }
}
