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
@Table(name = "atividade", catalog = "mydb")
public class Atividade extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ATIVIDADE", unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_REPETICAO")
	private Repeticao repeticao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_TIPO_ATIVIDADE")
	private TipoAtividade tipoAtividade;

	@Column(name = "ATIVIDADE")
	private String atividade;

	@Column(name = "SEGUNDA")
	private Boolean segunda;

	@Column(name = "TERCA")
	private Boolean terca;

	@Column(name = "QUARTA")
	private Boolean quarta;

	@Column(name = "QUINTA")
	private Boolean quinta;

	@Column(name = "SEXTA")
	private Boolean sexta;

	@Column(name = "SABADO")
	private Boolean sabado;

	@Column(name = "DOMINGO")
	private Boolean domingo;

	public Atividade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Repeticao getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(Repeticao repeticao) {
		this.repeticao = repeticao;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getTerca() {
		return terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividade == null) ? 0 : atividade.hashCode());
		result = prime * result + ((domingo == null) ? 0 : domingo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quarta == null) ? 0 : quarta.hashCode());
		result = prime * result + ((quinta == null) ? 0 : quinta.hashCode());
		result = prime * result + ((repeticao == null) ? 0 : repeticao.hashCode());
		result = prime * result + ((sabado == null) ? 0 : sabado.hashCode());
		result = prime * result + ((segunda == null) ? 0 : segunda.hashCode());
		result = prime * result + ((sexta == null) ? 0 : sexta.hashCode());
		result = prime * result + ((terca == null) ? 0 : terca.hashCode());
		result = prime * result + ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
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
		Atividade other = (Atividade) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (domingo == null) {
			if (other.domingo != null)
				return false;
		} else if (!domingo.equals(other.domingo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quarta == null) {
			if (other.quarta != null)
				return false;
		} else if (!quarta.equals(other.quarta))
			return false;
		if (quinta == null) {
			if (other.quinta != null)
				return false;
		} else if (!quinta.equals(other.quinta))
			return false;
		if (repeticao == null) {
			if (other.repeticao != null)
				return false;
		} else if (!repeticao.equals(other.repeticao))
			return false;
		if (sabado == null) {
			if (other.sabado != null)
				return false;
		} else if (!sabado.equals(other.sabado))
			return false;
		if (segunda == null) {
			if (other.segunda != null)
				return false;
		} else if (!segunda.equals(other.segunda))
			return false;
		if (sexta == null) {
			if (other.sexta != null)
				return false;
		} else if (!sexta.equals(other.sexta))
			return false;
		if (terca == null) {
			if (other.terca != null)
				return false;
		} else if (!terca.equals(other.terca))
			return false;
		if (tipoAtividade == null) {
			if (other.tipoAtividade != null)
				return false;
		} else if (!tipoAtividade.equals(other.tipoAtividade))
			return false;
		return true;
	}

}
