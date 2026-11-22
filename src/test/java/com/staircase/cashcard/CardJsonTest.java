package com.staircase.cashcard;

import com.staircase.cashcard.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class CardJsonTest {


    @Autowired
    private JacksonTester<Card> json;

    final long ID=2L;
    final double AMOUNT=2323.23;

    @Test
    void serializationTest() throws IOException {
        Card cashCard=new Card(ID,AMOUNT);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("id").isEqualTo(2);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("amount").isEqualTo(AMOUNT);
    }

    @Test
    void deSerializationTest() throws IOException {
        String expected = """
                {
                  "id": 2,
                  "amount": 2323.23
                }
           """;
       assertThat(json.parse(expected)).isEqualTo(new Card(ID,AMOUNT));
       assertThat(json.parseObject(expected).id()).isEqualTo(ID);
       assertThat(json.parseObject(expected).amount()).isEqualTo(AMOUNT);
    }
}
