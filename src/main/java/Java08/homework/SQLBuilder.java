package Java08.homework;
import java.util.StringJoiner;

public class SQLBuilder {
    private StringJoiner stringJoiner;
    private StringBuilder stringBuilder;

    /**
     * 初始化stringJoiner方法
     *
     * @param prefix    前缀
     * @param suffix    后缀
     * @param delimiter 链接器
     */
    private void createJoiner(String prefix, String suffix, String delimiter){
        stringJoiner = new StringJoiner(delimiter, prefix, suffix);
    }

    /**
     * 初始化stringBuilder方法
     * @param init 初始化SQL字符串
     */
    private void createBuilder(String init){
        stringBuilder = new StringBuilder(init);
    }

    /**
     * 构建insert语句。
     * @return SQLbuilder创建器
     */
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

    /**
     * 表格列值。
     *
     * @param columns 列名
     * @return {@link SQLBuilder}SQL构建器
     */
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

    /**
     * 方法用于确保SQL语句以values结尾！
     */
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
     * values values关键字
     * @param values values值
     * @return {@link SQLBuilder}SQL构建器
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

    /**
     * 构建SQL语句的终点方法。
     * @return 最终的SQLBuilder，包含完整的SQL语句
     */
    public SQLBuilder build(){
        //
        if (stringJoiner != null){
            String s = stringJoiner.toString();
            stringBuilder.append(s);
            // 让stringJoiner回到初始值，习惯
            stringJoiner = null;
        }
        return this;
    }

    /**
     * 扩展方法，之后需要重新设计，重点重写方法
     * @param obj
     * @return
     */
    protected String typeJudge(Object obj){
        if(obj.getClass() == Integer.class){
            int intValue = (int) obj;
            return String.valueOf(intValue);
        }else if (obj.getClass() == String.class){
            return "'" + obj + "'";
        }else {
            return "null";
        }
    }


    /**
     * update关键字
     * @param tableName 表名
     * @return SQLBuidler对象
     */
    public static SQLBuilder update(String tableName){
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.createBuilder("update `" + tableName + "`\n");
        return sqlBuilder;
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

    private SQLBuilder headTemplate(String headerKeyWord, String expOrColumn, boolean isColumn){
        boolean isUpdateSQL = checkSetAndAppendToBuilder();
        if (isUpdateSQL){
            stringBuilder.append(headerKeyWord).append(" ");
            if (isColumn){
                stringBuilder.append("`").append(expOrColumn).append("`");
            }else{
                stringBuilder.append(expOrColumn);
            }
        }
        return this;
    }
    private SQLBuilder where(String expOrColumn, boolean isColumn){
       return headTemplate("where", expOrColumn, isColumn);
    }
    public SQLBuilder where(String column){
        return where(column, true);
    }
    public SQLBuilder where(Object exp){
        return where(exp.toString(),false);
    }

    private SQLBuilder compare(String symbol, Object value){
        stringBuilder.append(" ").append(symbol).append(" ").append(typeJudge(value));
        return this;
    }
    public SQLBuilder large(Object value){
       return compare(">", value);
    }
    public SQLBuilder largeEqual(Object value){
        return compare(">=", value);
    }

    public SQLBuilder equal(Object value){
        return compare("=", value);
    }
    public SQLBuilder little(Object value){
        return compare("<", value);
    }
    public SQLBuilder littleEqual(Object value){
        return compare("<=", value);
    }
    public SQLBuilder notEqual(Object value){
        return compare("!=", value);
    }
    public SQLBuilder notLarge(Object value){
        return compare("!>", value);
    }
    public SQLBuilder notLittle(Object value){
        return compare("!<", value);
    }
    private SQLBuilder valuesTemplate(String op, Object value){
        stringBuilder.append(" ").append(op).append(" ").append(typeJudge(value));
        return this;
    }
    public SQLBuilder between(Object value){
        return valuesTemplate("between", value);
    }
    public SQLBuilder notBetween(Object value){
        return valuesTemplate("not between", value);
    }
    public SQLBuilder and(Object value){
        return valuesTemplate("and", value);
    }
    public SQLBuilder and(Object valueOrColumn, boolean isNextBetween){
        if (isNextBetween){
            return and(valueOrColumn);
        }else{
            String s = valueOrColumn.toString();
            return and(s);
        }
    }

    public SQLBuilder like(String exp){
        return valuesTemplate("like", exp);
    }
    public SQLBuilder notLike(String exp){
        return valuesTemplate("not like", exp);
    }

    public SQLBuilder in(Object... values){
        if (stringJoiner == null){
            createJoiner(" in (", ")", ",");
        }
        for (Object object:
             values) {
            stringJoiner.add(typeJudge(object));
        }
        stringBuilder.append(stringJoiner.toString());
        stringJoiner = null;
        return this;
    }
    public SQLBuilder notIn(Object... values){
        if (stringJoiner == null){
            createJoiner(" not in (", ")", ",");
        }
        for (Object object: values){
            stringJoiner.add(typeJudge(object));
        }
        stringBuilder.append(stringJoiner.toString());
        stringJoiner = null;
        return this;
    }

    public SQLBuilder isNull(){
        stringBuilder.append(" ").append("is").append(" ").append("null");
        return this;
    }
    public SQLBuilder isNotNull(){
        stringBuilder.append(" ").append("is").append(" ").append("not").append(" ").append("null");
        return this;
    }

    public SQLBuilder and(String column){
        return headTemplate("and", column, true);
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


    private boolean checkSetAndAppendToBuilder(){
        if(toString().contains("update") && !toString().contains("set")){
            String s = stringJoiner.toString();
            stringBuilder.append(s);
            stringJoiner = null;
            return true;
        }
        return false;
    }
    @Deprecated
    public SQLBuilder where(String expPrefix, String expSymbol, Object expSuffix){
        checkSetAndAppendToBuilder();
        stringBuilder.append("where ").append("`" + expPrefix + "`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    @Deprecated
    public SQLBuilder and(String expPrefix, String expSymbol, Object expSuffix){
        stringBuilder.append(" and ").append("`").append(expPrefix).append("`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    @Deprecated
    public SQLBuilder or(String expPrefix, String expSymbol, Object expSuffix){
        stringBuilder.append(" or ").append("`").append(expPrefix).append("`").append(" ").append(expSymbol).append(" ").append(typeJudge(expSuffix));
        return this;
    }
    @Deprecated
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
