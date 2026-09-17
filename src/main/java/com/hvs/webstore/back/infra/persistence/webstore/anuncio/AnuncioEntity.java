package com.hvs.webstore.back.infra.persistence.webstore.anuncio;

import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "anuncio")
public class AnuncioEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String titulo;
    private String descricao;
    private String imageUrl;
    private String linkUrl;
    private String statusDesc;
    private String moeda;
    private String walletAddress;
    private Double valorPago;
    private String txHash;
    private Integer posicao;
    private Integer largura;
    private Integer altura;

    public AnuncioEntity() {
    }

    public AnuncioEntity(final Long id, final String uuid, final String titulo,
                         final String descricao, final String imageUrl, final String linkUrl,
                         final String statusDesc, final String moeda, final String walletAddress,
                         final Double valorPago, final String txHash, final Integer posicao,
                         final Integer largura, final Integer altura) {
        this.id = id;
        this.uuid = uuid;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.statusDesc = statusDesc;
        this.moeda = moeda;
        this.walletAddress = walletAddress;
        this.valorPago = valorPago;
        this.txHash = txHash;
        this.posicao = posicao;
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public String getUuid() { return uuid; }
    public void setUuid(final String uuid) { this.uuid = uuid; }
    public String getTitulo() { return titulo; }
    public void setTitulo(final String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(final String descricao) { this.descricao = descricao; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(final String imageUrl) { this.imageUrl = imageUrl; }
    public String getLinkUrl() { return linkUrl; }
    public void setLinkUrl(final String linkUrl) { this.linkUrl = linkUrl; }
    public String getStatusDesc() { return statusDesc; }
    public void setStatusDesc(final String statusDesc) { this.statusDesc = statusDesc; }
    public String getMoeda() { return moeda; }
    public void setMoeda(final String moeda) { this.moeda = moeda; }
    public String getWalletAddress() { return walletAddress; }
    public void setWalletAddress(final String walletAddress) { this.walletAddress = walletAddress; }
    public Double getValorPago() { return valorPago; }
    public void setValorPago(final Double valorPago) { this.valorPago = valorPago; }
    public String getTxHash() { return txHash; }
    public void setTxHash(final String txHash) { this.txHash = txHash; }
    public Integer getPosicao() { return posicao; }
    public void setPosicao(final Integer posicao) { this.posicao = posicao; }
    public Integer getLargura() { return largura; }
    public void setLargura(final Integer largura) { this.largura = largura; }
    public Integer getAltura() { return altura; }
    public void setAltura(final Integer altura) { this.altura = altura; }
}
