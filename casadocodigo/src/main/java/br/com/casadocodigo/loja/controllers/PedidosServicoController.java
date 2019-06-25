package br.com.casadocodigo.loja.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
public class PedidosServicoController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/pedidos")
	public ModelAndView listaPedidos() {
		
		ResponseEntity<List<Pedido>> response = restTemplate.exchange(
			"https://book-payment.herokuapp.com/orders",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<Pedido>>() {});
			
		List<Pedido> pedidos = response.getBody();
		
		System.out.println(pedidos.toString());
		
		ModelAndView modelAndView = new ModelAndView("pedidos/pedidos");
		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView;
	}
}
