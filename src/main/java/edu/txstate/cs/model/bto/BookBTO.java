package edu.txstate.cs.model.bto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookBTO {

    private String title;
    private String location;

    public BookBTO(String title, String location)
    {
        this.title = title;
        this.location = location;
    }
}

