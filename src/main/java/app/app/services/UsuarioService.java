package com.sismanut.sismanut.services;

import com.sismanut.sismanut.config.customExceptions.*;
import com.sismanut.sismanut.config.messageHandling.errorMessages.ExceptionMessages;
import com.sismanut.sismanut.config.messageHandling.successMessages.SuccessMessages;
import com.sismanut.sismanut.config.validations.EnumValidation;
import com.sismanut.sismanut.dto.usuario.entrada.*;
import com.sismanut.sismanut.dto.usuario.saida.UsuarioLogadoToken;
import com.sismanut.sismanut.dto.usuario.saida.UsuarioRegistradoDTOOut;
import com.sismanut.sismanut.entities.Setor;
import com.sismanut.sismanut.entities.UsuarioEntity;
import com.sismanut.sismanut.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    public UsuarioRegistradoDTOOut buscarUsuario(UsuarioABuscarDTOIn usuario)
            throws UserNotFoundException {
        UsuarioEntity usuarioEntity = repository.encontrarPorNomeEmailOuLogin(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin());

        if(usuarioEntity == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        return modelMapper.map(usuarioEntity, UsuarioRegistradoDTOOut.class);
    }
    public List<UsuarioRegistradoDTOOut> buscarUsuarios(UsuariosAListarDTOIn tipoDeUsuarios)
        throws UserListNotFoundException, IllegalArgumentException{

        EnumValidation<Setor> enumValidator = new EnumValidation<>();
        if(!enumValidator.enumValidation(tipoDeUsuarios.getSetor())){
            throw new IllegalArgumentException(ExceptionMessages.ENUM_NOT_FOUND);
        }

        List<UsuarioEntity> usuarioEntities = repository.encontrarPorCargoOuSetor(
                tipoDeUsuarios.getCargo(),
                tipoDeUsuarios.getSetor());
        if(usuarioEntities == null || usuarioEntities.isEmpty()){
            throw new UserListNotFoundException(ExceptionMessages.USER_LIST_NOT_FOUND);
        }

        List<UsuarioRegistradoDTOOut> usuariosARetornar = new ArrayList<>();
        usuarioEntities.forEach(usuarioAConverter -> {
            usuariosARetornar.add(modelMapper.map(usuarioAConverter, UsuarioRegistradoDTOOut.class));
        });

        return usuariosARetornar;
    }
    public String salvarUsuario(UsuarioARegistrarDTOIn usuario)
            throws DataIntegrityViolationException, IllegalArgumentException {

        EnumValidation<Setor> enumValidator = new EnumValidation<>();
        if(!enumValidator.enumValidation(usuario.getSetor())){
            throw new IllegalArgumentException(ExceptionMessages.ENUM_NOT_FOUND);
        }

        if(repository.verificarSeNomeExiste(usuario.getNome())){
            throw new DataIntegrityViolationException(ExceptionMessages.NAME_ALREADY_EXISTS);
        } else if(repository.verificarSeEmailExiste(usuario.getEmail())){
            throw new DataIntegrityViolationException(ExceptionMessages.EMAIL_ALREADY_EXISTS);
        } else if(repository.verificarSeLoginExiste(usuario.getLogin())){
            throw new DataIntegrityViolationException(ExceptionMessages.LOGIN_ALREADY_EXISTS);
        }

        UsuarioEntity usuarioASalvar = modelMapper.map(usuario, UsuarioEntity.class);
        repository.save(usuarioASalvar);

        return SuccessMessages.USER_SAVED;
    }
    public String atualizarUsuario(UsuarioAAtualizarDTOIn usuario)
            throws UserNotFoundException,
            IllegalArgumentException,
            DataIntegrityViolationException{

        EnumValidation<Setor> enumValidator = new EnumValidation<>();
        if(!enumValidator.enumValidation(usuario.getSetor())){
            throw new IllegalArgumentException(ExceptionMessages.ENUM_NOT_FOUND);
        }

        UsuarioEntity usuarioEntity = repository.encontrarPorNomeEmailOuLogin(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin());

        if(usuarioEntity == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        if(repository.verificarSeNomeExiste(usuario.getNome())
                && !usuarioEntity.getNome().equals(usuario.getNome())){
            throw new DataIntegrityViolationException(ExceptionMessages.NAME_ALREADY_EXISTS);
        } else if(repository.verificarSeEmailExiste(usuario.getEmail())
                && !usuarioEntity.getEmail().equals(usuario.getEmail())){
            throw new DataIntegrityViolationException(ExceptionMessages.EMAIL_ALREADY_EXISTS);
        } else if(repository.verificarSeLoginExiste(usuario.getLogin())
                && !usuarioEntity.getLogin().equals(usuario.getLogin())){
            throw new DataIntegrityViolationException(ExceptionMessages.LOGIN_ALREADY_EXISTS);
        }

        UsuarioEntity usuarioASalvar = modelMapper.map(usuario, UsuarioEntity.class);
        usuarioASalvar.setId(usuarioEntity.getId());
        repository.save(usuarioASalvar);

        return SuccessMessages.USER_UPDATED;
    }
    public String atualizarSenha(UsuarioAAtualizarSenhaDTOIn usuario)
            throws UserNotFoundException,
            PasswordsDontMatchException,
            OldPasswordProvidedException{

        UsuarioEntity usuarioEntity = repository.encontrarPorNomeEmailOuLogin(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin());

        if(usuarioEntity == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        } else if(!usuarioEntity.getPassword().equals(usuario.getSenhaAtual())){
            throw new PasswordsDontMatchException(ExceptionMessages.PASSWORDS_DONT_MATCH);
        } else if(!usuario.getNovaSenha().equals(usuario.getSenhaAtual())){
            throw new OldPasswordProvidedException(ExceptionMessages.OLD_PASSWORD);
        }

        usuarioEntity.setPassword(usuario.getNovaSenha());
        repository.save(usuarioEntity);

        return SuccessMessages.PASSWORD_UPDATED;
    }
    public String excluirOuDesativarUsuario(UsuarioAExcluirDTOIn usuario)
            throws UserNotFoundException{

        UsuarioEntity usuarioEntity = repository.encontrarPorNomeEmailOuLogin(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin());

        if(usuarioEntity == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        } else if(!usuarioEntity.getPassword().equals(usuario.getSenha())){
            throw new PasswordNotValidException(ExceptionMessages.WRONG_PASSWORD);
        }

        repository.delete(usuarioEntity);
        return SuccessMessages.USER_DELETED;
    }

    public UsuarioLogadoToken logarUsuario(UsuarioALogarDTOIn usuario)
            throws PasswordNotValidException,
            UserNotFoundException{

//        private String ipAddress;
//        private String accessedLogin;
//        private String temporaryToken;

        httpServletRequest.getRemoteAddr();

        UsuarioEntity usuarioEntity = repository.encontrarPorLogin(usuario.getLogin());

        if(usuarioEntity == null){
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        } else if(!usuarioEntity.getPassword().equals(usuario.getSenha())){
            throw new PasswordNotValidException(ExceptionMessages.WRONG_PASSWORD);
        }

//        UsuarioLogadoToken usuarioRetorno = new UsuarioLogadoToken();
//        usuarioRetorno.setContator();
//        this.contador++;
        return new UsuarioLogadoToken(String.valueOf(httpServletRequest.getRemoteAddr()));
    }
}
