package br.com.esf.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "feriados_escolares", catalog = "mydb")
public class FeriadosEscolares extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FERIADOS_ESCOLARES", unique = true)
	private Long id;

	@Column(name = "ANO")
	private String ano;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DIA_INICIO")
	private Date diaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DIA_FIM")
	private Date diaFim;

	public FeriadosEscolares() {
	}

	public FeriadosEscolares(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Date getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getDiaFim() {
		return diaFim;
	}

	public void setDiaFim(Date diaFim) {
		this.diaFim = diaFim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((diaFim == null) ? 0 : diaFim.hashCode());
		result = prime * result + ((diaInicio == null) ? 0 : diaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FeriadosEscolares other = (FeriadosEscolares) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (diaFim == null) {
			if (other.diaFim != null)
				return false;
		} else if (!diaFim.equals(other.diaFim))
			return false;
		if (diaInicio == null) {
			if (other.diaInicio != null)
				return false;
		} else if (!diaInicio.equals(other.diaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
