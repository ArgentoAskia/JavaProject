# Java-XML

归功于`Java`强大的类库和第三方库，在`Java`中处理任何一类文件都稍微方便了一些。这些文件包括但不仅限于`PDF`、图像、二维码、`Word`、`Excel`、`XML`、`YML`、`Properties`等等。本篇将会简单介绍在Java中如何解析并验证一个`XML`文件。

在`Java`中，对一个`XML`文件的解析有三种库：

- `DOM`（`Document Object Model`）：基于树结构进行解析的库，也叫文档对象模型，会将一个`xml`文件变成一棵树加载到内存中，这种方式适合小规模读和写，内存足够大就删掉小字。 
- `SAX`（`Simple API For XML`）：一种基于流的读取方式，通过事件来完成对文档的操作，关注于`xml`中某个标签的值或内容，读能力非常强。
- `STAX`（`The Stream API For XML`）：同样基于流的读取方式，通过流或者事件的方式来操作`xml`。读取能力非常强。

在说明这些库之前，先简单了解一下`XML`。

## XML文件

## Java中XML文件处理

处理一个`xml`文件，可以总结为这三方面的内容：

- `xml`解析：包括获取各类节点信息，添加、删除节点、属性等一些读操作
- `xml`定位：通过一些"路径"，如`XPath`等定位`xml`元素
- `xml`验证：验证`xml`合法性等

后面介绍的所有`xml`的处理方法也是完全基于上面三方面的内容，以一种`How To`的方式来和大家讲解，方便后期补充。

### DOM方式

`DOM`方式在三种处理库中最简单、最直接，是`W3C`处理`xml`的标准`API`，不仅`Java`中有，`JavaScript`中处理`HTML`也是采用这套标准。核心方式是将整个`xml`文件解析成一颗树结构放进内存中以便操作和解析，**如下面的一段简单的**`xml`：

![image-20230202165427926](README/image-20230202165427926.png)

`DOM`**的最终解析结果会生成一颗这样的树结构：**

![image-20230202165509035](README/image-20230202165509035.png)

可以看到这颗树上有很多节点，`DOM`会把`xml`里面的任何元素都解析成节点的形式。在`DOM`的`API`中，`Node`接口的对象就代表树种的一个节点。

**特别注意，在**`DOM`**解析中，不会忽略标签与标签之间的换行，这些换行也会被解析成相应的**`whitespace`**节点，因此，加上\<font\>和\<name\>之间的空白、\</name\>和\<size\>之间的空白、\</size\>和\</font\>之间的空白就有5个子节点。**

`DOM`方式在面对大数据量的`xml`解析时可能会产生内存泄漏和程序崩溃的情况，虽然现代机器内存都很大，因此发生的概率很小。

#### 1.如何读入一个XML

`DOM`中读入一个`xml`文件有下面几个步骤：

1. 通过`DocumentBuilderFactory` 的`newInstance()`获取一个`DocumentBuilderFactory`实例
2. 通过`DocumentBuilderFactory`实例的`newDocumentBuilder()`获取一个`DocumentBuilder`实例
3. 通过`DocumentBuilder`实例的`parse()`方法，解析一个`xml`文件成树，返回`Document`对象代表这个`xml`文件，其中`parse()`有下面的方法签名：

```java
public Document parse(InputStream is);
public Document parse(String uri);
public Document parse(File f);
```

可以参考下面的一段代码：

```java
// 1. 读入XML文件到流，File对象或者URL等都ok
InputStream mybatisConfigXml = MybatisConfigXMLParseDemo.class.getResourceAsStream("/mybatis-config.xml");

// 2. 通过DocumentBuilderFactory.newInstance()获取DocumentBuilderFactory对象
DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

// 3. 通过documentBuilderFactory.newDocumentBuilder()来创建DocumentBuilder对象
DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

// 4.通过documentBuilder.parse()来解析XML输入流、XML File对象或者XML文件URL等
Document mybatisConfigDocument = documentBuilder.parse(mybatisConfigXml);
```

#### 2.如何创建一个空的xml文档

如果不希望解析`xml`而是创建一个新的文档的话，则不需要使用`parse()`，在`DocumentBuilder`中`newDocument()`可以创建一个新的`Document`对象。

完整代码参考：

```java
// 1.. 通过DocumentBuilderFactory.newInstance()获取DocumentBuilderFactory对象
DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

// 2. 通过documentBuilderFactory.newDocumentBuilder()来创建DocumentBuilder对象
DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

// 4.通过documentBuilder.parse()来解析XML输入流、XML File对象或者XML文件URL等
Document newDocument = documentBuilder.newDocument();
```

#### 3.如何解析节点，获取节点信息

当拿到一个`Document`对象的时候，可以使用下面的`Api`来获取节点，即`Node`接口的对象。

```java
// 获取根节点，通常解析XML文件都是从这个方法开始的
public Element getDocumentElement();
// 通过标签名的方式来获取节点，
public NodeList getElementsByTagName(String tagname);
// 获取子节点，返回节点列表
public NodeList getChildNodes();
// 获取第一个子节点
public Node getFirstChild();
// 获取最后一个子节点
public Node getLastChild();
// 获取父节点
public Node getParentNode();
// 获取右兄弟节点
public Node getNextSibling();
// 获取左兄弟节点
public Node getPreviousSibling();
```

前面提过`Node`接口是`xml`中所有元素的抽象，代表`xml`被解析成树之后，树上的任何一个节点。

`Node`接口下面，有很多真正代表`xml`中各种类型元素的子接口，这些节点类型可以参考下面的继承图：

![image-20230203094023216](README/image-20230203094023216.png)

因此在处理这些节点的时候，可以使用`instanceof`关键字来判断`Node`的类型并进行强制转换，参考下面的处理方式：

```java
Document mybatisConfigDocument = ...;
NodeList childNodes1 = mybatisConfigDocument.getChildNodes();
System.out.println("mybatis-config.xml共有" + childNodes1.getLength() + "个节点");
for (int i = 0; i < childNodes1.getLength(); i++) {
    Node item = childNodes1.item(i);
    if (item instanceof Comment){
        Comment comment = (Comment) item;
        String nodeValue = comment.getNodeValue();
        System.out.println("这是一段注释，注释的内容是：" + nodeValue);
    }
    if (item instanceof Attr){
        Attr attr = (Attr) item;
        String name = attr.getName();
        String value = attr.getValue();
        String tagName = attr.getOwnerElement().getTagName();
        System.out.println("这是一个属性，属性名是" + name + "，属性值是：" + value + "，标签名：" + tagName);
    }
    if (item instanceof Text){
        Text text = (Text) item;
        String data = text.getData();
        String wholeText = text.getWholeText();
        System.out.println("这是一个标签文本节点：" + data);
        System.out.println("这是一个标签文本节点：" + wholeText);
            }
    if (item instanceof Element){
        Element element = (Element) item;
        String tagName = element.getTagName();
        System.out.println("这是一个标签，标签名是" + tagName);
    }
    if (item instanceof DocumentType){
        DocumentType documentType = (DocumentType) item;
        String name = documentType.getName();
        String systemId = documentType.getSystemId();
        String publicId = documentType.getPublicId();
        System.out.println("这是一个文档声明，声明内容为：" + name + ",系统ID：" + systemId + ",公共ID：" + publicId);
    }
}
```

