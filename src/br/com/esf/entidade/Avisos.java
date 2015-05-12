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
@Table(name = "avisos", catalog = "mydb")
public class Avisos extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AVISOS", unique = true)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA")
	private Date data;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA")
	private Date hora;

	@Column(name = "TEXTO",columnDefinition="TEXT")
	private String texto;

	@Column(name = "EMISSOR")
	private String emissor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORARIO_SAIDA")
	private Date horarioSaida;

	public Avisos(Long id) {
		this.id = id;
	}

	public Avisos() {
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

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
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

}
