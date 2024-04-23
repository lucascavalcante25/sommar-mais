package com.sommarApp.sommarApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sommarApp.sommarApp.models.Cliente;
import com.sommarApp.sommarApp.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Value("${wsReceita.url}")
	private String wsReceitaURL;

	@RequestMapping("/cadastrarcliente")
	public String form(Model model, MultipartFile file) {
		model.addAttribute("cliente", new Cliente());
		return "cliente/cadastrar-cliente";
	}

	@PostMapping("/cadastrarcliente")
	public String cadastrarcliente(@Valid @NonNull Cliente cliente, @RequestParam("file") MultipartFile file,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarcliente";
		}

		// Define os bytes do arquivo na entidade cliente
		// Salva o cliente no banco de dados
		clienteRepository.save(cliente);

		attributes.addFlashAttribute("mensagem", "cliente cadastrado com sucesso!");

		return "redirect:/clientes";
	}

	// GET que lista clientes
	@RequestMapping("/clientes")
	public ModelAndView listaclientees() {
		ModelAndView mv = new ModelAndView("cliente/cliente");
		Iterable<Cliente> cliente = clienteRepository.findAll();
		mv.addObject("cliente", cliente);
		return mv;
	}

	// GET que chama o FORM de edição do cliente
	@RequestMapping("/editar-cliente")
	public ModelAndView editarcliente(@RequestParam long id) {
		Cliente cliente = clienteRepository.findById(id);
		ModelAndView mv = new ModelAndView("cliente/update-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@PostMapping("/atualizar-cliente")
	public String atualizarcliente(@ModelAttribute Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {

		Cliente clienteExistente = clienteRepository.findById(cliente.getId());

		// Verifica se o cliente existe antes de atualizar
		if (clienteExistente != null) {
			// Atualize as propriedades do cliente existente com os dados do formulário
			clienteExistente.setNome(cliente.getNome());
			clienteExistente.setAbertura(cliente.getAbertura());
			clienteExistente.setSituacao(cliente.getSituacao());
			clienteExistente.setTipo(cliente.getTipo());
			clienteExistente.setPorte(cliente.getPorte());
			clienteExistente.setFantasia(cliente.getFantasia());
			clienteExistente.setCnpjCadastro(cliente.getCnpjCadastro());
			clienteExistente.setLogradouro(cliente.getLogradouro());
			clienteExistente.setNumero(cliente.getNumero());
			clienteExistente.setMunicipio(cliente.getMunicipio());
			clienteExistente.setBairro(cliente.getBairro());
			clienteExistente.setUf(cliente.getUf());
			clienteExistente.setCep(cliente.getCep());
			clienteExistente.setStatus(cliente.getStatus());
			clienteExistente.setNaturezaJuridica(cliente.getNaturezaJuridica());
//			clienteExistente.setListaDeAtividadesSecundarias(cliente.getListaDeAtividadesSecundarias());

			// Salva o cliente atualizado no repositório
			clienteRepository.save(clienteExistente);
			attributes.addFlashAttribute("successs", "Cliente alterado com sucesso!");

			// Redireciona de volta para a página de lista de clientes
			return "redirect:/clientes";
		} else {
			attributes.addFlashAttribute("erro", "Erro ao tentar atualizar!");
			return "redirect:/clientes";
		}
	}

	@SuppressWarnings("null")
	@RequestMapping("/deletarcliente")
	public String deletarcliente(@RequestParam long id) {
		Cliente cliente = clienteRepository.findById(id);
		clienteRepository.delete(cliente);

		return "redirect:/clientes";
	}

	@RequestMapping("/consultar-cnpj")
    public ResponseEntity<String> consultarCNPJ(@RequestParam String cnpj) {
        // Remove pontos e traços do CNPJ
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Criação do RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Chamada HTTP GET para o serviço da Receita Federal
            String jsonCNPJ = restTemplate.getForObject(wsReceitaURL + cnpj, String.class);

            // Formatação do JSON
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(jsonCNPJ, Object.class);
            String formattedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

            // Retorno do JSON formatado
            return ResponseEntity.ok(formattedJson);
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status 500 (Internal Server Error)
            return ResponseEntity.status(500).body("Erro ao consultar o CNPJ: " + e.getMessage());
        }
    }
	
	@PostMapping("/salvar-cliente-manual")
	public String cadastrarClienteManual(@ModelAttribute Cliente cliente) {
		// Salvar o novo cliente no banco de dados
		clienteRepository.save(cliente);
		// Redirecionar de volta para a lista de membros
		return "redirect:/cliente";
	}

//	@GetMapping("/visualizarArquivo")
//	public ResponseEntity<byte[]> visualizarArquivo(@RequestParam long id) {
//		Cliente cliente = clienteRepository.findById(id);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_PDF);
//		headers.setContentDisposition(
//				ContentDisposition.builder("attachment").filename(cliente.getNome() + " cifra.pdf").build());
//		headers.setContentLength(cliente.getArquivo().length);
//		return ResponseEntity.ok().headers(headers).body(cliente.getArquivo());
//	}

//	@RequestMapping(value = "/baixar-arquivo/{clienteId}", method = RequestMethod.GET)
//	public ResponseEntity<byte[]> baixarArquivo(@PathVariable Long clienteId) {
//		Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
//
//		if (cliente != null && cliente.getArquivo() != null) {
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//			headers.setContentDispositionFormData("attachment", "cifra.pdf"); // nome do arquivo de exemplo
//
//			return new ResponseEntity<>(cliente.getArquivo(), headers, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

}
