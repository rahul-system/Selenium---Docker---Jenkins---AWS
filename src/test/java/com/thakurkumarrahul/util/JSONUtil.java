package com.thakurkumarrahul.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thakurkumarrahul.test.vendorPortal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtil {

    private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type) throws Exception {
       try( InputStream stream = ResourceLoader.readResource(path)){
           return mapper.readValue(stream,type);
       } catch (IOException e) {
           log.error("Unable to read test data {}", path, e);
       }
        return null;
    }




}
