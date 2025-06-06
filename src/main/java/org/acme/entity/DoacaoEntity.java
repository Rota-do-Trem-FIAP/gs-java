package org.acme.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_SF_DOACAO")
public class DoacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doacao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "usuario_doador_id")
    private UsuarioEntity doador;

    @ManyToOne
    @JoinColumn(name = "usuario_receptor_id")
    private UsuarioEntity receptor;

    private Double valor_estimado;

    @Temporal(TemporalType.DATE)
    private Date data_doacao;

    private String status;

    public Integer getIdDoacao() {
        return id_doacao;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public UsuarioEntity getUsuarioDoador() {
        return doador;
    }

    public UsuarioEntity getUsuarioReceptor() {
        return receptor;
    }

    public Double getValorEstimado() {
        return valor_estimado;
    }

    public Date getDataDoacao() {
        return data_doacao;
    }

    public String getStatus() {
        return status;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public void setUsuarioDoador(UsuarioEntity doador) {
        this.doador = doador;
    }

    public void setUsuarioReceptor(UsuarioEntity receptor) {
        this.receptor = receptor;
    }

    public void setValorEstimado(Double valor_estimado) {
        this.valor_estimado = valor_estimado;
    }

    public void setDataDoacao(Date data_doacao) {
        this.data_doacao = data_doacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
