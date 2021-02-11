package com.idealista.infrastructure.persistence.models;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureVO {

    private Integer id;
    private String url;
    private String quality;

}
