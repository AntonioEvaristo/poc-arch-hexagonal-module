package br.com.ae.integration.mapper;

import br.com.ae.domain.model.Categoria;
import br.com.ae.domain.model.Produto;
import br.com.ae.integration.model.CategoriaEntity;
import br.com.ae.integration.model.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
    public abstract Produto produtoEntityToProduto(ProdutoEntity produtoEntity);
    public abstract ProdutoEntity produtoToProdutoEntity(Produto produto);

    public abstract Categoria categoriaEntityToCategoria(CategoriaEntity categoriaEntity);
    public abstract CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);
}
