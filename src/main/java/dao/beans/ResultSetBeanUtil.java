package dao.beans;

import com.mchange.v1.db.sql.ResultSetUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 把ResultSet的一行数据，根据字段名，和实体类的字段名进行映射，然后进行填充值
 */
public class ResultSetBeanUtil {

    private static final HashMap<String, Method> resultGetXXXMethodMappings;
    static {
        resultGetXXXMethodMappings = new HashMap<>();
        // 创建临时的method映射
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = ResultSet.class.getMethods();
        for (Method method:
                methods
             ) {
            String methodName = method.getName().toUpperCase();
            System.out.println(methodName);
            // 过滤掉所有不带getXXX的方法
            if (!methodName.contains("GET")){
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1){
                continue;
            }
            if (parameterTypes[0].equals(int.class)){
                methodMap.put(methodName + "(int)".toUpperCase(), method);
            }else{
                methodMap.put(methodName + "(String)".toUpperCase(), method);
            }

        }
        Properties properties = new Properties();
        InputStream mapping = ResultSetUtils.class.getResourceAsStream("/dao/ResultSetGetXXXMethodMapping");

        try {
            properties.load(mapping);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String keyType : properties.stringPropertyNames()) {
            String method = properties.getProperty(keyType);
            Method methodInstance = methodMap.get(method.toUpperCase());
            resultGetXXXMethodMappings.put(keyType, methodInstance);
        }
    }


    // TODO: 2022/9/26  ReflectUtil add Map<String, Field> method（带过滤器版本）
    public static <T> T resultSetSolveWithColumnNameMatching(ResultSet resultSet, Class<T> beanType) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Field[] declaredFields = beanType.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field f :
                declaredFields) {
            fieldMap.put(f.getName().toUpperCase(), f);
        }

        // 获取所有列名
        ResultSetMetaData metaData = resultSet.getMetaData();
        String[] columnNames = new String[metaData.getColumnCount()];

        // 将数据库的带_的命名映射转为驼峰命名法
        for (int i = 0; i < columnNames.length; i++) {
            resultSet.getObject((i + 1));
            // 获取列名
            String columnLabel = metaData.getColumnLabel((i + 1));
            // 去除下划线
            if (columnLabel.contains("_")){
                columnLabel = columnLabel.replace("_", "");
            }
            columnNames[i] = columnLabel.toUpperCase();
        }

        // 创建返回对象
        T t = beanType.newInstance();

        // 设置Handler
        for (int i = 0; i < columnNames.length; i++) {
            Field field = fieldMap.get(columnNames[i]);
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object o = resultSetGetXXXMethodInvoke(resultSet, i + 1, null, fieldType);
            field.set(t, o);
        }
        return t;
    }


    @SuppressWarnings("unchecked")
    private static <T> T resultSetGetXXXMethodInvoke(ResultSet resultSet, Integer index, String columnName, Class<T> fieldTypeForBean) throws InvocationTargetException, IllegalAccessException {
        String simpleName = fieldTypeForBean.getSimpleName();
        String name = fieldTypeForBean.getName();
        T ret = null;
        if (index == null){
            Method method = resultGetXXXMethodMappings.get(simpleName + "2") != null?
                    resultGetXXXMethodMappings.get(simpleName + "2"):resultGetXXXMethodMappings.get(name + "2");
            ret = (T) method.invoke(resultSet, columnName);
        }else{
            Method method = resultGetXXXMethodMappings.get(name + "1") != null?
                    resultGetXXXMethodMappings.get(name + "1"):resultGetXXXMethodMappings.get(simpleName + "1");
            ret = (T) method.invoke(resultSet, index);
        }
        Objects.requireNonNull(ret, "ret不可能为null");
        return ret;
    }
}
