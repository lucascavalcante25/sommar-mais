package br.com.sommar.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;;

@ManagedBean
@ViewScoped
public class ConsultaCnpjBean {

	private String cnpj;
	private JSONObject resultado;
	private List<DadosCNPJBuscado> dadosCNPJBuscado;

	public void consultar() {
		try {
			String url = "https://www.receitaws.com.br/v1/cnpj/" + this.cnpj;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				JSONObject json = new JSONObject(response.toString());
				this.dadosCNPJBuscado = new ArrayList<DadosCNPJBuscado>();

				if (json != null) {

					JSONObject jsonObj = new JSONObject(json.toString());
					DadosCNPJBuscado dadosCNPJ = new DadosCNPJBuscado();

					String abertura = jsonObj.getString("abertura");
					String situacao = jsonObj.getString("situacao");
					String tipo = jsonObj.getString("tipo");
					String nome = jsonObj.getString("nome");
					String porte = jsonObj.getString("porte");
					String fantasia = jsonObj.getString("fantasia");
					String ultimaAtualizacao = jsonObj.getString("ultima_atualizacao");
					String cnpj = jsonObj.getString("cnpj");
					String logradouro = jsonObj.getString("logradouro");
					String numero = jsonObj.getString("numero");
					String municipio = jsonObj.getString("municipio");
					String bairro = jsonObj.getString("bairro");
					String uf = jsonObj.getString("uf");
					String cep = jsonObj.getString("cep");
					String email = jsonObj.getString("email");
					String telefone = jsonObj.getString("telefone");
					String status = jsonObj.getString("status");
					String complemento = jsonObj.getString("complemento");
					String capitalSocial = jsonObj.getString("capital_social");
					String naturezaJuridica = jsonObj.getString("natureza_juridica");

					dadosCNPJ.setAbertura(abertura);
					dadosCNPJ.setSituacao(situacao);
					dadosCNPJ.setTipo(tipo);
					dadosCNPJ.setNome(nome);
					dadosCNPJ.setPorte(porte);
					dadosCNPJ.setFantasia(fantasia);
					dadosCNPJ.setUltimaAtualizacao(ultimaAtualizacao);
					dadosCNPJ.setCnpj(cnpj);
					dadosCNPJ.setLogradouro(logradouro);
					dadosCNPJ.setNumero(numero);
					dadosCNPJ.setMunicipio(municipio);
					dadosCNPJ.setBairro(bairro);
					dadosCNPJ.setUf(uf);
					dadosCNPJ.setCep(cep);
					dadosCNPJ.setEmail(email);
					dadosCNPJ.setTelefone(telefone);
					dadosCNPJ.setStatus(status);
					dadosCNPJ.setComplemento(complemento);
					dadosCNPJ.setCapitalSocial(capitalSocial);
					dadosCNPJ.setNaturezaJuridica(naturezaJuridica);

					JSONArray atividadePrincipal = jsonObj.getJSONArray("atividade_principal");

					for (int i = 0; i < atividadePrincipal.length(); i++) {
						JSONObject atividadeSecundariaObj = atividadePrincipal.getJSONObject(i);
						String atividadePrincipalCode = atividadeSecundariaObj.getString("code");
						String atividadePrincipalText = atividadeSecundariaObj.getString("text");

						dadosCNPJ.setAtividadePrincipalCode(atividadePrincipalCode);
						dadosCNPJ.setAtividadePrincipalText(atividadePrincipalText);
					}

					JSONArray atividadesSecundariasArray = jsonObj.getJSONArray("atividades_secundarias");
					List<String> listaDeAtividadesSecundarias = new ArrayList<String>();

					for (int i = 0; i < atividadesSecundariasArray.length(); i++) {
						JSONObject atividadeSecundariaObj = atividadesSecundariasArray.getJSONObject(i);
						String atividadeSecundariaCode = atividadeSecundariaObj.getString("code");
						String atividadeSecundariaText = atividadeSecundariaObj.getString("text");
						
						listaDeAtividadesSecundarias.add("Código: " + atividadeSecundariaCode + ", " + "Atividade: " + atividadeSecundariaText);
						
					}
					
					dadosCNPJ.setListaDeAtividadesSecundarias(listaDeAtividadesSecundarias);

					this.dadosCNPJBuscado.add(dadosCNPJ);

				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao consultar CNPJ", "HTTP error code: " + responseCode));
				Logger.getLogger(ConsultaCnpjBean.class.getName()).log(Level.SEVERE, "HTTP error code: {0}",
						responseCode);
			}
		} catch (

		Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao consultar CNPJ", e.getMessage()));
			Logger.getLogger(ConsultaCnpjBean.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public JSONObject getResultado() {
		return resultado;
	}

	public List<DadosCNPJBuscado> getDadosCNPJBuscado() {
		return dadosCNPJBuscado;
	}

	public void setDadosCNPJBuscado(List<DadosCNPJBuscado> dadosCNPJBuscado) {
		this.dadosCNPJBuscado = dadosCNPJBuscado;
	}

}
