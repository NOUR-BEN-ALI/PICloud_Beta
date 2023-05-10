package arctic.example.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.modelmapper.ModelMapper;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import com.stripe.param.TokenCreateParams;

import static org.springframework.http.RequestEntity.post;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@EnableScheduling

public class  PiApplication {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
        Stripe.apiKey = "sk_test_51MyFeLDEQW6yrUeCMVp7tRHTRLmXT8qlTbcqjhWsdIvd7QRlKxYPkP9vHW4v3unhXYEaipeZV8tCkUXgFuEyw0tJ00jbuEWC1U";
        TokenCreateParams params = TokenCreateParams.builder()
                .setCard(TokenCreateParams.Card.builder()
                        .setNumber("4242424242424242")
                        .setExpMonth(String.valueOf(12))
                        .setExpYear(String.valueOf(2024)) // This is a valid expiration year
                        .setCvc("123")
                        .build())
                .build();

/*
        KonnectPaymentRequest paymentRequest;


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentRequest);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);



        Request request = new Request.Builder()
                .url("https://api.konnect.network/api/v2")
                .header("x-api-key", "6443e5dd40ea7a478cfd5537:8om1uuHuPtCt44pt2VZUsXGQ36CYI0zz")
                .post(requestBody)
                .build();
*/



        try {
            Token token = Token.create(params);
            System.out.println("Token ID: " + token.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
