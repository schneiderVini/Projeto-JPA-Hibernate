package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {

    private String nomeProduto;
    private Long quantidadeVenda;
    private LocalDate dataUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVenda, LocalDate dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVenda = quantidadeVenda;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Long getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(Long quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public LocalDate getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    public void setDataUltimaVenda(LocalDate dataUltimaVenda) {
        this.dataUltimaVenda = dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVenda=" + quantidadeVenda +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }
}
