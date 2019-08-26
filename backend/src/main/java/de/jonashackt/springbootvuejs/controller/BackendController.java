package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    BackendController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(path = "/getCountry/{phone}", method = RequestMethod.POST)
    public ResponseEntity<String> getCountry(@PathVariable("phone") String phone) {

        String countryByCountryCode = phoneService.getCountryByCountryCode(phone);
        if (StringUtils.isEmpty(countryByCountryCode)) {
            return new ResponseEntity<>("No country found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(countryByCountryCode, HttpStatus.OK);
    }

}
