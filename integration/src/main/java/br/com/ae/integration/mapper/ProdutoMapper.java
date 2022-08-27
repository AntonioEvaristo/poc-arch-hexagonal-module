package br.com.ae.integration.mapper;

import br.com.ae.domain.model.Categoria;
import br.com.ae.domain.model.Produto;
import br.com.ae.integration.model.CategoriaEntity;
import br.com.ae.integration.model.ProdutoEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(target = "categoria", source = "produtoEntity.categoriaEntity")
    public abstract Produto produtoEntityToProduto(ProdutoEntity produtoEntity);
    public abstract Categoria categoriaEntityToCategoria(CategoriaEntity categoriaEntity);
    public abstract CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);

    @Mapping(target = "nome", source = "produto.nome")
    @Mapping(target = "codigo", source = "produto.codigo")
    @Mapping(target = "categoriaEntity", source = "categoria")
    @Mapping(target = "id", ignore = true)
    public abstract ProdutoEntity produtoToProdutoEntity(Produto produto, CategoriaEntity categoria);

}
