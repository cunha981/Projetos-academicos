package br.com.scnc.bean;

import java.util.Date;
import java.util.List;

public class Voluntario {
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String tel;
	private String estadoCivil;
	private Endereco endereco;
	private Date dataNascimento;
	private Character genero;
	//private AreaAtuacao areaAtuacao;
	private boolean status;
	private List<AreaAtuacao> areasAtuacao;
	private List<Disponibilidade> disponibilidades;
	private List<Projeto> projetos;
	private Integer qtdProjetos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Character getGenero() {
		return genero;
	}

	public void setGenero(Character genero) {
		this.genero = genero;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	*/
	public List<AreaAtuacao> getAreasAtuacao() {
		return areasAtuacao;
	}
	
	public void setAreaAtuacoes(List<AreaAtuacao> areasAtuacao) {
		this.areasAtuacao = areasAtuacao;
	}
	
	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Integer getQtdProjetos() {
		return qtdProjetos;
	}

	public void setQtdProjetos(Integer qtdProjetos) {
		this.qtdProjetos = qtdProjetos;
	}
}
