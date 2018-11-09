package com.amazing.github;

public class DeepInteger {
    /**
     * 在Integer.toBinaryString(i)的基础补全前缀0
     * @param i int
     */
    private static String toBinaryStringFormat(int i){
        String s = Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder();
        int prefix_size = 32 - s.length();
        for(int k = 0; k < prefix_size; k ++){
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args){
        int i1 = -1;
        int i2 = 1;
        System.out.println(toBinaryStringFormat(i1));
        //11111111111111111111111111111111 -1补码
        //(负数补码=原码[10000000000000000000000000000001]除符号位每位取反,末位加1）
        System.out.println(toBinaryStringFormat(i2));
        //00000000000000000000000000000001  1补码（正数补码=原码）

        int ia = 0x7FFFFFFF;
        int ib = 1;
        System.out.println(toBinaryStringFormat(ia + ib));
        //10000000000000000000000000000000 溢出，变成负值

        Character character = new Character('c');
        Integer auto_boxing = 10; // Autoboxing
        char un_boxing = character; // Unboxing
        System.out.println(auto_boxing + "----" + un_boxing);

        long t = System.currentTimeMillis();
        //Long sum = 0L; //使用不当造成性能问题
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("total:" + sum + ", processing time: " + (System.currentTimeMillis() - t) + " ms");

        Integer a1 = 15;
        Integer a2 = 15;
        Integer a3 = new Integer(15);
        Integer b1 = 129;
        Integer b2 = 129;
        Long b3 = 129L;
        int b4 = 129;
        Integer sum1 = 258;

        System.out.println(a1 == a2); // true
        System.out.println(a1 == a3); // false
        System.out.println(b1 == b2); // false
        //System.out.println(b3 == b1); // compile error
        System.out.println(b3 == b4); // true
        System.out.println(b3.equals(b1)); //false
        System.out.println(sum1 == b1 + b2); // true
        System.out.println(sum1 == b1 + b3); // true
        System.out.println(sum1.equals(b1 + b2)); // true
        System.out.println(sum1.equals(b1 + b3)); // false

        int s = 0xaa001020;
        int s1 = s >>> -5;
        int s2 = s >> -5;
        System.out.println(toBinaryStringFormat(s));
        //10101010000000000001000000100000
        System.out.println(toBinaryStringFormat(s1));
        //00000000000000000000000000010101 相当于逻辑左移27位（32-5）
        System.out.println(toBinaryStringFormat(s2));
        //11111111111111111111111111110101 相当于算术左移27位 (32-5)
    }
}
