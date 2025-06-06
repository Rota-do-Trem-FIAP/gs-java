package org.acme.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_SF_USUARIO")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(nullable = false)
    private String email;

    private String cnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String tipo_usuario;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro = new Date();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EnderecoEntity> enderecos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ProdutoEntity> produtos;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public void setDataCadastro(Date data_cadastro) {
        this.dataCadastro = data_cadastro;
    }
}
