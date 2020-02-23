package ir.bourse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ir.bourse.service.dto.OrderRequestDTO;
import ir.bourse.service.dto.RequestDTO;
import ir.bourse.service.dto.SendOrderDTO;
import ir.bourse.service.dto.UserAccountDTO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableAsync
public class BuyService {
    private final RestTemplate restTemplate;
    private final String CAPTCHA_URL = "https://online.agah.com/Auth/Captcha?123456";
    private final String LOGIN_URL = "https://online.agah.com/Auth/Login";
    private final String AGAH_URL = "https://online.agah.com/";
    private final String NONCE_URL = "https://online.agah.com/Order/GenerateNonce";
    private final String SEND_ORDER = "https://online.agah.com/Order/SendOrder";
    ObjectWriter obj = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private String cookie;

    public BuyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCaptcha() throws IOException, TesseractException {
        String captcha = "";
        byte[] imageByte = restTemplate.getForObject(CAPTCHA_URL, byte[].class);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/home/mohammad/Downloads/");
        captcha = tesseract.doOCR(ImageIO.read(bais)).trim();
        return captcha;
    }

    public Boolean login(UserAccountDTO user, String captcha) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("captcha", captcha);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, entity, String.class);

        String forObject = restTemplate.getForObject(AGAH_URL, String.class);

        return true;
    }

    @Async
    public void buy(OrderRequestDTO orderRequestDTO, int i) throws JsonProcessingException {

        //generate nonce
        HttpHeaders headers = new HttpHeaders();

        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.set("Accept-Language", "en-US,en;q=0.5");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Host", "online.agah.com");
        headers.set("Origin", "https://online.agah.com");
        headers.set("Referer", "https://online.agah.com/Auth/Login?ReturnUrl=%2f");
        headers.set("Upgrade-Insecure-Requests", "1");
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        String nonce = restTemplate.postForObject(NONCE_URL, entity, String.class);

        //
        HttpHeaders sendOrderHeader = new HttpHeaders();
        sendOrderHeader.set("Accept-Encoding", "gzip, deflate, br");
        sendOrderHeader.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        sendOrderHeader.set("Accept", "application/json, text/plain, */*");
        sendOrderHeader.set("Accept-Language", "en-ZA,en-GB;q=0.9,en-US;q=0.8,en;q=0.7,fa;q=0.6");
        sendOrderHeader.set("Connection", "keep-alive");
        sendOrderHeader.set("Content-Type", "application/json;charset=UTF-8");
        sendOrderHeader.set("Host", "online.agah.com");
        sendOrderHeader.set("Origin", "https://online.agah.com");
        sendOrderHeader.set("Referer", "https://online.agah.com/Auth/Login?ReturnUrl=%2f");
        sendOrderHeader.set("Upgrade-Insecure-Requests", "1");
        sendOrderHeader.set("Sec-Fetch-Mode", "cors");
        sendOrderHeader.set("Sec-Fetch-Site", "same-origin");
        sendOrderHeader.set("X-Requested-With", "XMLHttpRequest");
        RequestDTO request = new RequestDTO();
        SendOrderDTO dto = new SendOrderDTO();
        dto.setBankAccountId(orderRequestDTO.getBankAccountId());
        dto.setValue(orderRequestDTO.getValue());
        dto.setValidityType(orderRequestDTO.getValidityType());
        dto.setValidityDate(orderRequestDTO.getValidityDate());
        dto.setTradedQuantity(orderRequestDTO.getTradedQuantity());
        dto.setSignInstrumentName(orderRequestDTO.getSignInstrumentName());
        dto.setSignInstrumentIsin(orderRequestDTO.getSignInstrumentIsin());
        dto.setSignId(orderRequestDTO.getSignId());
        dto.setRemainingQuantity(orderRequestDTO.getRemainingQuantity());
        dto.setQuantity(orderRequestDTO.getQuantity());
        dto.setPrice(orderRequestDTO.getPrice());
        dto.setOrderSideId(orderRequestDTO.getOrderSideId());
        dto.setOrderExecuterId(orderRequestDTO.getOrderExecuterId());
        dto.setMinimumQuantity(orderRequestDTO.getMinimumQuantity());
        dto.setId(orderRequestDTO.getId());
        dto.setExpectedRemainingQuantity(orderRequestDTO.getExpectedRemainingQuantity());
        dto.setDisclosedQuantity(orderRequestDTO.getDisclosedQuantity());
        dto.setCustomerTitle(orderRequestDTO.getCustomerTitle());
        dto.setCustomerId(orderRequestDTO.getCustomerId());
        dto.setCategoryId(orderRequestDTO.getCategoryId());
        request.setOrderModel(dto);
        request.setNonce(nonce);
        String json = obj.writeValueAsString(request);
        HttpEntity<String> sendOrderEntity = new HttpEntity<>(json, sendOrderHeader);
        String sendOrderRes = restTemplate.postForObject(SEND_ORDER, sendOrderEntity, String.class);

        System.out.println(sendOrderRes + "---------  " + i);
    }
}
