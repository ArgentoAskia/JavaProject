package cn.argentoaskia.homework;

import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;

/**
 *  javadoc,代码习惯，hutool
 */
public class ReflectUtil {
    public static void main(String[] args) throws NoSuchMethodException {
//        boolean b = ReflectUtil.modifierJudge(ReflectUtil.class, isPublic);
//        System.out.println(b);
//        Class<String> strCl = String.class;
//        Method valueOf = strCl.getMethod("valueOf", int.class);
//        boolean isPublic = ReflectUtil.modifiersJudge(valueOf, ReflectUtil.isPublic);
//        System.out.println(isPublic);
        String name = getName(ReflectUtil[].class, CLASS_SIMPLE_NAME);
        System.out.println(name);
    }

    public static final String isAbstract = "isAbstract";
    public static final String isPublic = "isPublic";
    public static final String isPrivate = "isPrivate";
    public static final String isProtected = "isProtected";
    public static final String isStatic = "isStatic";
    public static final String isFinal = "isFinal";
    public static final String isSynchronized = "isSynchronized";
    public static final String isVolatile = "isVolatile";
    public static final String isTransient = "isTransient";
    public static final String isNative = "isNative";
    public static final String isInterface = "isInterface";
    public static final String isStrict = "isStrict";

    /**
     * 判断某个方法、构造器、字段是否具有某个类型的修饰符。
     * <p>
     * 使用方法：<br>
     * <pre>
     * Class&lt;String&gt; strCl = String.class;
     * Method toString = strCl.getMethod("toString");
     * boolean isPublic = ReflectUtil.modifierJudge(toString, ReflectUtil.isPublic);
     * </pre>
     *
     * @param member 可以是一个{@link Field}对象或者{@link Constructor}对象或者{@link Method}对象
     * @param modifierFunctionName 判断的修饰符名，参考ReflectUtil内所有带isXXX开头的常量，如：{@link #isAbstract}等
     * @return 如果返回true，则代表包含这个修饰符，否则则不包含。
     * @see #isAbstract
     * @see #isFinal
     * @see #isInterface
     * @see #isNative
     * @see #isPrivate
     * @see #isProtected
     * @see #isPublic
     * @see #isStatic
     * @see #isStrict
     * @see #isSynchronized
     * @see #isTransient
     * @see #isVolatile
     */
    public static boolean modifierJudge(Member member, String modifierFunctionName) {
        try{
            Method modifiersFunction = Modifier.class.getMethod(modifierFunctionName, int.class);
            int modifiers = member.getModifiers();
            Object invoke = modifiersFunction.invoke(null, modifiers);
            return (boolean) invoke;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifierFunctionName + "的属性");
        }
    }
    public static boolean modifierJudge(Parameter parameter, String modifierFunctionName) {
        try{
            Method modifiersFunction = Modifier.class.getMethod(modifierFunctionName, int.class);
            int modifiers = parameter.getModifiers();
            Object invoke = modifiersFunction.invoke(null, modifiers);
            return (boolean) invoke;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifierFunctionName + "的属性");
        }
    }
    public static boolean modifierJudge(Class<?> clazz, String modifierFunctionName) {
        try{
            Method modifiersFunction = Modifier.class.getMethod(modifierFunctionName, int.class);
            int modifiers = clazz.getModifiers();
            Object invoke = modifiersFunction.invoke(null, modifiers);
            return (boolean) invoke;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifierFunctionName + "的属性");
        }
    }

    /**
     * 判断某个方法、构造器、字段是否同时具备多个修饰符。
     * <p>
     * 使用方法：<br>
     * <pre>
     * Class&lt;String&gt; strCl = String.class;
     * Method toString = strCl.getMethod("valueOf");
     * boolean isPublicAndStatic = ReflectUtil.modifiersJudge(toString, ReflectUtil.isPublic, ReflectUtil.isStatic);
     * </pre>
     *
     * @param member 可以是一个{@link Field}对象或者{@link Constructor}对象或者{@link Method}对象
     * @param modifiersFunctionName 判断的修饰符名，参考ReflectUtil内所有带isXXX开头的常量，如：{@link #isAbstract}等，可以传递多个。
     * @return 只有当所有的修饰符都具有时，才会返回{@code true}，只要有一个不满足就返回{@code false}
     * @see #isAbstract
     * @see #isFinal
     * @see #isInterface
     * @see #isNative
     * @see #isPrivate
     * @see #isProtected
     * @see #isPublic
     * @see #isStatic
     * @see #isStrict
     * @see #isSynchronized
     * @see #isTransient
     * @see #isVolatile
     */
    public static boolean modifiersJudge(Member member, String ...modifiersFunctionName){
        try{
            for (String modifierFunctionName:
                    modifiersFunctionName) {
                if (!modifierJudge(member, modifierFunctionName)){
                    return false;
                }
            }
            return true;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifiersFunctionName + "的属性");
        }
    }
    public static boolean modifiersJudge(Parameter parameter, String ...modifiersFunctionName){
        try{
            for (String modifierFunctionName:
                    modifiersFunctionName) {
                if (!modifierJudge(parameter, modifierFunctionName)){
                    return false;
                }
            }
            return true;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifiersFunctionName + "的属性");
        }
    }
    public static boolean modifiersJudge(Class<?> clazz, String ...modifiersFunctionName){
        try{
            for (String modifierFunctionName:
                    modifiersFunctionName) {
                if (!modifierJudge(clazz, modifierFunctionName)){
                    return false;
                }
            }
            return true;
        }catch (Exception exception){
            throw new RuntimeException("无法判定方法：" + modifiersFunctionName + "的属性");
        }
    }

    public static Member[] modifiersFilter(Member[] members, String ...modifierFunctionName){
        List<Member> memberList = new LinkedList<>();
        for (Member member :
                members) {
            if (modifiersJudge(member, modifierFunctionName)){
                memberList.add(member);
            }
        }
        return memberList.toArray(new Member[0]);
    }
    public static Parameter[] modifiersFilter(Parameter[] parameters, String ...modifierFunctionName){
        List<Parameter> memberList = new LinkedList<>();
        for (Parameter parameter :
                parameters) {
            if (modifiersJudge(parameter, modifierFunctionName)){
                memberList.add(parameter);
            }
        }
        return memberList.toArray(new Parameter[0]);
    }
    public static Class<?>[] modifiersFilter(Class<?>[] classes, String ...modifierFunctionName){
        List<Class<?>> memberList = new LinkedList<>();
        for (Class<?> clazz :
                classes) {
            if (modifiersJudge(clazz, modifierFunctionName)){
                memberList.add(clazz);
            }
        }
        return memberList.toArray(new Class[0]);
    }

    // Member = 构造器 | 方法 | 字段
    public static String getModifiersAsString(Member member){
        if (member instanceof Method){
            Method method = (Method) member;
        }else if (member instanceof Field){

        }else if(member instanceof Constructor){
        }
        return null;
    }
    private static String getMethodModifiersAsString(Method method){
        // 拼接字符串
        StringBuilder modifierStrs = new StringBuilder();
        int modifiers = method.getModifiers();
        // check public | private | default | protected
        // modifiers & 00000111
        switch (modifiers & 0b111){
            case 1:{
                modifierStrs.append("public");
                break;
            }
            case 2:{
                modifierStrs.append("private");
                break;
            }
            case 4:{
                modifierStrs.append("protected");
                break;
            }
        }
        modifierStrs.append(" ");
        // check static
        // modifiers & 0x00000008
        switch (modifiers & 0x00000008){
            case 0x00000008:{
                modifierStrs.append("static");
            }
        }
        return modifierStrs.toString();

    }


    public static boolean equals(AnnotatedElement element1, AnnotatedElement element2){
        return element1 == element2 || element1.equals(element2);
    }


    public static final String PARAM_NAME = "name";
    public static final String PARAM_TYPE_NAME = "type-name";
    public static final String PARAM_SIGNATURE_TYPE_NAME = "signature-type-name";

    // 字段：字段名，类型 + 字段名，修饰符 + 类型，修饰符 + 类型 + 字段名
    // 方法：方法名，方法名 + 参数，修饰符 + 返回值 + 方法名，修饰符 + 返回值 + 方法名 + 参数
    // 构造器：构造器名，构造器 + 参数，修饰符 + 构造器，修饰符 + 方法名 + 参数
    public static final String MEMBER_FIELD_NAME = "field-name";
    public static final String MEMBER_FIELD_TYPE_NAME = "field-type-name";
    public static final String MEMBER_FIELD_SIGNATURE_TYPENAME_NAME = "field-signature-type-name";

    public static final String MEMBER_METHOD_NAME = "method-name";
    public static final String MEMBER_METHOD_NAME_PARAM = "method-name-param";
    public static final String MEMBER_METHOD_SIGNATURE_TYPE_NAME_PARAM = "method-signature-type-name-param";
    public static final String MEMBER_METHOD_SIGNATURE_TYPE_NAME = "method-signature-type-name";

    public static final String MEMBER_CONSTRUCTOR_NAME = "constructor-name";
    public static final String MEMBER_CONSTRUCTOR_NAME_PARAM = "constructor-name-param";
    public static final String MEMBER_CONSTRUCTOR_SIGNATURE_NAME = "constructor-signature-name";
    public static final String MEMBER_CONSTRUCTOR_SIGNATURE_NAME_PARAM = "constructor-signature-name-param";

    public static String getName(Parameter parameter, String formatOfName){
        switch (formatOfName){
            case PARAM_NAME:{
                return parameter.getName();
            }
            case PARAM_SIGNATURE_TYPE_NAME:{
                String typeName = parameter.getParameterizedType().getTypeName();
                return modifierJudge(parameter, isFinal)?
                        "final " + typeName + " " +parameter.getName():typeName + " " +parameter.getName();
            }
            case PARAM_TYPE_NAME:{
                String typeName = parameter.getParameterizedType().getTypeName();
                return typeName + " " + parameter.getName();
            }
            default:{
                throw new RuntimeException("formatOfName需要传递PARAM_开头的常量值！！");
            }
        }
    }
    public static String getName(Member member, String formatOfName){
        switch (formatOfName){
            case MEMBER_CONSTRUCTOR_NAME:{
                String constructorName = member.getName();
                return constructorName;
            }
            case MEMBER_CONSTRUCTOR_NAME_PARAM:{
                Constructor<?> constructor = (Constructor<?>) member;
                StringBuilder constructorNameParam = new StringBuilder(member.getName()).append("(");
                Parameter[] parameters = constructor.getParameters();
                for (Parameter param: parameters
                     ) {
                    String typeName = param.getParameterizedType().getTypeName();
                    String name = param.getName();
                    constructorNameParam.append(typeName).append(" ").append(name).append(",");
                }
                constructorNameParam.replace(constructorNameParam.length() - 1, constructorNameParam.length(), ")");
                return constructorNameParam.toString();
            }
            case MEMBER_CONSTRUCTOR_SIGNATURE_NAME:{
                Modifier.fieldModifiers();

            }
            case MEMBER_CONSTRUCTOR_SIGNATURE_NAME_PARAM:{

            }
            case MEMBER_FIELD_NAME:{

            }
            case MEMBER_FIELD_SIGNATURE_TYPENAME_NAME:{

            }
            case MEMBER_FIELD_TYPE_NAME:{

            }
            case MEMBER_METHOD_NAME:{

            }
            case MEMBER_METHOD_NAME_PARAM:{

            }
            case MEMBER_METHOD_SIGNATURE_TYPE_NAME:{

            }
            case MEMBER_METHOD_SIGNATURE_TYPE_NAME_PARAM:{

            }
            default:{

            }
        }
        return member.getName();
    }

    public static final String CLASS_NAME = "getName";
    public static final String CLASS_SIMPLE_NAME = "getSimpleName";
    public static final String CLASS_TYPE_NAME = "getTypeName";
    public static final String CLASS_CANONICAL_NAME = "getCanonicalName";
    public static String getName(Class<?> clazz, String typeOfName){
        try {
            Method getNameMethod = Class.class.getMethod(typeOfName);
            Object clazzName = getNameMethod.invoke(clazz);
            return (String) clazzName;
        } catch (Exception e) {
            throw new RuntimeException("获取类名称失败");
        }
    }
}
