package Java08.homework;

import java.awt.datatransfer.StringSelection;
import java.util.Comparator;
import java.util.StringJoiner;

public class SQLBuilder {
    private StringJoiner stringJoiner;
    private StringBuilder stringBuilder;

    private void createJoiner(String prefix, String suffix, String delimiter){
        stringJoiner = new StringJoiner(delimiter, prefix, suffix);
    }
    private void createBuilder(String init){
        stringBuilder = new StringBuilder(init);
    }

    public static SQLBuilder insert(){
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.createBuilder("insert ");
        return sqlBuilder;
    }
    public SQLBuilder into(String tableName){
        stringBuilder.append("into ");
        stringBuilder.append(tableName);
        return this;
    }
    public SQLBuilder column(String column){
        if (stringJoiner == null){
            createJoiner("(",")", ",");
        }
        stringJoiner.add("`" + column + "`");
        return this;
    }
    public SQLBuilder columns(String ...columns){
        createJoiner("(", ")", ",");
        for (String column :
                columns) {
            stringJoiner.add("`" + column + "`");
        }
        String s = stringJoiner.toString();
        stringBuilder.append(s);
        stringJoiner = null;
        return this;
    }
    private void keepEndWithValues(){
        if (!toString().contains("values") && !stringBuilder.toString().endsWith("values")){
            if (stringJoiner != null){
                String columns = stringJoiner.toString();
                stringBuilder.append(columns);
                stringJoiner = null;
            }
            stringBuilder.append(" \nvalues\n");
        }
    }
    /**
     * values
     * @param values
     * @return
     */
    public SQLBuilder values(Object... values) {
        keepEndWithValues();
        if (stringJoiner == null) {
            createJoiner("", "", ",\n");
        }
        StringJoiner valuesStringJoiner = new StringJoiner(",", "(", ")");
        for (int i = 0; i < values.length; i++) {
            String s = typeJudge(values[i]);
            valuesStringJoiner.add(s);
        }
        String valuesStr = valuesStringJoiner.toString();
        stringJoiner.add(valuesStr);
        return this;
    }
    public SQLBuilder build(){
        if (stringJoiner != null){
            String s = stringJoiner.toString();
            stringBuilder.append(s);
        }
        return this;
    }

    /**
     * 扩展方法，之后需要重新设计
     * @param obj
     * @return
     */
    private String typeJudge(Object obj){
        if(obj.getClass() == Integer.class){
            int intValue = (int) obj;
            return String.valueOf(intValue);
        }else if (obj.getClass() == String.class){
            return "'" + obj + "'";
        }else {
            return "null";
        }
    }


    public static SQLBuilder update(String tableName){
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.createBuilder("update `" + tableName + "`\n");
        return sqlBuilder;
    }

    @Deprecated
    public SQLBuilder set(Object[][] keyValues){
        createJoiner("set ", "", ", ");
        for (Object[] kv :
                keyValues) {
            String key = kv[0].toString();
            String value = typeJudge(kv[1]);
            String keyValue = key + " = " + value;
            stringJoiner.add(keyValue);
        }
        String s = stringJoiner.toString();
        stringBuilder.append(s);
        return this;
    }

    public SQLBuilder set(String column, Object value){
        if (stringJoiner == null){
            createJoiner("set ", "\n", ",\n    ");
        }
        String valueStr = typeJudge(value);
        String keyValue = "`" + column + "`" + " = " + valueStr;
        stringJoiner.add(keyValue);
        return this;
    }
    private void checkSet(){
        if(toString().contains("update") && !toString().contains("set")){
            String s = stringJoiner.toString();
            stringBuilder.append(s);
            stringJoiner = null;
        }
    }

    public SQLBuilder where(String expPrefix, String expSymbol, Object expSuffix){
        checkSet();
        stringBuilder.append("where ").append("`" + expPrefix + "`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    public SQLBuilder and(String expPrefix, String expSymbol, Object expSuffix){
        stringBuilder.append(" and ").append("`").append(expPrefix).append("`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    public SQLBuilder or(String expPrefix, String expSymbol, Object expSuffix){
        stringBuilder.append(" or ").append("`").append(expPrefix).append("`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    public SQLBuilder betweenAnd(String column, Object low, Object up){
        stringBuilder.append(" ").append("`").append(column).append("`").append(" ")
                .append("BETWEEN").append(" ").append(typeJudge(low)).append(" ")
                .append("AND").append(" ").append(typeJudge(up));
        return this;
    }


    
    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        // insert into 表 values()
        // API风格，流式API
        String sql = SQLBuilder.insert().into("表").values("参数1", "参数2").build().toString();

        SQLBuilder insert = SQLBuilder.insert()
                .into("person").columns("123", "456", "789")
                .values("10000", "user-0", "男", "城市-0", "签名-0", 881, 59, 65212254, "诗人", 35)
                .values("20000","user-0", "男", "城市-0", "签名-0", 881, 59, 65212254, "诗人", 35)
                .build();
        SQLBuilder person = SQLBuilder.update("person")
                .set("id", 10001)
                .set("username", "user-1")
                .where("id", "=", 10001)
                .build();
        System.out.println(person);
    }
}
