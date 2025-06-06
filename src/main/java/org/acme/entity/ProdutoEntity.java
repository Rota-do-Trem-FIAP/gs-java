package org.acme.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_SF_PRODUTO")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(name = "nome_produto")
    private String nome_produto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "quantidade_descricao")
    private String quantidade_descricao;

    @Column(name = "validades_dias")
    private Integer validades_dias;

    @Column(name = "data_anuncio")
    @Temporal(TemporalType.DATE)
    private Date data_anuncio = new Date();

    private Double valor_estimado;
    private String status;

    public Integer getId_produto() {
        return id_produto;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public String getNomeProduto() {
        return nome_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getQuantidadeDescricao() {
        return quantidade_descricao;
    }

    public Integer getValidadesDias() {
        return validades_dias;
    }

    public Date getDataAnuncio() {
        return data_anuncio;
    }

    public Double getValorEstimado() {
        return valor_estimado;
    }

    public String getStatus() {
        return status;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setNomeProduto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setQuantidadeDescricao(String quantidade_descricao) {
        this.quantidade_descricao = quantidade_descricao;
    }

    public void setValidadesDias(Integer validades_dias) {
        this.validades_dias = validades_dias;
    }

    public void setDataAnuncio(Date data_anuncio) {
        this.data_anuncio = data_anuncio;
    }

    public void setValorEstimado(Double valor_estimado) {
        this.valor_estimado = valor_estimado;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
