package br.com.scnc.bean;

import java.util.Date;
import java.util.List;

public class Projeto {
	private Integer id;
	private String nome;
	private String descricao;
	private List<Disponibilidade> disponibilidades;
	private List<Voluntario> voluntarios;
	private AreaAtuacao areaAtuacao;
	private Date dataCadastro;
	private Date dataInicio;
	private Date periodoInscricao;
	private Integer qtdVoluntarios;
	private Integer os;
	private String nmOS;

	public enum Situacao {
		BuscaDeVoluntario, Ativo, Suspenso, Extinto;
	}

	private Situacao situacao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getPeriodoInscricao() {
		return periodoInscricao;
	}

	public void setPeriodoInscricao(Date periodoInscricao) {
		this.periodoInscricao = periodoInscricao;
	}

	public Integer getQtdVoluntarios() {
		return qtdVoluntarios;
	}

	public void setQtdVoluntarios(Integer qtdVoluntarios) {
		this.qtdVoluntarios = qtdVoluntarios;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getNmOS() {
		return nmOS;
	}

	public void setNmOS(String nmOS) {
		this.nmOS = nmOS;
	}
}
