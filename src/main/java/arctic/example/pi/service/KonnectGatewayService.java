package arctic.example.pi.service;

import arctic.example.pi.entity.KonnectPaymentRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class KonnectGatewayService {
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${konnect.api.url}")
    private String konnectApiUrl;

    @Value("${konnect.api.key}")
    private String konnectApiKey;

    public KonnectGatewayService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String initPayment(KonnectPaymentRequest paymentRequest) throws IOException {

        String url = konnectApiUrl + "/payments/init-payment";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(paymentRequest));

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + konnectApiKey)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseString = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseString);

            if (jsonNode.has("errors")) {
                throw new RuntimeException("Failed to initiate payment: " + jsonNode.get("errors"));
            }

            return jsonNode.get("paymentId").asText();
        }
    }


}
