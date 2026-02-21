package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.DadosCadastrarProdutoDTO;
import br.com.contadora.contadora_api.model.Produto;

public class ProdutoMapper {

    public static DadosCadastrarProdutoDTO paraDTO(Produto produto) {
        return new DadosCadastrarProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getCategoria(),
                produto.getPrecoquepaguei(),
                produto.getPrecodevenda(),
                produto.getImagem()
        );
    }

    public static Produto paraProduto(DadosCadastrarProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setCategoria(dto.categoria());
        produto.setPrecoquepaguei(dto.precoquepaguei());
        produto.setPrecodevenda(dto.precodevenda());
        produto.setImagem(dto.imagem());
        return produto;

    }
}

