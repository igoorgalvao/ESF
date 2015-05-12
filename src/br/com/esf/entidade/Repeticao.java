package br.com.esf.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "repeticao", catalog = "mydb")
public class Repeticao extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REPETICAO", unique = true, nullable = false)
	private Long id;

	@Column(name = "REPETICAO")
	private String repeticao;

	public Repeticao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(String repeticao) {
		this.repeticao = repeticao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
