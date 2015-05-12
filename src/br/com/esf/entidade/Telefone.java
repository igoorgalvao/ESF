package br.com.esf.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "telefone", catalog = "mydb")
public class Telefone extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TELEFONE", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_PROFESSOR")
	private Professor professor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_ALUNO")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_RESPONSAVEL")
	private Responsavel responsavel;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "DDD")
	private String ddd;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "OPERADORA_CELULAR")
	private String operadoraCelular;

	@Column(name = "RASTREADO")
	private Boolean rastreado;

	public Telefone() {
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOperadoraCelular() {
		return operadoraCelular;
	}

	public void setOperadoraCelular(String operadoraCelular) {
		this.operadoraCelular = operadoraCelular;
	}

	public Boolean getRastreado() {
		return rastreado;
	}

	public void setRastreado(Boolean rastreado) {
		this.rastreado = rastreado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operadoraCelular == null) ? 0 : operadoraCelular.hashCode());
		result = prime * result + ((rastreado == null) ? 0 : rastreado.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Telefone other = (Telefone) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operadoraCelular == null) {
			if (other.operadoraCelular != null)
				return false;
		} else if (!operadoraCelular.equals(other.operadoraCelular))
			return false;
		if (rastreado == null) {
			if (other.rastreado != null)
				return false;
		} else if (!rastreado.equals(other.rastreado))
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
		return true;
	}

}
