package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.model.StringResponse;
import de.jonashackt.springbootvuejs.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    private PhoneService phoneService;

    @Autowired
    BackendController(@Qualifier("phoneService") PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping(path = "/getCountry/{phone}")
    public ResponseEntity<StringResponse> getCountry(@PathVariable("phone") String phone) {

        String countryByCountryCode = phoneService.getCountryByCountryCode(phone);
        if (StringUtils.isEmpty(countryByCountryCode)) {
            return new ResponseEntity<StringResponse>(new StringResponse("No country found"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<StringResponse>(new StringResponse(countryByCountryCode), HttpStatus.OK);
    }

}
