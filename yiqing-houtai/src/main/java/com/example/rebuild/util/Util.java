package com.example.rebuild.util;

import com.example.rebuild.util.aip.AipClientConst;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Util {

    private static BitSet URI_UNRESERVED_CHARACTERS = new BitSet();
    private static String[] PERCENT_ENCODED_STRINGS = new String[256];

    static {
        for (int i = 'a'; i <= 'z'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        for (int i = '0'; i <= '9'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        URI_UNRESERVED_CHARACTERS.set('-');
        URI_UNRESERVED_CHARACTERS.set('.');
        URI_UNRESERVED_CHARACTERS.set('_');
        URI_UNRESERVED_CHARACTERS.set('~');

        for (int i = 0; i < PERCENT_ENCODED_STRINGS.length; ++i) {
            PERCENT_ENCODED_STRINGS[i] = String.format("%%%02X", i);
        }

    }

    public static String mkString(Iterator<String> iter, char seprator) {
        if (!iter.hasNext()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        while (iter.hasNext()) {
            String item = iter.next();
            builder.append(item);
            builder.append(seprator);
        }

        builder.deleteCharAt(builder.length() - 1);     // remove last sep
        return builder.toString();
    }

    public static String uriEncode(String value, boolean encodeSlash) {
        try {
            StringBuilder builder = new StringBuilder();
            for (byte b : value.getBytes(AipClientConst.DEFAULT_ENCODING)) {
                if (URI_UNRESERVED_CHARACTERS.get(b & 0xFF)) {
                    builder.append((char) b);
                } else {
                    builder.append(PERCENT_ENCODED_STRINGS[b & 0xFF]);
                }
            }
            String encodeString = builder.toString();
            if (!encodeSlash) {
                return encodeString.replace("%2F", "/");
            }
            return encodeString;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCanonicalTime() {
        SimpleDateFormat utcDayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat utcHourFormat = new SimpleDateFormat("hh:mm:ss");
        utcDayFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        utcHourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date now = new Date();
        return String.format("%sT%sZ", utcDayFormat.format(now), utcHourFormat.format(now));
    }

    /**
     *
     * @param filePath 文件路径
     * @return file bytes
     * @throws IOException 读取文件错误
     */

    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream(((int) file.length()));
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static void writeBytesToFileSystem(byte[] data, String output) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
            out.write(data);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static JSONObject getGeneralError(int errorCode, String errorMsg) {
        JSONObject json = new JSONObject();
        json.put("error_code", errorCode);
        json.put("error_msg", errorMsg);
        return json;
    }

    public static boolean isLiteral(String input) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z_]*");
        return pattern.matcher(input).matches();
    }

    public static boolean isNull(JSONObject json, String key){
        return json.get(key) == null;
    }

}
