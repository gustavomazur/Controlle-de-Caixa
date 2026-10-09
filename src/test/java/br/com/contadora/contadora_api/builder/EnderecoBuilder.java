package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.dto.EnderecoDTO;
import br.com.contadora.contadora_api.model.endereco.Endereco;
import net.datafaker.Faker;

public class EnderecoBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));


    private String nome = "Casa";
    private String cep = faker.address().zipCode();
    private String rua = faker.address().streetName();
    private String numero = faker.address().buildingNumber();
    private String referencia = faker.address().secondaryAddress();

    public static EnderecoBuilder umEndereco() {
        return new EnderecoBuilder();
    }

    public EnderecoBuilder comCep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoDTO buildDTO() {
        return new EnderecoDTO(nome, cep, rua, numero, referencia);
    }

    public Endereco buildEntity() {
        Endereco e = new Endereco();
        e.setNome(nome);
        e.setCep(cep);
        e.setRua(rua);
        e.setNumero(numero);
        e.setReferecncia(referencia);
        return e;
    }
}