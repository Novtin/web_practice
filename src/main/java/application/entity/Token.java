package application.entity;

import jakarta.persistence.*;

@Entity
public class Token {
    @Basic
    @Column(name = "token", nullable = false, length = -1)
    private String token;
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;
        if (clientId != null ? !clientId.equals(token1.clientId) : token1.clientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }
}
