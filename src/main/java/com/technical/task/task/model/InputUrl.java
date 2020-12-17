package com.technical.task.task.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.net.URL;

@Data
@Builder
public class InputUrl {

    @NotNull
    private URL url;

}