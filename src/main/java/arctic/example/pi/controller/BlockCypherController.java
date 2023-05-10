package arctic.example.pi.controller;

import arctic.example.pi.entity.SendMoneyRequest;
import org.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController



public class BlockCypherController {

    @Value("${blockcypher.api.token}")
    private String blockCypherApiToken;

    private static final String BLOCKCYPHER_API_URL = "https://api.blockcypher.com/v1/btc/main/addrs/";

    private static final String BLOCKCYPHER_API_URL2 = "https://api.blockcypher.com/v1/btc/main/";




    // https://api.blockcypher.com/v1/btc/main/addrs/bc1qk4d3x04gzavr7sj53cgdu7524n0g4ujdje3x5g


    //  http://localhost:9091/address/bc1qk4d3x04gzavr7sj53cgdu7524n0g4ujdje3x5g?token=eba851b4afe84c74b08d3a5540681baf


//https://api.blockcypher.com/v1/tokens/eba851b4afe84c74b08d3a5540681baf

    @GetMapping("/address/{address}")
    public String getAddressInfo(@PathVariable String address, @RequestParam("token") String blockCypherApiToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BLOCKCYPHER_API_URL + address + "?token=" + blockCypherApiToken;
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }


    @PostMapping("/sendmoney")
    public String sendMoney(@RequestBody SendMoneyRequest request, @RequestParam("token") String blockCypherApiToken) {
        RestTemplate restTemplate = new RestTemplate();

        // Create the JSON request body
        JSONObject json = new JSONObject();
        JSONArray inputsArray = new JSONArray();
        JSONObject inputs = new JSONObject();
        inputs.put("addresses", new String[]{request.getFromAddress()});
        inputs.put("script_type", "pay-to-pubkey-hash");
        inputs.put("output_index", 0);
        inputs.put("output_value", request.getAmount());
        inputsArray.put(inputs);
        json.put("inputs", inputsArray);

        JSONArray outputsArray = new JSONArray();
        JSONObject outputs = new JSONObject();
        outputs.put("addresses", new String[]{request.getToAddress()});
        outputs.put("script_type", "pay-to-pubkey-hash");
        outputs.put("value", request.getAmount());
        outputsArray.put(outputs);
        json.put("outputs", outputsArray);

        // Send the API request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);
        String url = BLOCKCYPHER_API_URL + "txs/new?token=" + blockCypherApiToken;
        String response = restTemplate.postForObject(url, entity, String.class);

        return response;
    }




}
