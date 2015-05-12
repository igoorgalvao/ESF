package br.com.esf.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "tipo_atividade", catalog = "mydb")
public class TipoAtividade extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_ATIVIDADE", unique = true, nullable = false)
	private Long id;

	@Column(name = "TIPO", nullable = false)
	private String tipo;

	public TipoAtividade() {
	}

	public TipoAtividade(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
