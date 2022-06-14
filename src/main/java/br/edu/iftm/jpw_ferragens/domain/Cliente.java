package br.edu.iftm.jpw_ferragens.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{nome.notblank}")
    @Size(min = 2, max = 255, message = "{nome.invalidesize}")
    private String nome;

    @NotBlank(message = "{telefone.notblank}")
    @Size(min = 13, max = 14, message = "{telefone.invalidesize}")
    private String telefone;

    @CreationTimestamp
    private LocalDateTime localDateTime;
}
