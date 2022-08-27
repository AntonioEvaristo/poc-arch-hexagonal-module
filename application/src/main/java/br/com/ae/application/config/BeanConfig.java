package br.com.ae.application.config;

import br.com.ae.domain.repository.IProdutoRepository;
import br.com.ae.domain.service.IProdutoService;
import br.com.ae.domain.service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IProdutoService produtoService(IProdutoRepository produtoRepository){
        return new ProdutoService(produtoRepository);
    }

}
