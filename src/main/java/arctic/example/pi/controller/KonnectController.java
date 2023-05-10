package arctic.example.pi.controller;

import arctic.example.pi.entity.KonnectPaymentRequest;
import arctic.example.pi.entity.KonnectPaymentResponse;
import arctic.example.pi.entity.KonnectPaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")


public class KonnectController {

    private static final String KONNECT_API_KEY = "6443e5dd40ea7a478cfd5537:4GXgSflRjrUS2nZJYK8z31rZx8c87l";
    private static final String KONNECT_API_URL = "https://api.konnect.network/api/v2/payments/init-payment";


    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public KonnectController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @PostMapping("/konnect-payment")
    public ResponseEntity<KonnectPaymentResponse> initiateKonnectPayment(@RequestBody KonnectPaymentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(KONNECT_API_KEY);

        HttpEntity<KonnectPaymentRequest> requestBody = new HttpEntity<>(request, headers);

        ResponseEntity<KonnectPaymentResponse> response = restTemplate.postForEntity(KONNECT_API_URL, requestBody, KonnectPaymentResponse.class);

        return response;
    }



    //  Authorization : [{"key":"Authorization","value":"Bearer 6443e5dd40ea7a478cfd5537:4GXgSflRjrUS2nZJYK8z31rZx8c87l","description":"","type":"default","enabled":true}]
    @GetMapping("/konnect-payment/{paymentId}")
    public ResponseEntity<KonnectPaymentStatus> getKonnectPaymentStatus(@PathVariable String paymentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(KONNECT_API_KEY);

        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<KonnectPaymentStatus> response = restTemplate.exchange(
                "https://api.konnect.network/api/v2/payments/" + paymentId,
                HttpMethod.GET,
                request,
                KonnectPaymentStatus.class
        );

        return response;
    }



}
