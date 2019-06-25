package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@RestController
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
	
	@Autowired
	ProdutoDAO produtoDao = new ProdutoDAO();

	@GetMapping
	@ResponseBody
	public List<Produto> detalhes(){
		System.out.println("entrei no /relatorio-produtos");
		return produtoDao.listar();
	}
	
	@GetMapping(params={"data"})
	@ResponseBody
	public List<Produto> filtrarProdutosPorData(@RequestParam("data") String data) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = sdf.parse(data);
		Calendar calendar = Calendar. getInstance();
		calendar.setTime(date);
		return produtoDao.buscarPorData(calendar);
	}
	
}
