package com.test.util;
import java.io.BufferedReader;
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

    public static void main(String[] args) {
        String commandStr = "keytool -genkey -keyalg RSA -keysize 2048 -validity 36500 -alias SEC_TEST -keypass 123456 -keystore d:/cer/test.keystore -storepass 123456 -dname \"CN=localhost,OU=DEP,O=CN,L=BJ,ST=BJ,C=CN\"";
        //String commandStr = "ipconfig";
        Command.exeCmd(commandStr);
    }
}