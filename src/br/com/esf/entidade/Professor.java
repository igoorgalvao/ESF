package br.com.esf.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.arquitetura.entidade.Entidade;

@Entity
@Table(name = "professor", catalog = "mydb")
public class Professor extends Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROFESSOR", unique = true)
	private Long id;

	@Column(name = "PROFESSOR", nullable = false, length = 40)
	private String professor;

	public Professor() {
	}

	public Professor(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

}
