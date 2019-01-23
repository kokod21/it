package com.koko.it;



import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;

public class HelloWorld {

    static String code = "aa0f0e020112a8825800000002000000640bcc";
    public static void main(String[] args){

//        renameFile();
        long time = System.currentTimeMillis()/1000;
        String phone = "13560335577";
//        String phone = "13415832022";
        String signTemp = "appid=CgdbJKc0g2DRphWY&mobile="+phone+"&timestamp="+time;
        String signTemp2 = MD5(signTemp+"&key=ghxhBdMy4WCXjZpbtqZnBmGGCIKoQYfa").toUpperCase();
        String url = "https://mjz.xiaoxianai.cn/auth?"+signTemp+"&sign="+signTemp2;
//        String url = "https://mls.xiaoxianai.cn/papers?"+signTemp+"&sign="+signTemp2;
        System.out.println(url);
    }

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void renameFile(){
        String path = "E:\\cdy\\aaa";
        String newPath = "E:\\cdy\\aaa\\aaa";
        File file = new File(path);
        String [] ss = file.list();
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            int pos = s.lastIndexOf(".");
            if(pos > 0){
                String name = s.substring(0,pos);
                String lastName = s.substring(pos);
                String[] nn = name.split("-");
                String newName = nn[1].trim()+" - "+nn[0].trim()+lastName;
                System.out.println(i+"----"+newName);
                File oldFile = new File(path+File.separator+s);
                File newFile = new File(newPath+File.separator+newName);
                oldFile.renameTo(newFile);
            }
        }
    }


    private static void codeTest() {
        //aa--0e01--0112a881--5800000001--00000000--6c--0f--cc
        //aa 开始码 start=0
        //0e01 指令（用户信息，2位）start=2
        //0112a881 流水号（4位）start=6
        //5800000001 卡号 （5位） start=14
        //00000000 自由余额（4位） start = 24
        //6c 校验码 start = length-6 == length-4
        //0f 长度码（指令字节+数据字节）
        //cc 结束码

//        String code   = "aa0e010112a8815800000001000000006c0fcc";

//        16进制的负数进行10进制转换，用bigInteger

//        System.out.println(Integer.parseInt("FFFFC310", 16));
        BigInteger bi = new BigInteger("6400", 16);
        System.out.println(bi.intValue());

        String code   = "aa0e010112a8855800000005000027105b0fcc";

        String codeNo = code.substring(6,6+8);
        String codeCardNo = code.substring(14, 14+10);
        String codeData = code.substring(2,code.length()-6);
        String codeMoney = code.substring(code.length()-14, code.length()-6);
        int money = Integer.parseInt(codeMoney, 16);
        String verifyCode = code.substring(code.length()-6, code.length()-4);
        String lengthCode = code.substring(code.length()-4, code.length()-2);
        System.out.println("codeNo="+codeNo+"--codeCardNo="+codeCardNo+"--codeData="+codeData
                +"--money="+money+"--verifyCode="+verifyCode+"--lengthCode="+lengthCode);
        String s = getVerifyCodeByData(codeData);
        System.out.println("s.equals(verifyCode)="+(s.equals(verifyCode)));


        String costMoney = Integer.toHexString(20);
        while(costMoney.length() < 8){
            costMoney = "0"+costMoney;
        }
        System.out.println("spandMoney="+costMoney);

        String sendCodeData = "0e02"+codeNo+codeCardNo+costMoney;
        String sendVerifyCode = getVerifyCodeByData(sendCodeData);
        System.out.println("sendVirifyCode="+sendVerifyCode);

        String sendLength = Integer.toHexString(sendCodeData.length()/2);
        if (sendLength != null && sendLength.length() == 1)
            sendLength = "0"+sendLength;
        String sendCode = "aa"+sendCodeData+sendVerifyCode+sendLength+"cc";
        System.out.println(sendCode);
    }


    private static String getVerifyCodeByData(String code){
        int length = code.length();
        int rang = 2;
        int count = length/rang;
        int index = Integer.parseInt(code.substring(0,2), 16);
//        System.out.println("0p_index="+index+"--binary="+conver2HexStr(hexStringToByte(code.substring(0,2))));
        for (int i = 1; i < count; i++) {
            int start = i*rang;
            int end = i*rang+2;
            if(end > length){
                end = length;
            }
            String s2 = code.substring(start, end);
            int p = Integer.parseInt(s2, 16);
            index = index^p;
//            System.out.println(i+"p_index="+index+"---p="+p+"--binary="+binary);
        }
        System.out.println("十六进制："+Integer.toHexString(index));
        return Integer.toHexString(index);
    }

//    /**
//     * byte数组转换为二进制字符串,每个字节以","隔开
//     * **/
//    public static String binaryToHexStr(byte [] b) {
//        StringBuffer result = new StringBuffer();
//        for (int i = 0; i < b.length; i++) {
//            result.append(Long.toString(b[i] & 0xff, 2)+"," );
//        }
//        return result.toString().substring(0, result.length() - 1);
//    }

    /**
     * 十六进制转byte
     * @param hex
     * @return
     */
    private static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }

}
