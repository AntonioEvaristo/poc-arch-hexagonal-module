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

    @Mapping(target = "id", source = "produtoEntity.id")
    @Mapping(target = "categoria.id", source = "produtoEntity.categoriaEntity.id")
    public abstract Produto produtoEntityToProduto(ProdutoEntity produtoEntity);
    public abstract Categoria categoriaEntityToCategoria(CategoriaEntity categoriaEntity);
    public abstract CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);

    @Mapping(target = "nome", source = "produto.nome")
    @Mapping(target = "codigo", source = "produto.codigo")
    @Mapping(target = "categoriaEntity", source = "categoria")
    @Mapping(target = "id", ignore = true)
    public abstract ProdutoEntity produtoToProdutoEntitySave(Produto produto, CategoriaEntity categoria);
    @Mapping(target = "id", source = "produto.id")
    @Mapping(target = "categoriaEntity.id", source = "produto.categoria.id")
    public abstract ProdutoEntity produtoToProdutoEntity(Produto produto);

}
