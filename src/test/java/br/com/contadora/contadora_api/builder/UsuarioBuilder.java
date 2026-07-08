package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.dto.UsuarioRequest;
import net.datafaker.Faker;

public class UsuarioBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));

    private String nome = faker.name().fullName();
    private String email = faker.internet().emailAddress();
    private String senha = "123456";

    public static UsuarioBuilder umUsuario() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return  this;
    }

    public UsuarioRequest build() {
        return new UsuarioRequest(nome, email, senha);
    }
}