package com.newidea.cursomc;

import com.newidea.cursomc.domain.Categoria;
import com.newidea.cursomc.domain.Cidade;
import com.newidea.cursomc.domain.Estado;
import com.newidea.cursomc.domain.Produto;
import com.newidea.cursomc.repositories.CategoriaRepository;
import com.newidea.cursomc.repositories.CidadeRepository;
import com.newidea.cursomc.repositories.EstadoRepository;
import com.newidea.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "desktop");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Sao Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "Sao Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll((Arrays.asList(e1, e2)));

		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}

