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

@Entity
@Table(name = "alunos_de_saida", catalog = "mydb")
public class AlunosDeSaida implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ALUNOS_DE_SAIDA", unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESCOLA")
	private Escola escola;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ALUNO")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESPONSAVEL")
	private Responsavel responsavel;

	@Column(name = "FOTO_ALUNO",columnDefinition="BLOB")
	private byte[] fotoAluno;

	@Column(name = "FOTO_RESPONSAVEL",columnDefinition="BLOB")
	private byte[] fotoResponsavel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORARIO_SAIDA")
	private Date horarioSaida;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORARIO_BUSCA")
	private Date horarioBusca;

	@Column(name = "CELULAR_RESPONSAVEL")
	private String celularResponsavel;

	public AlunosDeSaida() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
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

	public byte[] getFotoAluno() {
		return fotoAluno;
	}

	public void setFotoAluno(byte[] fotoAluno) {
		this.fotoAluno = fotoAluno;
	}

	public byte[] getFotoResponsavel() {
		return fotoResponsavel;
	}

	public void setFotoResponsavel(byte[] fotoResponsavel) {
		this.fotoResponsavel = fotoResponsavel;
	}

	public Date getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(Date horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public Date getHorarioBusca() {
		return horarioBusca;
	}

	public void setHorarioBusca(Date horarioBusca) {
		this.horarioBusca = horarioBusca;
	}

	public String getCelularResponsavel() {
		return celularResponsavel;
	}

	public void setCelularResponsavel(String celularResponsavel) {
		this.celularResponsavel = celularResponsavel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
