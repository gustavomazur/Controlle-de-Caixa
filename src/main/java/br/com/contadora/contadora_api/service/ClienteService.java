package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.dto.EnderecoDTO;
import br.com.contadora.contadora_api.mapper.ClienteMapper;
import br.com.contadora.contadora_api.mapper.EnderecoMapper;
import br.com.contadora.contadora_api.model.Cliente.Cliente;
import br.com.contadora.contadora_api.model.endereco.Endereco;
import br.com.contadora.contadora_api.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final EnderecoMapper enderecoMapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper, EnderecoMapper enderecoMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.enderecoMapper = enderecoMapper;
    }

    public ClienteDTO findByNome(String nome) {
        Cliente cliente = repository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Usuário " + nome + " não encontrado"));
        return mapper.paraDTO(cliente);
    }

    public ClienteDTO insert(@Valid ClienteDTO clienteDTO) {
        Cliente novoCliente = mapper.paraEntidade(clienteDTO);
        novoCliente.setId(null);
        novoCliente = repository.save(novoCliente);
        return mapper.paraDTO(novoCliente);
    }

    public ClienteDTO criarEndereco(Long clienteId, EnderecoDTO dto) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        if (cliente.getEndereco() != null && !cliente.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("Cliente já possui endereço cadastrado. Utilize o endpoint de atualização.");
        }

        Endereco endereco = enderecoMapper.paraEntidade(dto);

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);
        cliente.setEndereco(enderecos);

        cliente = repository.save(cliente);
        return mapper.paraDTO(cliente);
    }

    public ClienteDTO atualizarEndereco(Long clienteId, Long enderecoId, EnderecoDTO dto) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("Cliente não possui endereço cadastrado. Utilize o endpoint de criação.");
        }

        Endereco endereco = cliente.getEndereco().stream()
                .filter(e -> e.getId().equals(enderecoId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + enderecoId));

        endereco.setNome(dto.nome());
        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setReferecncia(dto.referencia());

        cliente = repository.save(cliente);
        return mapper.paraDTO(cliente);

    }
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

        if (clienteDTO.nome() != null) cliente.setNome(clienteDTO.nome());
        if (clienteDTO.telefone() != null) cliente.setTelefone(clienteDTO.telefone());
        if (clienteDTO.cpf() != null) cliente.setCpf(clienteDTO.cpf());

        cliente = repository.save(cliente);
        return mapper.paraDTO(cliente);
    }

    public List<ClienteDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::paraDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id.longValue());
    }
}