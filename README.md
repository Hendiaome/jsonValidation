# jsonValidation
a framework  to validate json request
to user it you must 


使用帮助
0.下载项目，编译打包，成web，validation和war包。

1.在自己的项目引入jar包，引入validation即可。

2.在controller使用注解（RequestBody为次项目的包，非spring不要导错）

  @PostMapping("/validate")
  public Object validate(@RequestBody(code = "90000", msg = "呵呵呵") JsonRequest request) {
      return 123;
  }
  
3.在目标类上使用注解（msg为提示信息， isObjective为对象属性，即对象嵌套 ），校验类可以自己实现，实现ValidateFiled接口即可。
public @interface Validation {
    /**默认提示信息**/
    String msg() default "";

    /**次属性是否为对象**/
    boolean isObject() default false;

    /**默认校验类**/
    Class validateClass() default ValidateEmpty.class;
}
-----------------------------------------------------------
  public class JsonRequest {
  
    @Validation(msg = "姓名不能空")
    private String name;

    @Validation(msg = "年龄不能空")
    private Integer age;

    @Validation(isObject = true, msg = "孩子不能空")
    private JsonChild child;
  }
  public class JsonChild {

    @Validation(msg = "孩子名字不能空")
    private String name;
}
4.发起请求开始使用

