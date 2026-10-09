package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.model.endereco.Endereco;
import net.datafaker.Faker;
import java.util.List;


public class ClienteBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));

    private String nome = faker.name().fullName();
    private String telefone = faker.phoneNumber().cellPhone();
    private String cpf = faker.cpf().valid(false);
    private List<Endereco> enderecos = List.of(EnderecoBuilder.umEndereco().buildEntity());

    public static ClienteBuilder umCliente() {
        return new ClienteBuilder();
    }

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteDTO build() {
        return new ClienteDTO(null, nome, telefone, cpf, enderecos);
    }
}