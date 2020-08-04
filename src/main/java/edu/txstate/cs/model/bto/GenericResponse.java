package edu.txstate.cs.model.bto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private String message;
    private String error;
 
    public GenericResponse(String message) {
        this.message = message;
    }

}
