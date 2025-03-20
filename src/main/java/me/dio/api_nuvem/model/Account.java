package me.dio.api_nuvem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // valor unico e não nulo
    @Column(unique = true, nullable = false)
    private String number;
    private String agency;

    //scale é o total de números, e precision é o de decimal, ou seja, dos 13 números, 11 inteiros e 2 decimal
    @Column(precision = 13,scale = 2)
    private BigDecimal balance;
    // name, coluna que irá ficar no banco
    @Column(name = "additional_limit", precision = 13,scale = 2)
    private BigDecimal limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
