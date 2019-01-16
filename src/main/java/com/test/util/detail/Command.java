package com.test.util.detail;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Command {
    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //生成keystore
        long thistime = DateTime.now().getMillis();
        String keystore = "keytool -genkey -keyalg RSA -keysize 2048 -validity 36500 -alias SEC_TEST -keypass 123456 -keystore d:/cer/test"+thistime+".keystore -storepass 123456 -dname \"CN=localhost,OU=DEP,O=CN,L=BJ,ST=BJ,C=CN\"";
        //String commandStr = "ipconfig";
        Command.exeCmd(keystore);
        Thread.sleep(3000);
        //导出公钥
        //new File("d:/cer/test_pub_cer.cer").deleteOnExit();
        Thread.sleep(1000);
        String cer = "keytool -export -alias SEC_TEST -file d:/cer/test_pub_cer.cer -keystore d:/cer/test"+thistime+".keystore -storepass 123456";
        Command.exeCmd(cer);
    }
}