package know;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import netscape.javascript.JSObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 一、认识FastJson
 * Fastjson 是一个 Java 库，可以将 Java 对象转换为 JSON 格式，当然它也可以将 JSON 字符串转换为 Java 对象。
 * Fastjson 可以操作任何 Java 对象，即使是一些预先存在的没有源码的对象。
 * 二、Fastjson特性
 * 1、提供toJSONString() 和 parseObject() 方法来将 Java 对象与 JSON 相互转换。
 * 调用toJSONString方 法即可将对象转换成 JSON 字符串，parseObject 方法则反过来将 JSON 字符串转换成对象。
 * 2、Java泛型的广泛支持。支持任意复杂对象。
 * */
public class KnowFastJson {
    /**
     * 定义一个内部类方便测试练习
     * 总结：
     *  一、@JSONField注解可以配置在getter/setter方法或者字段上；若属性是私有的，必须有set*方法。否则无法反序列化
     *  二、@JSONField注解的属性：name 序列映射为JSON的Key；format 配置⽇期格式化；
     *      serialize/deserialize 指定字段不序列化;ordinal 指定字段的顺序
     *  三、java中的的日期格式为:
     *     yyyy-MM-dd HH:mm:ss:代表将时间转换为24小时制,例: 2020-01-07 13:21:55
     *     yyyy-MM-dd hh:mm:ss: 代表将时间转换为12小时制,例: 2020-01-07  03:24:21
     *  四、private List<PersonDeom> listOfPersons = new ArrayList<PersonDeom>();
     *     左边List<>泛型类型是ArrayList<>数组集合类型的父类接口
     *     ArrayList<>数组集合类型是List<>的实现类
     *     这样定义好处是List作为接口接收，程序层面更具有扩展性；
     *
     * */
    private class PersonDeom{

        @JSONField(name = "AGE",ordinal = 2)
        private int age;
        @JSONField(name = "FullName",ordinal = 1)
        private String fullName;

        @JSONField(name = "Birthday",format = "yyyy-MM-dd HH:mm:ss",ordinal = 3)
        private Date birthday;
        @JSONField(serialize=false)
        private String tall;

        public PersonDeom(int age, String fullName, Date birthday,String tall) {
            this.age = age;
            this.fullName = fullName;
            this.birthday = birthday;
            this.tall = tall;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getTall() {
            return tall;
        }

        public void setTall(String tall) {
            this.tall = tall;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }


    private List<PersonDeom> listOfPersons = new ArrayList<PersonDeom>();

    @BeforeMethod
    public void setUp(){
        listOfPersons.add(new PersonDeom(15,"Tom",new Date(),"159cm"));
        listOfPersons.add(new PersonDeom(35,"HXM",new Date(),"180cm"));
    }

    @Test
    public void outJsonString(){
        //将listOfPersons这个集合转换输出为JSON格式
        System.out.println(JSON.toJSONString(listOfPersons));
    }

    //--创建 JSON 对象
    @Test
    public void testJsonObject(){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject jsonField = new JSONObject();
            jsonField.put("AGE",10);
            jsonField.put("Name","Tom" + i);
            jsonField.put("Birthday",new Date());
            jsonArray.add(jsonField);
        }
        System.out.println(jsonArray.toJSONString());
    }

    //--JSON 字符串Parse转换为 Java 对象
    @Test
    public void testParseObject(){
        //1、创建JSON字符串
        PersonDeom personDeom = new PersonDeom(15,"Tom",new Date(),"189cm");
        String parsePerson =JSON.toJSONString(personDeom);
        //2、解析
        PersonDeom personDeom1 = JSON.parseObject(parsePerson,PersonDeom.class);
        Assert.assertEquals(personDeom1.getAge(),10);
        Assert.assertEquals(personDeom1.getFullName(),listOfPersons.get(0).getFullName());
    }

}
