package com.epam.mjc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        //throw new UnsupportedOperationException("You should implement this method.");
        MethodSignature signature = new MethodSignature(signatureString);

        List <String> wordsList = Arrays.stream(signatureString.split("[, ()]"))
                                    .filter(x -> !x.equals("")).toList();
        int index = 0;

        if(wordsList.get(index).equals("private") || wordsList.get(index).equals("protected") || wordsList.get(index).equals("public")){
            signature.setAccessModifier(wordsList.get(index++));
        }

        signature.setReturnType(wordsList.get(index++));

        signature.setMethodName(wordsList.get(index++));

        List<MethodSignature.Argument> arguments = new LinkedList<>();
        for(;index< wordsList.size();index+=2){
            arguments.add(new MethodSignature.Argument(wordsList.get(index),wordsList.get(index+1)));
        }

        MethodSignature ansSignature = new MethodSignature(signature.getMethodName(),arguments);
        ansSignature.setReturnType(signature.getReturnType());
        ansSignature.setAccessModifier(signature.getAccessModifier());
        return ansSignature;
    }
}
