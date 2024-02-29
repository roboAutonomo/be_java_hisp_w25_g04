package com.breakingbytes.be_java_hisp_w25_g04.dto.request;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPostDTO {
    @JsonProperty("user_id")
    @NotNull(message = "El  id no puede estar vacío.")
    @Positive(message = "El id debe ser mayor a cero.")
    Integer userId;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "La fecha no puede estar vacía.")
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate date;
    @Valid
    ProductDTO product; // TODO: Agregar validaciones
    @NotNull(message = "El campo no puede estar vacío.")
    Integer category;
    @NotNull(message = "El campo no puede estar vacío.")
    @Max(value = 10000000,message = "El precio máximo por producto es de 10.000.000")
    Double price;
}
