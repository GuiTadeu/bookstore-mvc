package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private UsuarioValidation usuarioValidation;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(usuarioValidation);
	}
	
	@RequestMapping("/form")
	public ModelAndView formCadastro(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/cadastroForm");
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return formCadastro(usuario);
		}
		
		usuarioDao.gravar(usuario);
		
		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/usuarios");
	}
	
	@GetMapping
	public ModelAndView listarUsuarios() {
		List<Usuario> usuarios = usuarioDao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@GetMapping("/roles/{email}")
	public ModelAndView listarRoles(@RequestParam("email") String email) {
		List<Role> roles = roleDao.listar();
		Usuario usuario = usuarioDao.findByEmail(email);
		ModelAndView modelAndView = new ModelAndView("usuarios/roles");
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
	@PostMapping("/atualizarRoles")
	public ModelAndView atualizarRoles(
			@RequestParam("checkRoles") List<Role> roles,
			String email, 
			RedirectAttributes redirectAttributes) {
		
		// Formatar os campos removendo a virgula
		email = email.replace(",", "");
		roles.remove(0);
		
		usuarioDao.alterRoles(email, roles);
		
		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		return listarUsuarios();
	}
}
