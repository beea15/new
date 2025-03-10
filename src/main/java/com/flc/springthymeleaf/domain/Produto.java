package com.flc.springthymeleaf.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 25)
    @NotBlank(message = "Campo não pode ser em branco")
    @NotNull(message = "Produto não pode ser nulo")
    private String nome;

    @NotNull(message = "Selecione subgrupo relativo ao produto")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "subgrupo_id")
    @JsonIgnore
    private Subgrupo subgrupo;

    @OneToMany(mappedBy = "produto", cascade = { CascadeType.ALL })
    @JsonBackReference
    private List<Propriedade> propriedades = new ArrayList<>();

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Subgrupo getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(Subgrupo subgrupo) {
        this.subgrupo = subgrupo;
    }

    public List<Propriedade> getPropriedades() {
        return Collections.unmodifiableList(propriedades);
    }

    public void setPropriedades(List<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    public void addPropriedade(Propriedade thePropriedade) {
        this.propriedades.add(thePropriedade);
        thePropriedade.setProduto(this);
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", subgrupo=" + subgrupo + ", propriedades=" + propriedades + "]";
    }
}
