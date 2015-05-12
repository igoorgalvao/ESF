package br.com.esf.entidade;

// Generated 12/05/2015 15:56:52 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "destinatarios", catalog = "mydb")
public class Destinatarios extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_DESTINATARIOS", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_AVISOS", nullable = false)
	private Avisos avisos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_RESPONSAVEL", nullable = false)
	private Responsavel responsavel;

	public Destinatarios() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Avisos getAvisos() {
		return avisos;
	}

	public void setAvisos(Avisos avisos) {
		this.avisos = avisos;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

}
