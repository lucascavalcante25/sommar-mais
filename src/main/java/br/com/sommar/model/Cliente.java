package br.com.sommar.model;

import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String abertura;
	private String situacao;
	private String tipo;
	private String nome;
	private String porte;
	private String fantasia;
	private String ultimaAtualizacao;
	private String cnpj;
	private String logradouro;
	private String numero;
	private String municipio;
	private String bairro;
	private String uf;
	private String cep;
	private String email;
	private String telefone;
	private String status;
	private String complemento;
	private String capitalSocial;
	private String naturezaJuridica;
	private List<String> listaDeAtividadesSecundarias;

	private String atividadePrincipalCode;
	private String atividadePrincipalText;

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(String capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	public List<String> getListaDeAtividadesSecundarias() {
		return listaDeAtividadesSecundarias;
	}

	public void setListaDeAtividadesSecundarias(List<String> listaDeAtividadesSecundarias) {
		this.listaDeAtividadesSecundarias = listaDeAtividadesSecundarias;
	}

	public String getAtividadePrincipalCode() {
		return atividadePrincipalCode;
	}

	public void setAtividadePrincipalCode(String atividadePrincipalCode) {
		this.atividadePrincipalCode = atividadePrincipalCode;
	}

	public String getAtividadePrincipalText() {
		return atividadePrincipalText;
	}

	public void setAtividadePrincipalText(String atividadePrincipalText) {
		this.atividadePrincipalText = atividadePrincipalText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abertura == null) ? 0 : abertura.hashCode());
		result = prime * result + ((atividadePrincipalCode == null) ? 0 : atividadePrincipalCode.hashCode());
		result = prime * result + ((atividadePrincipalText == null) ? 0 : atividadePrincipalText.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((capitalSocial == null) ? 0 : capitalSocial.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fantasia == null) ? 0 : fantasia.hashCode());
		result = prime * result
				+ ((listaDeAtividadesSecundarias == null) ? 0 : listaDeAtividadesSecundarias.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((naturezaJuridica == null) ? 0 : naturezaJuridica.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((porte == null) ? 0 : porte.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + ((ultimaAtualizacao == null) ? 0 : ultimaAtualizacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (abertura == null) {
			if (other.abertura != null)
				return false;
		} else if (!abertura.equals(other.abertura))
			return false;
		if (atividadePrincipalCode == null) {
			if (other.atividadePrincipalCode != null)
				return false;
		} else if (!atividadePrincipalCode.equals(other.atividadePrincipalCode))
			return false;
		if (atividadePrincipalText == null) {
			if (other.atividadePrincipalText != null)
				return false;
		} else if (!atividadePrincipalText.equals(other.atividadePrincipalText))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (capitalSocial == null) {
			if (other.capitalSocial != null)
				return false;
		} else if (!capitalSocial.equals(other.capitalSocial))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fantasia == null) {
			if (other.fantasia != null)
				return false;
		} else if (!fantasia.equals(other.fantasia))
			return false;
		if (listaDeAtividadesSecundarias == null) {
			if (other.listaDeAtividadesSecundarias != null)
				return false;
		} else if (!listaDeAtividadesSecundarias.equals(other.listaDeAtividadesSecundarias))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (naturezaJuridica == null) {
			if (other.naturezaJuridica != null)
				return false;
		} else if (!naturezaJuridica.equals(other.naturezaJuridica))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (porte == null) {
			if (other.porte != null)
				return false;
		} else if (!porte.equals(other.porte))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		if (ultimaAtualizacao == null) {
			if (other.ultimaAtualizacao != null)
				return false;
		} else if (!ultimaAtualizacao.equals(other.ultimaAtualizacao))
			return false;
		return true;
	}
}
