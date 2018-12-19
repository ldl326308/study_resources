package com.nf.lc.proxy;

public class MathTest {

    public static void main(String[] args) {
        MathImp math = (MathImp)new JdkProxyMath().getProxyObject(new Math());

        int n1 = 100, n2 = 0;
        math.add(n1,n2);
        math.sub(n1,n2);
        math.mut(n1,n2);
//        math.div(n1,n2);
    }

}
