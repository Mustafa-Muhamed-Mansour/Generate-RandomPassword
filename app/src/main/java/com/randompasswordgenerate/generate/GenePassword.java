package com.randompasswordgenerate.generate;

import java.util.concurrent.ThreadLocalRandom;

public class GenePassword
{

    public static final int MIN_CODE = 0, MAX_CODE = 200;

    public static String process(int length)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            builder.append((char) ThreadLocalRandom.current().nextInt(MIN_CODE, MAX_CODE));
        }
        return builder.toString();
    }
}
