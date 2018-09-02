package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

	@Entity
	@Table(name = "SinonimoMetodologiaTesting")
	public class SinonimoMetodologiaTesting {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="IdSinonimo")
		private Long id;
		
		@NotNull
		@Size(min = 1, max = 255)
		@Column(name = "Nombre")
		private String nombre;

		@ManyToOne
		@JoinColumn(name = "IdMetodologiaTesting")
		private MetodologiaTesting metodologiaTesting;
		
		public SinonimoMetodologiaTesting() {
		}

		public SinonimoMetodologiaTesting(Long id, @NotNull @Size(min = 1, max = 255) String nombre) {
			this.nombre = nombre;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		

	}