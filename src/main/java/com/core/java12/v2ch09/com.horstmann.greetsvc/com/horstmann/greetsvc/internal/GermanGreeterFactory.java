package com.core.java12.v2ch09.com.horstmann.greetsvc.com.horstmann.greetsvc.internal;

import com.horstmann.greetsvc.*;
import java.util.Map;
import java.util.HashMap;

public class GermanGreeterFactory 
{
   public static GreeterService provider() 
   {
      Map<String, String> dictionary = new HashMap<>();
      dictionary.put("World", "Welt");
      dictionary.put("Modular World", "modulare Welt");
      return new GermanGreeter(dictionary);
   }
}
