package br.com.ae.integration.repository;

import br.com.ae.integration.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoEntityRepository extends JpaRepository<ProdutoEntity, Long>{

    Optional<ProdutoEntity> findByCodigo(String codigo);
}
