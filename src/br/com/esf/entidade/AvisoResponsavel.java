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
@Table(name = "avisos_resp", catalog = "mydb")
public class AvisoResponsavel extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AVISOS", unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_RESPONSAVEL")
	private Responsavel responsavel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA")
	private Date data;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA")
	private Date hora;

	@Column(name = "TEXTO", columnDefinition = "TEXT")
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORARIO_SAIDA")
	private Date horarioSaida;

	public AvisoResponsavel(Long id) {
		this.id = id;
	}

	public AvisoResponsavel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(Date horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

}
