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
@Table(name = "periodo_responsavel", catalog = "mydb")
public class PeriodoResponsavel extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERIODO_RESPONSAVEL", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_RESPONSAVEL")
	private Responsavel responsavel;

	@Column(name = "INICIO")
	private Date inicio;

	@Column(name = "TERMINO")
	private Date termino;

	@Column(name = "PERMANENTE")
	private Boolean permanente;

	@Column(name = "PODE_AUTORIZAR")
	private Boolean podeAutorizar;

	public PeriodoResponsavel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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

	public Boolean getPermanente() {
		return permanente;
	}

	public void setPermanente(Boolean permanente) {
		this.permanente = permanente;
	}

	public Boolean getPodeAutorizar() {
		return podeAutorizar;
	}

	public void setPodeAutorizar(Boolean podeAutorizar) {
		this.podeAutorizar = podeAutorizar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((permanente == null) ? 0 : permanente.hashCode());
		result = prime * result + ((podeAutorizar == null) ? 0 : podeAutorizar.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((termino == null) ? 0 : termino.hashCode());
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
		PeriodoResponsavel other = (PeriodoResponsavel) obj;
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
		if (permanente == null) {
			if (other.permanente != null)
				return false;
		} else if (!permanente.equals(other.permanente))
			return false;
		if (podeAutorizar == null) {
			if (other.podeAutorizar != null)
				return false;
		} else if (!podeAutorizar.equals(other.podeAutorizar))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (termino == null) {
			if (other.termino != null)
				return false;
		} else if (!termino.equals(other.termino))
			return false;
		return true;
	}

}
