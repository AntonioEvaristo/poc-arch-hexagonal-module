package br.com.ae.integration.mapper;

import br.com.ae.domain.model.Categoria;
import br.com.ae.domain.model.Produto;
import br.com.ae.integration.model.CategoriaEntity;
import br.com.ae.integration.model.ProdutoEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(target = "categoria", source = "produtoEntity.categoriaEntity")
    public abstract Produto produtoEntityToProduto(ProdutoEntity produtoEntity);
    @Mapping(target = "categoriaEntity", source ="produto.categoria")
    public abstract ProdutoEntity produtoToProdutoEntity(Produto produto);

}
