package com.amaris.ai.cloud.search;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGeneratorTest {

  /**
   *  scanType_ = "";
    timeStamp_ = "";
    spectStart_ = "";
    spectEnd_ = "";
    numPoints_ = "";
    resolution_ = "";
    numAverages_ = "";
    measTime_ = "";
    
    string Wavelength = 1;
       string Intensity = 2;
       string Absorbance = 3;
       string Reflectance = 4;
       string nameTs = 5;
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    final Map<String, Object> map = new java.util.HashMap<>();
    map.put("ScanType", "pills");
    map.put("TimeStamp", System.currentTimeMillis());
    map.put("SpectStart", "spectStart");
    map.put("SpectEnd", "spectEnd");
    map.put("NumPoints", "10");
    
    map.put("Resolution", "resolved");
    map.put("NumAverages", "10");
    map.put("MeasTime", System.currentTimeMillis());
    

    final Map<String, Object> map2 = new java.util.HashMap<>();
    map2.put("Wavelength", "12");
    map2.put("Intensity", "13");
    map2.put("Absorbance", "15");
    map2.put("Reflectance", "12");
    map2.put("nameTs", System.currentTimeMillis());
    System.out.println(new ObjectMapper().writeValueAsString(map));
    System.out.println(new ObjectMapper().writeValueAsString(map2));
  }

}
