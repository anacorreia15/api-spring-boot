package med.voll.api.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {

    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    private String cep;

    public Adress(AdressData endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }

    public void updateAdress(AdressData data) {
        if (logradouro != null) {
            this.logradouro = data.logradouro();
        }
        if (bairro != null) {
            this.bairro = data.bairro();
        }
        if (numero != null) {
            this.numero = data.numero();
        }
        if (complemento != null) {
            this.complemento = data.complemento();
        }
        if (cidade != null) {
            this.cidade = data.cidade();
        }
        if (uf != null) {
            this.uf = data.uf();
        }
        if (cep != null) {
            this.cep = data.cep();
        }
    }
}
