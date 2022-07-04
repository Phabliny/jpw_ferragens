package com.jpw_ferragens.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @NotNull(message = "{nome.notNull}")
    @NotBlank(message = "{nome.notBlank}")
    @NotEmpty(message = "{nome.notEmpty}")
    @Size(min = 2, max = 255, message = "{nome.invalideSize}")
    private String nome;

    @NotNull(message = "{telefone.notNull}")
    @NotBlank(message = "{telefone.notBlank}")
    @NotEmpty(message = "{telefone.notEmpty")
    @Size(min = 10, max = 12, message = "{telefone.invalideSize}")
    //@Pattern(regexp = "\\d+", message = "Você deve informar apenas números!")
    private String telefone;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
}
