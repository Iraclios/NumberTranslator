package com.example.demo.controller;

import com.example.demo.service.IAppService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private final IAppService appService;

    public AppController(IAppService appService) {
        this.appService = appService;
    }

    /**
     * Returns the main page.
     * @return index.html
     */
    @GetMapping(value = "/")
    public String getIndexPage()
    {
        logger.trace("Returning index.html");
        return "index";
    }

    /**
     * Gets parameters from HTML form, translates number into other positional
     * number systems, creates pdf file containing the results and returns
     * the url of this pdf file located in minio.
     * @param fullname is used to identify users
     * @param number user's desired number
     * @param chosenOperation user's desired operation
     * @return url of this pdf file
     */
    @PostMapping("/api")
    @ResponseBody
    public String translateNumber(@RequestParam(name = "fullname") String fullname,
                                     @RequestParam(name = "number") String number,
                                     @RequestParam(name = "chosenOperation") String chosenOperation) {
        logger.trace("Returning result");
        return appService.translateNumberAndSaveAsPdfInMinio(fullname, number, chosenOperation);
    }
}
