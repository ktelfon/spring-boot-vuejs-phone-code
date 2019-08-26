package de.jonashackt.springbootvuejs.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PhoneService {

     private PhoneCountryCodeProvider phoneCountryCodeProvider;

     @Autowired
     PhoneService(PhoneCountryCodeProvider phoneCountryCodeProvider){
         this.phoneCountryCodeProvider = phoneCountryCodeProvider;
     }

     public Map<String, String> getAllCountryCodes(){
         return phoneCountryCodeProvider.getPhoneCodeCountryMap();
     }

     public String getCountryByCountryCode(String phone){

         String country = "";
         if(!StringUtils.isNumeric(phone) || StringUtils.isEmpty(phone)){
            return country;
         }

         String firstDigit = phone.charAt(0) + "";
         Map<String, String> phoneCodeCountryMap = phoneCountryCodeProvider.getPhoneCodeCountryMap();

         if("1".equals(firstDigit)){
             String countryCode = phone.substring(0, 4);
             if(phoneCodeCountryMap.containsKey(countryCode)){
                 country = phoneCodeCountryMap.get(countryCode);
             }else{
                 country = phoneCodeCountryMap.get(firstDigit);
             }
         }

         if("7".equals(firstDigit)){
             country = phoneCodeCountryMap.get(firstDigit);
         }

         if(phoneCodeCountryMap.containsKey(phone.substring(0,2))){
             country = phoneCodeCountryMap.get(phone.substring(0,2));
         }else if(phoneCodeCountryMap.containsKey(phone.substring(0,3))){
             country = phoneCodeCountryMap.get(phone.substring(0,3));
         }

         return country;
     }
}
