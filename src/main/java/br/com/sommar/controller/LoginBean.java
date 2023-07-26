package br.com.sommar.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sommar.model.Usuario;

@ManagedBean
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();

	  public String doEfetuarLogin() {
	    if("lucas".equals(usuario.getLogin())
	            && "123".equals(usuario.getSenha())) {
	    	
	      return "/paginas/Index.xhtml";
	    } else {
	      /* Cria uma mensagem. */
	      FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
	      /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
	      FacesContext.getCurrentInstance().addMessage("erro", msg);
	      return null;
	    }
	  }

	  public Usuario getUsuario() {
	    return usuario;
	  }

	  public void setUsuario(Usuario usuario) {
	    this.usuario = usuario;
	  }
    
}
