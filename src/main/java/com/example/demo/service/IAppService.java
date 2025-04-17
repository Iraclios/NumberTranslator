package com.example.demo.service;

public interface IAppService {
    /**
     * Provides realisation to AppConroller.translateNumber().
     * Gets parameters from HTML form, translates number into other positional
     * number systems, creates pdf file containing the results and returns
     * the url of this pdf file located in minio.
     * @param fullname is used to identify users
     * @param number user's desired number
     * @param chosenOperation user's desired operation
     * @return url of this pdf file
     */
    String translateNumberAndSaveAsPdfInMinio (String fullname, String number, String chosenOperation);
}
