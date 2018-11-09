package com.amazing.github;

public class DeepFloat {
    /**
     * 在Integer.toBinaryString(Float.floatToRawIntBits(f))的基础，格式化输出结果
     * 1.补全前缀0 2.数符与阶码之间用逗号分隔，阶码与尾数用分号分割
     * @param f float
     */
    private static String toBinaryStringFormat(float f){
        String s = Integer.toBinaryString(Float.floatToRawIntBits(f));
        StringBuilder sb = new StringBuilder();
        int prefix_size = 32 - s.length();
        for(int i = 0; i < prefix_size; i ++){
            sb.append('0');
        }
        sb.append(s);
        sb = sb.insert(1, ',').insert(10, ';');
        return sb.toString();
    }

    public static void main(String[] args){
        float f1 = 123.4567890123456789f;
        float f2 = 999.4567890123456789f;
        double d1 = 123.4567890123456789;
        double d2 = 999.4567890123456789;

        System.out.println(f1);
        //out: 123.45679 7位有效数字
        System.out.println(f2);
        //out: 999.4568 6位有效数字
        System.out.println(d1);
        //out: 123.45678901234568 16位有效数字
        System.out.println(d2);
        //out: 999.4567890123457 15位有效数字

        System.out.println(toBinaryStringFormat(-289.53125f));
        //out: 1,10000111;00100001100010000000000

        float f3 = 100000.001f;
        float f4 = 100000.0f;
        float f34 = Float.intBitsToFloat(Float.floatToRawIntBits(f4));
        float f5 = 999992323.0f;
        float f6 = 999992300.0f;
        float f56 = Float.intBitsToFloat(Float.floatToRawIntBits(f6));
        float f7 = 0.001f;
        float f8 = 100000.0f;
        System.out.println(f3==f4);
        System.out.println(f34);
        //true  100000.0f f3右规丢失精度, f4可以精确表示, 內存值为100000.0f
        System.out.println(f5==f6);
        System.out.println(f56);
        //true  999992320.0f f5,f6右规均丢失精度，內存值为999992320.0f
        System.out.println(f7 + f8);
        //100000.0f 浮点数相加过程中，需要对阶，阶数小的向阶数大的对齐， f7在对阶过程中精度丢失，变成了0

        System.out.println(toBinaryStringFormat(Float.POSITIVE_INFINITY));
        //0,11111111;00000000000000000000000 正无穷大, 1.0f/0.0
        System.out.println(toBinaryStringFormat(Float.NEGATIVE_INFINITY));
        //1,11111111;00000000000000000000000 负无穷大，-1.0f/0.0
        System.out.println(toBinaryStringFormat(Float.NaN));
        System.out.println(Float.NaN != Float.NaN);
        //0,11111111;10000000000000000000000 true 非数字，和自身不相等,[其实阶码为128，除了正无穷与负无穷，都是NaN]
        System.out.println(toBinaryStringFormat(Float.MIN_NORMAL));
        //0,00000001;00000000000000000000000 规格化得最小正数，即2^-126
        System.out.println(toBinaryStringFormat(Float.MAX_VALUE));
        //0,11111110;11111111111111111111111 最大正数（也是规格化的） 即 (2 - 2^-23）* 2^127
        System.out.println(toBinaryStringFormat(Float.MIN_VALUE));
        //0,00000000;00000000000000000000001 非规格化最小正数， 即2^-149
        System.out.println(toBinaryStringFormat(-0.0f));
        //1,00000000;00000000000000000000000 负0
        System.out.println(toBinaryStringFormat(0.0f));
        //0,00000000;00000000000000000000000 正0
        System.out.println(-0.0f == 0.0f);
        //true 数值比较
        System.out.println(new Float(-0.0f).equals(new Float(0.0f)));
        //false 参考hashcode 和 equals的实现，比较的是bits是否相等
        System.out.println(Math.max(-0.0f, 0.0f));
        System.out.println(Math.max(Float.POSITIVE_INFINITY,Float.NaN));
        System.out.println(Math.min(Float.NEGATIVE_INFINITY,Float.NaN));
        //0.0 NaN NaN IEEE754规定，正0大于负0, NaN与任何数字比较大小都是NaN

        System.out.println(Float.intBitsToFloat(0x7f800001));
        System.out.println(Float.intBitsToFloat(0x7f800002));
        System.out.println(new Float(Float.intBitsToFloat(0x7f800001)).equals(Float.intBitsToFloat(0x7f800002)));
        //NaN NaN true 0,11111111;00000000000000000000001 阶码为128，尾数不为0，都是NaN,
        //虽然NaN != NaN， 但是equals, 具体参考equals的实现
        System.out.println(Float.intBitsToFloat(0x000000010));
        //2.24E-44 0,00000000;00000000000000000100000, 阶码为-127时，尾数可以不用规格化，如果规格化，值肯定就是0了
        float f9 = 0.00000000000000000000000000000000000000000001f;
        System.out.println(toBinaryStringFormat(f9));
        //0,00000000;00000000000000000000111, 阶码为-127时，不规格化，可以保证数据精度。

        System.out.println(Float.compare(Float.NaN, Float.NaN));
        //0 内部比较的floatToRawIntBits对应的int值
        System.out.println(Float.compare(-0.0f, 0.0f));
        //-1 内部比较的floatToRawIntBits对应的int值
    }
}
