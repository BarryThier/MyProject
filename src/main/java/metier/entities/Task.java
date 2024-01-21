package metier.entities;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titre;
    private String description;
    private String date;
    private String auteur;
    
	public Task(int id, String titre, String description, String date, String auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.date = date;
		this.auteur = auteur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", auteur="
				+ auteur + "]";
	}
}
