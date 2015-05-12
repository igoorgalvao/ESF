package br.com.esf.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "turma", catalog = "mydb")
public class Turma extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TURMA", unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_PROFESSOR")
	private Professor professor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ESCOLA")
	private Escola escola;

	@Column(name = "TURMA")
	private String turma;

	@Column(name = "ANO")
	private String ano;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INICIO")
	private Date inicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TERMINO")
	private Date termino;

	@Column(name = "ENSINO")
	private String ensino;

	public Turma(Long id) {
		this.id = id;
	}

	public Turma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getEnsino() {
		return ensino;
	}

	public void setEnsino(String ensino) {
		this.ensino = ensino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((ensino == null) ? 0 : ensino.hashCode());
		result = prime * result + ((escola == null) ? 0 : escola.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((termino == null) ? 0 : termino.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		Turma other = (Turma) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (ensino == null) {
			if (other.ensino != null)
				return false;
		} else if (!ensino.equals(other.ensino))
			return false;
		if (escola == null) {
			if (other.escola != null)
				return false;
		} else if (!escola.equals(other.escola))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (termino == null) {
			if (other.termino != null)
				return false;
		} else if (!termino.equals(other.termino))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

}
