package br.com.casadocodigo.loja.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

@Component
public class UsuarioValidation implements Validator{
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");

		Usuario usuario = (Usuario) target;
		
		// Validando o tamanho da senha
		if(usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "field.required.usuario.tamanhoSenha");
		}
		
		// Validando os dois campos de senha
		if(!usuario.getSenha().equals(usuario.getSenhaConfirmada())){
			errors.rejectValue("senhaConfirmada", "field.required.usuario.senhaConfirmada");
		}
		
		// Verificando se email já existe
		if(usuarioDao.emailExists(usuario.getEmail())) {
			errors.rejectValue("email", "field.required.usuario.emailExistente");
		}
	}
}